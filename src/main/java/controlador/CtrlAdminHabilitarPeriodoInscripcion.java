package controlador;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JOptionPane;

import dao.PeriodoInscripcionDAO;
import modelo.PeriodoInscripcion;
import modelo.Usuario;
import vista.HabilitarInscripcionView;

public class CtrlAdminHabilitarPeriodoInscripcion {

    private HabilitarInscripcionView habilitarInscripcionView;
    private PeriodoInscripcionDAO inscripcionDAO;
    private Usuario user;
    private String accion;
    private PeriodoInscripcion periodoInscripcion;

    public CtrlAdminHabilitarPeriodoInscripcion(HabilitarInscripcionView habilitarInscripcionView, Usuario user,
            String accion) {
        this.habilitarInscripcionView = habilitarInscripcionView;
        this.user = user;
        this.accion = accion;
        this.inscripcionDAO = new PeriodoInscripcionDAO();

        if (accion.equalsIgnoreCase("Actualizar")) {
            habilitarInscripcionView.getBtnGuardar().setText("Actualizar");

            try {

                this.periodoInscripcion = inscripcionDAO.periodoActivo();

                Date fechaApertura = Date.from(
                        periodoInscripcion.getFechaApertura()
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant());

                Date fechaCierre = Date.from(
                        periodoInscripcion.getFechaCierre()
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant());

                habilitarInscripcionView.getJcInicio().setDate(fechaApertura);
                habilitarInscripcionView.getJcFinal().setDate(fechaCierre);
                habilitarInscripcionView.getJcInicio().setEnabled(false);
            } catch (Exception e) {
                // TODO: handle exception
            }

        }

        habilitarInscripcionView.getBtnGuardar().addActionListener(e -> {

            if (accion.equalsIgnoreCase("Agregar")) {

                guardar();
            }

            if (accion.equalsIgnoreCase("Actualizar")) {

                actualizar();
            }

        });
    }

    private void guardar() {

        this.periodoInscripcion = llenarObjeto();

        if (!validarFechas(periodoInscripcion)) {
            return;
        }

        try {
            inscripcionDAO.insertar(user.getUsername(), periodoInscripcion);
            JOptionPane.showMessageDialog(null, "Periodo Habilitado correctamente", "Operacion Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pude realizar la accion", "Operacion Fallida",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }

    }

    private void actualizar() {
        this.periodoInscripcion = llenarObjeto();

        if (!validarFechas(periodoInscripcion)) {
            return;
        }

        try {
            inscripcionDAO.actualizar(periodoInscripcion);
            JOptionPane.showMessageDialog(null, "Periodo actualizado correctamente", "Operacion Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pude realizar la accion", "Operacion Fallida",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }

    }

    private PeriodoInscripcion llenarObjeto() {
        java.util.Date fechaInicioSelect = habilitarInscripcionView.getJcInicio().getDate();
        LocalDate fechaInicio = fechaInicioSelect.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        java.util.Date fechaFinSelect = habilitarInscripcionView.getJcFinal().getDate();
        LocalDate fechaFinal = fechaFinSelect.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        this.periodoInscripcion.setFechaApertura(fechaInicio);
        this.periodoInscripcion.setFechaCierre(fechaFinal);

        return periodoInscripcion;
    }

    private boolean validarFechas(PeriodoInscripcion periodoInscripcion) {
        if (periodoInscripcion.getFechaApertura().isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "La fecha de apertura no puede iniciar antes del dia actual", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (periodoInscripcion.getFechaCierre().isBefore(periodoInscripcion.getFechaApertura())) {
            JOptionPane.showMessageDialog(null, "La fecha de cierre no puede ser antes de la fecha de apertura",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

}
