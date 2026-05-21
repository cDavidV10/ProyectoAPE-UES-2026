/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import modelo.Cursos;

/**
 *
 * @author alexi
 */
public class CursosDAO {
    // private static final String INSERT = "INSERT INTO public.curso (nombre,
    // estado, capacidad, fecha_inicio, fecha_cierre) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT = "INSERT INTO curso (codigo, nombre, descripcion) VALUES (?,?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM curso ORDER BY id_curso";
    private static final String SELECT_ID = "SELECT * FROM curso WHERE id_curso = ?";
    private static final String UPDATE = "UPDATE curso SET nombre = ?, descripcion = ? WHERE id_curso = ?";
    private static final String DELETE = "DELETE FROM curso WHERE id_curso = ?";

    public void insertar(Cursos c) throws Exception {

        try {
            Connection conn = Conexion.getConexion();

            if (conn == null) {
                throw new Exception("No se pudo establecer conexión con la base de datos.");
            }

            conn.setAutoCommit(false);

            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, c.getCodigo());
            ps.setString(2, c.getNombreCurso());
            ps.setString(3, c.getDescripcion());

            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Cursos> listar() throws Exception {
        List<Cursos> lista = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Cursos c = new Cursos();
            c.setIdCurso(rs.getInt("id_curso"));
            c.setCodigo(rs.getString("codigo"));
            c.setNombreCurso(rs.getString("nombre"));
            c.setDescripcion(rs.getString("descripcion"));
            lista.add(c);
        }
        conn.close();
        return lista;
    }

    public void eliminar(int id) throws Exception {
        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void actualizar(Cursos c) throws Exception {
        Connection conn = Conexion.getConexion();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, c.getNombreCurso());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getIdCurso());

            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }

    public Cursos buscar(int idCurso) throws Exception {
        Cursos c = null;
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM public.curso WHERE id_curso = ?");
        ps.setInt(1, idCurso);
        // ps.setString(2, descripcion);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            c = new Cursos();
            c.setIdCurso(rs.getInt("id_curso"));
            c.setNombreCurso(rs.getString("nombre"));
            c.setDescripcion(rs.getString("descripcion"));
            // c.setEstado(rs.getBoolean("estado"));
            // c.setCapacidad(rs.getInt("capacidad"));
            // c.setInicioCurso(rs.getDate("fecha_inicio").toLocalDate());
            // c.setCierreCurso(rs.getDate("fecha_cierre").toLocalDate());
        }

        conn.close();
        return c;
    }

}