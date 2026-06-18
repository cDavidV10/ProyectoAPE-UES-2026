/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaz;

import modelo.Evaluacion;

/**
 *
 * @author danie
 */
public interface IEvaluacionDAO {
    public boolean registrarEvaluacion(Evaluacion evaluacion) throws Exception;
}
