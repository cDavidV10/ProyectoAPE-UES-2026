
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;

public class CursosDisponiblesDAO {

    // =====================================================================
    // CONSULTA PRINCIPAL: Un curso por fila (SIN join a horario)
    // Usa INNER JOIN entre inicio_curso, curso y docente
    // =====================================================================
    public List<Object[]> listarDisponibles() throws Exception {
        String sql = """
                    SELECT
                        c.codigo,
                        c.nombre,
                        d.nombre || ' ' || d.apellido AS docente,
                        ic.cupo_maximo,
                        ic.id_inicio_curso
                    FROM inicio_curso ic
                    INNER JOIN curso c ON ic.id_curso = c.id_curso
                    INNER JOIN docente d ON ic.id_docente = d.id_docente
                    WHERE ic.id_periodo = (
                        SELECT id_periodo FROM periodo_inscripcion WHERE estado = 'Activo'
                    )
                    AND ic.estado = 'Activo'
                    ORDER BY c.codigo
                """;

        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Object[]> lista = new ArrayList<>();
        while (rs.next()) {
            // Indices: [0]=codigo, [1]=nombre, [2]=docente, [3]=cupo, [4]=idInicioCurso
            Object[] fila = new Object[5];
            fila[0] = rs.getString("codigo");
            fila[1] = rs.getString("nombre");
            fila[2] = rs.getString("docente");
            fila[3] = rs.getString("cupo_maximo");
            fila[4] = rs.getInt("id_inicio_curso");
            lista.add(fila);
        }
        conn.close();
        return lista;
    }

    // =====================================================================
    // BUSCAR POR CODIGO: Misma estructura, filtrado por codigo del curso
    // =====================================================================
    public List<Object[]> buscarPorCodigo(String codigo) throws Exception {
        String sql = """
                    SELECT
                        c.codigo,
                        c.nombre,
                        d.nombre || ' ' || d.apellido AS docente,
                        ic.cupo_maximo,
                        ic.id_inicio_curso
                    FROM inicio_curso ic
                    INNER JOIN curso c ON ic.id_curso = c.id_curso
                    INNER JOIN docente d ON ic.id_docente = d.id_docente
                    WHERE ic.id_periodo = (
                        SELECT id_periodo FROM periodo_inscripcion WHERE estado = 'Activo'
                    )
                    AND ic.estado = 'Activo'
                    AND UPPER(c.codigo) LIKE UPPER(?)
                    ORDER BY c.codigo
                """;

        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + codigo + "%");
        ResultSet rs = ps.executeQuery();

        List<Object[]> lista = new ArrayList<>();
        while (rs.next()) {
            Object[] fila = new Object[5];
            fila[0] = rs.getString("codigo");
            fila[1] = rs.getString("nombre");
            fila[2] = rs.getString("docente");
            fila[3] = rs.getString("cupo_maximo");
            fila[4] = rs.getInt("id_inicio_curso");
            lista.add(fila);
        }
        conn.close();
        return lista;
    }

    // =====================================================================
    // HORARIOS PARA EL JDIALOG DE DETALLES
    // Usa INNER JOIN entre horario y aula
    // Este es el metodo que alimenta la tabla del JDialog
    // =====================================================================
    public List<Object[]> obtenerHorarios(int idInicioCurso) throws Exception {
        String sql = """
                    SELECT
                        h.dia,
                        h.hora_inicio,
                        h.hora_final,
                        a.codigo AS aula
                    FROM horario h
                    INNER JOIN aula a ON h.id_aula = a.id_aula
                    WHERE h.id_inicio_curso = ?
                    ORDER BY
                        CASE h.dia
                            WHEN 'Lunes' THEN 1
                            WHEN 'Martes' THEN 2
                            WHEN 'Miercoles' THEN 3
                            WHEN 'Jueves' THEN 4
                            WHEN 'Viernes' THEN 5
                            WHEN 'Sabado' THEN 6
                            WHEN 'Domingo' THEN 7
                        END,
                        h.hora_inicio
                """;

        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idInicioCurso);
        ResultSet rs = ps.executeQuery();

        List<Object[]> lista = new ArrayList<>();
        while (rs.next()) {
            Object[] fila = new Object[4];
            fila[0] = rs.getString("dia");
            fila[1] = rs.getObject("hora_inicio", LocalTime.class);
            fila[2] = rs.getObject("hora_final", LocalTime.class);
            fila[3] = rs.getString("aula");
            lista.add(fila);
        }
        conn.close();
        return lista;
    }

    // =====================================================================
    // VERIFICAR INSCRIPCION (sin cambios)
    // =====================================================================
    public boolean verificarInscripcion(int idEstudiante, int idInicioCurso) throws Exception {
        String sql = """
                    SELECT COUNT(*) FROM inscripcion
                    WHERE id_estudiante = ? AND id_inicio_curso = ?
                """;

        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idEstudiante);
        ps.setInt(2, idInicioCurso);
        ResultSet rs = ps.executeQuery();
        rs.next();
        boolean existe = rs.getInt(1) > 0;
        conn.close();
        return existe;
    }

    // =====================================================================
    // INSCRIBIR ESTUDIANTE (sin cambios)
    // =====================================================================
    public void inscribir(int idEstudiante, int idInicioCurso) throws Exception {
        String sql = """
                    INSERT INTO inscripcion (id_estudiante, id_inicio_curso, fecha_inscripcion, estado)
                    VALUES (?, ?, CURRENT_DATE, 'Activo')
                """;

        Connection conn = Conexion.getConexion();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEstudiante);
            ps.setInt(2, idInicioCurso);
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
    }

}
