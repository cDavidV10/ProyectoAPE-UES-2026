/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.DocenteDAO;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import javax.swing.JOptionPane;
import modelo.Docente;
import vista.FormDocenteModif;

/**
 *
 * @author MINEDUCYT
 */
public class CtrlFormModifDocente {

    private FormDocenteModif view;
    private Docente docenteModif;

    public CtrlFormModifDocente(FormDocenteModif vieew) {
        view = vieew;

        view.getBtnGuardarDocente().addActionListener(e -> {
            guardarDatosD();
        });
        
        view.getBtnCancelarDocente().addActionListener(e -> {
            view.getJpnModifDocente().setVisible(false);
        });

        /*this.view.getTxtNombre().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (String.valueOf(view.getTxtNombre().getText()).equals("Nombre")) {
                    view.getTxtNombre().setText("");
                    view.getTxtNombre().setForeground(new Color(0, 0, 0));
                }

                if (String.valueOf(view.getTxtApellido().getText()).isEmpty()) {
                    view.getTxtApellido().setText("Apellido");
                    view.getTxtApellido().setForeground(new Color(170, 170, 170));
                }
            }

        });
        
        this.view.getTxtApellido().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (String.valueOf(view.getTxtApellido().getText()).equals("Apellido")) {
                    view.getTxtApellido().setText("");
                    view.getTxtApellido().setForeground(new Color(0, 0, 0));
                }

                if (String.valueOf(view.getTxtCorreo().getText()).isEmpty()) {
                    view.getTxtCorreo().setText("example@gmail.com");
                    view.getTxtApellido().setForeground(new Color(170, 170, 170));
                }
            }

        });*/
    }

    public void traerDatosD(Docente d) {
        docenteModif = d;
        view.getTxtDui().setEditable(false);
        view.getTxtDui().setText(d.getDui());
        view.getTxtNombre().setText(d.getNombre());
        view.getTxtApellido().setText(d.getApellido());
        view.getTxtCorreo().setText(d.getCorreo());
        view.getTxtTelefono().setText(d.getTelefono());
        view.getSpnFechaNacimiento().setValue(d.getFechaNacimiento());
        view.getCbTipoContrato().setSelectedItem(d.getTipoContrato());
        view.getCbEspecialidad().setSelectedItem(d.getEspecialidad());
        view.getCbGradoAcademico().setSelectedItem(d.getGradoAcademico());
    }

    /*private int idDocente;
    private String dui;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private Date fechaNacimiento;
    private String tipoContrato;
    private String especialidad;
    private String gradoAcademico;*/

    public void guardarDatosD() {
        String nombre;
        String apellido;
        String correo;
        String telefono;
        Date fechaNacimiento;
        String tipoContrato;
        String especialidad;
        String gradoAcademico;
        
        boolean modificado;
        
        int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro de modificar los datos?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (respuesta == JOptionPane.YES_OPTION){
            try {
                nombre = view.getTxtNombre().getText();
                apellido = view.getTxtApellido().getText();
                correo = view.getTxtCorreo().getText();
                telefono = view.getTxtTelefono().getText();
                fechaNacimiento = new java.sql.Date(view.getSpnFechaNacimiento().getValue().hashCode());
                tipoContrato = view.getCbTipoContrato().getSelectedItem().toString();
                especialidad = view.getCbEspecialidad().getSelectedItem().toString();
                gradoAcademico = view.getCbGradoAcademico().getSelectedItem().toString();
                
                docenteModif.setNombre(nombre);
                docenteModif.setApellido(apellido);
                docenteModif.setCorreo(correo);
                docenteModif.setTelefono(telefono);
                docenteModif.setFechaNacimiento(fechaNacimiento);
                docenteModif.setTipoContrato(tipoContrato);
                docenteModif.setEspecialidad(especialidad);
                docenteModif.setGradoAcademico(gradoAcademico);
                
                modificado = new DocenteDAO().modificarDatos(docenteModif);
                
                if (modificado) {
                    JOptionPane.showMessageDialog(null, "Se modificaron los datos del docente");
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ingrese datos validos");
            }
        }        
    }
}
