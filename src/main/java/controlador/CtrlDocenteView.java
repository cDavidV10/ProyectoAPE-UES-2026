package controlador;

import dao.DocenteCursosDAO;
import funciones.Paneles;
import funciones.UsuarioActivo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import modelo.Docente;
import modelo.Usuario;
import vista.DocenteVerCursosAsignados;
import vista.DocenteView;
import vista.Login;

public class CtrlDocenteView {
    private DocenteView docenteView;
    private Usuario usuario;
    private Login login;

    public CtrlDocenteView(DocenteView docenteView, Usuario usuario, Login login) {
        this.docenteView = docenteView;
        this.usuario = usuario;
        this.login = login;

        docenteView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new UsuarioActivo().cambiarLabelUsuario(docenteView.getTxtUser(), usuario);
            }
        });

        docenteView.getBtnCurso().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DocenteVerCursosAsignados docenteCursosAsign = new DocenteVerCursosAsignados();

                DocenteCursosDAO dao = new DocenteCursosDAO();
                Docente docente = usuario.getDocente();
                new CtrlDocenteVerCursosAsignados(dao, docenteCursosAsign, docente);

                new Paneles().insertarPaneles(docenteCursosAsign, docenteView.getBgPanel());
            }
        });

        this.docenteView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                login.setVisible(true);
            }
        });
    }
}
