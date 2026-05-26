package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import interfaz.IEstudianteDAO;
import modelo.Estudiante;

public class RegEstuDAO implements IEstudianteDAO{
    
private static final String INSERT =
        "INSERT INTO estudiante (dui, nombre, apellido, fecha_nacimiento, correo) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL =
        "SELECT * FROM estudiante ORDER BY id_estudiante";
    private static final String SELECT_ID =
        "SELECT * FROM estudiante WHERE id_estudiante = ?";
    private static final String UPDATE =
        "UPDATE estudiante SET dui = ?, nombre = ?, apellido = ?, fecha_nacimiento = ?, correo = ? WHERE id_estudiante = ?";
    private static final String DELETE =
        "DELETE FROM estudiante WHERE id_estudiante = ?";
    private static final String SELECT_MAX_ID =
        "SELECT COALESCE(MAX(id_estudiante), 0) + 1 AS siguiente FROM estudiante";

    public int generarId() throws Exception {
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(SELECT_MAX_ID);
        ResultSet rs = ps.executeQuery();
        int id = 1;
        if (rs.next()) {
            id = rs.getInt("siguiente");
        }
        conn.close();
        return id;
    }

    public void insertar(Estudiante e) throws Exception {
        Connection conn = Conexion.getConexion();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, e.getDui());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellido());
            ps.setDate(4, e.getFechaNacimiento());
            ps.setString(5, e.getCorreo());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }

    public void actualizar(Estudiante e) throws Exception {
        Connection conn = Conexion.getConexion();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, e.getDui());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellido());
            ps.setDate(4, e.getFechaNacimiento());
            ps.setString(5, e.getCorreo());
            ps.setInt(6, e.getIdEstudiante());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }

    public void eliminar(int idEstudiante) throws Exception {
        Connection conn = Conexion.getConexion();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setInt(1, idEstudiante);
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }

    public List<Estudiante> listar() throws Exception {
        List<Estudiante> lista = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Estudiante e = new Estudiante();
            e.setIdEstudiante(rs.getInt("id_estudiante"));
            e.setDui(rs.getString("dui"));
            e.setNombre(rs.getString("nombre"));
            e.setApellido(rs.getString("apellido"));
            e.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            e.setCorreo(rs.getString("correo"));
            lista.add(e);
        }
        conn.close();
        return lista;
    }

    public Estudiante buscar(int idEstudiante) throws Exception {
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(SELECT_ID);
        ps.setInt(1, idEstudiante);
        ResultSet rs = ps.executeQuery();
        Estudiante e = null;
        if (rs.next()) {
            e = new Estudiante();
            e.setIdEstudiante(rs.getInt("id_estudiante"));
            e.setDui(rs.getString("dui"));
            e.setNombre(rs.getString("nombre"));
            e.setApellido(rs.getString("apellido"));
            e.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            e.setCorreo(rs.getString("correo"));
        }
        conn.close();
        return e;
    }

}
