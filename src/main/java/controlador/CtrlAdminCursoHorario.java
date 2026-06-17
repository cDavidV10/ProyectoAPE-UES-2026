package controlador;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import dao.CursosDAO;
import funciones.Paneles;
import modelo.Curso;
import modelo.Horario;
import modelo.InicioCurso;
import vista.AgregarHorarioNuevo;
import vista.AgregarHorarioView;
import vista.CursosTablaTodos;

public class CtrlAdminCursoHorario {

    private CursosTablaTodos viewAnterior;
    private AgregarHorarioNuevo aHorarioNuevo;
    private JPanel bgContente;
    private Curso curso;
    private CursosDAO cursosDAO;

    public CtrlAdminCursoHorario(CursosTablaTodos viewAnterior, AgregarHorarioNuevo aHorarioNuevo,
        JPanel bgContente, Curso curso) {
    this.viewAnterior = viewAnterior;
    this.aHorarioNuevo = aHorarioNuevo;
    this.bgContente = bgContente;
    this.curso = curso;
    Paneles paneles = new Paneles();
    this.cursosDAO = new CursosDAO();
    this.aHorarioNuevo.getTxtCurso().setText(curso.getNombreCurso());
    mostrarDatos();

        this.aHorarioNuevo.getBtnAgregar().addActionListener(e -> {
            AgregarHorarioView agregarView = new AgregarHorarioView(null, false);
            new CtrlAdminAgregarHorario(agregarView);

            agregarView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    mostrarDatos();
                }
            });
            agregarView.setVisible(true);
        });

        this.aHorarioNuevo.getBtnRegresar().addActionListener(e -> {
        paneles.insertarPaneles(viewAnterior, bgContente);
    });
}

    private void mostrarDatos() {
        DefaultTableModel modelo = new DefaultTableModel();
        String[] titulos = { "Dia", "Inicio", "Final", "Aula" };
        modelo.setColumnIdentifiers(titulos);

        try {
            List<Horario> info = cursosDAO.infoCurso(curso.getCodigo());

            info.forEach(dato -> {
                String docente = String.format("%s %s",
                        dato.getInicioCurso().getDocente().getNombre(),
                        dato.getInicioCurso().getDocente().getApellido());
                aHorarioNuevo.getTxtDocente().setText(docente);
            });

            info.forEach(dato -> {
                Object[] ob = {
                        dato.getDia(),
                        dato.getHoraInicio(),
                        dato.getHoraFinal(),
                        dato.getAula().getCodigo()
                };

                modelo.addRow(ob);
            });

            aHorarioNuevo.getJtInfo().setModel(modelo);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
