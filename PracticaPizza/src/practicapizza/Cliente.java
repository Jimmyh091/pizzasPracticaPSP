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
        
        System.out.println("El cliente sopesa sus opciones... Sabe que Jaime es manco pero sopesa el nivel de manco");
        
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
            
            do {
                
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                restaurante.semaforosMostrador[tipoProducto].acquire();
                
                if (restaurante.mostrador[tipoProducto] >= cantidad - cantidadRecogida) {
                    restaurante.mostrador[tipoProducto] -= cantidad - cantidadRecogida;
                    cantidadRecogida = cantidad;
                }else{
                    cantidadRecogida += restaurante.mostrador[tipoProducto];
                    restaurante.mostrador[tipoProducto] = 0;
                }
                
                if (cantidadRecogida == cantidad){
                    System.out.println("El cliente esta satisfecho");
                    satisfecho = true;
                }else{
                    System.out.println("El cliente no esta satisfecho y espera");
                }
                
                restaurante.semaforosMostrador[tipoProducto].release();
            } while (!satisfecho);
            
            System.out.println("El cliente paga y se va");
            
            restaurante.semaforoDineroRecaudado.acquire();
            restaurante.dineroRecaudado += precio * cantidad;
            restaurante.semaforoDineroRecaudado.release();
            
            if (tipoProducto == 0) {
                
                restaurante.semaforoPizzasVendidas.acquire();
                restaurante.pizzasVendidas += cantidad;
                restaurante.semaforoPizzasVendidas.release();
                
            }else{
                
                restaurante.semaforoBocadillosVendidos.acquire();
                restaurante.bocadillosVendidos += cantidad;
                restaurante.semaforoBocadillosVendidos.release();
                
            }
            
            restaurante.semaforoClientes.acquire();
            restaurante.numClientes--;
            restaurante.semaforoClientes.release();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
