/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import java.util.List;
import modelo.Curso;
import interfaz.IDocenteCursosDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import modelo.Aula;
import modelo.Docente;
import modelo.Estudiante;
import modelo.Horario;
import modelo.InicioCurso;

/**
 *
 * @author Yonathan
 */
public class DocenteCursosDAO implements IDocenteCursosDAO {

    private static final String SELECT = """
        SELECT c.codigo, c.nombre, c.descripcion, ic.fecha_apertura, ic.fecha_cierre, ic.cupo_maximo 
        FROM inicio_curso ic 
        INNER JOIN curso c ON ic.id_curso = c.id_curso 
        WHERE ic.id_docente = ?
        """;
    
    private static final String SELECT_DETALLE_CURSO = """
        SELECT c.codigo, c.nombre, c.descripcion, ic.fecha_apertura, ic.fecha_cierre, ic.cupo_maximo, 
            COUNT(i.id_inscripcion) AS total_estudiantes_inscritos, a.codigo AS aula, h.dia, h.hora_inicio, h.hora_final
        FROM inicio_curso ic INNER JOIN curso c ON ic.id_curso = c.id_curso 
        LEFT JOIN inscripcion i ON ic.id_inicio_curso = i.id_inicio_curso
        LEFT JOIN horario h ON ic.id_inicio_curso = h.id_inicio_curso 
        LEFT JOIN aula a ON h.id_aula = a.id_aula
        WHERE c.codigo = ? AND ic.id_docente = ?
        GROUP BY c.codigo, c.nombre, c.descripcion, ic.fecha_apertura, ic.fecha_cierre, ic.cupo_maximo, 
            a.codigo, h.dia, h.hora_inicio, h.hora_final
        """;
    
    private static final String BUSCAR_POR_CURSO = """
        SELECT e.nombre, e.apellido, e.fecha_nacimiento, e.correo FROM inscripcion i 
        INNER JOIN estudiante e ON i.id_estudiante  = e.id_estudiante
        INNER JOIN inicio_curso ic ON i.id_inicio_curso  = ic.id_inicio_curso
        INNER JOIN curso c ON ic.id_curso  = c.id_curso  
        WHERE c.codigo  =  ? AND  ic.id_docente =  ?
    """;
    
    @Override
    public List<InicioCurso> listarCursosxDocente(Docente docente) throws Exception {
        List<InicioCurso> lista = new ArrayList<>();

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(SELECT)) {
            ps.setObject(1, docente.getIdDocente());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Crear objeto Curso
                Curso curso = new Curso();
                curso.setCodigo(rs.getString("codigo"));
                curso.setNombreCurso(rs.getString("nombre"));
                curso.setDescripcion(rs.getString("descripcion"));

                // Crear objeto InicioCurso
                InicioCurso inicio = new InicioCurso();
                inicio.setFechaApertura(rs.getObject("fecha_apertura", LocalDate.class));
                inicio.setFechaCierre(rs.getObject("fecha_cierre", LocalDate.class));
                inicio.setCupoMaximo(rs.getString("cupo_maximo"));

                //Relaciones
                inicio.setCursos(curso);
                lista.add(inicio);
            }
        }
        return lista;
    }
    
    @Override
    public InicioCurso buscarCursos(String codigo, Docente docente) throws Exception {
        Connection conn = Conexion.getConexion();
        
        PreparedStatement ps = conn.prepareStatement(SELECT_DETALLE_CURSO);
        ps.setString(1, codigo);
        ps.setInt(2, docente.getIdDocente());
        ResultSet rs = ps.executeQuery();

        InicioCurso curso = null;
        List<Horario> horarios = new ArrayList<>();

        while (rs.next()) {
            if (curso == null) {
                curso = new InicioCurso();
                curso.setFechaApertura(rs.getObject("fecha_apertura", LocalDate.class));
                curso.setFechaCierre(rs.getObject("fecha_cierre", LocalDate.class));
                curso.setCupoMaximo(rs.getString("cupo_maximo"));
                curso.setTotalInscritos(rs.getInt("total_estudiantes_inscritos"));
                
                Curso c = new Curso();
                c.setCodigo(rs.getString("codigo"));
                c.setNombreCurso(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                curso.setCursos(c);
            }

            Horario h = new Horario();
            h.setDia(rs.getString("dia"));
            h.setHoraInicio(rs.getObject("hora_inicio", LocalTime.class));
            h.setHoraFinal(rs.getObject("hora_final", LocalTime.class));
            
            Aula aula = new Aula();
            aula.setCodigo(rs.getString("aula"));
            h.setAula(aula);
            horarios.add(h);
        }
        
        curso.setHorario((ArrayList<Horario>) horarios);
        return curso;
    }
    
        public List<Estudiante> listarEstudiantesPorCurso(String codigo, int idDocente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Estudiante> listarEstudiantesPorCurso(String codigoCurso, Docente docente) throws Exception {
        List<Estudiante> lista = new ArrayList<>();

        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(BUSCAR_POR_CURSO)) {
            ps.setString(1, codigoCurso);
            ps.setInt(2, docente.getIdDocente());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Estudiante est = new Estudiante();
                est.setNombre(rs.getString("nombre"));
                est.setApellido(rs.getString("apellido"));
                LocalDate fechaNac = rs.getObject("fecha_nacimiento", LocalDate.class);
                est.setFechaNacimiento(fechaNac);
                est.setCorreo(rs.getString("correo"));

                // calcular edad
                int edad = Period.between(fechaNac, LocalDate.now()).getYears();
                est.setEdad(edad);

                lista.add(est);
            }
        }
        return lista;
    }
}
