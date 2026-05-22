/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import java.util.List;
/**
 *
 * @author Yonathan
 */
public interface IDocenteCursosDAO {
    List<Object[]> listarCursosxDocente(int idDocente) throws Exception;
}
