package interfaz;

import java.util.List;
import modelo.Estudiante;


public interface IEstudianteDAO {
    
    void insertar(Estudiante e) throws Exception;
    
    void actualizar(Estudiante e) throws Exception;
    
    void eliminar(int idEstudiante) throws Exception;
    
    List<Estudiante> listar() throws Exception;
    
    Estudiante buscar(int idEstudiante) throws Exception;
    
    
}
