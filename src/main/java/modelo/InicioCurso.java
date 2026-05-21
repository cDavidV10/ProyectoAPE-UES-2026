/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Yonathan
 */
public class InicioCurso {
    private int idInicioCurso;
    private Date fechaApertura;
    private Date fechaCierre;
    private String cupoMaximo;

    public InicioCurso() {
    }

    public InicioCurso(Integer idInicioCurso, Date fechaApertura, Date fechaCierre, String cupoMaximo) {
        this.idInicioCurso = idInicioCurso;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.cupoMaximo = cupoMaximo;
    }
    
    //SETTER Y GETTER

    public Integer getIdInicioCurso() {
        return idInicioCurso;
    }

    public void setIdInicioCurso(Integer idInicioCurso) {
        this.idInicioCurso = idInicioCurso;
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

    public String getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(String cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }
    
    

}
