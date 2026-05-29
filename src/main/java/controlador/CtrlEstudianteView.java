package controlador;

import funciones.UsuarioActivo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import modelo.Usuario;
import vista.EstudianteView;
import vista.Login;

public class CtrlEstudianteView {
    private EstudianteView estudianteView;
    private Usuario usuario;
    private Login login;

    public CtrlEstudianteView(EstudianteView estudianteView, Usuario usuario, Login login) {
        this.estudianteView = estudianteView;
        this.usuario = usuario;
        this.login = login;

        estudianteView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new UsuarioActivo().cambiarLabelUsuario(estudianteView.getTxtUser(), usuario);
            }

        });

        this.estudianteView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                login.setVisible(true);
            }
        });
    }

}
