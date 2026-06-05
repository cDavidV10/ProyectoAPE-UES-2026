/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.AdministradorDAO;
import dao.UsuarioDAO;
import funciones.Paneles;
import java.awt.Component;
import javax.swing.JOptionPane;
import modelo.Administrador;
import modelo.Usuario;
import vista.FormModifAdmin;
import vista.VistaCredenciales;
import vista.VistaModifCredenc;

/**
 *
 * @author danie
 */
public class CtrlFormModifAdmin {

    private FormModifAdmin view;
    private VistaCredenciales viewCredenciales;
    private VistaModifCredenc viewModifCreden;
    private Administrador modif;
    private Usuario user;

    public CtrlFormModifAdmin(FormModifAdmin vieww, VistaCredenciales viewCred) {
        this.view = vieww;
        this.viewCredenciales = viewCred;
        this.viewModifCreden = new VistaModifCredenc();
        view.setLocation(130, 0);
        view.getBtnGuardar().addActionListener(e -> {
            guardarCambiosAdmin();

        });

        view.getBtnCambiarContraseña().addActionListener(e -> {
            try {
                view.setLocation(-50, 0);
                new Paneles().insertarPaneles(viewModifCreden, viewCredenciales.getJpanelForms(), 300, 450, false);
                viewModifCreden.setVisible(true);
                viewModifCreden.setLocation(400, 50);

                viewCredenciales.getJpanelForms().revalidate();
                viewCredenciales.getJpanelForms().repaint();
                traerCredenAdmin();
                view.setEnabled(false);
                for (Component componente : view.getComponents()) {
                    componente.setEnabled(false);
                }
            } catch (Exception ex) {
                System.getLogger(CtrlFormModifAdmin.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });

        viewModifCreden.getBtnGuardar().addActionListener(e -> {

            try {
                cambiarContraUser();
            } catch (Exception ex) {
                System.getLogger(CtrlFormModifAdmin.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }

        });

        viewModifCreden.getBtnCancelar().addActionListener(e -> {
            viewModifCreden.setVisible(false);
            view.setEnabled(true);
            for (Component componente : view.getComponents()) {
                if (componente != view.getTxtDui()) {
                    componente.setEnabled(true);
                }
            }
            view.setLocation(130, 0);
        });
    }

    public void traerDatosAdmin(Administrador a) {
        modif = a;
        view.getTxtDui().setEnabled(false);
        view.getTxtDui().setText(a.getDui());
        view.getTxtNombres().setText(a.getNombre());
        view.getTxtApellidos().setText(a.getApellido());
    }

    private void traerCredenAdmin() throws Exception {
        user = new UsuarioDAO().buscarUsuario(modif.getId(), "id_admind");

        viewModifCreden.getTxtUsuario().setEnabled(false);
        viewModifCreden.getTxtUsuario().setText(user.getUsername());
        viewModifCreden.getTxtContraseña().setText("cambie_contraseña");
    }

    private void guardarCambiosAdmin() {
        String nombre;
        String apellido;

        boolean modificado;

        int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro de modificar los datos?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                nombre = view.getTxtNombres().getText();
                apellido = view.getTxtApellidos().getText();
                modif.setNombre(nombre);
                modif.setApellido(apellido);

                modificado = new AdministradorDAO().modificarRegistro(modif);

                if (modificado) {
                    JOptionPane.showMessageDialog(null, "Se modificaron los datos del administrador");
                    view.setVisible(false);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ingrese datos validos");
            }
        }
    }

    private void cambiarContraUser() throws Exception {
        boolean modificado;
        String password = viewModifCreden.getTxtContraseña().getText();

        int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro de modificar la contraseña?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION && !password.equals("cambie_contraseña") && !password.isEmpty()) {
            String bdPassword = BCrypt.withDefaults().hashToString(12,
                    password.toCharArray());
            user.setPassword(bdPassword);

            modificado = new UsuarioDAO().modificarUsuario(user, "id_admind");

            if (modificado) {
                JOptionPane.showMessageDialog(null, "Contraseña modificada");
                viewModifCreden.setVisible(false);
                view.setEnabled(true);
                for (Component componente : view.getComponents()) {
                    if (componente != view.getTxtDui()) {
                        componente.setEnabled(true);
                    }
                }
                view.setLocation(130, 0);

            }
        }

    }
}
