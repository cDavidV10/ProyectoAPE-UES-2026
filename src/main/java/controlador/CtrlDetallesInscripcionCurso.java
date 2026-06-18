
package controlador;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import vista.DetallesInscripcionCurso;

/**
 *
 * @author alfar
 */
public class CtrlDetallesInscripcionCurso {

    private DetallesInscripcionCurso vista;

    public CtrlDetallesInscripcionCurso(DetallesInscripcionCurso vista,
            String nombreCurso,
            String docente,
            List<Object[]> horarios) {
        this.vista = vista;
        cargarDatos(nombreCurso, docente, horarios);

        this.vista.btnCerrar.addActionListener(e -> vista.dispose());
    }

    private void cargarDatos(String nombreCurso, String docente, List<Object[]> horarios) {
        // --- Poblar labels ---
        vista.lbNombreCurso.setText(nombreCurso);
        vista.lbNombreDocente.setText(docente);

        // --- Configurar columnas de la tabla de horarios ---
        String[] columnas = { "Día", "Hora Inicio", "Hora Final", "Aula" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla de solo lectura
            }
        };

        // --- Poblar filas ---
        for (Object[] h : horarios) {
            modelo.addRow(new Object[] {
                    h[0], // dia
                    h[1], // hora_inicio
                    h[2], // hora_final
                    h[3] // aula
            });
        }

        vista.tblHorarioCursoDetalle.setModel(modelo);
    }
}
