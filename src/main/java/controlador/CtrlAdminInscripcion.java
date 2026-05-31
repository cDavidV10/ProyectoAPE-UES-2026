package controlador;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.PeriodoInscripcionDAO;
import vista.InscripcionAdminView;
import modelo.InicioCurso;

public class CtrlAdminInscripcion {
    private InscripcionAdminView inscripcionView;
    private PeriodoInscripcionDAO periodoDAO;

    public CtrlAdminInscripcion(InscripcionAdminView inscripcionView) {
        this.inscripcionView = inscripcionView;
        this.periodoDAO = new PeriodoInscripcionDAO();

        mostrarDatos();
    }

    private void mostrarDatos() {
        DefaultTableModel modelo = new DefaultTableModel();
        String[] titulos = { "Codigo", "Nombre", "Docente", "Fecha Apertura", "Fecha Cierre", "Cupos" };
        modelo.setColumnIdentifiers(titulos);

        try {
            List<InicioCurso> datos = periodoDAO.listar();

            datos.forEach(dato -> {
                Object[] obj = {
                        dato.getCursos().getCodigo(),
                        dato.getCursos().getNombreCurso(),
                        dato.getDocente().getNombre() + " " + dato.getDocente().getApellido(),
                        dato.getFechaApertura(),
                        dato.getFechaCierre(),
                        dato.getCupoMaximo()
                };

                modelo.addRow(obj);
            });

            this.inscripcionView.getJtCursos().setModel(modelo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
