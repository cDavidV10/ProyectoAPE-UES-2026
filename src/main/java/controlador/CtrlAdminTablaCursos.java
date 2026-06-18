package controlador;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import modelo.Curso;
import dao.CursosDAO;
import funciones.Paneles;
import vista.AgregarHorarioNuevo;
import vista.CursosHabilitar;
import vista.CursosTablaTodos;
import vista.CursosRegistrar;
import vista.CursosTablaHabilitados;

import funciones.AbiriReporte; 

/**
 *
 * @author alexi
 */

public class CtrlAdminTablaCursos {
    private CursosTablaTodos vista;
    private CursosHabilitar vistaH;
    private DefaultTableModel modelo;
    private CursosDAO dao = new CursosDAO();
    private JPanel bgContent;
    private Paneles paneles;

    public CtrlAdminTablaCursos(CursosTablaTodos vistaTabla, JPanel bgContent) {
        this.vista = vistaTabla;
        this.vistaH = new CursosHabilitar();
        this.bgContent = bgContent;
        this.paneles = new Paneles();

        //le agregue datos a la vista pq me estaba dando problema de q NULO
        this.modelo = (DefaultTableModel) this.vista.getTblAdmin().getModel();
        this.vista.getBtnEliminar().addActionListener(e -> eliminar());
        //this.vista.getBtnBack().addActionListener(e -> vista.dispose());
        this.vista.getBtnHabilitar().setEnabled(false);
        this.vista.getBtnAgregar().addActionListener(e -> agregador(null));
        this.vista.getBtnModificar().addActionListener(e -> editar());

        //reporte
        this.vista.getBtnReporte().addActionListener(e -> {
            new AbiriReporte().abrirReporte("cursosss.jasper");
        });

        onClickVerHabili();
        cargarTabla();

        this.vista.getTblAdmin().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = vista.getTblAdmin().getSelectedRow();
                vista.getBtnHabilitar().setEnabled(fila != -1);
            }
        });
    }

    private void eliminar(){
        int fila = vista.getTblAdmin().getSelectedRow();
        if(fila<0){
            JOptionPane.showMessageDialog(vista, "no seleccionaste nada");
            return;            
        }
        try{
            String codigo = vista.getTblAdmin().getValueAt(fila, 0).toString();
            Curso curso = dao.buscar(codigo);
            dao.eliminar(curso.getIdCurso());
            cargarTabla();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void habilitar() {
        int fila = vista.getTblAdmin().getSelectedRow();
        String codigo = (String) vista.getTblAdmin().getValueAt(fila, 0);
        String cursoName = (String) vista.getTblAdmin().getValueAt(fila, 1);

        Curso curso = new Curso();
        curso.setCodigo(codigo);
        curso.setNombreCurso(cursoName);
        try {
            if (!dao.cursoActivo(codigo)) {
                CtrlAdminCursosHabilitar ctrlHabilitar = new CtrlAdminCursosHabilitar(vistaH, vista, bgContent);
                paneles.insertarPaneles(vistaH, bgContent);
                return;
            }

            AgregarHorarioNuevo aHorarioNuevo = new AgregarHorarioNuevo();
            paneles.insertarPaneles(aHorarioNuevo, bgContent);
            CtrlAdminCursoHorario ctrcHorario = new CtrlAdminCursoHorario(vista,
                    aHorarioNuevo, bgContent, curso);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void onClickVerHabili() {
        vista.getBtnHabilitar().addActionListener(e -> {
            habilitar();
        });
    }
    /*private void tablaHabilitados() {
        CursosTablaHabilitados vistaTabla = new CursosTablaHabilitados();
        vistaTabla.setVisible(true);
    }*/
    void cargarTabla() {
        try {
            modelo.setRowCount(0);
            for (Curso c : dao.listar()) {
                modelo.addRow(new Object[] {
                        c.getCodigo(),
                        c.getNombreCurso(),
                        c.getDescripcion()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void agregador(Curso curso) {
        CursosRegistrar vista = new CursosRegistrar();
        CtrlAdminRegistrarCursos control = new CtrlAdminRegistrarCursos(vista, curso, this);
        vista.setVisible(true);
    }

    private void editar() {
        int fila= vista.getTblAdmin().getSelectedRow();
        if (fila== -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un curso");
            return;
        }
        try {
            String codigo = vista.getTblAdmin().getValueAt(fila, 0).toString();
            Curso c = dao.buscar(codigo);
            agregador(c);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,e.getMessage());
        }
    }
}