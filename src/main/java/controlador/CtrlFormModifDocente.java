/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.DocenteDAO;
import dao.UsuarioDAO;
import funciones.Paneles;
import java.awt.Component;
import java.awt.Container;
import java.time.LocalDate;
import java.util.Date;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JOptionPane;
import modelo.Docente;
import modelo.Usuario;
import vista.FormDocenteModif;
import vista.VistaCredenciales;
import vista.VistaModifCredenc;

/**
 *
 * @author MINEDUCYT
 */
public class CtrlFormModifDocente {
    
    private VistaCredenciales viewCredenciales;
    private FormDocenteModif view;
    private VistaModifCredenc viewModifCreden;
    private Docente docenteModif;
    private Usuario user;

    public CtrlFormModifDocente(FormDocenteModif vieew, VistaCredenciales viewCredenc) {
        this.view = vieew;
        this.viewCredenciales =viewCredenc;
        this.viewModifCreden = new VistaModifCredenc();
        view.setLocation(130, 0);
        

        view.getBtnGuardarDocente().addActionListener(e -> {
            guardarDatosD();
        });

        view.getBtnCancelarDocente().addActionListener(e -> {
            view.setVisible(false);
        });
        
        view.getBtnCambiarContraseña().addActionListener(e -> {
            try {
                view.setLocation(-30, 0);
                new Paneles().insertarPaneles(viewModifCreden, viewCredenciales.getJpanelForms(), 300, 450, false);
                viewModifCreden.setVisible(true);
                viewModifCreden.setLocation(500, 100);

                viewCredenciales.getJpanelForms().revalidate();
                viewCredenciales.getJpanelForms().repaint();
                traerCredenDocent();
                view.setEnabled(false);
                for (Component componente : view.getComponents()) {
                    componente.setEnabled(false);
                }
            } catch (Exception ex) {
                System.getLogger(CtrlFormModifDocente.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
        
        viewModifCreden.getBtnGuardar().addActionListener(e -> {

            try {
                cambiarContraUser();
            } catch (Exception ex) {
                System.getLogger(CtrlFormModifDocente.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
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

        /*
         * this.view.getTxtNombre().addMouseListener(new MouseAdapter() {
         * 
         * @Override
         * public void mousePressed(MouseEvent e) {
         * if (String.valueOf(view.getTxtNombre().getText()).equals("Nombre")) {
         * view.getTxtNombre().setText("");
         * view.getTxtNombre().setForeground(new Color(0, 0, 0));
         * }
         * 
         * if (String.valueOf(view.getTxtApellido().getText()).isEmpty()) {
         * view.getTxtApellido().setText("Apellido");
         * view.getTxtApellido().setForeground(new Color(170, 170, 170));
         * }
         * }
         * 
         * });
         * 
         * this.view.getTxtApellido().addMouseListener(new MouseAdapter() {
         * 
         * @Override
         * public void mousePressed(MouseEvent e) {
         * if (String.valueOf(view.getTxtApellido().getText()).equals("Apellido")) {
         * view.getTxtApellido().setText("");
         * view.getTxtApellido().setForeground(new Color(0, 0, 0));
         * }
         * 
         * if (String.valueOf(view.getTxtCorreo().getText()).isEmpty()) {
         * view.getTxtCorreo().setText("example@gmail.com");
         * view.getTxtApellido().setForeground(new Color(170, 170, 170));
         * }
         * }
         * 
         * });
         */
    }
    
    
    private void traerCredenDocent() throws Exception {
        user = new UsuarioDAO().buscarUsuario(docenteModif.getIdDocente(), "id_docente");

        viewModifCreden.getTxtUsuario().setEnabled(false);
        viewModifCreden.getTxtUsuario().setText(user.getUsername());
        viewModifCreden.getTxtContraseña().setText("cambie contraseña");
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

    public void traerDatosD(Docente d) {
        docenteModif = d;
        view.getTxtDui().setEditable(false);
        view.getTxtDui().setText(d.getDui());
        view.getTxtNombre().setText(d.getNombre());
        view.getTxtApellido().setText(d.getApellido());
        view.getTxtCorreo().setText(d.getCorreo());
        view.getTxtTelefono().setText(d.getTelefono());
        Date fechaNac = java.sql.Date.valueOf(d.getFechaNacimiento());
        view.getSpnFechaNacimiento().setValue(fechaNac);
        view.getCbTipoContrato().setSelectedItem(d.getTipoContrato());
        view.getCbEspecialidad().setSelectedItem(d.getEspecialidad());
        view.getCbGradoAcademico().setSelectedItem(d.getGradoAcademico());
    }

    /*
     * private int idDocente;
     * private String dui;
     * private String nombre;
     * private String apellido;
     * private String correo;
     * private String telefono;
     * private Date fechaNacimiento;
     * private String tipoContrato;
     * private String especialidad;
     * private String gradoAcademico;
     */

    public void guardarDatosD() {
        String nombre;
        String apellido;
        String correo;
        String telefono;
        Date fecha;
        LocalDate fechaNacimiento;
        String tipoContrato;
        String especialidad;
        String gradoAcademico;

        boolean modificado;

        int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro de modificar los datos?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                nombre = view.getTxtNombre().getText();
                apellido = view.getTxtApellido().getText();
                correo = view.getTxtCorreo().getText();
                telefono = view.getTxtTelefono().getText();
                fecha = (Date) view.getSpnFechaNacimiento().getValue();
                fechaNacimiento = new java.sql.Date(fecha.getTime()).toLocalDate();
                java.util.Date utilDate = (java.util.Date) view.getSpnFechaNacimiento().getValue();

                LocalDate fechaSeleccionada = utilDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                tipoContrato = view.getCbTipoContrato().getSelectedItem().toString();
                especialidad = view.getCbEspecialidad().getSelectedItem().toString();
                gradoAcademico = view.getCbGradoAcademico().getSelectedItem().toString();

                docenteModif.setNombre(nombre);
                docenteModif.setApellido(apellido);
                docenteModif.setCorreo(correo);
                docenteModif.setTelefono(telefono);
                docenteModif.setFechaNacimiento(fechaSeleccionada);
                docenteModif.setTipoContrato(tipoContrato);
                docenteModif.setEspecialidad(especialidad);
                docenteModif.setGradoAcademico(gradoAcademico);

                modificado = new DocenteDAO().modificarDatos(docenteModif);

                if (modificado) {
                    JOptionPane.showMessageDialog(null, "Se modificaron los datos del docente");
                    view.setVisible(false);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ingrese datos validos");
                e.printStackTrace();
            }
        }
    }
}
