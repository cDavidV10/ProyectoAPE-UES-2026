/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.DocenteDAO;
import funciones.Credenciales;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Docente;
import vista.AdminDocente;
import vista.AdminFormularioDocente;

/**
 *
 * @author Yonathan
 */
public class CtrlAdminDocente {
    private DocenteDAO dao = new DocenteDAO();
    private AdminDocente vistaPrincipal;

    public CtrlAdminDocente(AdminDocente vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        this.dao = new DocenteDAO();

        cargarTabla();
        onClickAgregar();
        onClickEliminar();
        
        /*
         * onClickModificar();
         * onClickBuscar();
         */
    }

    private void cargarTabla() {
        try {
            List<Docente> lista = dao.listar();
            DefaultTableModel modelo = vistaPrincipal.getModelo();
            modelo.setRowCount(0);//limpiar
            
            for (Docente d : lista) {
                modelo.addRow(new Object[]{
                    d.getIdDocente(),
                    d.getDui(),
                    d.getNombre(),
                    d.getApellido(),
                    d.getCorreo(),
                    d.getTelefono(),
                    d.getFechaNacimiento(),
                    d.getTipoContrato(),
                    d.getEspecialidad(),
                    d.getGradoAcademico()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void onClickAgregar() {
        vistaPrincipal.getBtnNuevoDocente().addActionListener( e -> {
           AdminFormularioDocente formDocente = new AdminFormularioDocente();
           new CtrlAdminFormularioDocente(formDocente, this.dao, this);
           formDocente.setVisible(true);
        });
    }
    
    public void onClickEliminar() {
        vistaPrincipal.getBtnEliminarDocente().addActionListener(e -> {
            int fila = vistaPrincipal.getTableDocentes().getSelectedRow();
            if (fila == -1) {
                return;
            }

            // Tomar el dui del docente (columna 1)
            String dui = vistaPrincipal.getTableDocentes().getValueAt(fila, 1).toString();
            int confirm = JOptionPane.showConfirmDialog(
                    vistaPrincipal,
                    "¿Está seguro de eliminar este docente?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    dao.eliminar(dui);
                    cargarTabla(); // refrescar la tabla
                    JOptionPane.showMessageDialog(vistaPrincipal, "Docente eliminado correctamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vistaPrincipal, ex.getMessage());
                }
            }
        });
    }

    public void refrescarTabla(){
        cargarTabla();
    }

}
