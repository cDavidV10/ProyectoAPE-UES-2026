/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto_ape;

import controlador.ControllerRegEstu;
import vista.VistaRegEstu;


/**
 *
 * @author cdavi
 */
public class Proyecto_APE {

    public static void main(String[] args) {
          java.awt.EventQueue.invokeLater(() -> {
            VistaRegEstu vista = new VistaRegEstu();
            new ControllerRegEstu(vista);
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
        });
    }
}
