/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import interfaz.IHorarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import modelo.Horario;

/**
 *
 * @author alexi
 */

public class HorarioDAO implements IHorarioDAO {

    @Override
    public void insertar(Horario horario) throws Exception {
        String insert = """
                insert into horario (id_aula, id_inicio_curso, dia, hora_inicio, hora_final)
                VALUES (null, null, ?::dias, ?, ?)
                                """;
        Connection conexion = Conexion.getConexion();

        PreparedStatement ps = conexion.prepareStatement(insert);

        try {
            conexion.setAutoCommit(false);

            ps.setString(1, horario.getDia());
            ps.setTime(2, Time.valueOf(horario.getHoraInicio()));
            ps.setTime(3, Time.valueOf(horario.getHoraFinal()));

            ps.executeUpdate();

            conexion.commit();

        } catch (Exception e) {
            conexion.rollback();
        } finally {
            conexion.close();
        }
    }

    @Override
    public List<Horario> listar() throws Exception {
        List<Horario> lista = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM horario order by dia");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
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