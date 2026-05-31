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

}
