/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import interfaz.ICredencialesDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MINEDUCYT
 */
public class ConsultaRegistro implements ICredencialesDAO {
    private String SELECT = "";
    private String INSERT = "";

    @Override
    public final String buscarRegistro(String tipo, String nombre, String apellido) throws SQLException {
        String resultado = "No hay resultado";
        String iniciales = "" + nombre.charAt(0) + apellido.charAt(0);
        SELECT = "SELECT username FROM usuario WHERE username Like ? ORDER BY username DESC LIMIT 1";

        Connection conexion = Conexion.getConexion();
        PreparedStatement ps = conexion.prepareStatement(SELECT);
        ps.setString(1, tipo.charAt(0) + iniciales + '%');

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            resultado = rs.getString("username");
        }
        rs.close();
        ps.close();
        conexion.close();
        return resultado;
    }

    @Override
    public final int buscarRegistroUserID() throws SQLException {
        int resultado = -1;
        SELECT = "SELECT id_usuario FROM usuario ORDER BY id_usuario DESC LIMIT 1";

        Connection conexion = Conexion.getConexion();
        PreparedStatement ps = conexion.prepareStatement(SELECT);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            resultado = rs.getInt("id_usuario");
        }
        rs.close();
        ps.close();
        conexion.close();
        return resultado;
    }

    @Override
    public final void registrarCredenciales(String user, String contra, String tipo, String dui) throws SQLException {

        if (tipo.equals("Administrador")) {
            INSERT = "INSERT INTO usuario (username, password, tipo, id_estudiante, id_docente, id_admind) " +
                    "SELECT ?, ?, ?::tipos, null, null, a.id_admind " +
                    "FROM administrador a WHERE a.dui = ?";

        } else if (tipo.equals("Docente")) {
            INSERT = "INSERT INTO usuario (username, password, tipo, id_estudiante, id_docente, id_admind) " +
                    "SELECT ?, ?, ?::tipos, null, d.id_docente, null " +
                    "FROM docente d WHERE d.dui = ?";

        } else if (tipo.equals("Estudiante")) {
            INSERT = "INSERT INTO usuario (username, password, tipo, id_estudiante, id_docente, id_admind) " +
                    "SELECT ?, ?, ?::tipos, e.id_estudiante, null, null " +
                    "FROM estudiante e WHERE e.dui = ?";
        }

        try (Connection conexion = Conexion.getConexion()) {
            conexion.setAutoCommit(false);
            PreparedStatement ps = conexion.prepareStatement(INSERT);
            ps.setString(1, user);
            ps.setString(2, contra);
            ps.setString(3, tipo);
            ps.setString(4, dui);

            ps.executeUpdate();
            conexion.commit();

        } catch (Exception ex) {
            throw ex;
        }
    }
}
