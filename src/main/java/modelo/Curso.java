/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author alexi
 */
public class Curso {
    private int idCurso;
    private String Codigo;
    private String nombreCurso;
    private String descripcion;
    private ArrayList<InicioCurso> InicioCurso;

    public Curso() {
    }

    public Curso(int idCurso, String codigo, String nombreCurso, String descripcion) {
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

    public ArrayList<InicioCurso> getInicioCurso() {
        return InicioCurso;
    }

    public void setInicioCurso(ArrayList<InicioCurso> InicioCurso) {
        this.InicioCurso = InicioCurso;
    }

    @Override
    public String toString() {
        return "Cursos{" + "idCurso=" + idCurso + ", descripcion=" + descripcion + ", nombreCurso=" + nombreCurso + '}';
    }

}
