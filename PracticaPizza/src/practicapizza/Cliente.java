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
public class Cliente extends Thread{
    
    private final int tipoProducto; // si pizzas o bocadillos
    private final int precio;
    private final int cantidad;
    
    private Restaurante restaurante;
    
    public Cliente(Restaurante r){
        restaurante = r;
        
        System.out.println("El cliente sopesa sus opciones...");
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        tipoProducto = (int) Math.random() * 1; // esto no seria siempre 0
        precio = (tipoProducto == 0) ? 12 : 6; 
        cantidad = (int) Math.random() * (4 + 1);
        
        this.start(); // ???
    }
    
    @Override
    public void run(){ // paga cuando termina?
        int cantidadRecogida = 0;
        boolean satisfecho = false;
        do {
            
            System.out.println("El cliente recoge sus productos");
            while(restaurante.getMostrador()[tipoProducto] > 0 || cantidadRecogida != cantidad){
                if (tipoProducto == 0) restaurante.recogerPizza();
                else restaurante.recogerBocadillo();

                cantidadRecogida++;
            }

            if (cantidadRecogida == cantidad){
                System.out.println("El cliente esta satisfecho");
                satisfecho = true;
            }else{
                System.out.println("El cliente no esta satisfecho y espera");
                
                try {
                    sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } while (satisfecho);
        
        System.out.println("El cliente paga y se va");
        restaurante.pagar(precio * cantidad);
    }
}
