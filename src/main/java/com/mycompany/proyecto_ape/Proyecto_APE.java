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
        Login login = new Login();
        CtrlLogin ctrLogin = new CtrlLogin(login);

        login.setVisible(true);
    }
}
}
/*          VistaRegEstu vista = new VistaRegEstu();
            new ControllerRegEstu(vista);
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);*/
