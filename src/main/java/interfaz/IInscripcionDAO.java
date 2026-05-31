package interfaz;

import java.util.List;

import modelo.InicioCurso;

public interface IInscripcionDAO {
    public List<InicioCurso> listar() throws Exception;
}
