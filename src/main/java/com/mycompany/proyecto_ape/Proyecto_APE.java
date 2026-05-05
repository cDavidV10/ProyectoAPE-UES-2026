/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto_ape;

import controlador.ControladorAdministrarCursos;
import controlador.ControladorRegistrarCursos;
import vista.AdministrarCursos;
import vista.RegistrarCursos;


import controlador.CtrlLogin;
import vista.Login;

/**
 *
 * @author cdavi
 */
public class Proyecto_APE {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdministrarCursos vista = new AdministrarCursos();
                RegistrarCursos vistaReg = new RegistrarCursos();
                new ControladorAdministrarCursos(vista);
                vista.setVisible(true);
            }
        });
        Login login = new Login();
        CtrlLogin ctrLogin = new CtrlLogin(login);

        login.setVisible(true);
    }
}
