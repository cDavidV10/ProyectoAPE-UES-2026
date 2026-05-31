/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.DocenteDAO;
import dao.UsuarioDAO;
import modelo.Usuario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

import vista.AdminView;
import vista.DocenteView;
import vista.EstudianteView;
import vista.Login;
import vista.VistaRegEstu;

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

        this.loginView.getBtnRegistro().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaRegEstu formEstudiante = new VistaRegEstu();
                ControllerRegEstu ctrFormEstudiante = new ControllerRegEstu(formEstudiante);
                formEstudiante.setVisible(true);
            }

        });

        this.loginView.getTxtUser().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (loginView.getTxtUser().getText().equals("Ingrese su nombre de usuario")) {
                    loginView.getTxtUser().setText("");
                    loginView.getTxtUser().setForeground(new Color(0, 0, 0));
                }

                if (String.valueOf(loginView.getTxtPassword().getPassword()).isEmpty()) {
                    loginView.getTxtPassword().setText("****");
                    loginView.getTxtPassword().setForeground(new Color(170, 170, 170));
                }
            }

        });

        this.loginView.getTxtPassword().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (String.valueOf(loginView.getTxtPassword().getPassword()).equals("****")) {
                    loginView.getTxtPassword().setText("");
                    loginView.getTxtPassword().setForeground(new Color(0, 0, 0));
                }

                if (String.valueOf(loginView.getTxtUser().getText()).isEmpty()) {
                    loginView.getTxtUser().setText("Ingrese su nombre de usuario");
                    loginView.getTxtUser().setForeground(new Color(170, 170, 170));
                }
            }

        });
    }

    private void acceder() {

        String username = this.loginView.getTxtUser().getText().toUpperCase();
        String password = String.valueOf(this.loginView.getTxtPassword().getPassword());

        try {
            String result = usuarioDAO.buscar(username, password);
            Usuario usuario = usuarioDAO.getUsuario();

            if (result.equalsIgnoreCase("Administrador")) {
                AdminView adminView = new AdminView();
                CtrlAdmin ctrlAdmin = new CtrlAdmin(adminView, usuario, loginView);
                adminView.setVisible(true);
                loginView.dispose();
            }

            if (result.equalsIgnoreCase("Estudiante")) {
                // EstudianteDAO estudianteDAO = new EstudianteDAO();
                // modelo.Estudiante estudiante = (modelo.Estudiante)
                // estudianteDAO.buscarRegistro(username);

                // guarda id dedel estudiante en el usuario
                // usuario.setEstudiante(estudiante);

                EstudianteView estudianteView = new EstudianteView();
                CtrlEstudianteView ctrlEstudianteView = new CtrlEstudianteView(estudianteView, usuario, loginView);

                estudianteView.setVisible(true);
                loginView.dispose();
            }

            if (result.equalsIgnoreCase("Docente")) {

                DocenteDAO docenteDAO = new DocenteDAO();

                modelo.Docente docente = docenteDAO.buscarIdPorUsuario(username);

                // Guardar el id en el usuario
                usuario.setDocente(docente);

                DocenteView docenteView = new DocenteView();
                CtrlDocenteView ctrlDocenteView = new CtrlDocenteView(docenteView, usuario, loginView);

                // CtrlDocenteView ctrlDocenteView = new CtrlDocenteView(docenteView, usuario,
                // loginView);
                docenteView.setVisible(true);
                loginView.dispose();
            }

            if (result.equalsIgnoreCase("No")) {
                JOptionPane.showMessageDialog(loginView, "Usuario y/o contraseña incorrectos");
            }

            limpiarForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(loginView, e.getMessage());
        }

    }

    private void limpiarForm() {
        loginView.getTxtUser().setText("Ingrese su nombre de usuario");
        loginView.getTxtPassword().setText("****");

        loginView.getTxtUser().setForeground(new Color(170, 170, 170));
        loginView.getTxtPassword().setForeground(new Color(170, 170, 170));
    }

}
