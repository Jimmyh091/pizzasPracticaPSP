/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicapizza;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author EAG
 */

public class Preparador extends Thread{
    
    private Restaurante restaurante;
    private int tipoProducto;
    
    
    public Preparador(Restaurante r, int t){
        restaurante = r;
        tipoProducto = t;
    }
    
    @Override
    public void run(){
        
        while(restaurante.numClientes > 0){
            if (tipoProducto == 0) { // pizzas
                
                System.out.println("Estirando la masa");
                dormir(2);

                System.out.println("Poniendo los ingredientes");
                dormir(1);

                System.out.println("Cocinando la pizza");
                dormir(5);
                
            }else{
                
                System.out.println("Cortando pan");
                dormir(1);

                System.out.println("Poniendo la mayonesa");
                dormir(2);

                System.out.println("Poniendo los ingredientes");
                dormir(2);

                System.out.println("Envolviendo el bocadillo");
                dormir(3);
                
            }
            
            try {
                
                String linea = (tipoProducto == 0) ? "El pizzero " : "El bocatero ";
                linea += "va a servir su producto";                
                System.out.println(linea);
                
                restaurante.semaforosMostrador[tipoProducto].acquire();                
                restaurante.mostrador[tipoProducto]++;
                System.out.println("Hay " + restaurante.mostrador[tipoProducto] + "");
                restaurante.semaforosMostrador[tipoProducto].release();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Preparador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void dormir(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            System.out.println("El hilo no quiere dormir");
        }
    }
}
