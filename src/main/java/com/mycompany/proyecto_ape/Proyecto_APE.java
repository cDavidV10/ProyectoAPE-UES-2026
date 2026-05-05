package com.mycompany.proyecto_ape;

import controlador.ControllerRegEstu;
import vista.VistaRegEstu;
public class Proyecto_APE {

 public static void main(String[] args) {
    try {
        java.awt.EventQueue.invokeLater(() -> {
            VistaRegEstu vista = new VistaRegEstu();
            new ControllerRegEstu(vista);
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
        });
    } catch (Exception ex) {
        ex.printStackTrace(); // Esto imprimirá el error real en la consola de abajo
    }
}
}
