/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import interfaz.IAdministradorDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Administrador;

/**
 *
 * @author MINEDUCYT
 */
public class AdministradorDAO implements IAdministradorDAO {

    @Override
    public boolean crearRegistro(Administrador a) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearRegistro'");
    }

    @Override
    public boolean modificarRegistro(Administrador a) throws Exception {
        String UPDATE = "UPDATE administrador SET nombre = ?, apellido = ? WHERE id_admind = ?";
        
        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setInt(3, a.getId());

            int filaAfectada = ps.executeUpdate();

            ps.close();
            conn.close();
            return filaAfectada > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Algo salio mal en la modificacion-Docente");
            return false;
        }
    }

    @Override
    public ArrayList listarRegistros() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarRegistros'");
    }

    @Override
    public boolean desactivarRegistro(Administrador a) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object buscarRegistro(String buscar) throws Exception {
        final String SELECT = "Select * from administrador where dui = ?";
        Administrador encontrado = null;
        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(SELECT);
            ps.setString(1, buscar);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                encontrado = new Administrador();

                encontrado.setNombre(rs.getString("nombre"));
                encontrado.setApellido(rs.getString("apellido"));
                encontrado.setDui(rs.getString("dui"));
                encontrado.setId(rs.getInt("id_admind"));

            } else {
                return 0;
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error-Administrador");
        }
        return encontrado;
    }
}
