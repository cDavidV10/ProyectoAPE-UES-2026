/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.EstudianteDAO;
import funciones.AbiriReporte;
import funciones.Paneles;
import funciones.UsuarioActivo;

import java.awt.event.WindowEvent;
import modelo.Estudiante;
import modelo.Usuario;
import vista.AdminView;
import vista.AdministrarCursos;
import vista.Login;
import vista.VistaCredenciales;
import vista.AdminDocente;
import vista.VistaEstudiantesRegistrados;

/**
 *
 * @author cdavi
 */
public class CtrlAdmin {

    AdminView adminView;
    EstudianteDAO dao = new EstudianteDAO();
    Usuario usuario;
    Login login;

    public CtrlAdmin(AdminView adminView, Usuario usuario, Login login) {
        this.adminView = adminView;
        this.usuario = usuario;
        this.login = login;

        adminView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new UsuarioActivo().cambiarLabelUsuario(adminView.getTxtUser(), usuario);
            }

        });

        adminView.getBtnDocente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AdminDocente vistaPrincipal = new AdminDocente();

                CtrlAdminDocente controlador = new CtrlAdminDocente(vistaPrincipal);
                new Paneles().insertarPaneles(vistaPrincipal, adminView.getBgPanel());
            }

        });

        adminView.getBtnCurso().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AdministrarCursos administrarCursos = new AdministrarCursos();
                ControladorAdministrarCursos ctrlCursos = new ControladorAdministrarCursos(administrarCursos);
                new Paneles().insertarPaneles(administrarCursos, adminView.getBgPanel());
            }

        });

        adminView.getBtnEstudiante().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VistaEstudiantesRegistrados estudiantesRegistrados = new VistaEstudiantesRegistrados();
                cargarTabla(estudiantesRegistrados);
                new Paneles().insertarPaneles(estudiantesRegistrados, adminView.getBgPanel());
            }

        });

        adminView.getBtnCredencialess().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaCredenciales credenciales = new VistaCredenciales();
                new CtrlCredenciales(credenciales);
                new Paneles().insertarPaneles(credenciales, adminView.getBgPanel());
            }
        });

        this.adminView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                login.setVisible(true);
            }
        });
    }

    private void cargarTabla(VistaEstudiantesRegistrados vistaTabla) {
        DefaultTableModel modelo = (DefaultTableModel) vistaTabla.getTblEstudiantes().getModel();
        modelo.setRowCount(0);
        vistaTabla.getBtnModifDatos().setEnabled(true);

        try {
            List<Estudiante> lista = dao.listar();
            for (Estudiante e : lista) {
                modelo.addRow(new Object[] {
                        e.getIdEstudiante(),
                        e.getDui(),
                        e.getNombre(),
                        e.getApellido(),
                        e.getFechaNacimiento(),
                        e.getCorreo()
                });
            }

            vistaTabla.getBtnReporte().addActionListener(e -> {
                new AbiriReporte().abrirReporte("repEstudiante.jasper");
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vistaTabla,
                    "Error al cargar datos: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
