/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.AdministradorDAO;
import dao.DocenteDAO;
import dao.EstudianteDAO;
import funciones.Paneles;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import modelo.Administrador;
import modelo.Docente;
import modelo.Estudiante;
import vista.FormDocenteModif;
import vista.FormModifAdmin;
import vista.VistaCredenciales;
import vista.VistaFormModifEstudiante;

/**
 *
 * @author MINEDUCYT
 */
public class CtrlCredenciales {

    private VistaCredenciales view;
    private VistaFormModifEstudiante viewEstudiante;
    private FormDocenteModif viewFormDocente;
    private FormModifAdmin viewFormAdmin;

    public CtrlCredenciales(VistaCredenciales vieew) {
        view = vieew;
        llenarCombo();
        
        view.getBtnBuscar().addActionListener(e -> {
            Object editar = 1;
            try {
                editar = buscarCoincidencias();
            } catch (Exception ex) {
                System.getLogger(CtrlCredenciales.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            
            
            if (editar instanceof Docente) {
                
                //JOptionPane.showMessageDialog(null, "Se encontró docente");
                viewFormDocente = new FormDocenteModif();
                new CtrlFormModifDocente(viewFormDocente, view).traerDatosD((Docente)editar);
                new Paneles().insertarPaneles(viewFormDocente, view.getJpanelForms(), 600, 500, true);
                
            } else if (editar instanceof Estudiante) {
                
                //JOptionPane.showMessageDialog(null, "Se encontró estudiante");
                viewEstudiante = new VistaFormModifEstudiante();
                new CtrlFormModifEstudiante(viewEstudiante, view).traerDatosE((Estudiante)editar);
                new Paneles().insertarPaneles(viewEstudiante, view.getJpanelForms(), 600, 500, true);
                
            }else if (editar instanceof Administrador){
                
                //JOptionPane.showMessageDialog(null, "Se encontró administrador");
                viewFormAdmin = new FormModifAdmin();
                new CtrlFormModifAdmin(viewFormAdmin, view).traerDatosAdmin((Administrador) editar);
                new Paneles().insertarPaneles(viewFormAdmin, view.getJpanelForms(), 450, 500, true);
            }else if(editar == (Integer)0){
                JOptionPane.showMessageDialog(null, "No se encontró coincidencia");
            }
        });
        
        this.view.getTxtBuscar().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (String.valueOf(view.getTxtBuscar().getText()).equals("Digite DUI")) {
                    view.getTxtBuscar().setText("");
                    view.getTxtBuscar().setForeground(new Color(0, 0, 0));
                }

                if (String.valueOf(view.getCmbFiltrar().getSelectedItem()).compareTo("Buscar...") == 0) {
                    view.getCmbFiltrar().setForeground(new Color(170, 170, 170));
                }
            }

        });
        
        this.view.getCmbFiltrar().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
                if (String.valueOf(view.getCmbFiltrar().getSelectedItem()).compareTo("Buscar...") == 0) {
                    view.getCmbFiltrar().setForeground(new Color(0, 0, 0));
                }
                
                if (String.valueOf(view.getTxtBuscar().getText()).isEmpty()) {
                    view.getTxtBuscar().setText("Digite DUI");
                    view.getTxtBuscar().setForeground(new Color(170, 170, 170));
                }
            }

        });
    }

    public void llenarCombo() {
        view.getCmbFiltrar().removeAllItems();
        view.getCmbFiltrar().addItem("Buscar...");
        view.getCmbFiltrar().addItem("Docente");
        view.getCmbFiltrar().addItem("Estudiante");
        view.getCmbFiltrar().addItem("Administrador");
    }

    public Object buscarCoincidencias() throws Exception {
        String buscar;
        String buscarPor;
        Object result = 1;
        try {
            buscar = view.getTxtBuscar().getText();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Ingrese caracteres");
            return 1;
        }
        buscarPor = view.getCmbFiltrar().getSelectedItem().toString();
        switch (buscarPor) {
            case "Docente": {
                result = new DocenteDAO().buscarRegistro(buscar);
                break;

            }
            case "Estudiante":{
                result = new EstudianteDAO().buscarRegistro(buscar);
                break;
            }
            case "Administrador":{
                result = new AdministradorDAO().buscarRegistro(buscar);
                break;
            }

            default:
                JOptionPane.showMessageDialog(null, "no se seleccionó el rol de la persona");
                break;
        }
        return result;
    }
}
