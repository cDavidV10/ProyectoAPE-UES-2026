/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import java.util.List;
import modelo.Docente;

/**
 *
 * @author Yonathan
 */
public interface IDocenteDAO {
    void insertar(Docente docente) throws Exception;
    List<Docente> listar() throws Exception;
    public Object buscarRegistro(String buscar) throws Exception;
    void eliminar(String dui) throws Exception;
    Docente buscarDocentePorUsuario(String username) throws Exception;
    Docente buscarPorDui(String dui) throws Exception;
}
