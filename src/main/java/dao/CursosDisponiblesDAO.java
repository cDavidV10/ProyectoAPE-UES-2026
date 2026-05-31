
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;

public class CursosDisponiblesDAO {

    // SQL Para cursos disponibles actuales
    private static final String SELECT_DISPONIBLES = "SELECT c.codigo, c.nombre, " +
            "d.nombre || ' ' || d.apellido AS docente, " +
            "h.dia || ' ' || CAST(h.hora_inicio AS VARCHAR) || ' - ' || CAST(h.hora_final AS VARCHAR) AS horario, " +
            "ic.cupo_maximo, " +
            "ic.id_inicio_curso " +
            "FROM inicio_curso ic " +
            "JOIN curso c ON ic.id_curso = c.id_curso " +
            "JOIN docente d ON ic.id_docente = d.id_docente " +
            "LEFT JOIN horario h ON h.id_inicio_curso = ic.id_inicio_curso " +
            "WHERE ic.fecha_apertura <= CURRENT_DATE " +
            "AND ic.fecha_cierre >= CURRENT_DATE " +
            "ORDER BY c.codigo";

    // Busca cursos vigentes filtrando por código (búsqueda parcial)
    private static final String SELECT_POR_CODIGO = "SELECT c.codigo, c.nombre, " +
            "d.nombre || ' ' || d.apellido AS docente, " +
            "h.dia || ' ' || CAST(h.hora_inicio AS VARCHAR) || ' - ' || CAST(h.hora_final AS VARCHAR) AS horario, " +
            "ic.cupo_maximo, " +
            "ic.id_inicio_curso " +
            "FROM inicio_curso ic " +
            "JOIN curso c ON ic.id_curso = c.id_curso " +
            "JOIN docente d ON ic.id_docente = d.id_docente " +
            "LEFT JOIN horario h ON h.id_inicio_curso = ic.id_inicio_curso " +
            "WHERE ic.fecha_apertura <= CURRENT_DATE " +
            "AND ic.fecha_cierre >= CURRENT_DATE " +
            "AND LOWER(c.codigo) LIKE LOWER(?) " +
            "ORDER BY c.codigo";

    // Inscribe al estudiante en el periodo activo del curso seleccionado
    private static final String INSCRIBIR = "INSERT INTO inscripcion (id_estudiante, id_periodo, fecha_inscripcion, estado) "
            +
            "VALUES (?, " +
            "  (SELECT id_periodo FROM periodo_inscripcion " +
            "   WHERE id_inicio_curso = ? " +
            "   AND fecha_apertura <= CURRENT_DATE " +
            "   AND fecha_cierre >= CURRENT_DATE " +
            "   LIMIT 1), " +
            "CURRENT_DATE, 'activo')";

    // Verifica si el estudiante ya está inscrito en ese inicio_curso
    private static final String VERIFICAR_INSCRIPCION = "SELECT COUNT(*) FROM inscripcion i " +
            "JOIN periodo_inscripcion pi ON i.id_periodo = pi.id_periodo " +
            "WHERE i.id_estudiante = ? " +
            "AND pi.id_inicio_curso = ?";

    // Lista para retornar los cursos disponibles
    public List<Object[]> listarDisponibles() throws Exception {
        List<Object[]> lista = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(SELECT_DISPONIBLES);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Object[] {
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("docente"),
                        rs.getString("horario"),
                        rs.getString("cupo_maximo"),
                        rs.getInt("id_inicio_curso")
                });
            }
        } finally {
            conn.close();
        }
        return lista;
    }

    // Buscar cursos por codigo
    public List<Object[]> buscarPorCodigo(String codigo) throws Exception {
        List<Object[]> lista = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(SELECT_POR_CODIGO);
            ps.setString(1, "%" + codigo + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Object[] {
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("docente"),
                        rs.getString("horario"),
                        rs.getString("cupo_maximo"),
                        rs.getInt("id_inicio_curso")
                });
            }
        } finally {
            conn.close();
        }
        return lista;
    }

    // Verifica si el estudiante ya está inscrito en ese curso
    public boolean verificarInscripcion(int idEstudiante, int idInicioCurso) throws Exception {
        Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(VERIFICAR_INSCRIPCION);
            ps.setInt(1, idEstudiante);
            ps.setInt(2, idInicioCurso);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } finally {
            conn.close();
        }
        return false;
    }

    // Inscribir al estudiante en el curso seleccionado
    public void inscribir(int idEstudiante, int idInicioCurso) throws Exception {
        Connection conn = Conexion.getConexion();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(INSCRIBIR);
            ps.setInt(1, idEstudiante);
            ps.setInt(2, idInicioCurso);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new Exception("No se encontró un periodo de inscripción activo para este curso.");
            }
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }
}
