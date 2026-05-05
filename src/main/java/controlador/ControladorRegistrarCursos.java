/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cursos;
import dao.CursosDAO;
import vista.AdministrarCursos;
import vista.RegistrarCursos;

/**
 *
 * @author alexi
 */

public class ControladorRegistrarCursos {

    private RegistrarCursos vista;
    private CursosDAO dao = new CursosDAO();
    private Cursos cursoEditar;
    private ControladorAdministrarCursos controladorTabla;

    public ControladorRegistrarCursos(RegistrarCursos vista, Cursos cursoEditar,
            ControladorAdministrarCursos controladorTabla) {
        this.vista = vista;
        this.cursoEditar = cursoEditar;
        this.controladorTabla = controladorTabla;

        this.vista.getBtnGuardar().addActionListener(e -> guardar());
        this.vista.getBtnBack().addActionListener(e -> vista.dispose());

        if (cursoEditar != null) {
            cargarDatos();
        }
    }

    private void cargarDatos() {
        vista.getTxtNombre().setText(cursoEditar.getNombreCurso());
        vista.getAreaDescripcion().setText(cursoEditar.getDescripcion());
        // vista.getSptCapacidad().setValue(cursoEditar.getCapacidad());
        // vista.getRbtnDisponible().setSelected(cursoEditar.isEstado());

        // vista.getDateFechaInicio().setDate(java.sql.Date.valueOf(cursoEditar.getInicioCurso()));
        // vista.getDateFechaCierre().setDate(java.sql.Date.valueOf(cursoEditar.getCierreCurso()));

        vista.getBtnGuardar().setText("Actualizar");
    }

    private void guardar() {
        /*
         * java.util.Date utilInicio = vista.getDateFechaInicio().getDate();
         * java.util.Date utilCierre = vista.getDateFechaCierre().getDate();
         * 
         * if (utilInicio == null || utilCierre == null) {
         * JOptionPane.showMessageDialog(vista, "Por favor, seleccione ambas fechas.");
         * return;
         * }
         */

        try {
            Cursos c = new Cursos();
            c.setNombreCurso(vista.getTxtNombre().getText().trim());
            c.setDescripcion(vista.getAreaDescripcion().getText().trim());
            // c.setCapacidad((int) vista.getSptCapacidad().getValue());
            // c.setEstado(vista.getRbtnDisponible().isSelected());

            // c.setInicioCurso(utilInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            // c.setCierreCurso(utilCierre.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            if (cursoEditar != null) {
                c.setIdCurso(cursoEditar.getIdCurso());
                dao.actualizar(c);
                JOptionPane.showMessageDialog(vista, "Curso actualizado correctamente");
            } else {
                dao.insertar(c);
                JOptionPane.showMessageDialog(vista, "Curso guardado con éxito");
            }

            controladorTabla.recargarTabla();
            vista.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }
}
