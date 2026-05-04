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
    int capacidad;
    String nombreCurso;
    String departamento;
    boolean estado;
    LocalDate inicioCurso;
    LocalDate cierreCurso;

    public Cursos() {
    }
    
    public Cursos(int idCurso, String nombreCurso, String departamento, boolean estado, int capacidadMax, LocalDate inicioCurso) {
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.departamento = departamento;
        this.estado = estado;
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

    public LocalDate getInicioCurso() {
        return inicioCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setInicioCurso(LocalDate inicioCurso) {
        this.inicioCurso = inicioCurso;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public LocalDate getCierreCurso() {
        return cierreCurso;
    }

    public void setCierreCurso(LocalDate cierreCurso) {
        this.cierreCurso = cierreCurso;
    }

    @Override
    public String toString() {
        return "Cursos{" + "idCurso=" + idCurso + ", capacidad=" + capacidad + ", nombreCurso=" + nombreCurso + ", departamento=" + departamento + ", estado=" + estado + ", inicioCurso=" + inicioCurso + ", cierreCurso=" + cierreCurso + '}';
    }

    public Object getApellido() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getApellido'");
    }
    
    
    
    
    

    
    
    
}
