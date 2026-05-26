package modelo;

import java.util.ArrayList;
import java.util.Date;

public class PeriodoInscripcion {
    private int id;
    private Date fechaApertura;
    private Date fechaCierre;
    // ? Clases Relacionadas
    private Administrador administrador;
    private ArrayList<Inscripcion> inscripciones;

    public PeriodoInscripcion() {
    }

    public PeriodoInscripcion(int id, Date fechaApertura, Date fechaCierre) {
        this.id = id;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }
    
    
}
