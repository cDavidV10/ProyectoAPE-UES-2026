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
    public final String buscarRegistro(String tipo, String nombre, String apellido) throws SQLException{
        String resultado = "No hay resultado";
        String iniciales = "" + nombre.charAt(0) + apellido.charAt(0);
        SELECT = "SELECT user_name FROM usuario WHERE user_name Like ? ORDER BY user_name DESC LIMIT 1";
        
        Connection conexion = Conexion.getConexion();
        PreparedStatement ps = conexion.prepareStatement(SELECT);
        ps.setString(1, tipo + iniciales + '%');
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()){
            resultado = rs.getString("user_name");
        }
        rs.close();
        ps.close();
        conexion.close();
        return resultado;
    }
    
    @Override
    public final int buscarRegistroUserID()throws SQLException{
        int resultado = -1;
        SELECT = "SELECT id_usuario FROM usuario ORDER BY id_usuario DESC LIMIT 1";
        
        Connection conexion = Conexion.getConexion();
        PreparedStatement ps = conexion.prepareStatement(SELECT);
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()){
            resultado = rs.getInt("id_usuario");
        }
        rs.close();
        ps.close();
        conexion.close();
        return resultado;
    }

    @Override
    public final void registrarCredenciales(String user, String contra, String tipo) throws SQLException {
        INSERT = "INSERT INTO usuario (user_name, password, tipo) VALUES (?,?,?)";
        
        try (Connection conexion = Conexion.getConexion()) {
            conexion.setAutoCommit(false);
            PreparedStatement ps = conexion.prepareStatement(INSERT);
            ps.setString(1, user);
            ps.setString(2, contra);
            ps.setString(3, tipo);
            
            ps.executeUpdate();
            conexion.commit();
            
        } catch (Exception ex) {
            throw ex;
        }
    }
}
