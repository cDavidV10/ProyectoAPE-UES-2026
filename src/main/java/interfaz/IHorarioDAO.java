package interfaz;

import java.util.List;

import modelo.Aula;
import modelo.Horario;

public interface IHorarioDAO {
    public List<Horario> listar() throws Exception;

    public void insertar(Horario horario) throws Exception;

    public List<Aula> listarAulas() throws Exception;
}
