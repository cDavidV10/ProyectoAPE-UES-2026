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
    // CREATE
    private static final String INSERT = "INSERT INTO public.curso (nombre, estado, capacidad, fecha_inicio, fecha_cierre) VALUES (?, ?, ?, ?, ?)";

    // READ (Listar todos)
    private static final String SELECT_ALL = "SELECT * FROM public.curso ORDER BY id_curso";

    // READ (Buscar por ID)
    private static final String SELECT_ID = "SELECT * FROM public.curso WHERE id_curso = ?";

    // UPDATE
    private static final String UPDATE = "UPDATE public.curso SET nombre = ?, estado = ?, capacidad = ?, fecha_inicio = ?, fecha_cierre = ? WHERE id_curso = ?";

    // DELETE
    private static final String DELETE = "DELETE FROM public.curso WHERE id_curso = ?";

    public void insertar(Cursos c) throws Exception {
        System.out.println("TETAAAAAAS");

        try {
            Connection conn = Conexion.getConexion();

            if (conn == null) {
                throw new Exception("No se pudo establecer conexión con la base de datos.");
            }

            conn.setAutoCommit(false);

            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, c.getNombreCurso());
            ps.setBoolean(2, c.isEstado());
            ps.setInt(3, c.getCapacidad());
            ps.setDate(4, java.sql.Date.valueOf(c.getInicioCurso()));
            ps.setDate(5, java.sql.Date.valueOf(c.getCierreCurso()));

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
            c.setNombreCurso(rs.getString("nombre"));
            c.setEstado(rs.getBoolean("estado"));
            c.setCapacidad(rs.getInt("capacidad"));
            c.setInicioCurso(rs.getDate("fecha_inicio").toLocalDate());
            c.setCierreCurso(rs.getDate("fecha_cierre").toLocalDate());
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
            ps.setBoolean(2, c.isEstado());
            ps.setInt(3, c.getCapacidad());
            ps.setDate(4, java.sql.Date.valueOf(c.getInicioCurso()));
            ps.setDate(5, java.sql.Date.valueOf(c.getCierreCurso()));
            ps.setInt(6, c.getIdCurso());

            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }

}