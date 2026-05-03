/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author alexi
 */
public class Cursos {
    int idCurso;
    String nombreCurso;
    String departamento;
    boolean estado;
    int capacidadMax;
    LocalDate inicioCurso;

    public Cursos() {
    }
    
    public Cursos(int idCurso, String nombreCurso, String departamento, boolean estado, int capacidadMax, LocalDate inicioCurso) {
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.departamento = departamento;
        this.estado = estado;
        this.capacidadMax = capacidadMax;
        this.inicioCurso = inicioCurso;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public boolean isEstado() {
        return estado;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public LocalDate getInicioCurso() {
        return inicioCurso;
    }
    
    
}
