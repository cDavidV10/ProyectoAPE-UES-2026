package funciones;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.swing.JOptionPane;

import conexion.Conexion;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author cdavi
 */
public class AbiriReporte {
    public void abrirReporte(String nombreReporte) {
        try {
            Connection cn = Conexion.getConexion();
            InputStream archivo = getClass().getResourceAsStream("/reportes/" + nombreReporte);
            JasperPrint jp = JasperFillManager.fillReport(archivo, new HashMap<>(), cn);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al abrir reporte\n" + e);
        }
    }
}