/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vista.Login;

/**
 *
 * @author cdavi
 */
public class CtrlLogin {
    private Login loginView;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public CtrlLogin(Login loginView) {
        this.loginView = loginView;

        this.loginView.setLocationRelativeTo(null);
        this.loginView.getBtnIngresar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acceder();
            }

        });
    }

    public void acceder() {

        String username = this.loginView.getTxtUser().getText();
        String password = String.valueOf(this.loginView.getTxtPassword().getPassword());

        try {
            String result = usuarioDAO.buscar(username, password);

            if (result.equalsIgnoreCase("Administrador")) {
                JOptionPane.showMessageDialog(loginView, result);
            }

            if (result.equalsIgnoreCase("Estudiante")) {
                JOptionPane.showMessageDialog(loginView, result);
            }

            if (result.equalsIgnoreCase("Docente")) {
                JOptionPane.showMessageDialog(loginView, result);
            }

            if (result.equalsIgnoreCase("No")) {
                JOptionPane.showMessageDialog(loginView, "Usuario y/o contraseña incorrectos");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(loginView, e.getMessage());
        }

    }

}
