/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * @author daw1
 */
//Todo esto es para que la tabla se vaya actualizando

public class ClaseTableModel extends AbstractTableModel {

    private String[] columnas = {"Codigo", "Titular", "Saldo"};
    private ArrayList<Cuenta> cuentas;

    public ClaseTableModel(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public Object getValueAt(int fila, int columna) {
        Cuenta c = cuentas.get(fila);
        Object o = null;
        switch (columna) {
            case 0:
                o = c.getCodigo();
                break;
            case 1:
                o = c.getTitular();
                break;
            case 2:
                o = c.getSaldo();
                break;
        }
        return o;
    }

    @Override
    public int getRowCount() {
        return cuentas.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int columna) {
        return columnas[columna];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override //Esto lo que hace es que actualiza la tabla al usar "Operar Cuenta"
    public Class<?> getColumnClass(int columna) {
        Class clase = null;
        switch (columna) {
            case 0:
            case 1:
                clase = String.class;
                break;
            case 2:
                clase = Float.class;
                break;
        }

        return clase;
    }
// https://github.com/Arzurian/BancoMVC.git
}
