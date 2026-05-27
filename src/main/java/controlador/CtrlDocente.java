/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.DocenteDAO;
import funciones.Credenciales;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Docente;
import vista.DocentePrincipalView;
import vista.FormDocente;

/**
 *
 * @author Yonathan
 */
public class CtrlDocente {
    private DocenteDAO dao = new DocenteDAO();
    private DocentePrincipalView vistaPrincipal;
    private Credenciales credenciales = new Credenciales();

    public CtrlDocente(DocentePrincipalView vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        this.dao = new DocenteDAO();

        cargarTabla();
        onClickAgregar();
        /*
         * onClickModificar();
         * onClickEliminar();
         * onClickBuscar();
         */
    }

    public void onClickAgregar() {
        vistaPrincipal.getBtnNuevoDocente().addActionListener(e -> {

            // Crear el formulario en este momento
            FormDocente formDocente = new FormDocente();

            // Conectar el botón Guardar del formulario
            formDocente.getBtnGuardarDocente().addActionListener(ev -> {

                try {
                    // Capturar datos desde la vista
                    String dui = formDocente.getTxtDui().getText().trim();
                    String nombre = formDocente.getTxtNombre().getText().trim();
                    String apellido = formDocente.getTxtApellido().getText().trim();
                    String correo = formDocente.getTxtCorreo().getText().trim();
                    String telefono = formDocente.getTxtTelefono().getText().trim();

                    java.util.Date utilDate = (java.util.Date) formDocente.getSpnFechaNacimiento().getValue();

                    LocalDate fechaSeleccionada = utilDate.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    String tipoContrato = formDocente.getCbTipoContrato().getSelectedItem().toString();
                    String especialidad = formDocente.getCbEspecialidad().getSelectedItem().toString();
                    String gradoAcademico = formDocente.getCbGradoAcademico().getSelectedItem().toString();

                    // Crear objeto Docente
                    Docente docente = new Docente(0, dui, nombre, apellido, correo, telefono, fechaSeleccionada,
                            tipoContrato, especialidad, gradoAcademico);

                    // Validar y guardar
                    validar(docente);

                    if (dao.existeDui(docente.getDui())) {
                        JOptionPane.showMessageDialog(null, "El DUI ya está registrado, no se puede guardar.");
                        return;
                    }
                    dao.insertar(docente);

                    JOptionPane.showMessageDialog(null, "Docente guardado correctamente");
                    credenciales.registrarCredenciales(docente.getNombre(), docente.getApellido(), "Docente",
                            docente.getDui(), docente.getCorreo());
                    cargarTabla(); // Actualizando la tabla
                    formDocente.dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            });

            // Conectar el botón Cancelar
            formDocente.getBtnCancelarDocente().addActionListener(ev -> formDocente.dispose());

            formDocente.setVisible(true);
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
        if (!docente.getTelefono().matches("\\d{8}")) {
            throw new Exception("Teléfono inválido (8 dígitos)");
        }

        // Validar fecha de nacimiento: no nula y no futura
        if (docente.getFechaNacimiento() == null) {
            throw new Exception("Fecha de nacimiento requerida");
        }
        if (docente.getFechaNacimiento().isAfter(LocalDate.now())) {
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

    private void cargarTabla() {
        try {
            List<Docente> lista = dao.listar();
            vistaPrincipal.mostrarDocentes(lista);// en la JTable
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}