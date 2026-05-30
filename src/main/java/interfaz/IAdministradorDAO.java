/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaz;

import java.util.ArrayList;

import modelo.Administrador;

/**
 *
 * @author MINEDUCYT
 */
public interface IAdministradorDAO {
    public boolean crearRegistro(Administrador a) throws Exception;

    public boolean modificarRegistro(Administrador a) throws Exception;

    public ArrayList listarRegistros() throws Exception;

    public boolean desactivarRegistro(Administrador a) throws Exception;
}
