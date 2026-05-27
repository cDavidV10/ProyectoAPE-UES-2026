package modelo;

import java.util.ArrayList;
import java.time.LocalDate;

public class PeriodoInscripcion {
    private int id;
    private LocalDate fechaApertura;
    private LocalDate fechaCierre;
    // ? Clases Relacionadas
    private Administrador administrador;
    private ArrayList<Inscripcion> inscripciones;

    public PeriodoInscripcion() {
    }

    public PeriodoInscripcion(int id, LocalDate fechaApertura, LocalDate fechaCierre) {
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
