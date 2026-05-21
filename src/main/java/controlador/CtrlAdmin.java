/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.RegEstuDAO;
import funciones.Paneles;
import funciones.UsuarioActivo;

import java.awt.event.WindowEvent;
import modelo.ModelRegEstu;
import modelo.Usuario;
import vista.AdminView;
import vista.AdministrarCursos;
import vista.DocentePrincipalView;
import vista.VistaEstudiantesRegistrados;

/**
 *
 * @author cdavi
 */
public class CtrlAdmin {
    AdminView adminView;
    RegEstuDAO dao = new RegEstuDAO();
    Usuario usuario;

    public CtrlAdmin(AdminView adminView, Usuario usuario) {
        this.adminView = adminView;
        this.usuario = usuario;

        adminView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new UsuarioActivo().cambiarLabelUsuario(adminView.getTxtUser(), usuario);
            }

        });

        adminView.getBtnDocente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DocentePrincipalView vistaPrincipal = new DocentePrincipalView();

                CtrlDocente controlador = new CtrlDocente(vistaPrincipal);
                new Paneles().insertarPaneles(vistaPrincipal, adminView.getBgPanel());
            }

        });

        adminView.getBtnCurso().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AdministrarCursos administrarCursos = new AdministrarCursos();
                ControladorAdministrarCursos ctrlCursos = new ControladorAdministrarCursos(administrarCursos);
                new Paneles().insertarPaneles(administrarCursos, adminView.getBgPanel());
            }

        });

        adminView.getBtnEstudiante().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VistaEstudiantesRegistrados estudiantesRegistrados = new VistaEstudiantesRegistrados();
                cargarTabla(estudiantesRegistrados);
                new Paneles().insertarPaneles(estudiantesRegistrados, adminView.getBgPanel());
            }

        });

    }

    private void cargarTabla(VistaEstudiantesRegistrados vistaTabla) {
        DefaultTableModel modelo = (DefaultTableModel) vistaTabla.getTblEstudiantes().getModel();
        modelo.setRowCount(0);

        try {
            List<ModelRegEstu> lista = dao.listar();
            for (ModelRegEstu e : lista) {
                modelo.addRow(new Object[] {
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

}
