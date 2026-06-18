/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    private ArrayList<Horario> horario;
    private PeriodoInscripcion periodoInscripcion;
    private int totalInscritos;
            
    public InicioCurso() {
    }

    public InicioCurso(LocalDate fechaApertura, LocalDate fechaCierre, String cupoMaximo) {
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.cupoMaximo = cupoMaximo;
    }

    public InicioCurso(int idInicioCurso, LocalDate fechaApertura, LocalDate fechaCierre, String cupoMaximo, Curso cursos, Docente docente, ArrayList<Horario> horario, PeriodoInscripcion periodoInscripcion, int totalInscritos) {
        this.idInicioCurso = idInicioCurso;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.cupoMaximo = cupoMaximo;
        this.cursos = cursos;
        this.docente = docente;
        this.horario = horario;
        this.periodoInscripcion = periodoInscripcion;
        this.totalInscritos = totalInscritos;
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

    public int getTotalInscritos() {
        return totalInscritos;
    }

    public void setTotalInscritos(int totalInscritos) {
        this.totalInscritos = totalInscritos;
    }
    
}
