package vista;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import modelo.Docente;
import modelo.Horario;

public class CursosHabilitar extends javax.swing.JPanel {

    private static final java.util.logging.Logger logger = java.util.logging.Logger
            .getLogger(CursosHabilitar.class.getName());

    public CursosHabilitar() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        fechaCierre = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        spinCupo = new javax.swing.JSpinner();
        btnCancelar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        cmbDocente = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbHorario = new javax.swing.JComboBox<>();
        btnHorario = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblEspecialidad = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtAula = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Habilitar Curso");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 107, 172, -1));

        jLabel3.setText("Fecha de apertura");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 97, -1));
        add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 210, -1));
        add(fechaCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 290, 210, -1));

        jLabel4.setText("Fecha de cierre");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, -1, -1));

        jLabel5.setText("Cupo maximo");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, -1, -1));
        add(spinCupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 210, -1));

        btnCancelar.setText("Cancelar");
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 533, 100, 60));

        btnAgregar.setText("Agregar");
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 533, 100, 60));

        add(cmbDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 210, -1));

        jLabel6.setText("Docente encargado");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, -1, -1));

        jLabel7.setText("Horario");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, -1));

        add(cmbHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 210, -1));

        btnHorario.setText("Agregar Hora");
        add(btnHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 533, 110, 60));

        jLabel8.setText("Especialidad:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, -1, -1));

        lblEspecialidad.setText("...");
        add(lblEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 210, -1));

        jLabel2.setText("Aula");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 330, -1, -1));

        txtAula.setText("...");
        add(txtAula, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 360, 170, -1));
    }// </editor-fold>//GEN-END:initComponents

    public JDateChooser getFechaCierre() {
        return fechaCierre;
    }

    public JDateChooser getFechaInicio() {
        return fechaInicio;
    }

    public JLabel getLblEspecialidad() {
        return lblEspecialidad;
    }

    public JComboBox<Docente> getCmbDocente() {
        return cmbDocente;
    }

    public JComboBox<Horario> getCmbHorario() {
        return cmbHorario;
    }

    public JSpinner getSpinCupo() {
        return spinCupo;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnHorario() {
        return btnHorario;
    }

    public JLabel getTxtAula() {
        return txtAula;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnHorario;
    private javax.swing.JComboBox<modelo.Docente> cmbDocente;
    private javax.swing.JComboBox<modelo.Horario> cmbHorario;
    public com.toedter.calendar.JDateChooser fechaCierre;
    public com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblEspecialidad;
    private javax.swing.JSpinner spinCupo;
    private javax.swing.JLabel txtAula;
    // End of variables declaration//GEN-END:variables
}