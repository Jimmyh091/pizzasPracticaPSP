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
public class Cliente extends Thread {

    private int tipoProducto;
    private int precio;
    private int cantidad;
    private int cantidadRecogida;

    private Restaurante restaurante;
    private int id;

    public Cliente(Restaurante r, int i) {
        restaurante = r;
        id = i + 1;
    }

    @Override
    public void run() {
        System.out.println("El cliente " + id + " sopesa sus opciones...");
        
        // asignamos los valores
        tipoProducto = (Math.random() <= 0.5) ? 0 : 1;
        precio = (tipoProducto == 0) ? 12 : 6;
        cantidad = (int) (Math.random() * (4 - 1) + 1);

        
        String linea = "El cliente " + id + " quiere " + cantidad + " ";
        linea += (tipoProducto == 0) ? "pizzas" : "bocadillos";
        System.out.println(linea);
        
        
        cantidadRecogida = 0;
        
        do {
            try {
                
                Thread.sleep(10000);
                restaurante.semaforosMostrador[tipoProducto].acquire();

            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("El cliente ");
            if (restaurante.mostrador[tipoProducto] >= cantidad - cantidadRecogida) {
                restaurante.mostrador[tipoProducto] -= cantidad - cantidadRecogida;
                cantidadRecogida = cantidad;
            } else {
                cantidadRecogida += restaurante.mostrador[tipoProducto];
                restaurante.mostrador[tipoProducto] = 0;
            }

            if (cantidadRecogida == cantidad) {
                System.out.println("El cliente esta satisfecho");
                System.out.println("El cliente paga y se va");
            } else {
                System.out.println("El cliente no esta satisfecho y espera");
            }

            restaurante.semaforosMostrador[tipoProducto].release();
        } while (cantidadRecogida < cantidad);

        

        try {

            restaurante.semaforoDineroRecaudado.acquire();
            restaurante.dineroRecaudado += precio * cantidad;
            restaurante.semaforoDineroRecaudado.release();

            if (tipoProducto == 0) {

                restaurante.semaforoPizzasVendidas.acquire();
                restaurante.pizzasVendidas += cantidad;
                restaurante.semaforoPizzasVendidas.release();

            } else {

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
