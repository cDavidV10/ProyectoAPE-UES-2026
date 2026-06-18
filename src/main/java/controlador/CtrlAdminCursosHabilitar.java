package controlador;

import dao.CursoInicioDAO;
import dao.DocenteDAO;
import dao.HorarioDAO;
import funciones.Paneles;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import modelo.Docente;
import modelo.Horario;
import modelo.InicioCurso;
import vista.AgregarHorarioView;
import vista.CursosHabilitar;
import vista.CursosTablaTodos;

public class CtrlAdminCursosHabilitar {
    private CursosHabilitar vistaHabilitar;
    private CursosTablaTodos viewAnterior;
    private JPanel bgContent;
    private CursoInicioDAO dao = new CursoInicioDAO();
    private modelo.Curso cursoSeleccionado;

    public CtrlAdminCursosHabilitar(CursosHabilitar vistaHabilitar, CursosTablaTodos viewAnterior,
            JPanel bgContent, modelo.Curso cursoSeleccionado) {
        this.vistaHabilitar = vistaHabilitar;
        this.viewAnterior = viewAnterior;
        this.bgContent = bgContent;
        this.cursoSeleccionado = cursoSeleccionado;
        Paneles paneles = new Paneles();

        cargarCombos();
        mostrarEspecialidad();
        mostrarAula();

        this.vistaHabilitar.getCmbDocente().addActionListener(e -> mostrarEspecialidad());
        this.vistaHabilitar.getCmbHorario().addActionListener(e -> mostrarAula());

        this.vistaHabilitar.getBtnHorario().addActionListener(e -> {
            AgregarHorarioView agregarHorarioView = new AgregarHorarioView(null, false);
            CtrlAdminAgregarHorario ctrlAgregarHorario = new CtrlAdminAgregarHorario(agregarHorarioView);

            agregarHorarioView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    cargarCombos();
                }
            });
            agregarHorarioView.setVisible(true);
        });

        this.vistaHabilitar.getBtnAgregar().addActionListener(e -> {
            guardar();
        });

        this.vistaHabilitar.getBtnCancelar().addActionListener(e -> {
            paneles.insertarPaneles(viewAnterior, bgContent);
        });
    }

    private void mostrarEspecialidad() {
        Docente docente = (Docente) vistaHabilitar.getCmbDocente().getSelectedItem();
        if (docente != null) {
            vistaHabilitar.getLblEspecialidad().setText(docente.getEspecialidad());
        }
    }

    private void mostrarAula() {
        Horario horario = (Horario) vistaHabilitar.getCmbHorario().getSelectedItem();
        if (horario != null) {
            vistaHabilitar.getTxtAula().setText(horario.getAula().getCodigo());
        }
    }

    private void guardar() {
        try {
            if (vistaHabilitar.getCmbDocente().getSelectedIndex() == -1 ||
                    vistaHabilitar.getCmbHorario().getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(vistaHabilitar, "Por favor, seleccione un curso, docente y horario.");
                return;
            }

            InicioCurso ci = new InicioCurso();

            Docente docenteSel = (Docente) vistaHabilitar.getCmbDocente().getSelectedItem();
            Horario horarioSel = (Horario) vistaHabilitar.getCmbHorario().getSelectedItem();

            ci.setCursos(cursoSeleccionado);
            ci.setDocente(docenteSel);

            ArrayList<Horario> listaHorarios = new ArrayList<>();
            listaHorarios.add(horarioSel);
            ci.setHorario(listaHorarios);

            ci.setFechaApertura(
                    vistaHabilitar.getFechaInicio().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            ci.setFechaCierre(
                    vistaHabilitar.getFechaCierre().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            ci.setCupoMaximo(String.valueOf(vistaHabilitar.getSpinCupo().getValue()));

            dao.insertar(ci);
            JOptionPane.showMessageDialog(vistaHabilitar, "Curso guardado con éxito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaHabilitar, "Error: " + e.getMessage());
        }
    }

    private void cargarCombos() {
        try {
            vistaHabilitar.getCmbDocente().removeAllItems();
            vistaHabilitar.getCmbHorario().removeAllItems();

            DocenteDAO daoDocente = new DocenteDAO();
            HorarioDAO daoHorario = new HorarioDAO();

            List<Docente> listaDocentes = daoDocente.listar();
            for (Docente d : listaDocentes) {
                vistaHabilitar.getCmbDocente().addItem(d);
            }

            List<Horario> listaHorarios = daoHorario.horariosDisponibles();
            for (Horario h : listaHorarios) {
                vistaHabilitar.getCmbHorario().addItem(h);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaHabilitar, "Error al cargar listas: " + e.getMessage());
        }
    }
}