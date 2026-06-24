package modelo;

import java.util.ArrayList;
import java.time.LocalDate;

public class PeriodoInscripcion {
    private int id;
    private LocalDate fechaApertura;
    private LocalDate fechaCierre;
    private String estado;

    // Clases Relacionadas
    private Administrador administrador;

    private ArrayList<InicioCurso> inicioCurso;

    public PeriodoInscripcion() {
    }

    public PeriodoInscripcion(int id, LocalDate fechaApertura, LocalDate fechaCierre, String estado) {
        this.id = id;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public ArrayList<InicioCurso> getInicioCurso() {
        return inicioCurso;
    }

    public void setInicioCurso(ArrayList<InicioCurso> inicioCurso) {
        this.inicioCurso = inicioCurso;
    }

}
