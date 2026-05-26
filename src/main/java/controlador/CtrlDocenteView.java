package controlador;

import funciones.UsuarioActivo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import modelo.Usuario;
import vista.DocenteView;

public class CtrlDocenteView {
    private DocenteView docenteView;
    private Usuario usuario;

    public CtrlDocenteView(DocenteView docenteView, Usuario usuario) {
        this.docenteView = docenteView;
        this.usuario = usuario;

        docenteView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new UsuarioActivo().cambiarLabelUsuario(docenteView.getTxtUser(), usuario);
            }

        });
    }

}
