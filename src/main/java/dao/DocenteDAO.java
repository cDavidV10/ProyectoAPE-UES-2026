/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import interfaz.IDocenteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import modelo.Docente;

/**
 *
 * @author Yonathan
 */
public class DocenteDAO implements IDocenteDAO {
    Docente docente = null; //Objetp identificador para sus cursos
    private static final String INSERT = "INSERT INTO docente (dui, nombre, apellido, correo, telefono, fecha_nacimiento, tipo_contrato, especialidad, grado_academico) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT id_docente, dui, nombre, apellido, correo, telefono, fecha_nacimiento, tipo_contrato, especialidad, grado_academico FROM docente";
    private static final String DELETE_REGISTRO = "DELETE FROM docente WHERE dui = ?";
    private static final String SELECT_DOCENTE_XUSER = "SELECT d.* FROM docente d INNER JOIN usuario u ON d.id_docente = u.id_docente WHERE u.username = ?";
    
    public void insertar(Docente docente) throws Exception {

        Connection conn = Conexion.getConexion();// Metodo getConexion() que tengo en mi clase conexion

        try {
            // INSERCION
            conn.setAutoCommit(false); // permite la insercion a la bd
            PreparedStatement ps = conn.prepareStatement(INSERT); 

            ps.setString(1, docente.getDui());// Voy insertando por posiciones
            ps.setString(2, docente.getNombre());
            ps.setString(3, docente.getApellido());
            ps.setString(4, docente.getCorreo());
            ps.setString(5, docente.getTelefono());
            ps.setObject(6, docente.getFechaNacimiento());
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
            docente.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
            docente.setTipoContrato(rs.getString("tipo_contrato"));
            docente.setEspecialidad(rs.getString("especialidad"));
            docente.setGradoAcademico(rs.getString("grado_academico"));
            lista.add(docente);
        }
        conn.close();
        return lista;
    }

    @Override
    public void eliminar(String dui) throws Exception {
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(DELETE_REGISTRO);
        ps.setString(1, dui); 
        ps.executeUpdate(); //Para ejecutar importante
        conn.close();
    }

    @Override
    public Docente buscarDocentePorUsuario(String username) throws Exception {    
        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(SELECT_DOCENTE_XUSER)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                docente = new Docente();
                docente.setIdDocente(rs.getInt("id_docente"));
                docente.setDui(rs.getString("dui"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellido(rs.getString("apellido"));
                docente.setCorreo(rs.getString("correo"));
                docente.setTelefono(rs.getString("telefono"));
                docente.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
                docente.setTipoContrato(rs.getString("tipo_contrato"));
                docente.setEspecialidad(rs.getString("especialidad"));
                docente.setGradoAcademico(rs.getString("grado_academico"));
            }
        }
        return docente;
    }

    public Object buscarRegistro(String buscar) {
        final String SELECT = """
                              Select * from docente d
                              join usuario u on e.id_docente = u.id_docente
                              where d.dui = ? or d.nombre = ? or u.username = ?
                              """;
        Docente encontrado = null;
        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(SELECT);
            ps.setString(1, buscar);
            ps.setString(2, buscar);
            ps.setString(3, buscar);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                encontrado = new Docente();
                encontrado.setIdDocente(rs.getInt("id_docente"));
                encontrado.setNombre(rs.getString("nombre"));
                encontrado.setDui(rs.getString("dui"));
                encontrado.setNombre(rs.getString("nombre"));
                encontrado.setApellido(rs.getString("apellido"));
                encontrado.setCorreo(rs.getString("correo"));
                encontrado.setTelefono(rs.getString("telefono"));
                encontrado.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
                encontrado.setTipoContrato(rs.getString("tipo_contrato"));
                encontrado.setEspecialidad(rs.getString("especialidad"));
                encontrado.setGradoAcademico(rs.getString("grado_academico"));
            }else{
                return 0;
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error-Docente");
        }
        return encontrado;
    }

    public boolean modificarDatos(Docente docentAModif) {
        final String UPDATE = "UPDATE docente SET nombre = ?, apellido = ?, correo = ?, telefono = ?, fecha_nacimiento = ?, tipo_contrato = ?, especialidad = ?, grado_academico = ? WHERE id_docente = ?";

        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, docentAModif.getNombre());
            ps.setString(2, docentAModif.getApellido());
            ps.setString(3, docentAModif.getCorreo());
            ps.setString(4, docentAModif.getTelefono());
            ps.setObject(5, docentAModif.getFechaNacimiento());
            ps.setString(6, docentAModif.getTipoContrato());
            ps.setString(7, docentAModif.getEspecialidad());
            ps.setString(8, docentAModif.getGradoAcademico());
            ps.setInt(9, docentAModif.getIdDocente());

            int filaAfectada = ps.executeUpdate();

            ps.close();
            conn.close();
            return filaAfectada > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Algo salio mal en la modificacion-Docente");
            return false;
        }
    }
}
