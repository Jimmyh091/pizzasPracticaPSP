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
    
    private final int tipoProducto;
    private final int precio;
    private final int cantidad;
    
    private Restaurante restaurante;
    
    public Cliente(Restaurante r){
        restaurante = r;
        
        System.out.println("El cliente sopesa sus opciones...");
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        tipoProducto = (Math.random() <= 0.5) ? 0 : 1; //no me iba bien
        precio = (tipoProducto == 0) ? 12 : 6; 
        cantidad = (int) (Math.random() * (4 - 1) + 1);
        
        System.out.print("El cliente quiere " + cantidad + " ");
        if (tipoProducto == 0) System.out.println("pizzas");
        else System.out.println("bocadillos");
    }
    
    @Override
    public void run(){
        
        try {
            int cantidadRecogida = 0;
            boolean satisfecho = false;
            
            restaurante.semaforosMostrador[tipoProducto].acquire();
            do {
                while(restaurante.mostrador[tipoProducto] > 0){
                    if (cantidadRecogida != cantidad) {
                        
                        restaurante.mostrador[tipoProducto]--;
                        cantidadRecogida++;
                        
                    }else{
                        break;
                    }
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

            } while (!satisfecho);
            
            restaurante.semaforosMostrador[tipoProducto].release();
            
            System.out.println("El cliente paga y se va");
            restaurante.pagar(precio * cantidad);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
