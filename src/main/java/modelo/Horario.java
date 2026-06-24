package modelo;

import java.time.LocalTime;

public class Horario {
    private int id;
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    
    private InicioCurso inicioCurso;
    private Aula aula;

    public Horario() {
    }

    public Horario(int id, String dia, LocalTime horaInicio, LocalTime horaFinal) {
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

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
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
    
    @Override
    public String toString() {
        return dia + " (" + horaInicio + " - " + horaFinal + ")";
    }
}