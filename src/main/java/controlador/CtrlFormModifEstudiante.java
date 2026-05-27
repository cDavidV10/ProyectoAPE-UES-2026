/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.EstudianteDAO;
import javax.swing.JOptionPane;
import modelo.Estudiante;
import vista.VistaFormModifEstudiante;

/**
 *
 * @author MINEDUCYT
 */
public class CtrlFormModifEstudiante {
    private VistaFormModifEstudiante viewEstudiante;
    private Estudiante estudModif;
    
    public CtrlFormModifEstudiante(VistaFormModifEstudiante vieew) {
        viewEstudiante = vieew;
        
        viewEstudiante.getBtnGuardar().addActionListener(e ->{
            guardarDatosE();
        });
        viewEstudiante.getBtnCancelar().addActionListener(e -> {
            viewEstudiante.setVisible(false);
        });
    }
    
    public void traerDatosE(Estudiante d) {
        estudModif = d;
        viewEstudiante.setTxtDui(d.getDui());
        viewEstudiante.setTxtNombres(d.getNombre());
        viewEstudiante.setTxtApellidos(d.getApellido());
        viewEstudiante.setJdFechaNaci(new java.sql.Date(d.getFechaNacimiento().getTime()));
        viewEstudiante.setTxtCorreo(d.getCorreo());
    }
    
    private void guardarDatosE(){
        boolean modificado;
        java.util.Date fechaNacimiento;
        
        int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro de modificar los datos?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (respuesta == JOptionPane.YES_OPTION){
            try {
                estudModif.setNombre(viewEstudiante.getTxtNombres().getText());
                estudModif.setApellido(viewEstudiante.getTxtApellidos().getText());
                fechaNacimiento = viewEstudiante.getJdFechaNaci().getDate();
                estudModif.setFechaNacimiento(new java.util.Date(fechaNacimiento.getTime()));
                estudModif.setCorreo(viewEstudiante.getTxtCorreo().getText());

                modificado = new EstudianteDAO().modificarDatos(estudModif);

                if (modificado) {
                    JOptionPane.showMessageDialog(null, "Se modificaron los datos del estdiante");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ingrese datos validos");
            }
        }
    }
}
