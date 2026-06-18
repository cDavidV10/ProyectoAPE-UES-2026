package controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import dao.HorarioDAO;
import modelo.Curso;
import modelo.Horario;
import modelo.InicioCurso;
import vista.AsignarHorarioView;

public class CtrlAdminAsignarHoraCurso {

    private AsignarHorarioView ahv;
    private Map<String, Horario> mapaHorarios;
    private HorarioDAO daoHorario;
    private Curso curso;

    public CtrlAdminAsignarHoraCurso(AsignarHorarioView ahv, Curso curso) {
        this.ahv = ahv;
        this.daoHorario = new HorarioDAO();
        this.mapaHorarios = new HashMap<>();
        this.curso = curso;

        cargarCombo();
        mostrarAula();
        
        ahv.getCbHora().addActionListener(e -> mostrarAula());

        ahv.getBtnAgregar().addActionListener(e ->{
            String seleccion = (String) ahv.getCbHora().getSelectedItem();
            Horario horario = mapaHorarios.get(seleccion);

            if (horario == null) {
                JOptionPane.showMessageDialog(null, "Selecciona un horario válido",
                    "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            InicioCurso iCurso = new InicioCurso();
            iCurso.setCursos(curso);
            horario.setInicioCurso(iCurso);

            try {
                daoHorario.insertarNuevoHorario(horario);
                JOptionPane.showMessageDialog(null, "Nuevo horario agregado correctamente",
                    "Exito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            
        });
        
    }

    private void cargarCombo(){
        try {
            ahv.getCbHora().removeAllItems();

            List<Horario> listaHorarios = daoHorario.horariosDisponibles();
            
            for (Horario h : listaHorarios) {
                ahv.getCbHora().addItem(h.toString());
                mapaHorarios.put(h.toString(),h);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

     private void mostrarAula() {

        String seleccion = (String) ahv.getCbHora().getSelectedItem();
        Horario horario = mapaHorarios.get(seleccion);
        if (horario != null) {
            ahv.getTxtAula().setText(horario.getAula().getCodigo());
        }
    }
    
    
}
