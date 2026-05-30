
package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Estudiante {
    private int idEstudiante;
    private String dui;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String correo;
    // ? Clases Relacionadas
    private Usuario usuario;
    private ArrayList<Inscripcion> inscripciones;

    public Estudiante() {
    }

    public Estudiante(int idEstudiante, String dui, String nombre,
            String apellido, LocalDate fechaNacimiento, String correo) {
        this.idEstudiante = idEstudiante;
        this.dui = dui;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}