
package modelo;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author Yonathan
 */
public class InicioCurso {

    private int idInicioCurso;
    private LocalDate fechaApertura;
    private LocalDate fechaCierre;
    private String cupoMaximo;
    // ? Clases Relacionadas
    private Curso cursos;
    private Docente docente;
    private PeriodoInscripcion periodoInscripcion;

    private ArrayList<Horario> horario;
    private ArrayList<Inscripcion> inscripciones;

    public InicioCurso() {
    }

    public InicioCurso(LocalDate fechaApertura, LocalDate fechaCierre, String cupoMaximo) {
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.cupoMaximo = cupoMaximo;
        this.idInicioCurso = idInicioCurso;
    }

    // SETTER Y GETTER

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getCupoMaximo() {
        return cupoMaximo;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setCupoMaximo(String cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public Curso getCursos() {
        return cursos;
    }

    public void setCursos(Curso cursos) {
        this.cursos = cursos;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public int getIdInicioCurso() {
        return idInicioCurso;
    }

    public void setIdInicioCurso(int idInicioCurso) {
        this.idInicioCurso = idInicioCurso;
    }

    public ArrayList<Horario> getHorario() {
        return horario;
    }

    public void setHorario(ArrayList<Horario> horario) {
        this.horario = horario;
    }

    public PeriodoInscripcion getPeriodoInscripcion() {
        return periodoInscripcion;
    }

    public void setPeriodoInscripcion(PeriodoInscripcion periodoInscripcion) {
        this.periodoInscripcion = periodoInscripcion;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

}
