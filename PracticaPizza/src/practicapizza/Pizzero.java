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
public class Pizzero extends Cocinero{

    private boolean masaEstirada;
    private boolean ingredientesPuestos;
    private boolean pizzaCocinada;
    
    public Pizzero(Restaurante r){
        super(r);
        
        masaEstirada = false;
        ingredientesPuestos = false;
        pizzaCocinada = false;
    }
    
    @Override
    public void cocinar() {
        System.out.println("Estirando la masa");
        dormir(2);
        masaEstirada = true;
        
        System.out.println("Poniendo los ingredientes");
        dormir(1);
        ingredientesPuestos = true;
        
        System.out.println("Cocinando la pizza");
        dormir(5);
        pizzaCocinada = true;
    }
    
    @Override
    public void servir() {
        System.out.println("Sirviendo pizza");
        super.getRestaurante().servirPizza();
        
        masaEstirada = false;
        ingredientesPuestos = false;
        pizzaCocinada = false;
    }
    
    public void dormir(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bocatero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
