/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.DocenteCursosDAO;
import dao.DocenteDAO;
import funciones.AbiriReporte;
import funciones.Credenciales;
import funciones.Paneles;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import modelo.Docente;
import vista.AdminDocente;
import vista.AdminFormDocente;

/**
 *
 * @author Yonathan
 */
public class CtrlAdminDocente {
    private DocenteDAO dao = new DocenteDAO();
    private AdminDocente vistaPrincipal;
    private Paneles paneles;
    private JPanel bgPanel;

    public CtrlAdminDocente(AdminDocente vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        this.dao = new DocenteDAO();
        this.paneles = new Paneles();
        
        cargarTabla();
        onClickAgregar();
        onClickBuscar();

        vistaPrincipal.getBtnReporte().addActionListener(e -> {
            new AbiriReporte().abrirReporte("/DocentesReporte.jasper");
        });
    }

    private void cargarTabla() {
        try {
            List<Docente> lista = dao.listar();
            DefaultTableModel modelo = vistaPrincipal.getModelo();
            modelo.setRowCount(0);// limpiar

            for (Docente d : lista) {
                modelo.addRow(new Object[] {
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
        vistaPrincipal.getBtnNuevoDocente().addActionListener(e -> {       
            AdminFormDocente formDocente = new AdminFormDocente();
            new CtrlAdminFormularioDocente(formDocente, this.dao, this, vistaPrincipal.getBgPanel());
            paneles.insertarPaneles(formDocente, vistaPrincipal.getBgPanel());
        });
    }

    public void refrescarTabla() {
        cargarTabla();
    }
    
    public void onClickBuscar() {
        vistaPrincipal.getBtnBuscar().addActionListener(e -> {
            String dui = vistaPrincipal.getTxtDuiBuscar().getText().trim();

            if (dui.isEmpty()) {
                JOptionPane.showMessageDialog(vistaPrincipal,
                        "Ingrese un DUI para buscar.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                Docente resultado = dao.buscarPorDui(dui);
                if (resultado == null) {
                    
                    DefaultTableModel model = (DefaultTableModel) vistaPrincipal.getTableDocentes().getModel();
                    model.setRowCount(0);

                    JOptionPane.showMessageDialog(vistaPrincipal,
                            "No se encontró ningún docente con ese DUI.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    mostrarDocente(resultado);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vistaPrincipal,
                        "Error en búsqueda: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    private void mostrarDocente(Docente d) {
        DefaultTableModel model = (DefaultTableModel) vistaPrincipal.getTableDocentes().getModel();
        model.setRowCount(0);
        model.addRow(new Object[]{
            d.getDui(),
            d.getNombre(),
            d.getApellido(),
            d.getCorreo(),
            d.getTelefono(),
            d.getEspecialidad(),
            d.getGradoAcademico()
        });
    }

}