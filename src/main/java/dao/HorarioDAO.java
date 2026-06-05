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

import modelo.Aula;
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
                select a.id_aula, null, ?::dias, ?, ?
                from aula a
                where a.codigo = ?
                                """;
        Connection conexion = Conexion.getConexion();

        PreparedStatement ps = conexion.prepareStatement(insert);

        try {
            conexion.setAutoCommit(false);

            ps.setString(1, horario.getDia());
            ps.setTime(2, Time.valueOf(horario.getHoraInicio()));
            ps.setTime(3, Time.valueOf(horario.getHoraFinal()));
            ps.setString(4, horario.getAula().getCodigo());

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
        String consulta = """
                select
                    h.dia,
                    h.hora_inicio,
                    h.hora_final,
                    a.codigo
                from horario h
                inner join aula a on a.id_aula = h.id_aula
                order by h.dia
                                """;
        List<Horario> lista = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Horario h = new Horario();
            Aula aula = new Aula();

            h.setDia(rs.getString(1));
            h.setHoraInicio(rs.getObject(2, LocalTime.class));
            h.setHoraFinal(rs.getObject(3, LocalTime.class));
            aula.setCodigo(rs.getString(4));

            h.setAula(aula);

            lista.add(h);
        }

        conn.close();
        return lista;
    }

    @Override
    public List<Aula> listarAulas() throws Exception {
        List<Aula> lista = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM aula");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Aula aula = new Aula();

            aula.setId(rs.getInt(1));
            aula.setCodigo(rs.getString(2));

            lista.add(aula);
        }

        conn.close();
        return lista;
    }

}