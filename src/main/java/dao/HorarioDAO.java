/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import modelo.Horario;

/**
 *
 * @author alexi
 */

public class HorarioDAO {

    public List<Horario> listar() throws Exception {
        List<Horario> lista = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM horario");
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Horario h = new Horario();
            h.setId(rs.getInt("id_horario"));
            h.setDia(rs.getString("dia"));
            h.setHoraInicio(rs.getObject("hora_inicio", LocalTime.class));
            h.setHoraFinal(rs.getObject("hora_final", LocalTime.class));
            
            lista.add(h);
        }
        
        conn.close();
        return lista;
    }
    
}