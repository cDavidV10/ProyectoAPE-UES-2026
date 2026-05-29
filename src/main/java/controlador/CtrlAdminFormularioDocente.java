/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.DocenteDAO;
import funciones.Credenciales;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Docente;
import vista.AdminFormularioDocente;

/**
 *
 * @author Yonathan
 */
public class CtrlAdminFormularioDocente {
    private AdminFormularioDocente vistaForm;
    //private DocenteDAO dao = new DocenteDAO();
    private DocenteDAO dao;
    private CtrlAdminDocente ctrlPrincipal;
    private Credenciales credenciales = new Credenciales();

    public CtrlAdminFormularioDocente(AdminFormularioDocente vistaForm, DocenteDAO dao, CtrlAdminDocente ctrlPrincipal) {
        this.vistaForm = vistaForm;
        this.dao = dao;
        this.ctrlPrincipal = ctrlPrincipal;
        
        aplicarPlaceholder(vistaForm.getTxtDui(), "00000000-0");
        aplicarPlaceholder(vistaForm.getTxtNombre(), "Ej. Carlos Bladimir");
        aplicarPlaceholder(vistaForm.getTxtApellido(), "Ej. Acevedo Aleman");
        aplicarPlaceholder(vistaForm.getTxtCorreo(), "nombre@ejemplo.com");
        aplicarPlaceholder(vistaForm.getTxtTelefono(), "0000-0000");
        
        onClickGuardar();
        onClickCancelar();
                /*
         * onClickModificar();
         * onClickEliminar();
         * onClickBuscar();
         */
    }
    
    private void onClickGuardar() {
        vistaForm.getBtnGuardarDocente().addActionListener(e -> {
              try {
                String dui = vistaForm.getTxtDui().getText().trim();
                String nombre = vistaForm.getTxtNombre().getText().trim();
                String apellido = vistaForm.getTxtApellido().getText().trim();
                String correo = vistaForm.getTxtCorreo().getText().trim();
                String telefono = vistaForm.getTxtTelefono().getText().trim(); 
                
                // Capturar fecha desde JDateChooser
                java.util.Date fechaSeleccionada = vistaForm.getJdcFechaNacimientoDocente().getDate();
                LocalDate fechaNacimiento = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Por si se usa LOCal
         
                String tipoContrato = vistaForm.getCbTipoContrato().getSelectedItem().toString();
                String especialidad = vistaForm.getCbEspecialidad().getSelectedItem().toString();
                String gradoAcademico = vistaForm.getCbGradoAcademico().getSelectedItem().toString();

                Docente docente = new Docente(0, dui, nombre, apellido, correo, telefono,
                        fechaSeleccionada, tipoContrato, especialidad, gradoAcademico);

                // Validar y guardar
                validar(docente);
                
                if (dao.existeDui(docente.getDui())) {
                    JOptionPane.showMessageDialog(null, "El DUI ya está registrado.");
                    return;
                }

                dao.insertar(docente); 
                JOptionPane.showMessageDialog(null, "Docente guardado correctamente");

                //Para las credenmciales 
                credenciales.registrarCredenciales(docente.getNombre(), docente.getApellido(), "Docente",
                    docente.getDui(), docente.getCorreo());
                ctrlPrincipal.refrescarTabla(); 
                
                // Refrescar tabla en la vista principal
                ctrlPrincipal.refrescarTabla();

                // Cerrar formulario
                vistaForm.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
    }

    // Validar los datos digitados
    private void validar(Docente docente) throws Exception {

        // Validar DUI: no vacío y con formato ########-#
        if (docente.getDui() == null || docente.getDui().isEmpty()) {
            throw new Exception("DUI requerido");
        }
        if (!docente.getDui().matches("\\d{8}-\\d")) {
            throw new Exception("Formato de DUI inválido (########-#)");
        }

        // Validar nombre: no vacío y solo letras
        if (docente.getNombre() == null || docente.getNombre().isEmpty()) {
            throw new Exception("Nombre requerido");
        }
        if (!docente.getNombre().matches("[A-Za-zÁÉÍÓÚáéíóúñÑ ]+")) {
            throw new Exception("Nombre inválido (solo letras)");
        }

        // Validar apellido: no vacío y solo letras
        if (docente.getApellido() == null || docente.getApellido().isEmpty()) {
            throw new Exception("Apellido requerido");
        }
        if (!docente.getApellido().matches("[A-Za-zÁÉÍÓÚáéíóúñÑ ]+")) {
            throw new Exception("Apellido inválido (solo letras)");
        }

        // Validar correo: no vacío y con formato válido
        if (docente.getCorreo() == null || docente.getCorreo().isEmpty()) {
            throw new Exception("Correo requerido");
        }
        if (!docente.getCorreo().matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            throw new Exception("Correo inválido");
        }

        // Validar teléfono: no vacío y exactamente 8 dígitos
        if (docente.getTelefono() == null || docente.getTelefono().isEmpty()) {
            throw new Exception("Teléfono requerido");
        }
        if (!docente.getTelefono().matches("\\d{4}-\\d{4}")) {
            throw new Exception("Teléfono inválido (formato requerido: 0000-0000)");
        }

        // Validar fecha de nacimiento: no nula y no futura
        if (docente.getFechaNacimiento() == null) {
            throw new Exception("Fecha de nacimiento requerida");
        }
        if (docente.getFechaNacimiento().after(new java.util.Date())) {
            throw new Exception("Fecha de nacimiento inválida (no puede ser futura)");
        }

        // Validar tipo de contrato: no vacío
        if (docente.getTipoContrato() == null || docente.getTipoContrato().isEmpty()) {
            throw new Exception("Tipo de contrato requerido");
        }

        // Validar especialidad: no vacío
        if (docente.getEspecialidad() == null || docente.getEspecialidad().isEmpty()) {
            throw new Exception("Especialidad requerida");
        }

        // Validar grado académico: no vacío
        if (docente.getGradoAcademico() == null || docente.getGradoAcademico().isEmpty()) {
            throw new Exception("Grado académico requerido");
        }
    }

    private void onClickCancelar(){
        vistaForm.getBtnCancelarDocente().addActionListener(e -> vistaForm.dispose());
    }
   
    private void aplicarPlaceholder(JTextField campo, String placeholder) {
        campo.setText(placeholder);
        campo.setForeground(Color.GRAY);

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText(placeholder);
                    campo.setForeground(Color.GRAY);
                }
            }
        });
    }
}
