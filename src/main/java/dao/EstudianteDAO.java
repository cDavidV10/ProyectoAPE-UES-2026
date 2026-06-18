package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import conexion.Conexion;
import interfaz.IEstudianteDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Estudiante;

public class EstudianteDAO implements IEstudianteDAO {

    private static final String INSERT = "INSERT INTO estudiante (dui, nombre, apellido, fecha_nacimiento, correo) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM estudiante ORDER BY id_estudiante";
    private static final String SELECT_ID = "SELECT * FROM estudiante WHERE id_estudiante = ?";
    private static final String UPDATE = "UPDATE estudiante SET dui = ?, nombre = ?, apellido = ?, fecha_nacimiento = ?, correo = ? WHERE id_estudiante = ?";
    private static final String DELETE = "DELETE FROM estudiante WHERE id_estudiante = ?";
    private static final String SELECT_MAX_ID = "SELECT COALESCE(MAX(id_estudiante), 0) + 1 AS siguiente FROM estudiante";

    public int generarId() throws Exception {
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(SELECT_MAX_ID);
        ResultSet rs = ps.executeQuery();
        int id = 1;
        if (rs.next()) {
            id = rs.getInt("siguiente");
        }
        conn.close();
        return id;
    }

    public void insertar(Estudiante e) throws Exception {
        Connection conn = Conexion.getConexion();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, e.getDui());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellido());
            ps.setObject(4, e.getFechaNacimiento());
            ps.setString(5, e.getCorreo());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }

    public void actualizar(Estudiante e) throws Exception {
        Connection conn = Conexion.getConexion();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, e.getDui());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellido());
            ps.setObject(4, e.getFechaNacimiento());
            ps.setString(5, e.getCorreo());
            ps.setInt(6, e.getIdEstudiante());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }

    public void eliminar(int idEstudiante) throws Exception {
        Connection conn = Conexion.getConexion();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setInt(1, idEstudiante);
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }

    public List<Estudiante> listar() throws Exception {
        List<Estudiante> lista = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Estudiante e = new Estudiante();
            e.setIdEstudiante(rs.getInt("id_estudiante"));
            e.setDui(rs.getString("dui"));
            e.setNombre(rs.getString("nombre"));
            e.setApellido(rs.getString("apellido"));
            e.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
            e.setCorreo(rs.getString("correo"));
            lista.add(e);
        }
        conn.close();
        return lista;
    }

    public Estudiante buscar(int idEstudiante) throws Exception {
        Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(SELECT_ID);
        ps.setInt(1, idEstudiante);
        ResultSet rs = ps.executeQuery();
        Estudiante e = null;
        if (rs.next()) {
            e = new Estudiante();
            e.setIdEstudiante(rs.getInt("id_estudiante"));
            e.setDui(rs.getString("dui"));
            e.setNombre(rs.getString("nombre"));
            e.setApellido(rs.getString("apellido"));
            e.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
            e.setCorreo(rs.getString("correo"));
        }
        conn.close();
        return e;
    }

    @Override
    public Object buscarRegistro(String buscar) throws Exception {
        final String SELECT = """
                              SELECT * FROM estudiante e
                              JOIN usuario u on e.id_estudiante = u.id_estudiante
                              WHERE e.dui = ? or e.nombre = ? or u.username = ?
                              """;
        Estudiante encontrado = null;
        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(SELECT);
            ps.setString(1, buscar);
            ps.setString(2, buscar);
            ps.setString(3, buscar);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                encontrado = new Estudiante();
                encontrado.setDui(rs.getString("dui"));
                encontrado.setNombre(rs.getString("nombre"));
                encontrado.setApellido(rs.getString("apellido"));
                encontrado.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
                encontrado.setCorreo(rs.getString("correo"));
                encontrado.setIdEstudiante(rs.getInt("id_estudiante"));
            }else{
                return 0;
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio un error-Estudiante");
            return 0;
        }
        return encontrado;
    }

    @Override
    public boolean modificarDatos(Estudiante estudAModif) throws Exception {
        final String UPDATE = "UPDATE estudiante SET nombre = ?, apellido = ?, fecha_nacimiento = ?, correo = ? WHERE id_estudiante = ?";

        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, estudAModif.getNombre());
            ps.setString(2, estudAModif.getApellido());
            ps.setObject(3, estudAModif.getFechaNacimiento());
            ps.setString(4, estudAModif.getCorreo());
            ps.setInt(5, estudAModif.getIdEstudiante());

            int filaAfectada = ps.executeUpdate();

            ps.close();
            conn.close();
            return filaAfectada > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algo salio mal en la modificacion-Estudiante");
            return false;
        }
    }
}
