package controlador;

import dao.DocenteCursosDAO;
import dao.DocenteDAO;
import funciones.Paneles;
import funciones.UsuarioActivo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Usuario;
import vista.DocenteView;
import vista.DocenteVerCursosAsignados;

public class CtrlDocente {
    DocenteView docenteView;
    Usuario usuario;

    public CtrlDocente(DocenteView docenteView, Usuario usuario) {
        this.docenteView = docenteView;
        this.usuario = usuario;

        docenteView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new UsuarioActivo().cambiarLabelUsuario(docenteView.getTxtUser(), usuario);

                // Limpia el panel al inicio
                docenteView.getBgPanel().removeAll();
                docenteView.getBgPanel().revalidate();
                docenteView.getBgPanel().repaint();
            }
        });

        docenteView.getBtnCurso().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DocenteVerCursosAsignados docenteCursosAsign = new DocenteVerCursosAsignados();
                DocenteCursosDAO dao = new DocenteCursosDAO();

                int idDocente = usuario.getDocente().getIdDocente();

                // Crear el controlador pasando el dui
                new CtrlDocenteVerCursosAsignados(dao, docenteCursosAsign, idDocente);
                new Paneles().insertarPaneles(docenteCursosAsign, docenteView.getBgPanel());
            }
        });
    }

    // Validar los datos digitados

}
