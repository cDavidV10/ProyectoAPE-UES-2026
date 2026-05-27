package modelo;

import java.time.LocalDate;

public class Horario {
    private int id;
    private String dia;
    private LocalDate horaInicio;
    private LocalDate horaFinal;
    // ? Clases Relaciondas
    private InicioCurso inicioCurso;
    private Aula aula;

    public Horario() {
    }

    public Horario(int id, String dia, LocalDate horaInicio, LocalDate horaFinal) {
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

    public LocalDate getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDate horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDate getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalDate horaFinal) {
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
