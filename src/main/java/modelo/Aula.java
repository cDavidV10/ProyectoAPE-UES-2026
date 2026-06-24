package modelo;

import java.util.ArrayList;

public class Aula {
    private int id;
    private String codigo;
    // ? Clases Relacionadas
    private ArrayList<Horario> horario;

    public Aula() {
    }

    public Aula(int id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Horario> getHorario() {
        return horario;
    }

    public void setHorario(ArrayList<Horario> horario) {
        this.horario = horario;
    }
    
    
}
