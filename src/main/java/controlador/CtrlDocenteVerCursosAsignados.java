/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.DocenteCursosDAO;
import funciones.Paneles;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Docente;
import modelo.InicioCurso;
import vista.DocenteVerCursoDetalle;
import vista.DocenteVerCursosAsignados;

/**
 *
 * @author Yonathan
 */
public class CtrlDocenteVerCursosAsignados {
    private DocenteCursosDAO dao;
    private DocenteVerCursosAsignados vista;
    private Docente docente;
    
    public CtrlDocenteVerCursosAsignados(DocenteCursosDAO dao, DocenteVerCursosAsignados vista, Docente docente) {
        this.dao = dao;
        this.vista = vista;
        this.docente = docente;
        
        vista.getBtnVerDetalles().setEnabled(false);
        cargarCursos(docente);
        habilitarBotonVerDetalles();
        onClickVerDetalles();
    }

    private void cargarCursos(Docente docente) {    
        try {
            List<InicioCurso> cursos = dao.listarCursosxDocente(docente);
            if (cursos.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "El docente no tiene cursos asignados actualmente.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
                vista.getTablaDocentesCursosAsignados().setVisible(false);
                return;
            } else {
                mostrarCursos(cursos);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al cargar cursos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void mostrarCursos(List<InicioCurso> cursos) {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaDocentesCursosAsignados().getModel();
        model.setRowCount(0); // limpiar
        
        for (InicioCurso ic : cursos) {
            model.addRow(new Object[]{
                ic.getCursos().getCodigo(),
                ic.getCursos().getNombreCurso(),
                ic.getCursos().getDescripcion(),
                ic.getFechaApertura(),
                ic.getFechaCierre(),
                ic.getCupoMaximo()
            });
        }
    }
    
    private void habilitarBotonVerDetalles() {
    vista.getTablaDocentesCursosAsignados().getSelectionModel().addListSelectionListener(e -> {
        boolean filaSeleccionada = vista.getTablaDocentesCursosAsignados().getSelectedRow() != -1;
        vista.getBtnVerDetalles().setEnabled(filaSeleccionada);
    });
    }

    private void onClickVerDetalles() {
        vista.getBtnVerDetalles().addActionListener(e -> {
            int fila = vista.getTablaDocentesCursosAsignados().getSelectedRow();
            String codigo = (String) vista.getTablaDocentesCursosAsignados().getValueAt(fila, 0);

            try {
                InicioCurso curso = dao.buscarCursos(codigo, docente);
                DocenteVerCursoDetalle detalleView = new DocenteVerCursoDetalle();

                new CtrlDocenteVerCursoDetalle(detalleView, curso, docente);
                new Paneles().insertarPaneles(detalleView, vista.getBgPanel());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar detalles: " + ex.getMessage());
            }
        }
        );

    vista.getBtnRegresar().addActionListener(e -> {
            vista.setVisible(false);
        });
    }
}
