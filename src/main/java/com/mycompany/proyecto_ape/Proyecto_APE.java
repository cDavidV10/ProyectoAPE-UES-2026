/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto_ape;

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
//jel worl
        login.setVisible(true);
    }
}
