package interfaz;

import java.util.List;
import modelo.ModelRegEstu;


public interface IEstudianteDAO {
    
    void insertar(ModelRegEstu e) throws Exception;
    
    void actualizar(ModelRegEstu e) throws Exception;
    
    void eliminar(int idEstudiante) throws Exception;
    
    List<ModelRegEstu> listar() throws Exception;
    
    ModelRegEstu buscar(int idEstudiante) throws Exception;
    
    
}
