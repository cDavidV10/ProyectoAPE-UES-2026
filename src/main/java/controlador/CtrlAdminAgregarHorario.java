package controlador;

import java.time.LocalTime;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.HorarioDAO;
import modelo.Horario;
import vista.AgregarHorarioView;

public class CtrlAdminAgregarHorario {
    private AgregarHorarioView agregarHorarioView;
    private HorarioDAO horarioDAO;

    public CtrlAdminAgregarHorario(AgregarHorarioView agregarHorarioView) {
        this.agregarHorarioView = agregarHorarioView;
        this.horarioDAO = new HorarioDAO();

        mostrarTabla();

        llenarCbDias();
        llenarCbHoras(this.agregarHorarioView.getCbInicioHoras());
        llenarCbHoras(this.agregarHorarioView.getCbFinalHoras());
        llenarCbMinutos(this.agregarHorarioView.getCbInicioMinutos());
        llenarCbMinutos(this.agregarHorarioView.getCbFinalMinutos());

        this.agregarHorarioView.getBtnAlmacenar().addActionListener(e -> {
            guardar();
            mostrarTabla();
        });

    }

    private void guardar() {
        Horario horario = new Horario();

        horario.setDia(this.agregarHorarioView.getCbDias().getSelectedItem().toString());
        String horaInicial = String.format(
                "%s:%s",
                this.agregarHorarioView.getCbInicioHoras().getSelectedItem().toString(),
                this.agregarHorarioView.getCbInicioMinutos().getSelectedItem().toString());

        String horaFinal = String.format(
                "%s:%s",
                this.agregarHorarioView.getCbFinalHoras().getSelectedItem().toString(),
                this.agregarHorarioView.getCbFinalMinutos().getSelectedItem().toString());

        if (horaInicial.equalsIgnoreCase(horaFinal)) {
            JOptionPane.showMessageDialog(null, "La hora de inicio y final no puede ser la misma", "Error de llenado",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        horario.setHoraInicio(LocalTime.parse(horaInicial));
        horario.setHoraFinal(LocalTime.parse(horaFinal));

        try {
            horarioDAO.insertar(horario);
            JOptionPane.showMessageDialog(null, "Horario Almacenado Correctamente", "Operacion Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();

        String[] titulos = { "Dia", "Inicio", "Final" };
        modelo.setColumnIdentifiers(titulos);

        try {
            List<Horario> datos = horarioDAO.listar();

            datos.forEach(dato -> {
                Object[] obj = {
                        dato.getDia(),
                        dato.getHoraInicio(),
                        dato.getHoraFinal()
                };

                modelo.addRow(obj);
            });

            this.agregarHorarioView.getJtHorarios().setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No hay datos para mostrar", "Error al cargar informacion",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void llenarCbDias() {
        this.agregarHorarioView.getCbDias().removeAllItems();

        String[] dias = { "Lunnes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" };

        for (int i = 0; i < dias.length; i++) {
            this.agregarHorarioView.getCbDias().addItem(dias[i]);
        }
    }

    private void llenarCbHoras(JComboBox<String> comboBox) {
        comboBox.removeAllItems();

        for (int i = 8; i < 18; i++) {
            comboBox.addItem(String.format("%02d", i));
        }
    }

    private void llenarCbMinutos(JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        String[] minutos = { "00", "15", "30", "45" };

        for (int i = 0; i < minutos.length; i++) {
            comboBox.addItem(minutos[i]);
        }
    }
}
