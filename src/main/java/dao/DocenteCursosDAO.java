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
import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Docente;
import modelo.InicioCurso;

/**
 *
 * @author Yonathan
 */
public class DocenteCursosDAO implements IDocenteCursosDAO {

    private static final String SELECT = "SELECT c.codigo, c.nombre, c.descripcion, ic.fecha_apertura, ic.fecha_cierre, ic.cupo_maximo FROM inicio_curso ic INNER JOIN curso c ON ic.id_curso = c.id_curso WHERE ic.id_docente = ?";
    
    @Override
    public List<InicioCurso> listarCursosxDocente(Docente docente) throws Exception {
        List<InicioCurso> lista = new ArrayList<>();

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(SELECT)) {
            ps.setObject(1, docente.getIdDocente());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Crear objeto Curso
                Curso curso = new Curso();
                curso.setCodigo(rs.getString("codigo"));
                curso.setNombreCurso(rs.getString("nombre"));
                curso.setDescripcion(rs.getString("descripcion"));

                // Crear objeto InicioCurso
                InicioCurso inicio = new InicioCurso();
                inicio.setFechaApertura(rs.getObject("fecha_apertura", LocalDate.class));
                inicio.setFechaCierre(rs.getObject("fecha_cierre", LocalDate.class));
                inicio.setCupoMaximo(rs.getString("cupo_maximo"));

                // Relacionar con el curso
                inicio.setCursos(curso);
                lista.add(inicio);
            }
        }
        return lista;
    }

}
