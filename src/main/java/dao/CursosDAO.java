/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import interfaz.ICursosDAO;
import modelo.Aula;
import modelo.Curso;
import modelo.Docente;
import modelo.Horario;
import modelo.InicioCurso;

/**
 *
 * @author alexi
 */
public class CursosDAO implements ICursosDAO {
    // private static final String INSERT = "INSERT INTO public.curso (nombre,
    // estado, capacidad, fecha_inicio, fecha_cierre) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT = "INSERT INTO curso (codigo, nombre, descripcion) VALUES (?,?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM curso ORDER BY id_curso";
    private static final String SELECT_ID = "SELECT * FROM curso WHERE id_curso = ?";
    private static final String UPDATE = "UPDATE curso SET nombre = ?, descripcion = ? WHERE id_curso = ?";
    private static final String DELETE = "DELETE FROM curso WHERE id_curso = ?";

    public void insertar(Curso c) throws Exception {

        try {
            Connection conn = Conexion.getConexion();

            if (conn == null) {
                throw new Exception("No se pudo establecer conexión con la base de datos.");
            }

            conn.setAutoCommit(false);

            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, c.getCodigo());
            ps.setString(2, c.getNombreCurso());
            ps.setString(3, c.getDescripcion());

            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Curso> listar() throws Exception {
        List<Curso> lista = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Curso c = new Curso();
            c.setIdCurso(rs.getInt("id_curso"));
            c.setCodigo(rs.getString("codigo"));
            c.setNombreCurso(rs.getString("nombre"));
            c.setDescripcion(rs.getString("descripcion"));
            lista.add(c);
        }
        conn.close();
        return lista;
    }

    public void eliminar(int id) throws Exception {
        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void actualizar(Curso c) throws Exception {
        Connection conn = Conexion.getConexion();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, c.getNombreCurso());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getIdCurso());

            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }

    public Curso buscar(int idCurso) throws Exception {
        Curso c = null;
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM curso WHERE id_curso = ?");
        ps.setInt(1, idCurso);
        // ps.setString(2, descripcion);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            c = new Curso();
            c.setIdCurso(rs.getInt("id_curso"));
            c.setNombreCurso(rs.getString("nombre"));
            c.setDescripcion(rs.getString("descripcion"));
            // c.setEstado(rs.getBoolean("estado"));
            // c.setCapacidad(rs.getInt("capacidad"));
            // c.setInicioCurso(rs.getDate("fecha_inicio").toLocalDate());
            // c.setCierreCurso(rs.getDate("fecha_cierre").toLocalDate());
        }

        conn.close();
        return c;
    }

    @Override
    public boolean cursoActivo(String codigo) throws Exception {
        String consulta = """
                select
                    count(*)
                from inicio_curso ic
                inner join curso c on c.id_curso = ic.id_curso
                where estado = 'Activo'
                and c.codigo = ?
                                """;

        Connection conexion = Conexion.getConexion();
        PreparedStatement ps = conexion.prepareStatement(consulta);

        ps.setString(1, codigo);

        ResultSet rs = ps.executeQuery();

        rs.next();

        conexion.close();
        return rs.getInt(1) > 0;

    }

    @Override
    public List<Horario> infoCurso(String codigo) throws Exception {
        String consulta = """
                 select
                    d.nombre as docenteNombre,
                    d.apellido as docenteApellido,
                    h.dia as dia,
                    h.hora_inicio as inicio,
                    h.hora_final as final,
                    a.codigo as aula
                from inicio_curso ic
                inner join docente d on d.id_docente = ic.id_docente
                inner join horario h on h.id_inicio_curso = ic.id_inicio_curso
                inner join aula a on a.id_aula = h.id_aula
                where ic.estado = 'Activo'
                and ic.id_curso = (select c.id_curso from curso c where c.codigo = ?);
                                """;

        Connection conexion = Conexion.getConexion();

        PreparedStatement ps = conexion.prepareStatement(consulta);

        ps.setString(1, codigo);

        List<Horario> datos = new ArrayList<>();

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Horario horario = new Horario();
            Docente docente = new Docente();
            Aula aula = new Aula();
            docente.setNombre(rs.getString(1));
            docente.setApellido(rs.getString(2));
            horario.setDia(rs.getString(3));
            horario.setHoraInicio(rs.getObject(4, LocalTime.class));
            horario.setHoraFinal(rs.getObject(5, LocalTime.class));
            aula.setCodigo(rs.getString(6));

            InicioCurso ic = new InicioCurso();

            ic.setDocente(docente);

            horario.setInicioCurso(ic);
            horario.setAula(aula);
            datos.add(horario);

        }

        return datos;
    }

}