package modelo;

import java.util.ArrayList;
import java.time.LocalDate;

public class Inscripcion {
    private int id;
    private LocalDate fechaInscripcion;
    private String estado;

    // Clases Relacionadas
    private Estudiante estudiante;
    private InicioCurso inicioCurso;

    private ArrayList<Evaluacion> evaluaciones;

    public Inscripcion() {
    }

    public Inscripcion(int id, LocalDate fechaInscripcion, String estado) {
        this.id = id;
        this.fechaInscripcion = fechaInscripcion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public InicioCurso getInicioCurso() {
        return inicioCurso;
    }

    public void setInicioCurso(InicioCurso inicioCurso) {
        this.inicioCurso = inicioCurso;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

}
