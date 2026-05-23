/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Yonathan
 */
public class InicioCurso {
    private Date fechaApertura;
    private Date fechaCierre;
    private String cupoMaximo;
    // ? Clases Relacionadas
    private Curso cursos;
    private Docente docente;
    private ArrayList<Horario> horario;
    private ArrayList<Inscripcion> inscripciones;

    public InicioCurso() {
    }

    public InicioCurso(Date fechaApertura, Date fechaCierre, String cupoMaximo) {
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.cupoMaximo = cupoMaximo;
    }

    // SETTER Y GETTER

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
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

    public ArrayList<Horario> getHorario() {
        return horario;
    }

    public void setHorario(ArrayList<Horario> horario) {
        this.horario = horario;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

}
