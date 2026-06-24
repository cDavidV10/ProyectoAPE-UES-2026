/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.EstudianteDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Docente;
import modelo.Estudiante;
import modelo.InicioCurso;
import vista.DocenteVerEstudiantesAsignados;

/**
 *
 * @author Yonathan
 */
public class CtrlDocentesVerEstudiantesAsignados {
    private EstudianteDAO dao;
    private DocenteVerEstudiantesAsignados vista;
    private Docente docente;
    private InicioCurso curso;

    public CtrlDocentesVerEstudiantesAsignados(DocenteVerEstudiantesAsignados vista, Docente docente, InicioCurso curso) {
        this.vista = vista;
        this.docente = docente;
        this.curso = curso;
        this.dao = new EstudianteDAO();
       

        cargarEstudiantes();
        onClickRegresar();
    }

    private void cargarEstudiantes() {
        try {
            List<Estudiante> estudiantes = dao.listarEstudiantesPorCurso(curso.getCursos().getCodigo(), docente);
            mostrarEstudiantes(estudiantes);
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(vista, "Error al cargar estudiantes: " + ex.getMessage());
        }
    }

    private void mostrarEstudiantes(List<Estudiante> estudiantes) {
        DefaultTableModel model = (DefaultTableModel) vista.getjTable1().getModel();
        model.setRowCount(0);
        for (Estudiante e : estudiantes) {
            model.addRow(new Object[]{
                e.getNombre(),
                e.getApellido(),
                e.getFechaNacimiento(),
                e.getEdad(),
                e.getCorreo()
            });
        }
    }

    /*
    private void oClicknBuscar() {
        vista.getgetdActionListener(e -> {
            String filtro = vista.getTxtBuscar().getText().trim().toLowerCase();
            try {
                List<Estudiante> estudiantes = dao.listarEstudiantesPorCurso(curso.getCursos().getCodigo(), docente.getIdDocente());
                List<Estudiante> filtrados = estudiantes.stream()
                        .filter(est -> est.getNombre().toLowerCase().contains(filtro)
                                    || est.getApellido().toLowerCase().contains(filtro)
                                    || est.getCorreo().toLowerCase().contains(filtro))
                        .toList();
                mostrarEstudiantes(filtrados);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error en búsqueda: " + ex.getMessage());
            }
        });
    }
*/

    private void onClickRegresar() {
        vista.getBtnRegresar().addActionListener(e -> {
            vista.setVisible(false);
        });
    }
}
