/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.Cuenta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author daw1
 */
public class CuentaDaoCsv implements CuentaDao {
    private Path path;

    public CuentaDaoCsv(String path) throws DaoException {
        this.path = Paths.get(path); //Convierte una ruta en un String
    }

    public Path getPath() {
        return path;
    }


    @Override
    public ArrayList<Cuenta> listar() throws DaoException  {
        ArrayList<Cuenta> cuentas;
        cuentas = new ArrayList();
        String linea=null;
        String [] separar;
        BufferedReader entrada = null;
        
        
        try {
        
            entrada=Files.newBufferedReader(path);
            
            linea = entrada.readLine();
        
            while(linea!=null){
               separar = linea.split(",");
               cuentas.add(new Cuenta(separar[0],separar[1],Float.parseFloat(separar[2])));
               linea=entrada.readLine();
            }
        
        
        
        }catch(EOFException eofe){
             throw new DaoException("");
        }catch(FileNotFoundException fnfe){
             throw new DaoException("");
        }catch(IOException ioe){
            throw new DaoException("");
        }
        finally{  
            if(entrada!=null){
                try {
                    entrada.close();
                } catch (IOException ex) {
                    throw new DaoException("");
                }
            }
        }
        return cuentas;
        
   
    }

    @Override
    public int insertar(ArrayList<Cuenta> cuentas) throws DaoException {
        BufferedWriter salida = null;
       try{
           salida=Files.newBufferedWriter(path);
            for (Cuenta c : cuentas){
                String reemplazar = c.toString().replace(":",","); //Reemplaza los : por la ,
                salida.write(reemplazar);  
                salida.newLine();
            }                
        }catch(IOException ioe){
            throw new DaoException("");
        }
        finally{  
            if(salida!=null){
                try {
                    salida.close();
                } catch (IOException ex) {
                   throw new DaoException("");
                }
            }
        }
       
     return cuentas.size();
    }
    
}
    
    
    
    
    

