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
}
