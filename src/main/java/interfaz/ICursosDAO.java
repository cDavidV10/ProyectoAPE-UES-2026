/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaz;

import java.util.List;
import modelo.Curso;

/**
 *
 * @author alexi
 */
public interface ICursosDAO {

    void insertar(Curso c) throws Exception;

    void actualizar(Curso c) throws Exception;

    void eliminar(int idCurso) throws Exception;

    List<Curso> listar() throws Exception;

    Curso buscar(int idCurso) throws Exception;
}