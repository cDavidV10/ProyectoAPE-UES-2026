package controlador;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.RegEstuDAO;
import modelo.ModelRegEstu;

import vista.VistaEstudiantesRegistrados;
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

        vista.btnRegistrar.addActionListener(e -> registrar());

        vista.btnLimpiar.addActionListener(e -> limpiar());
       
        vista.btnCancelar.addActionListener(e -> vista.dispose());
        
        vista.btnRegistrados.addActionListener(e -> VistaEstudiantesRegistrados());
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

    private void VistaEstudiantesRegistrados() {
        VistaEstudiantesRegistrados vistaTabla = new VistaEstudiantesRegistrados();

        cargarTabla(vistaTabla);

        vistaTabla.getBtnRegresar().addActionListener(e -> {
            vistaTabla.dispose();
            vista.setVisible(true);
            
        });

        vista.setVisible(false);
        vistaTabla.setVisible(true);
    }

    private void cargarTabla(VistaEstudiantesRegistrados vistaTabla) {
        DefaultTableModel modelo = (DefaultTableModel) vistaTabla.getTblEstudiantes().getModel();
        modelo.setRowCount(0);

        try {
            List<ModelRegEstu> lista = dao.listar();
            for (ModelRegEstu e : lista) {
                modelo.addRow(new Object[]{
                    e.getIdEstudiante(),
                    e.getNombre(),
                    e.getApellido(),
                    e.getDui(),
                    e.getFechaNacimiento(),
                    e.getCorreo()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vistaTabla,
                "Error al cargar datos: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // private void cancelar() {
    //     vista.dispose();
    // }

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
