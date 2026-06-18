package controlador;

import javax.swing.JOptionPane;
import modelo.Curso;
import dao.CursosDAO;
import vista.CursosRegistrar;

/**
 * @author alexi
 */
public class CtrlAdminRegistrarCursos {
    private CursosRegistrar vista;
    private CursosDAO dao = new CursosDAO();
    private Curso cursoEditar;
    private CtrlAdminTablaCursos controladorTabla;
    
    public CtrlAdminRegistrarCursos(CursosRegistrar vista, Curso cursoEditar, CtrlAdminTablaCursos controladorTabla) {
        this.vista = vista;
        this.cursoEditar = cursoEditar;
        this.controladorTabla = controladorTabla;
        this.vista.getBtnGuardar().addActionListener(e -> guardar());
        this.vista.getBtnBack().addActionListener(e -> vista.dispose());

        if(cursoEditar!=null) {
            cargarDatos();
        }
    }

    private void cargarDatos() {
        vista.getTxtCodigo().setText(cursoEditar.getCodigo());
        vista.getTxtNombre().setText(cursoEditar.getNombreCurso());
        vista.getAreaDescripcion().setText(cursoEditar.getDescripcion());

        vista.getBtnGuardar().setText("Actualizar");
    }

    private void guardar() {
        try {
            String codigo = vista.getTxtCodigo().getText().trim();
            String nombre = vista.getTxtNombre().getText().trim();
            String descripcion = vista.getAreaDescripcion().getText().trim();

            if(codigo.isEmpty()||nombre.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "El código y el nombre son obligatorios.");
                return;
            }

            Curso c = new Curso();
            c.setCodigo(codigo);
            c.setNombreCurso(nombre);
            c.setDescripcion(descripcion);

            if(cursoEditar != null) {
                //para actualizar
                c.setIdCurso(cursoEditar.getIdCurso());
                dao.actualizar(c);
                JOptionPane.showMessageDialog(vista, "Curso actualizado con exito");
            }else{
                //nuevo
                dao.insertar(c);
                JOptionPane.showMessageDialog(vista, "Curso guardado con exito");
            }
            if(controladorTabla != null) {
                controladorTabla.cargarTabla();
            }
            
            vista.dispose();
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage());
            ex.printStackTrace();
        }
    }
}