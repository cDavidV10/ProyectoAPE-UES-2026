/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import at.favre.lib.crypto.bcrypt.BCrypt;
import conexion.Conexion;
import interfaz.IUsuarioDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Administrador;
import modelo.Docente;
import modelo.Estudiante;
import modelo.Usuario;

/**
 *
 * @author cdavi
 */
public class UsuarioDAO implements IUsuarioDAO {
    private Usuario usuario = new Usuario();
    private static final String SELECT = "select * from usuario where username = ?";
    private String SELECT_USER = "";

    @Override
    public String buscar(String username, String password) throws Exception {
        Connection conexion = Conexion.getConexion();

        boolean existe = false;

        PreparedStatement ps = conexion.prepareStatement(SELECT);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            usuario.setId(rs.getInt(1));
            usuario.setUsername(rs.getString(2));
            usuario.setPassword(rs.getString(3));
            usuario.setTipo(rs.getString(4));

            existe = true;
        }

        if (existe) {
            if (usuario.getUsername().equalsIgnoreCase(username)) {
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(),
                        usuario.getPassword());

                if (result.verified) {

                    return usuario.getTipo();
                }
            }
        }

        conexion.close();
        return "No";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public Usuario buscarUsuario(int id, String campo) throws Exception {
        SELECT_USER = "SELECT * FROM usuario WHERE " + campo + " = ?";
        Usuario encontrado = null;
        
        try{
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(SELECT_USER);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                encontrado = new Usuario();
                encontrado.setUsername(rs.getString("username"));
                if (campo.equalsIgnoreCase("id_admind")) {
                    Administrador rol = new Administrador();
                    rol.setId(rs.getInt(campo));
                    encontrado.setAdministrador(rol);
                }else if (campo.equalsIgnoreCase("id_docente")) {
                    Docente rol = new Docente();
                    rol.setIdDocente(rs.getInt(campo));
                    encontrado.setDocente(rol);
                }else if (campo.equalsIgnoreCase("id_estudiante")) {
                    Estudiante rol = new Estudiante();
                    rol.setIdEstudiante(rs.getInt(campo));
                    encontrado.setEstudiante(rol);
                }
            }
            rs.close();
            ps.close();
            con.close();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error-Usuario");
        }
        return encontrado;
    }

    @Override
    public boolean modificarUsuario(Usuario u, String campo) throws Exception {
        final String UPDATE = "UPDATE usuario SET password = ? WHERE "+ campo + " = ?";

        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, u.getPassword());
            if (campo.equalsIgnoreCase("id_admind")){
                ps.setInt(2, u.getAdministrador().getId());
            }else if (campo.equalsIgnoreCase("id_docente")){
                ps.setInt(2, u.getDocente().getIdDocente());
            }else if (campo.equalsIgnoreCase("id_estudiante")){
                ps.setInt(2, u.getEstudiante().getIdEstudiante());
            }
            
            int filaAfectada = ps.executeUpdate();

            ps.close();
            conn.close();
            return filaAfectada > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algo salio mal en la modificacion-Usuario");
            return false;
        }
    }

}
