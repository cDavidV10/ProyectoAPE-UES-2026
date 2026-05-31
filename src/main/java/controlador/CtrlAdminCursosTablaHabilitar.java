/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.CursoInicioDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import modelo.Curso;
import modelo.InicioCurso;
import vista.CursosHabilitar;
import vista.CursosTablaHabilitados;

/**
 *
 * @author alexi
 */
public class CtrlAdminCursosTablaHabilitar {
    private CursosTablaHabilitados vista;
    private DefaultTableModel modelo;
    private CursoInicioDAO dao = new CursoInicioDAO();
    //private Curso modeloCurso;

    public CtrlAdminCursosTablaHabilitar(CursosTablaHabilitados vista) {
        this.vista = vista;
        this.modelo = (DefaultTableModel) vista.getTblHabilitados().getModel();
        //this.modeloCurso = modeloCurso;
        
        this.vista.getBtnEliminar().addActionListener(e -> eliminar());
        // this.vista.getBtnBack().addActionListener(e -> vista.dispose());

        this.vista.getBtnAgregar2().addActionListener(e -> abrirFormulario(null));
        this.vista.getBtnModificar().addActionListener(e -> editar());
        
        cargarTabla();
    }
    
    private void cargarTabla() {
        try {
            modelo.setRowCount(0);
            
            for (InicioCurso ci : dao.listar()) {
                
                Object idCurso = (ci.getCursos() != null) ? ci.getCursos().getIdCurso() : "";
                Object nombreCurso = (ci.getCursos() != null) ? ci.getCursos().getNombreCurso() : "";
                
                Object idHorario = (ci.getHorario() != null && !ci.getHorario().isEmpty()) ? ci.getHorario().get(0).getId() : "";
                Object horarioStr = (ci.getHorario() != null && !ci.getHorario().isEmpty()) ? ci.getHorario().get(0).toString() : "";
                
                modelo.addRow(new Object[] {
                        idCurso,
                        nombreCurso,
                        idHorario,
                        horarioStr,
                        ci.getFechaApertura(),
                        ci.getFechaCierre(),
                        ci.getCupoMaximo()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar la tabla: " + e.getMessage());
        }
    }
    
    private void abrirFormulario(InicioCurso inicioCurso) {
        CursosHabilitar form = new CursosHabilitar();
        new CtrlAdminCursosHabilitar(form, this.vista);
        form.setVisible(true);
    }

    private void editar() {
        int fila = vista.getTblHabilitados().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un curso");
            return;
        }

        try {
            int id = (int) vista.getTblHabilitados().getValueAt(fila, 0);
            InicioCurso ci = dao.buscar(id);

            abrirFormulario(ci);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage());
        }
    }
    
    private void eliminar(){
        int fila = vista.getTblHabilitados().getSelectedRow();
        
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "Seleccione un curso de la tabla para eliminar.");
            return;
        }
        
        int id= (int) vista.getTblHabilitados().getValueAt(fila, 2);
    }
}
