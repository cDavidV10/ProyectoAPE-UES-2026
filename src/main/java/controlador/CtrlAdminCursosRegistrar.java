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
import vista.RegistrarCursos;
import vista.CursosTablaHabilitados;

/**
 *
 * @author alexi
 */

public class CtrlAdminCursosRegistrar {

    private CursosTablaTodos vista;
    private CursosHabilitar vistaH;
    private DefaultTableModel modelo;
    private CursosDAO dao = new CursosDAO();
    private JPanel bgContent;
    private Paneles paneles;

    public CtrlAdminCursosRegistrar(CursosTablaTodos vistaTabla, JPanel bgContent) {
        this.vista = vistaTabla;
        this.vistaH = new CursosHabilitar();
        this.bgContent = bgContent;
        this.paneles = new Paneles();

        // le agregue datos a la vista pq me estaba dando problema de q NULO
        this.modelo = (DefaultTableModel) this.vista.getTblAdmin().getModel();

        // this.vista.getBtnEliminar().addActionListener(e -> eliminar());
        // this.vista.getBtnBack().addActionListener(e -> vista.dispose());
        this.vista.getBtnHabilitar().setEnabled(false);
        this.vista.getBtnVerTablaHabilitados().addActionListener(e -> tablaHabilitados());

        this.vista.getBtnAgregar().addActionListener(e -> abrirFormulario(null));
        this.vista.getBtnModificar().addActionListener(e -> editar());

        onClickVerHabili();
        cargarTabla();

        this.vista.getTblAdmin().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = vista.getTblAdmin().getSelectedRow();

                vista.getBtnHabilitar().setEnabled(fila != -1);
            }
        });
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

    private void tablaHabilitados() {
        CursosTablaHabilitados vistaTabla = new CursosTablaHabilitados();
        CtrlAdminCursosTablaHabilitar ctrlHabilitar = new CtrlAdminCursosTablaHabilitar(vistaTabla);
        vistaTabla.setVisible(true);
    }

    private void cargarTabla() {
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

    private void abrirFormulario(Curso curso) {
        RegistrarCursos form = new RegistrarCursos();
        form.setVisible(true);
    }

    private void editar() {
        int fila = vista.getTblAdmin().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un curso");
            return;
        }

        try {
            int id = (int) vista.getTblAdmin().getValueAt(fila, 0);
            Curso c = dao.buscar(id);

            abrirFormulario(c);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage());
        }
    }

    /*
     * private void eliminar() {
     * int fila = vista.getTblAdmin().getSelectedRow();
     * 
     * if (fila == -1) {
     * JOptionPane.showMessageDialog(vista,
     * "Seleccione un curso de la tabla para eliminar.");
     * return;
     * }
     * 
     * int id = (int) vista.getTblAdmin().getValueAt(fila, 0);
     * String nombre = vista.getTblAdmin().getValueAt(fila, 1).toString();
     * String descripcion = vista.getTblAdmin().getValueAt(fila, 2).toString();
     * 
     * int confirmar = JOptionPane.showConfirmDialog(vista,
     * "¿Esta seguro de eliminar el curso: " + nombre + "?",
     * "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
     * 
     * if (confirmar == JOptionPane.YES_OPTION) {
     * try {
     * dao.eliminar(id);
     * JOptionPane.showMessageDialog(vista, "Curso eliminado.");
     * cargarTabla();
     * } catch (Exception e) {
     * JOptionPane.showMessageDialog(vista, "Error al eliminar: " + e.getMessage());
     * }
     * }
     * }
     */

    public void recargarTabla() {
        cargarTabla();
    }
}