/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import interfaz.IEvaluacionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import modelo.Evaluacion;

/**
 *
 * @author danie
 */
public class EvaluacionDAO implements IEvaluacionDAO{

    @Override
    public boolean registrarEvaluacion(Evaluacion evaluacion) throws Exception {
        String sql = """
                     INSERT INTO evaluacion (tipo, descripcion, fecha_registro, nota, porcentaje) VALUES (1,?,?,null,null,?);
                     """;
        
        Connection conn = Conexion.getConexion();// Metodo getConexion() que tengo en mi clase conexion

        try {
            // INSERCION
            conn.setAutoCommit(false); // permite la insercion a la bd
            PreparedStatement ps = conn.prepareStatement(sql); 

            ps.setString(1, evaluacion.getTipo());// Voy insertando por posiciones
            ps.setString(2, evaluacion.getDescripcion());
            ps.setInt(3, evaluacion.getPorcentaje());

            ps.executeUpdate();
            conn.commit();
            return true;
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }
}
