package controlador;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.PeriodoInscripcionDAO;
import vista.HabilitarInscripcionView;
import vista.InscripcionAdminView;
import modelo.InicioCurso;
import modelo.PeriodoInscripcion;
import modelo.Usuario;

public class CtrlAdminInscripcion {
    private InscripcionAdminView inscripcionView;
    private PeriodoInscripcionDAO periodoDAO;
    private Usuario user;

    public CtrlAdminInscripcion(InscripcionAdminView inscripcionView, Usuario user) {
        this.inscripcionView = inscripcionView;
        this.user = user;
        this.periodoDAO = new PeriodoInscripcionDAO();

        mostrarDatos();

        mostrarPeridoActivo();

        inscripcionView.getBtnHabilitar().addActionListener(e -> {

            HabilitarInscripcionView habilitarInscripcionView = new HabilitarInscripcionView(null, false);
            if (inscripcionView.getBtnHabilitar().getText().equalsIgnoreCase("Agregar")) {

                CtrlAdminHabilitarPeriodoInscripcion habilitarPeriodo = new CtrlAdminHabilitarPeriodoInscripcion(
                        habilitarInscripcionView, user, "Agregar");
            }

            if (inscripcionView.getBtnHabilitar().getText().equalsIgnoreCase("Actualizar")) {

                CtrlAdminHabilitarPeriodoInscripcion habilitarPeriodo = new CtrlAdminHabilitarPeriodoInscripcion(
                        habilitarInscripcionView, user, "Actualizar");
            }

            habilitarInscripcionView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    mostrarPeridoActivo();
                }
            });
            habilitarInscripcionView.setLocationRelativeTo(null);
            habilitarInscripcionView.setVisible(true);

        });
    }

    private void mostrarPeridoActivo() {
        try {
            boolean periodoActivo = periodoDAO.existePeriodoActivo();

            if (periodoActivo) {

                PeriodoInscripcion periodoInscripcion = periodoDAO.periodoActivo();
                String periodo = String.format("Del %s Hasta %s",
                        periodoInscripcion.getFechaApertura().toString(),
                        periodoInscripcion.getFechaCierre().toString());
                inscripcionView.getTxtPeriodo().setText(periodo);
                inscripcionView.getBtnHabilitar().setText("Actualizar");
                return;
            }
            inscripcionView.getTxtPeriodo().setText("No Existe");
            inscripcionView.getBtnHabilitar().setText("Agregar");

        } catch (Exception e) {
            // TODO: handle exception
        }
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
