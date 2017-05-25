/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appbancomvc;

import Controlador.*;
import Vista.*;
import Modelo.*;

/**
 *
 * @author daw1
 */
public class AppBancoMVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Banco banco = new Banco("Banco");
        IVistaBanco vista = new VistaBanco();
        IControladorBanco controlador = new ControladorBanco(banco, vista);
        vista.setControlador(controlador);
        vista.mostrar();

    }

}
