/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

import dao.ConsultaRegistro;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 *
 * @author MINEDUCYT
 */
public class Credenciales {
    public final void registrarCredenciales(String nombre, String apellido, String tipo, String dui, String correo)
            throws SQLException {
        // verifica si el que se registra tiene coincidencias en los nombres para
        // generar el usuario
        String user = new ConsultaRegistro().buscarRegistro(tipo, nombre, apellido);

        // crea el usuario apartir de los resultados de la busqueda de coincidencia de
        // usuario
        String nuevoUsuario = crearUsuario(user, nombre, apellido, tipo);
        String password = crearContraseña();
        String bdPassword = BCrypt.withDefaults().hashToString(12,
                password.toCharArray());

        new ConsultaRegistro().registrarCredenciales(nuevoUsuario, bdPassword, tipo, dui);

        if (tipo.equalsIgnoreCase("Estudiante")) {
            new Correos().correoCredencialesEstudiante(nombre, apellido, nuevoUsuario, password, correo);
        }

        if (tipo.equalsIgnoreCase("Docente")) {
            new Correos().correoCredencialesDocentes(nombre, apellido, nuevoUsuario, password, correo);
        }

        JOptionPane.showMessageDialog(null, "Usuario y contraseña creados correctamente");
    }

    private String crearUsuario(String ultimoUser, String nombre, String apellido, String tipo) throws SQLException {
        String usuario = "user";
        String anio = String.valueOf(LocalDate.now().getYear());

        try {
            if (ultimoUser.equalsIgnoreCase("No hay resultado")) {
                usuario = "" + tipo.charAt(0)
                        + nombre.charAt(0)
                        + apellido.charAt(0)
                        + anio.charAt(2)
                        + anio.charAt(3)
                        + "001";
            } else {
                String cod = ultimoUser.substring(ultimoUser.length() - 3);
                int sigCod = Integer.parseInt(cod) + 1;
                String codCorrelativo = String.format("%03d", sigCod);

                usuario = ultimoUser.substring(0, ultimoUser.length() - 3) + codCorrelativo;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return usuario;
    }

    private String crearContraseña() {
        String alfabeto = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(5);

        for (int i = 0; i < 6; i++) {
            int indiceAlfabet = random.nextInt(alfabeto.length());
            sb.append(alfabeto.charAt(indiceAlfabet));
        }
        return sb.toString();
    }
}
