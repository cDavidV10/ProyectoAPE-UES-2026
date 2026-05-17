package controlador;

import funciones.AbiriReporte;
import vista.VistaEstudiantesRegistrados;

public class CtrlRegistroEstudiantes {
    VistaEstudiantesRegistrados estudiantesView;

    public CtrlRegistroEstudiantes(VistaEstudiantesRegistrados estudiantesView) {
        this.estudiantesView = estudiantesView;

        estudiantesView.getBtnReporteEstudiante().addActionListener(e -> {
            new AbiriReporte().abrirReporte("repEstudiante.jasper");
        });
    }

}
