/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.CursosDAO;
import dao.CursoInicioDAO;
import dao.DocenteDAO;
import dao.HorarioDAO;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Curso;
import modelo.Docente;
import modelo.Horario;
import modelo.InicioCurso;
import vista.CursosHabilitar;
import vista.CursosTablaHabilitados;


/**
 *
 * @author alexi
 */

public class CtrlAdminCursosHabilitar {
    private CursosHabilitar vistaHabilitar;
    private CursosTablaHabilitados vistaTabla;
    private DefaultTableModel modelo;
    private CursoInicioDAO dao = new CursoInicioDAO();

    public CtrlAdminCursosHabilitar(CursosHabilitar vistaHabilitar, CursosTablaHabilitados vistaTabla) {
        this.vistaHabilitar = vistaHabilitar;
        this.vistaTabla = vistaTabla;
        this.modelo = (DefaultTableModel) vistaTabla.getTblHabilitados().getModel();
        
        cargarCombos();
        
        this.vistaHabilitar.getBtnAgregar().addActionListener(e -> guardar());
        this.vistaHabilitar.getBtnCancelar().addActionListener(e -> vistaHabilitar.dispose());
    }
    
    private void guardar() {
        try {
            if (vistaHabilitar.getCmbCurso().getSelectedIndex() == -1 || 
                vistaHabilitar.getCmbDocente().getSelectedIndex() == -1 || 
                vistaHabilitar.getCmbHorario().getSelectedIndex() == -1) {
                
                JOptionPane.showMessageDialog(vistaHabilitar, "Por favor, seleccione un curso, docente y horario.");
                return;
            }

            InicioCurso ci = new InicioCurso();

            Curso cursoSel = (Curso) vistaHabilitar.getCmbCurso().getSelectedItem();
            Docente docenteSel = (Docente) vistaHabilitar.getCmbDocente().getSelectedItem();
            Horario horarioSel = (Horario) vistaHabilitar.getCmbHorario().getSelectedItem();

            ci.setCursos(cursoSel);
            ci.setDocente(docenteSel);
            
            ArrayList<Horario> listaHorarios = new ArrayList<>();
            listaHorarios.add(horarioSel);
            ci.setHorario(listaHorarios);

            ci.setFechaApertura(vistaHabilitar.getFechaInicio().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());        
            ci.setFechaCierre(vistaHabilitar.getFechaCierre().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            
            ci.setCupoMaximo(String.valueOf(vistaHabilitar.getSpinCupo().getValue()));
            
            dao.insertar(ci);
            JOptionPane.showMessageDialog(vistaHabilitar, "Curso guardado con éxito");
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(vistaHabilitar, "Error: " + e.getMessage());
        }
    }
    
    private void cargarCombos() {
        try {
            vistaHabilitar.getCmbCurso().removeAllItems();
            vistaHabilitar.getCmbDocente().removeAllItems();
            vistaHabilitar.getCmbHorario().removeAllItems();

            CursosDAO daoCurso = new CursosDAO();
            DocenteDAO daoDocente = new DocenteDAO();
            HorarioDAO daoHorario = new HorarioDAO();

            List<Curso> listaCursos = daoCurso.listar();
            for (Curso c : listaCursos) {
                vistaHabilitar.getCmbCurso().addItem(c);
            }

            List<Docente> listaDocentes = daoDocente.listar();
            for (Docente d : listaDocentes) {
                vistaHabilitar.getCmbDocente().addItem(d);
            }

            List<Horario> listaHorarios = daoHorario.listar();
            for (Horario h : listaHorarios) {
                vistaHabilitar.getCmbHorario().addItem(h);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaHabilitar, "Error al cargar listas: " + e.getMessage());
        }
    }
}