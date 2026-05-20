/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaz;

import java.sql.SQLException;

/**
 *
 * @author MINEDUCYT
 */
public interface ICredencialesDAO {
    String buscarRegistro(String tipo, String nombre, String apellido) throws SQLException;

    int buscarRegistroUserID() throws SQLException;

    void registrarCredenciales(String user, String contra, String tipo, String dui) throws SQLException;
}
