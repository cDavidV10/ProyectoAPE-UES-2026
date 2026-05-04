/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaz;

import java.util.List;
import modelo.Cursos;

/**
 *
 * @author alexi
 */
public interface ICursosDAO {
    void insertar(Cursos c) throws Exception;

    void actualizar(Cursos c) throws Exception;

    void eliminar(int idCurso) throws Exception;

    List<Cursos> listar() throws Exception;

    Cursos buscar(int idCurso) throws Exception;
}