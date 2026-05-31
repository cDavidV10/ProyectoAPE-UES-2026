package vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CursosDisponiblesView extends javax.swing.JPanel {

    public CursosDisponiblesView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCursosDisponibles = new javax.swing.JTable();
        btnInscribirCurso = new javax.swing.JButton();
        btnVerDetallesCurso = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtFiltrarCurso = new javax.swing.JTextField();
        btnBuscarCurso = new javax.swing.JButton();

        // ✅ SIN setDefaultCloseOperation, sin pack()

        jPanel1.setBackground(new java.awt.Color(0, 42, 120));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("APE VIRTUAL - Catalogo e inscripcion de cursos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 555,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(27, Short.MAX_VALUE)));

        tblCursosDisponibles.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null },
                        { null, null, null, null, null },
                        { null, null, null, null, null },
                        { null, null, null, null, null }
                },
                new String[] {
                        "Codigo", "Nombre", "Docente", "Horario", "Cupos Disponibles"
                }));
        jScrollPane1.setViewportView(tblCursosDisponibles);

        btnInscribirCurso.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnInscribirCurso.setText("INSCRIBIR CURSO SELECCIONADO");

        btnVerDetallesCurso.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnVerDetallesCurso.setText("VER DETALLE DE CURSO SELECCIONADO");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel2.setText("Filtrar por codigo: ");

        btnBuscarCurso.setText("Buscar");

        // ✅ GroupLayout sobre 'this' en lugar de getContentPane()
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnInscribirCurso, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        485, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnVerDetallesCurso, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        517, Short.MAX_VALUE))
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtFiltrarCurso, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(btnBuscarCurso)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtFiltrarCurso, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBuscarCurso))
                                .addGap(27, 27, 27)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnInscribirCurso, javax.swing.GroupLayout.DEFAULT_SIZE, 31,
                                                Short.MAX_VALUE)
                                        .addComponent(btnVerDetallesCurso, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 17, Short.MAX_VALUE)));
    }

    // ✅ Getters se mantienen igual
    public JButton getBtnBuscarCurso() {
        return btnBuscarCurso;
    }

    public JButton getBtnInscribirCurso() {
        return btnInscribirCurso;
    }

    public JButton getBtnVerDetallesCurso() {
        return btnVerDetallesCurso;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public JTable getTblCursosDisponibles() {
        return tblCursosDisponibles;
    }

    public JTextField getTxtFiltrarCurso() {
        return txtFiltrarCurso;
    }

    // Variables declaration
    public javax.swing.JButton btnBuscarCurso;
    public javax.swing.JButton btnInscribirCurso;
    public javax.swing.JButton btnVerDetallesCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tblCursosDisponibles;
    public javax.swing.JTextField txtFiltrarCurso;
}