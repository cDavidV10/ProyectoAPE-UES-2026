/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.EvaluacionDAO;
import javax.swing.JOptionPane;
import modelo.Evaluacion;
import vista.ViewDocenteApartadoActividades;

/**
 *
 * @author danie
 */
public class CtrlViewAddActividades {
    private ViewDocenteApartadoActividades vista;

    public CtrlViewAddActividades(ViewDocenteApartadoActividades vista) {
        this.vista = vista;
        
        
        this.vista.getBtnGuardar().addActionListener(e ->{
            try {
                crearApartadoActividad();
            } catch (Exception ex) {
                System.getLogger(CtrlViewAddActividades.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
        
        this.vista.getBtnRegresar().addActionListener(e ->{
            vista.setVisible(false);
        });
    }
    
    private void crearApartadoActividad() throws Exception{
        String actividad;
        String descripcion;
        int porcentaje;
        
        actividad = vista.getTxtNameActividad().getText();
        descripcion = vista.getTxtADescripcion().getText();
        porcentaje = Integer.parseInt(vista.getTxtPonderacion().getText());
        
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setTipo(actividad);
        evaluacion.getDescripcion();
        evaluacion.setPorcentaje(porcentaje);
        
        boolean resp = new EvaluacionDAO().registrarEvaluacion(evaluacion);
        
        if (resp){
            JOptionPane.showMessageDialog(null, "Registrado");
        }else{
            JOptionPane.showMessageDialog(null, "Algo salió mal");
        }
    }
}
