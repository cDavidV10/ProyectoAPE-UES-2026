/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.InicioCurso;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author alexi
 */
public class CursoInicioDAO {
    //private static final String INSERT = "INSERT INTO curso_inicio (fecha_apertura, fecha_cierre, cupo_maximo) VALUES (?,?,?)";
    private static final String INSERT = "INSERT INTO inicio_curso (id_docente, id_curso, fecha_apertura, fecha_cierre, cupo_maximo) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM inicioCurso ORDER BY id_inicio_curso";
    //private static final String SELECT_ID =

            
    public void insertar(InicioCurso ci) throws Exception {
        try {
            Connection conn = Conexion.getConexion();
            if (conn == null) {
                throw new Exception("No se pudo conectar a la base de datos");
            }
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(INSERT, java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ci.getDocente().getIdDocente());
            ps.setInt(2, ci.getCursos().getIdCurso());
            ps.setObject(3, ci.getFechaApertura());
            ps.setObject(4, ci.getFechaCierre());
            ps.setString(5, ci.getCupoMaximo());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            int idGenerado = 0;
            if (rs.next()) {
                idGenerado = rs.getInt(1); 
            }
            
            if (ci.getHorario() != null && !ci.getHorario().isEmpty()) {
                PreparedStatement psHorario = conn.prepareStatement("UPDATE horario SET id_inicio_curso = ? WHERE id_horario = ?");
                psHorario.setInt(1, idGenerado);
                psHorario.setInt(2, ci.getHorario().get(0).getId());
                psHorario.executeUpdate();
            }
            
            conn.commit();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<InicioCurso> listar() throws Exception {
        List<InicioCurso> lista = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        
        String sql = "SELECT ic.id_inicio_curso, ic.fecha_apertura, ic.fecha_cierre, ic.cupo_maximo, " +
                     "c.id_curso, c.nombre, h.id_horario, h.dia, h.hora_inicio, h.hora_final " +
                     "FROM inicio_curso ic " +
                     "INNER JOIN curso c ON ic.id_curso = c.id_curso " +
                     "LEFT JOIN horario h ON h.id_inicio_curso = ic.id_inicio_curso " +
                     "ORDER BY ic.id_inicio_curso";
                     
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            InicioCurso ci = new InicioCurso();
            ci.setIdInicioCurso(rs.getInt("id_inicio_curso"));
            ci.setFechaApertura(rs.getObject("fecha_apertura", java.time.LocalDate.class));
            ci.setFechaCierre(rs.getObject("fecha_cierre", java.time.LocalDate.class));
            ci.setCupoMaximo(rs.getString("cupo_maximo"));
            
            modelo.Curso curso = new modelo.Curso();
            curso.setIdCurso(rs.getInt("id_curso"));
            curso.setNombreCurso(rs.getString("nombre"));
            ci.setCursos(curso);
            
            int idHorario = rs.getInt("id_horario");
            if (!rs.wasNull()) {
                modelo.Horario horario = new modelo.Horario();
                horario.setId(idHorario);
                horario.setDia(rs.getString("dia"));
                horario.setHoraInicio(rs.getObject("hora_inicio", java.time.LocalTime.class));
                horario.setHoraFinal(rs.getObject("hora_final", java.time.LocalTime.class));
                
                ArrayList<modelo.Horario> listaHorarios = new ArrayList<>();
                listaHorarios.add(horario);
                ci.setHorario(listaHorarios);
            }
            
            lista.add(ci);
        }
        conn.close();
        return lista;
    }
    
    public InicioCurso buscar(int idInicioCurso) throws Exception {
        InicioCurso ci = null;
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM inicio_curso WHERE id_inicio_curso = ?");
        ps.setInt(1,idInicioCurso);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            ci = new InicioCurso();
            ci.setIdInicioCurso(rs.getInt("id_inicio_curso"));
            ci.setFechaApertura(rs.getObject("fecha_apertura", java.time.LocalDate.class));
            ci.setFechaCierre(rs.getObject("fecha_cierre", java.time.LocalDate.class));
            ci.setCupoMaximo(rs.getString("cupo_maximo"));
        }
        
        conn.close();
        return ci;
    }
}