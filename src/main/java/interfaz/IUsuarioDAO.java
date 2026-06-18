/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaz;

import modelo.Usuario;

/**
 *
 * @author cdavi
 */
public interface IUsuarioDAO {
    String buscar(String username, String password) throws Exception;
    Usuario buscarUsuario(int id, String campo) throws Exception;
    boolean modificarUsuario(Usuario u, String campo) throws Exception;
}
