/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import interfaz.IDocenteDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Docente;

//CAMBIAR LOS DATOS Y SEGUIR
/**
 *
 * @author Yonathan
 */
public class DocenteDAO implements IDocenteDAO {

    private static final String INSERT = "INSERT INTO docente (dui, nombre, apellido, correo, telefono, fecha_nacimiento, tipo_contrato, especialidad, grado_academico) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT id_docente, dui, nombre, apellido, correo, telefono, fecha_nacimiento, tipo_contrato, especialidad, grado_academico FROM docente";

    public void insertar(Docente docente) throws Exception {

        Connection conn = Conexion.getConexion();// Metodo getConexion() que tengo en mi clase conexion

        try {
            // INSERCION
            conn.setAutoCommit(false); // permite la insercion a la bd
            PreparedStatement ps = conn.prepareStatement(INSERT); // Le mando el INSERT con este objeto

            ps.setString(1, docente.getDui());// Voy insertando por posiciones
            ps.setString(2, docente.getNombre());
            ps.setString(3, docente.getApellido());
            ps.setString(4, docente.getCorreo());
            ps.setString(5, docente.getTelefono());
            ps.setDate(6, new java.sql.Date(docente.getFechaNacimiento().getTime()));
            ps.setString(7, docente.getTipoContrato());
            ps.setString(8, docente.getEspecialidad());
            ps.setString(9, docente.getGradoAcademico());

            ps.executeUpdate();
            conn.commit();

        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }

    // Compruebando si dui ya existe un docente con ese DUI
    public boolean existeDui(String dui) throws Exception {
        Connection conn = Conexion.getConexion();
        String sql = "SELECT COUNT(*) FROM Docente WHERE dui = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, dui);
        ResultSet rs = ps.executeQuery();
        rs.next();
        boolean existe = rs.getInt(1) > 0;
        conn.close();
        return existe;
    }

    public List<Docente> listar() throws Exception {

        List<Docente> lista = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
        ResultSet rs = ps.executeQuery(); // Son Todos los registros

        while (rs.next()) {
            Docente docente = new Docente(); // Uso del constructor vacio
            docente.setIdDocente(rs.getInt("id_docente"));
            docente.setNombre(rs.getString("nombre"));
            docente.setDui(rs.getString("dui"));
            docente.setNombre(rs.getString("nombre"));
            docente.setApellido(rs.getString("apellido"));
            docente.setCorreo(rs.getString("correo"));
            docente.setTelefono(rs.getString("telefono"));
            docente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            docente.setTipoContrato(rs.getString("tipo_contrato"));
            docente.setEspecialidad(rs.getString("especialidad"));
            docente.setGradoAcademico(rs.getString("grado_academico"));
            lista.add(docente);
        }
        conn.close();
        return lista;
    }

}
