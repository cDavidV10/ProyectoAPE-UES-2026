package controlador;

import java.sql.Date;
import java.util.Calendar;

import javax.swing.JOptionPane;

import dao.RegEstuDAO;
import modelo.ModelRegEstu;
import vista.VistaRegEstu;

public class ControllerRegEstu {
    
    private final VistaRegEstu vista;
    private final RegEstuDAO dao;

    public ControllerRegEstu(VistaRegEstu vista) {
        this.vista = vista;
        this.dao = new RegEstuDAO();
        iniciarEventos();
    }

    private void iniciarEventos() {
        vista.btnGenerarId.addActionListener(e -> generarId());
        vista.btnRegistrar.addActionListener(e -> registrar());
        vista.btnLimpiar.addActionListener(e -> limpiar());
        vista.btnCancelar.addActionListener(e -> cancelar());
    }

    private void generarId() {
        try {
            int id = dao.generarId();
            vista.txtIdEstudiante.setText(String.valueOf(id));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                "Error al generar ID: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrar() {
        if (!validarCampos()) return;
        try {
            ModelRegEstu e = new ModelRegEstu();
            e.setDui(vista.txtDui.getText().trim());
            e.setNombre(vista.txtNombre.getText().trim());
            e.setApellido(vista.txtApellido.getText().trim());

            // Convertir fecha del JDateChooser a java.sql.Date
            Calendar cal = Calendar.getInstance();
            cal.setTime(vista.JdFechaNaci.getDate());
            Date fecha = new Date(cal.getTimeInMillis());
            e.setFechaNacimiento(fecha);

            e.setCorreo(vista.txtCorreo.getText().trim());
            dao.insertar(e);
            JOptionPane.showMessageDialog(vista, "Estudiante registrado correctamente.");
            limpiar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista,
                "Error al registrar: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    private void limpiar() {
        vista.txtIdEstudiante.setText("");
        vista.txtDui.setText("");
        vista.txtNombre.setText("");
        vista.txtApellido.setText("");
        vista.JdFechaNaci.setDate(null);
        vista.txtCorreo.setText("");
    }

    private void cancelar() {
        vista.dispose();
    }

    private boolean validarCampos() {
        if (vista.txtNombre.getText().trim().isEmpty()
                || vista.txtApellido.getText().trim().isEmpty()
                || vista.txtDui.getText().trim().isEmpty()
                || vista.txtCorreo.getText().trim().isEmpty()
                || vista.JdFechaNaci.getDate() == null) {
            JOptionPane.showMessageDialog(vista,
                "Todos los campos son obligatorios.",
                "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

}
