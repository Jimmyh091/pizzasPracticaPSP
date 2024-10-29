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
        System.out.println("Cortando pan");
        dormir(1);
        
        System.out.println("Poniendo la mayonesa");
        dormir(2);
        
        System.out.println("Poniendo los ingredientes");
        dormir(2);
        
        System.out.println("Envolviendo el bocadillo");
        dormir(3);
    }
    
    @Override
    public void servir(){
        System.out.println("Sirviendo bocadillo");
        super.getRestaurante().mostrador[]++;
        
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
