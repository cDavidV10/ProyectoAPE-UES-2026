package controlador;

import funciones.UsuarioActivo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import modelo.Usuario;
import vista.EstudianteView;

public class CtrlEstudianteView {
    private EstudianteView estudianteView;
    private Usuario usuario;

    public CtrlEstudianteView(EstudianteView estudianteView, Usuario usuario) {
        this.estudianteView = estudianteView;
        this.usuario = usuario;

        estudianteView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new UsuarioActivo().cambiarLabelUsuario(estudianteView.getTxtUser(), usuario);
            }

        });
    }

}
