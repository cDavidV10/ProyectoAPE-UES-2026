package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import interfaz.IInscripcionDAO;
import modelo.Curso;
import modelo.Docente;
import modelo.InicioCurso;
import modelo.PeriodoInscripcion;

public class PeriodoInscripcionDAO implements IInscripcionDAO {

    @Override
    public void insertar(String username, PeriodoInscripcion periodoInscripcion) throws Exception {
        String consulta = """
                insert into periodo_inscripcion(id_administrador, id_inicio_curso, fecha_apertura, fecha_cierre)
                SELECT id_admind, null, ?, ?
                from usuario us
                where us.username = ?;
                                """;

        Connection conexion = Conexion.getConexion();

        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);

            ps.setObject(1, periodoInscripcion.getFechaApertura());
            ps.setObject(2, periodoInscripcion.getFechaCierre());
            ps.setString(3, username);
            conexion.setAutoCommit(false);

            ps.executeUpdate();
            conexion.commit();
        } catch (Exception e) {
            conexion.rollback();
            throw e;
        } finally {
            conexion.close();
        }

    }

    @Override
    public void actualizar(PeriodoInscripcion periodoInscripcion) throws Exception {
        String consulta = """
                update periodo_inscripcion
                set fecha_cierre = ?
                where id_periodo = ?;
                                    """;

        Connection conexion = Conexion.getConexion();

        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);

            ps.setObject(1, periodoInscripcion.getFechaCierre());
            ps.setInt(2, periodoInscripcion.getId());
            conexion.setAutoCommit(false);

            ps.executeUpdate();
            conexion.commit();
        } catch (Exception e) {
            conexion.rollback();
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public List<InicioCurso> listar() throws Exception {
        String cursosDisponibles = """
                SELECT
                    c.codigo as codigoCurso,
                    c.nombre as curso,
                    d.nombre as nombreDocente,
                    d.apellido as apellidoDocente,
                    ic.fecha_apertura as fechaApertura,
                    ic.fecha_cierre as fechaCierre,
                    ic.cupo_maximo as cupoMaximo
                FROM inicio_curso ic
                INNER JOIN curso c ON ic.id_curso = c.id_curso
                INNER JOIN docente d ON ic.id_docente = d.id_docente
                WHERE ic.id_inicio_curso NOT IN (
                    SELECT id_inicio_curso
                    FROM periodo_inscripcion
                    WHERE id_inicio_curso IS NOT NULL
                );
                                                """;
        Connection conexion = Conexion.getConexion();

        PreparedStatement ps = conexion.prepareStatement(cursosDisponibles);

        ResultSet rs = ps.executeQuery();

        List<InicioCurso> datos = new ArrayList<>();

        while (rs.next()) {
            PeriodoInscripcion periodoInscripcion = new PeriodoInscripcion();
            Docente docente = new Docente();
            Curso curso = new Curso();
            InicioCurso inicioCurso = new InicioCurso();

            curso.setCodigo(rs.getString("codigoCurso"));
            curso.setNombreCurso(rs.getString("curso"));
            docente.setNombre(rs.getString("nombreDocente"));
            docente.setApellido(rs.getString("apellidoDocente"));
            inicioCurso.setFechaApertura(rs.getObject("fechaApertura", LocalDate.class));
            inicioCurso.setFechaCierre(rs.getObject("fechaCierre", LocalDate.class));
            inicioCurso.setCupoMaximo(rs.getString("cupoMaximo"));

            inicioCurso.setDocente(docente);
            inicioCurso.setCursos(curso);

            datos.add(inicioCurso);

        }

        conexion.close();
        return datos;

    }

    @Override
    public boolean existePeriodoActivo() throws Exception {
        String consulta = """
                    select count(*) from periodo_inscripcion
                    where estado = 'Activo';
                """;

        Connection conexion = Conexion.getConexion();
        PreparedStatement ps = conexion.prepareStatement(consulta);

        ResultSet rs = ps.executeQuery();

        rs.next();

        conexion.close();
        return rs.getInt(1) > 0;
    }

    @Override
    public PeriodoInscripcion periodoActivo() throws Exception {
        String consulta = """
                select
                    id_periodo,
                    fecha_apertura,
                    fecha_cierre
                from periodo_inscripcion
                where estado = 'Activo';
                                """;

        Connection conexion = Conexion.getConexion();
        PreparedStatement ps = conexion.prepareStatement(consulta);

        ResultSet rs = ps.executeQuery();

        PeriodoInscripcion periodoInscripcion = new PeriodoInscripcion();

        if (rs.next()) {
            periodoInscripcion.setId(rs.getInt("id_periodo"));
            periodoInscripcion.setFechaApertura(rs.getObject("fecha_apertura", LocalDate.class));
            periodoInscripcion.setFechaCierre(rs.getObject("fecha_cierre", LocalDate.class));
        }

        return periodoInscripcion;
    }

}
