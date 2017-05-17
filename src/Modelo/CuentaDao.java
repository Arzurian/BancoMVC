/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.Cuenta;
import Modelo.DaoException;
import java.util.ArrayList;

/**
 *
 * @author daw1
 */
public interface CuentaDao {
    
    public ArrayList<Cuenta> listar() throws DaoException;
    public  int insertar(ArrayList<Cuenta> cuentas) throws DaoException; 
    
}
