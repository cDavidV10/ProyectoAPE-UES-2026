/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    private static final String URL = "jdbc:postgresql://localhost:5432/APPDB_EXPEDIENTE";
private static final String USER = "postgres";
private static final String PASSWORD = "8659";

    public static Connection getConexion() {
        
    try {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
}
