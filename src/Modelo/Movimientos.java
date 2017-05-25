/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author daw1
 */
public class Movimientos {
    
 private LocalDate fecha;
    private char tipo;
    private float cantidad;
    private float saldo;
    
    public Movimientos(LocalDate fecha, char tipo, float cantidad, float saldo) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.saldo = saldo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public char getTipo() {
        return tipo;
    }

    public float getCantidad() {
        return cantidad;
    }

    public float getSaldo() {
        return saldo;
    }
    
    
    @Override
    public String toString(){
        return fecha+":"+tipo+":"+cantidad+":"+saldo;
    }
}

