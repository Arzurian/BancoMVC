/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

//import appbanco.Cuenta;
//import appbanco.CuentaDao;
//import appbanco.DaoException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Luci_Gnar
 */
public class Banco {

    private String nombre;
    private ArrayList<Cuenta> cuentas;
    private CuentaDao cuentaDao;

    public Banco(String nombre) {
        this.nombre = nombre;
        cuentas = new ArrayList<>();
        this.cuentaDao = cuentaDao;
    }

    /* Banco(String mi_banco, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    /**
     * *GET Y SET**
     */
    public String getNombre() {
        return nombre;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public CuentaDao getCuentaDao() {
        return cuentaDao;
    }

    public void setCuentaDao(CuentaDao cuentaDao) {
        this.cuentaDao = cuentaDao;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * **Buscar cuenta***
     */
    private int buscarCuenta(String codigo) {
        int posicion = -1;
        for (int i = 0; i < cuentas.size() && posicion == -1; i++) {
            if (codigo.equals(cuentas.get(i).getCodigo())) {
                posicion = i;
            }
        }
        return posicion;
    }

    public boolean abrirCuenta(String codigo, String titular, float saldo) {
        Cuenta cuenta = null;
        boolean abierto = false;
        /*
            if(buscarCuenta(codigo)==-1){
                cuentas.add(new Cuenta(codigo,titular,saldo));
                abierto=true;
               }*/
        cuenta = new Cuenta(codigo, titular, saldo);
        if (!cuentas.contains(cuenta)) {
            cuentas.add(cuenta);
            abierto = true;
        }

        return abierto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(nombre);
        for (Cuenta c : cuentas) {
            sb.append(c.toString());
            sb.append('\n');
        }
        return sb.toString();
    }

    public float getTotalDepositos() {
        float acumulador = 0;

        for (Cuenta c : cuentas) {
            acumulador += c.getSaldo();
        }
        return acumulador;
    }

    public boolean cancelarCuenta(String codigo) {
        boolean cancelada = false;

        int posicion = buscarCuenta(codigo);

        if (posicion != -1) { // Si La posicion es distinta de -1,es que hemos encontrado la cuenta que queremos borrar
            cuentas.remove(posicion);
            cancelada = true;
        }
        return cancelada;
    }

    public Cuenta getCuenta(String codigo) {
        Cuenta cuenta = null;
        int posicion;
        posicion = cuentas.indexOf(new Cuenta(codigo, "", 0));
        if (posicion != -1) {
            cuenta = cuentas.get(posicion);
        }

        return cuenta;
    }

    public int guardarCuenta() throws DaoException {
        return cuentaDao.insertar(cuentas);

    }

    public int cargarCuenta() throws DaoException {
        ArrayList<Cuenta> listado = cuentaDao.listar();
        int cont = 0;
        for(Cuenta c: listado){
            if(this.abrirCuenta(c.getCodigo(),c.getTitular(), c.getSaldo())){
            cont++;
            }
        }
        return cont;

    }

}
