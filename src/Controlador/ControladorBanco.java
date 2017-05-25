/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.IVistaBanco;
import Modelo.Banco;
import Modelo.ClaseTableModel;
import Modelo.Cuenta;
import Modelo.CuentaDaoCsv;
import Modelo.DaoException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daw1
 */
public class ControladorBanco implements IControladorBanco {

    private Banco banco;
    private IVistaBanco vista;
    private ClaseTableModel modelo;

    public ControladorBanco(Banco banco, IVistaBanco vista) {
        this.banco = banco;
        this.vista = vista;
        this.modelo = new ClaseTableModel((ArrayList<Cuenta>) banco.getCuentas()); // Esta linea y la de abajo es para que carte el tableModel que hemos creado
        vista.setModel(modelo);
    }

    @Override
    public void abrir() {
        String codigo = vista.getCodigo();
        String titular = vista.getTitular();
        float saldo = vista.getSaldo();
        if (banco.abrirCuenta(codigo, titular, saldo)) {
            vista.mostrarMensaje("Cuenta creada");
            vista.mostrarCodigo(codigo);
            vista.mostrarTitular(titular);
            vista.mostrarSaldo(saldo);
            modelo.fireTableDataChanged();
        } else {
            vista.mostrarMensaje("No se ha podido crear la cuenta");
        }
    }

    @Override
    public void operar() {
        String codigo = vista.getCodigoCuenta();
        float cantidad;
        int opcion = 0;
        Cuenta cuenta = banco.getCuenta(codigo);

        if (cuenta != null) {
            switch (vista.getOperacion()) {
                case 1:
                    cuenta.ingresar(vista.getCantidad());
                    vista.mostrarMensaje("Cantidad ingresada");
                    vista.mostrarCodigo(cuenta.getCodigo());
                    vista.mostrarTitular(cuenta.getTitular());
                    vista.mostrarSaldo(cuenta.getSaldo());
                    modelo.fireTableDataChanged();
                    break;
                case 2:
                    cuenta.reintegrar(vista.getCantidad());
                    vista.mostrarCodigo(cuenta.getCodigo());
                    vista.mostrarTitular(cuenta.getTitular());
                    vista.mostrarSaldo(cuenta.getSaldo());
                    vista.mostrarMensaje("Cantidad retirada correctamente");
                    modelo.fireTableDataChanged();
                    break;
                case 3:
                    vista.mostrarMensaje("Consultar cuenta");
                    vista.mostrarCodigo(cuenta.getCodigo());
                    vista.mostrarTitular(cuenta.getTitular());
                    vista.mostrarSaldo(cuenta.getSaldo());
                    break;
            }
        } else {
            vista.mostrarMensaje("La cuenta no existe");
        }
    }

    @Override
    public void cancelar() {
        String codigo = vista.getCodigoCuenta();
        if (banco.cancelarCuenta(codigo)) {
            vista.mostrarMensaje("Cuenta cancelada");
        } else {
            vista.mostrarMensaje("No se ha podido cancelar la cuenta");
        }
    }

    @Override
    public void listar() {
        List<Cuenta> listado = banco.getCuentas();
        Object[][] matriz = new Object[listado.size()][3];
        int fila = 0;

        for (Cuenta c : listado) {
            matriz[fila][0] = c.getCodigo();
            matriz[fila][1] = c.getTitular();
            matriz[fila][2] = c.getSaldo();
            fila++;
        }

        vista.mostrarCuentas(matriz);

    }

    @Override
    public void guardar() {
        String ruta = vista.getArchivoGuardar();
        if (ruta != null) {
            try {
                banco.setCuentaDao(new CuentaDaoCsv(ruta));
                int numCuentas = banco.guardarCuenta();
                vista.mostrarMensaje(numCuentas + " cuentas guardadas");
            } catch (DaoException ex) {
                vista.mostrarMensaje("Error Dao");
            }
        } else {
            vista.mostrarMensaje("Ruta incorrecta");
        }
    }

    @Override
    public void cargar() {
        String ruta = vista.getArchivoAbrir();
        if (ruta != null) {
            try {
                banco.setCuentaDao(new CuentaDaoCsv(ruta));
                int numCuentas = banco.cargarCuenta();
                vista.mostrarMensaje(numCuentas + " cuentas leidas");
                modelo.fireTableDataChanged();
            } catch (DaoException ex) {
                vista.mostrarMensaje("Error Dao");
            }
        } else {
            vista.mostrarMensaje("Ruta incorrecta");
        }
    }

}
