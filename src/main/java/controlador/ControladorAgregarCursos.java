package controlador;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cursos;
import dao.CursosDAO;
import vista.AdministrarCursos;

public class ControladorAgregarCursos {
    private List<Cursos> listaCursos;
    private boolean modoEdicion = false;
    private AdministrarCursos vista;
    private DefaultTableModel modelo;
    private CursosDAO dao = new CursosDAO();

    public ControladorAgregarCursos(AdministrarCursos vista) {
        this.vista = vista;
        this.modelo = (DefaultTableModel) vista.getTblAdmin().getModel();

        // Botones
        this.vista.getBtnGuardar().addActionListener(e -> guardar());
        this.vista.getBtnEliminar().addActionListener(e -> eliminar());
        this.vista.getBtnBack().addActionListener(e -> vista.dispose());

        this.vista.getTblAdmin().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarCamposDesdeTabla();
            }
        });
        
        cargarTabla();
    }

    private void cargarTabla() {
        try {
            modelo.setRowCount(0);
            listaCursos = dao.listar();
            for (Cursos c : listaCursos) {
                modelo.addRow(new Object[]{
                    c.getIdCurso(),
                    c.getNombreCurso(),
                    c.isEstado(),
                    c.getCapacidad(),
                    c.getInicioCurso(),
                    c.getCierreCurso()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void guardar() {
        java.util.Date utilInicio = vista.getDateFechaInicio().getDate();
        java.util.Date utilCierre = vista.getDateFechaCierre().getDate();

        if (utilInicio == null || utilCierre == null) {
            JOptionPane.showMessageDialog(vista, "Por favor, seleccione ambas fechas.");
            return;
        }

        try {
            Cursos c = new Cursos();
            c.setNombreCurso(vista.getTxtNombre().getText().trim());
            c.setCapacidad((int) vista.getSptCapacidad().getValue());
            c.setEstado(vista.getRbtnDisponible().isSelected());
            
            c.setInicioCurso(utilInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            c.setCierreCurso(utilCierre.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            if (modoEdicion) {
                int fila = vista.getTblAdmin().getSelectedRow();
                int id = (int) vista.getTblAdmin().getValueAt(fila, 0);
                c.setIdCurso(id);
                dao.actualizar(c);
                JOptionPane.showMessageDialog(vista, "Curso actualizado correctamente");
            } else {
                dao.insertar(c);
                JOptionPane.showMessageDialog(vista, "Curso guardado con éxito");
            }

            cargarTabla();
            limpiar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al procesar: " + ex.getMessage());
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

        int confirmar = JOptionPane.showConfirmDialog(vista, 
            "¿Está seguro de eliminar el curso: " + nombre + "?", 
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            try {
                dao.eliminar(id);
                JOptionPane.showMessageDialog(vista, "Curso eliminado.");
                cargarTabla();
                limpiar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Error al eliminar: " + e.getMessage());
            }
        }
    }

    private void cargarCamposDesdeTabla() {
        int row = vista.getTblAdmin().getSelectedRow();
        if (row >= 0) {
            vista.getTxtNombre().setText(modelo.getValueAt(row, 1).toString());
            vista.getRbtnDisponible().setSelected((boolean) modelo.getValueAt(row, 2));
            vista.getSptCapacidad().setValue((int) modelo.getValueAt(row, 3));
            
            LocalDate fechaI = (LocalDate) modelo.getValueAt(row, 4);
            LocalDate fechaC = (LocalDate) modelo.getValueAt(row, 5);
            
            vista.getDateFechaInicio().setDate(java.sql.Date.valueOf(fechaI));
            vista.getDateFechaCierre().setDate(java.sql.Date.valueOf(fechaC));

            modoEdicion = true;
            vista.getBtnGuardar().setText("Actualizar");
        }
    }

    private void limpiar() {
        vista.getTxtNombre().setText("");
        vista.getSptCapacidad().setValue(0);
        vista.getRbtnDisponible().setSelected(false);
        vista.getDateFechaInicio().setDate(null);
        vista.getDateFechaCierre().setDate(null);
        vista.getTblAdmin().clearSelection();
        modoEdicion = false;
        vista.getBtnGuardar().setText("Guardar");
    }
}