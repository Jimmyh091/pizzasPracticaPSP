/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicapizza;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jaime
 */
public class Bocatero extends Cocinero{
    
    private boolean panCortado;
    private boolean mayonesaPuesta;
    private boolean ingredientesPuestos;
    private boolean bocadilloEnvuelto;
    
    public Bocatero(Restaurante r){
        super(r);
        
        panCortado = false;
        mayonesaPuesta = false;
        ingredientesPuestos = false;
        bocadilloEnvuelto = false;
    }

    @Override
    public void cocinar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void cortarPan(){
        System.out.println("Cortando pan");
        dormir(1);
        panCortado = true;
        
    }    
    private void ponerMayonesa(){
        System.out.println("Poniendo la mayonesa");
        dormir(2);
        mayonesaPuesta = true;
        
    }    
    private void ponerIngredientes(){
        System.out.println("Poniendo los ingredientes");
        dormir(2);
        ingredientesPuestos = true;
    }    
    private void envolverBocadillo(){
        System.out.println("Envolviendo el bocadillo");
        dormir(3);
        panCortado = true;
        
    }

    @Override
    public void servir(){
        restaurante
        
        panCortado = false;
        mayonesaPuesta = false;
        ingredientesPuestos = false;
        bocadilloEnvuelto = false;
    }
    
    public void dormir(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bocatero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
