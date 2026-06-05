/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.EstudianteDAO;
import dao.UsuarioDAO;
import funciones.Paneles;
import java.awt.Component;
import java.awt.Container;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import modelo.Estudiante;
import modelo.Usuario;
import vista.VistaCredenciales;
import vista.VistaFormModifEstudiante;
import vista.VistaModifCredenc;

/**
 *
 * @author MINEDUCYT
 */
public class CtrlFormModifEstudiante {
    private VistaCredenciales viewCredenciales;
    private VistaFormModifEstudiante viewEstudiante;
    private VistaModifCredenc viewModifCreden;
    private Estudiante estudModif;
    private Usuario user;

    public CtrlFormModifEstudiante(VistaFormModifEstudiante vieew, VistaCredenciales viewCred) {
        viewEstudiante = vieew;
        viewCredenciales = viewCred;
        viewModifCreden = new VistaModifCredenc();
        viewEstudiante.setLocation(130, 0);
        
        viewEstudiante.getBtnGuardar().addActionListener(e -> {
            guardarDatosE();
        });
        viewEstudiante.getBtnCancelar().addActionListener(e -> {
            viewEstudiante.setVisible(false);
        });
        
        viewEstudiante.getBtnCambiarContraseña().addActionListener(e -> {
            try {
                viewEstudiante.setLocation(-30, 0);
                new Paneles().insertarPaneles(viewModifCreden, viewCredenciales.getJpanelForms(), 300, 450, false);
                viewModifCreden.setVisible(true);
                viewModifCreden.setLocation(500, 100);

                viewCredenciales.getJpanelForms().revalidate();
                viewCredenciales.getJpanelForms().repaint();
                traerCredenEstudiante();
                viewEstudiante.setEnabled(false);
                deshabilitarComponentes(viewEstudiante);
            } catch (Exception ex) {
                System.getLogger(CtrlFormModifEstudiante.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
        
        viewModifCreden.getBtnGuardar().addActionListener(e -> {

            try {
                cambiarContraUser();
            } catch (Exception ex) {
                System.getLogger(CtrlFormModifEstudiante.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }

        });

        viewModifCreden.getBtnCancelar().addActionListener(e -> {
            viewModifCreden.setVisible(false);
            viewEstudiante.setEnabled(true);
            habilitarComponentes(viewEstudiante);
            viewEstudiante.setLocation(130, 0);
        });
    }
    
    private void deshabilitarComponentes(Container contenedor){
        for (Component componente : contenedor.getComponents()){
            
            componente.setEnabled(false);
            
            if (componente instanceof Container){
                deshabilitarComponentes((Container) componente);
            }
        }
    }
    
    private void habilitarComponentes(Container contenedor){
        for (Component componente : contenedor.getComponents()){
            
            if (componente != viewEstudiante.getTxtDui()){
            componente.setEnabled(true);
            }
            
            if (componente instanceof Container){
                habilitarComponentes((Container) componente);
            }
        }
    }
    
    private void traerCredenEstudiante() throws Exception {
        user = new UsuarioDAO().buscarUsuario(estudModif.getIdEstudiante(), "id_estudiante");

        viewModifCreden.getTxtUsuario().setEnabled(false);
        viewModifCreden.getTxtUsuario().setText(user.getUsername());
        viewModifCreden.getTxtContraseña().setText("cambie contraseña");
    }
    

    public void traerDatosE(Estudiante d) {
        java.util.Date fechaNac = java.util.Date.from(d.getFechaNacimiento()
                .atStartOfDay(java.time.ZoneId.systemDefault())
                .toInstant());
        estudModif = d;
        viewEstudiante.setTxtDui(d.getDui());
        viewEstudiante.setTxtNombres(d.getNombre());
        viewEstudiante.setTxtApellidos(d.getApellido());
        viewEstudiante.getJdFechaNaci().setDate(fechaNac);
        viewEstudiante.setTxtCorreo(d.getCorreo());
    }
    private void cambiarContraUser() throws Exception {
        boolean modificado;
        String password = viewModifCreden.getTxtContraseña().getText();

        int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro de modificar la contraseña?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION && !password.equals("cambie_contraseña") && !password.isEmpty()) {
            String bdPassword = BCrypt.withDefaults().hashToString(12,
                    password.toCharArray());
            user.setPassword(bdPassword);

            modificado = new UsuarioDAO().modificarUsuario(user, "id_docente");

            if (modificado) {
                JOptionPane.showMessageDialog(null, "Contraseña modificada");
                viewModifCreden.setVisible(false);
                viewEstudiante.setEnabled(true);
                for (Component componente : viewEstudiante.getComponents()) {
                    if (componente != viewEstudiante.getTxtDui()) {
                        componente.setEnabled(true);
                    }
                }
                viewEstudiante.setLocation(130, 0);

            }
        }

    }

    private void guardarDatosE() {
        boolean modificado;
        java.util.Date fechaNacimiento;

        int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro de modificar los datos?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                estudModif.setNombre(viewEstudiante.getTxtNombres().getText());
                estudModif.setApellido(viewEstudiante.getTxtApellidos().getText());
                fechaNacimiento = viewEstudiante.getJdFechaNaci().getDate();
                estudModif.setFechaNacimiento((LocalDate) fechaNacimiento.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
                estudModif.setCorreo(viewEstudiante.getTxtCorreo().getText());

                modificado = new EstudianteDAO().modificarDatos(estudModif);

                if (modificado) {
                    JOptionPane.showMessageDialog(null, "Se modificaron los datos del estdiante");
                    viewEstudiante.setVisible(false);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ingrese datos validos");
            }
        }
    }
}
