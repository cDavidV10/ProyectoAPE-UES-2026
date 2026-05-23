package modelo;

import java.util.Date;

public class Horario {
    private int id;
    private String dia;
    private Date horaInicio;
    private Date horaFinal;
    // ? Clases Relaciondas
    private InicioCurso inicioCurso;
    private Aula aula;

    public Horario() {
    }

    public Horario(int id, String dia, Date horaInicio, Date horaFinal) {
        this.id = id;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }

    public InicioCurso getInicioCurso() {
        return inicioCurso;
    }

    public void setInicioCurso(InicioCurso inicioCurso) {
        this.inicioCurso = inicioCurso;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }
    
    

}
