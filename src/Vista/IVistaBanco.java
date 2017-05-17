/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.IControladorBanco;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author daw1
 */
public interface IVistaBanco {
    public void setControlador(IControladorBanco controlador);
    public String getCodigo();
    public String getTitular();
    public float getSaldo();
    public String getCodigoCuenta();
    public int getOperacion();
    public float getCantidad();
    public String getArchivoAbrir();
    public String getArchivoGuardar();
    public void mostrarCuentas(Object[][] listado);
    public void mostrarCodigo(String codigo);
    public void mostrarTitular(String titular);
    public void mostrarSaldo(float saldo);
    public void mostrarMensaje(String mensaje);
    public void mostrar();
    public void setModel(AbstractTableModel modelo); //Esto es para meter la tabla que se actualiza sola
}
