package controlador;

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

public class ControladorAdministrarCursos {

    private AdministrarCursos vista;
    private DefaultTableModel modelo;
    private CursosDAO dao = new CursosDAO();

    public ControladorAdministrarCursos(AdministrarCursos vista) {
        this.vista = vista;
        this.modelo = (DefaultTableModel) vista.getTblAdmin().getModel();

        this.vista.getBtnEliminar().addActionListener(e -> eliminar());
        // this.vista.getBtnBack().addActionListener(e -> vista.dispose());

        this.vista.getBtnAgregar().addActionListener(e -> abrirFormulario(null));
        this.vista.getBtnModificar().addActionListener(e -> editar());

        cargarTabla();
    }

    private void cargarTabla() {
        try {
            modelo.setRowCount(0);
            for (Cursos c : dao.listar()) {
                modelo.addRow(new Object[] {
                        c.getIdCurso(),
                        c.getCodigo(),
                        c.getNombreCurso(),
                        c.getDescripcion()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void abrirFormulario(Cursos curso) {
        RegistrarCursos form = new RegistrarCursos();
        new ControladorRegistrarCursos(form, curso, this);
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
            Cursos c = dao.buscar(id);

            abrirFormulario(c);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage());
        }
    }

    private void eliminar() {
        int fila = vista.getTblAdmin().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un curso de la tabla para eliminar.");
            return;
        }

        int id = (int) vista.getTblAdmin().getValueAt(fila, 0);
        String nombre = vista.getTblAdmin().getValueAt(fila, 1).toString();
        String descripcion = vista.getTblAdmin().getValueAt(fila, 2).toString();

        int confirmar = JOptionPane.showConfirmDialog(vista,
                "¿Esta seguro de eliminar el curso: " + nombre + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            try {
                dao.eliminar(id);
                JOptionPane.showMessageDialog(vista, "Curso eliminado.");
                cargarTabla();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Error al eliminar: " + e.getMessage());
            }
        }
    }

    public void recargarTabla() {
        cargarTabla();
    }
}