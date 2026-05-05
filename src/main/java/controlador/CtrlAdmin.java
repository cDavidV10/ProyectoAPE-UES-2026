/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import funciones.Paneles;
import vista.AdminView;
import vista.DocentePrincipalView;

/**
 *
 * @author cdavi
 */
public class CtrlAdmin {
    AdminView adminView;

    public CtrlAdmin(AdminView adminView) {
        this.adminView = adminView;

        adminView.getBtnDocente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DocentePrincipalView vistaPrincipal = new DocentePrincipalView();

                CtrlDocente controlador = new CtrlDocente(vistaPrincipal);
                new Paneles().insertarPaneles(vistaPrincipal, adminView.getBgPanel());
            }

        });

    }

}
