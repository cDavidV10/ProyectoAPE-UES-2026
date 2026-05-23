package modelo;

import java.util.ArrayList;

public class Administrador {
    private int id;
    private String dui;
    private String nombre;
    private String apellido;
    // ? Clases Relacionadas
    private Usuario usuario;
    private ArrayList<PeriodoInscripcion> periodoInscripcion;

    public Administrador() {
    }

    public Administrador(int id, String dui, String nombre, String apellido) {
        this.id = id;
        this.dui = dui;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Administrador(int id, String dui, String nombre, String apellido, Usuario usuario) {
        this.id = id;
        this.dui = dui;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<PeriodoInscripcion> getPeriodoInscripcion() {
        return periodoInscripcion;
    }

    public void setPeriodoInscripcion(ArrayList<PeriodoInscripcion> periodoInscripcion) {
        this.periodoInscripcion = periodoInscripcion;
    }
    
    

}
