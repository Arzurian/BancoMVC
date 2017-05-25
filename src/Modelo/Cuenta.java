/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

//import appbanco.Movimientos;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 *
 * @author Luci_Gnar
 */
public class Cuenta implements Serializable {
   private String codigo;
   private String titular;
   private float saldo;
   private List<Movimientos> movimientos;

    public Cuenta(String codigo, String titular, float saldo) {
        this.codigo = codigo;
        this.titular = titular;
        this.saldo = saldo;
        movimientos=new LinkedList<>();
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitular() {
        return this.titular;
    }

    public List<Movimientos> getMovimientos() {
        return movimientos;
    }
    
    

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public float getSaldo() {
        return this.saldo;
    }
    
    public List<Movimientos> getMovimientos(LocalDate desde, LocalDate hasta){
        LinkedList<Movimientos> lista=null;
        if(desde.compareTo(hasta)<=0){
            lista=new LinkedList<>();   
            for(Movimientos m : movimientos){
                if (m.getFecha().compareTo(desde)>=0 && m.getFecha().compareTo(hasta)<=0){
                    lista.add(m);            
                }
        }
       
        }
         return lista;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
   
    public boolean ingresar(float cantidad){
       boolean ingresado=false;
       if (cantidad > 0 ){
           saldo+=cantidad;
           movimientos.add(new Movimientos(LocalDate.now(),'I',cantidad,saldo));
           ingresado=true;
       }
         return ingresado;  
       }
    
    
    public boolean reintegrar(float cantidad){    
        boolean reintegrado=false;
        if (cantidad > 0){
           if(cantidad <= this.saldo){
                saldo-=cantidad;
                movimientos.add(new Movimientos(LocalDate.now(),'R',-cantidad,saldo));
                reintegrado=true;
            }        
        }
        return reintegrado;
    }
    
    @Override
    public String toString() {
        return codigo+":"+titular+":"+saldo;
    }
    
     public boolean transferirDesde(Cuenta origen, float cantidad){
        boolean transferido = false;
        
        if(cantidad > 0){
            this.saldo+=cantidad;
            movimientos.add(new Movimientos(LocalDate.now(),'T',cantidad,saldo));
            transferido=true;
        }
        return transferido;
    }
     
    
    public boolean transferirHasta(Cuenta destino, float cantidad){
        boolean transferido = false;
        
        if(cantidad <= this.saldo){
            this.saldo-=cantidad;
            movimientos.add(new Movimientos(LocalDate.now(),'T',-cantidad,saldo));
            destino.transferirDesde(this,cantidad);
            transferido=true;
        }
        
        return transferido;
    
    }
    
    public String listaMovimientos(){
        StringBuilder sb=new StringBuilder();
        ListIterator li=movimientos.listIterator();
        
        while (li.hasNext()) {
                sb.append(li.next().toString());
                sb.append("\n");
        }             
        return sb.toString();
    }
    

    

    @Override
    public boolean equals(Object obj) {
        boolean iguales=false;
        
        if (this == obj) {
            iguales=true;
        }
        else{
            if(obj instanceof Cuenta){  //si el objeto es una cuenta
                Cuenta otraCuenta = (Cuenta)obj;
                iguales=otraCuenta.getCodigo().equals(codigo);  
            }
        }
        return iguales;
    }
    
    

    
    
    
}
      

