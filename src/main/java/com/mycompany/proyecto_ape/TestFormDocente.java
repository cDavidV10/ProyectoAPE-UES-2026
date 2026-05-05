/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_ape;

import controlador.CtrlDocente;
import vista.DocentePrincipalView;
import vista.FormDocente;

/**
 *
 * @author Yonathan
 */
public class TestFormDocente {
    public static void main(String[] args) {
        DocentePrincipalView vistaPrincipal = new DocentePrincipalView();
        CtrlDocente controlador = new CtrlDocente(vistaPrincipal);
        vistaPrincipal.revalidate();
        vistaPrincipal.repaint();
    }
}
