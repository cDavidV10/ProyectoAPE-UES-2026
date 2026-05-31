package controlador;

import funciones.Paneles;
import funciones.UsuarioActivo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import dao.CursosDisponiblesDAO;
import modelo.Usuario;
import vista.AdministrarCursos;
import vista.CursosDisponiblesView;
import vista.EstudianteView;
import vista.Login;

public class CtrlEstudianteView {

    private EstudianteView estudianteView;
    private Usuario usuario;
    private Login login;
    private Paneles paneles;
    private CursosDisponiblesView cursosDisponiblesView;
    private CtrlCursosDisponibles ctrlCursosDisponibles;

    public CtrlEstudianteView(EstudianteView estudianteView, Usuario usuario, Login login) {
        this.estudianteView = estudianteView;
        this.usuario = usuario;
        this.login = login;
        this.paneles = new Paneles();
        this.cursosDisponiblesView = new CursosDisponiblesView();

        ctrlCursosDisponibles = new CtrlCursosDisponibles(cursosDisponiblesView,
                usuario.getEstudiante().getIdEstudiante());
        paneles.insertarPaneles(cursosDisponiblesView, this.estudianteView.getBgContent());

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

        // Botón para acceder a cursos disponibles
        estudianteView.getBtnCursosDispo().addActionListener(e -> abrirCursosDisponibles());
    }

    private void abrirCursosDisponibles() {

        ctrlCursosDisponibles = new CtrlCursosDisponibles(cursosDisponiblesView,
                usuario.getEstudiante().getIdEstudiante());
        paneles.insertarPaneles(cursosDisponiblesView, this.estudianteView.getBgContent());

    }

}
