/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import java.util.List;
import modelo.Curso;
import interfaz.IDocenteCursosDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.InicioCurso;

/**
 *
 * @author Yonathan
 */
public class DocenteCursosDAO implements IDocenteCursosDAO {

    private static final String SELECT = "SELECT c.codigo, c.nombre, c.descripcion, ic.fecha_apertura, ic.fecha_cierre, ic.cupo_maximo FROM inicio_curso ic INNER JOIN curso c ON ic.id_curso = c.id_curso WHERE ic.id_docente = ?";

    @Override
    public List<Object[]> listarCursosxDocente(int idDocente) throws Exception {
                List<Object[]> lista = new ArrayList<>();

        try (Connection conn = Conexion.getConexion(); 
            PreparedStatement ps = conn.prepareStatement(SELECT)) {
            ps.setInt(1, idDocente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha_apertura"),
                    rs.getDate("fecha_cierre"),
                    rs.getString("cupo_maximo")
                });
            }
        }
        return lista;
    }

}
