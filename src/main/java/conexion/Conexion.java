package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author cdavi
 */
public class Conexion {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/ApeBd";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection getConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}
