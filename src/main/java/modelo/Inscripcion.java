package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Inscripcion {
    private int id;
    private Date fechaInscripcion;
    private String estado;
    // ? Clases Relacionadas
    private Estudiante estudiante;
    private InicioCurso inicioCurso;
    private PeriodoInscripcion periodoInscripcion;
    private ArrayList<Evaluacion> evaluaciones;

    public Inscripcion() {
    }

    public Inscripcion(int id, Date fechaInscripcion, String estado) {
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

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
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

    public PeriodoInscripcion getPeriodoInscripcion() {
        return periodoInscripcion;
    }

    public void setPeriodoInscripcion(PeriodoInscripcion periodoInscripcion) {
        this.periodoInscripcion = periodoInscripcion;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    
}
