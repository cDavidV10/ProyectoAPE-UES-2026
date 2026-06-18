
package controlador;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.CursosDisponiblesDAO;
import vista.CursosDisponiblesView;
import vista.DetallesInscripcionCurso;

public class CtrlCursosDisponibles {

    private final CursosDisponiblesView view;
    private final int idEstudiante;
    private CursosDisponiblesDAO cursosDAO;

    private List<Object[]> cursosLista = new ArrayList<>();

    public CtrlCursosDisponibles(CursosDisponiblesView view, int idEstudiante) {
        this.view = view;
        this.idEstudiante = idEstudiante;
        this.cursosDAO = new CursosDisponiblesDAO();

        iniciarEventos();
        cargarTabla();
    }

    private void iniciarEventos() {
        view.getBtnBuscarCurso().addActionListener(e -> buscar());
        view.getBtnInscribirCurso().addActionListener(e -> inscribir());
        view.getBtnVerDetallesCurso().addActionListener(e -> verDetalles());
    }

    private void cargarTabla() {
        try {
            List<Object[]> lista = cursosDAO.listarDisponibles();
            poblarTabla(lista);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,
                    "Error al cargar cursos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscar() {
        String filtro = view.getTxtFiltrarCurso().getText().trim();
        try {
            List<Object[]> lista;
            if (filtro.isEmpty()) {
                lista = cursosDAO.listarDisponibles();
            } else {
                lista = cursosDAO.buscarPorCodigo(filtro);
            }
            poblarTabla(lista);

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(view,
                        "No se encontraron cursos con ese código.",
                        "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,
                    "Error al buscar: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void poblarTabla(List<Object[]> lista) {
        this.cursosLista = lista;
        DefaultTableModel modelo = (DefaultTableModel) view.getTblCursosDisponibles().getModel();
        modelo.setRowCount(0);
        for (Object[] fila : lista) {
            modelo.addRow(new Object[] {
                    fila[0],
                    fila[1],
                    fila[2],
                    fila[3]
            });
        }
    }

    private void verDetalles() {
        int fila = view.getTblCursosDisponibles().getSelectedRow();

        if (fila < 0) {
            JOptionPane.showMessageDialog(view,
                    "Seleccione un curso de la tabla para ver sus detalles.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombreCurso = cursosLista.get(fila)[1].toString();
        String docente = cursosLista.get(fila)[2].toString();
        int idInicioCurso = (int) cursosLista.get(fila)[4];

        try {
            List<Object[]> horarios = cursosDAO.obtenerHorarios(idInicioCurso);

            DetallesInscripcionCurso dialog = new DetallesInscripcionCurso(null, true);

            new CtrlDetallesInscripcionCurso(dialog, nombreCurso, docente, horarios);

            dialog.setLocationRelativeTo(view);
            dialog.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,
                    "Error al cargar detalles del curso: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inscribir() {
        int fila = view.getTblCursosDisponibles().getSelectedRow();

        if (fila < 0) {
            JOptionPane.showMessageDialog(view,
                    "Seleccione un curso de la tabla para inscribirse.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombreCurso = cursosLista.get(fila)[1].toString();
        String codigoCurso = cursosLista.get(fila)[0].toString();
        int idInicioCurso = (int) cursosLista.get(fila)[4];
        // ==========================================================

        try {
            if (cursosDAO.verificarInscripcion(idEstudiante, idInicioCurso)) {
                JOptionPane.showMessageDialog(view,
                        "Ya estás inscrito en el curso: " + nombreCurso,
                        "Inscripción duplicada", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,
                    "Error al verificar inscripción: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(view,
                "¿Desea inscribirse en el curso:\n" +
                        "  Código: " + codigoCurso + "\n" +
                        "  Nombre: " + nombreCurso + "?",
                "Confirmar inscripción", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            try {
                cursosDAO.inscribir(idEstudiante, idInicioCurso);
                JOptionPane.showMessageDialog(view,
                        "¡Inscripción exitosa en " + nombreCurso + "!",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view,
                        "Error al inscribir: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
