/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.table.DefaultTableModel;
import modelo.Docente;
import modelo.Horario;
import modelo.InicioCurso;
import vista.DocenteVerCursoDetalle;

/**
 *
 * @author Yonathan
 */
public class CtrlDocenteVerCursoDetalle {
    private DocenteVerCursoDetalle vista;
    private InicioCurso curso;
    private Docente docente;

    public CtrlDocenteVerCursoDetalle(DocenteVerCursoDetalle vista, InicioCurso curso, Docente docente) {
        this.vista = vista;
        this.curso = curso;
        this.docente = docente;
        cargarDatos();
    }

    private void cargarDatos() {
        vista.getLblCodigoCurso().setText(curso.getCursos().getCodigo());
        vista.getLblNombreCurso().setText(curso.getCursos().getNombreCurso());
        vista.getLblDescripcionCurso().setText(curso.getCursos().getDescripcion());
        vista.getLblFechaInicio().setText(curso.getFechaApertura().toString());
        vista.getLblFechaFin().setText(curso.getFechaCierre().toString());
        vista.getLblCuposMaximos().setText(curso.getCupoMaximo());
        vista.getLblTotalEstudiantesInscritos().setText(String.valueOf(curso.getTotalInscritos()));

        DefaultTableModel model = (DefaultTableModel) vista.getTablaHorario().getModel();
        model.setRowCount(0);
        for (Horario h : curso.getHorario()) {
            model.addRow(new Object[]{
                h.getAula().getCodigo(),
                h.getDia(),
                h.getHoraInicio(),
                h.getHoraFinal()
            });
        }
    }
}
