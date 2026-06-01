package interfaz;

import java.util.List;

import modelo.InicioCurso;
import modelo.PeriodoInscripcion;

public interface IInscripcionDAO {
    public void insertar(String username, PeriodoInscripcion periodoInscripcion) throws Exception;

    public void actualizar(PeriodoInscripcion periodoInscripcion) throws Exception;

    public List<InicioCurso> listar() throws Exception;

    public boolean existePeriodoActivo() throws Exception;

    public PeriodoInscripcion periodoActivo() throws Exception;
}
