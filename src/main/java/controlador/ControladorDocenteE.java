/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.DocenteCursosDAO;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.DocenteCursosAsignados;

/**
 *
 * @author Yonathan
 */
public class ControladorDocenteE {
    private DocenteCursosDAO dao;
    private DocenteCursosAsignados vista;
    private int idDocente;

    public ControladorDocenteE(DocenteCursosDAO dao, DocenteCursosAsignados vista, int idDocente) {
        this.dao = dao;
        this.vista = vista;
        this.idDocente = idDocente;
        cargarCursos(idDocente);
        onClickVerDetalles();
    }

    private void cargarCursos(int idDocente) {
        try {
            List<Object[]> cursos = dao.listarCursosxDocente(idDocente);
            vista.mostrarCursos(cursos);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar cursos: " + ex.getMessage());
        }
    }

    private void onClickVerDetalles() {
        vista.getBtnVerDetalles().addActionListener(e -> {
            int fila = vista.getTablaDocentesCursosAsignados().getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un curso primero");
                return;
            }

            // paara obtener datos de la fila seleccionada
            String codigo = (String) vista.getTablaDocentesCursosAsignados().getValueAt(fila, 0);
            String nombre = (String) vista.getTablaDocentesCursosAsignados().getValueAt(fila, 1);
            String descripcion = (String) vista.getTablaDocentesCursosAsignados().getValueAt(fila, 2);
            Date apertura = (Date) vista.getTablaDocentesCursosAsignados().getValueAt(fila, 3);
            Date cierre = (Date) vista.getTablaDocentesCursosAsignados().getValueAt(fila, 4);
            String cupo = (String) vista.getTablaDocentesCursosAsignados().getValueAt(fila, 5);

            // vista de detalles
            /*
            CursoDetalleView detalleView = new CursoDetalleView();
            detalleView.mostrarDetalle(codigo, nombre, descripcion, apertura, cierre, cupo);
            detalleView.setVisible(true);
            */
        });

        // Botón Regresar
        vista.getBtnRegresar().addActionListener(e -> {
            vista.setVisible(false); 
        });
    }
}
