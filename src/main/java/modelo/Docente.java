/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

<<<<<<< HEAD
import java.util.ArrayList;
import java.time.LocalDate;
=======
import java.time.LocalDate;
import java.util.ArrayList;
>>>>>>> f156827ddc6fd0f375e5e482803344a079ee6f20

/**
 *
 * @author Yonathan
 */
public class Docente {
    private int idDocente;
    private String dui;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String tipoContrato;
    private String especialidad;
    private String gradoAcademico;
    // ? Clases Relacionadas
    private Usuario usuario;
    private ArrayList<InicioCurso> inicioCursos;

    public Docente() {
    }

    public Docente(int idDocente, String dui, String nombre, String apellido, String correo, String telefono,
            LocalDate fechaNacimiento, String tipoContrato, String especialidad, String gradoAcademico) {
        this.idDocente = idDocente;
        this.dui = dui;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoContrato = tipoContrato;
        this.especialidad = especialidad;
        this.gradoAcademico = gradoAcademico;
    }

    // GETTER

    public int getIdDocente() {
        return idDocente;
    }

    public String getDui() {
        return dui;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    // SETTER

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<InicioCurso> getInicioCursos() {
        return inicioCursos;
    }

    public void setInicioCursos(ArrayList<InicioCurso> inicioCursos) {
        this.inicioCursos = inicioCursos;
    }

<<<<<<< HEAD
    
}
=======
}
>>>>>>> f156827ddc6fd0f375e5e482803344a079ee6f20
