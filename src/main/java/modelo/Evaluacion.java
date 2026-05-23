package modelo;

import java.util.Date;

public class Evaluacion {
    private int id;
    private String tipo;
    private String descripcion;
    private Date fechaRegistro;
    private float nota;
    private int porcentaje;
    // ? Clases Relacionadas
    private Inscripcion inscripcion;

    public Evaluacion() {
    }

    public Evaluacion(int id, String tipo, Date fechaRegistro, float nota, int porcentaje) {
        this.id = id;
        this.tipo = tipo;
        this.fechaRegistro = fechaRegistro;
        this.nota = nota;
        this.porcentaje = porcentaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }
    
    
}
