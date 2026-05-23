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
    String Codigo;
    String nombreCurso;
    String descripcion;

    public Cursos() {
    }

    public Cursos(int idCurso, String codigo, String nombreCurso, String descripcion) {
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.descripcion = descripcion;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Cursos{" + "idCurso=" + idCurso + ", descripcion=" + descripcion + ", nombreCurso=" + nombreCurso + '}';
    }

}
