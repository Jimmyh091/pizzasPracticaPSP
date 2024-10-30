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

// La clase cliente es el hilo que se dedica a la compra de producto. Cuando se instancia espera,
// elige sus opciones y se pone a la cola. Luego, si llega su turno y cuando recoge los productos
// no tiene todos los deseados, espera 10 segundos para comprobar si los cocineros han terminado con
// el producto deseado. Luego, pagan y se van. Esta clase tambien ira indicando cada accion que haga.
public class Cliente extends Thread {

    // Donde se almacena si el cliente quiere comprar pizzas (0) o bocadillos(1).
    // podria ser un boolean de quierePizza pero lo veo menos comprensible
    private int tipoProducto;
    private int precio;
    private int cantidad;
    private int cantidadRecogida;

    // El id sera un identificador de cada hilo para poder diferenciarlos los unos de los otros.
    // Se utilizaran en los souts
    private final int id;
    private Restaurante restaurante;

    public Cliente(Restaurante r, int i) {
        restaurante = r;
        id = i + 1;
    }

    @Override
    public void run() {
        System.out.println("El cliente " + id + " sopesa sus opciones...");
        
        // Asignamos los valores
        tipoProducto = (Math.random() <= 0.5) ? 0 : 1;
        precio = (tipoProducto == 0) ? 12 : 6;
        // cantidad elige entre 1 y 4
        cantidad = (int) (Math.random() * (4 - 1) + 1);
        
        
        String linea = "El cliente " + id + " quiere " + cantidad + " ";
        linea += (tipoProducto == 0) ? "pizzas" : "bocadillos";
        System.out.println(linea);
        
        
        cantidadRecogida = 0;
        
        do {
            try {
                
                // Aqui hacemos acquire del semaforo asignado al mostrador del producto.
                // Espera 10 segundos por la eleccion de productos o por estar esperando a los cocineros
                Thread.sleep(10000);
                restaurante.semaforosMostrador[tipoProducto].acquire();

            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Si hay suficiente cantidad en el mostrador de la que quiere, se recoge todo.
            // Si no, se suma la que hay en el mostrador y se queda el mostrador vacio.
            // Con el bucle volveria a hacer el if hasta que el cliente qeude satisfecho.
            if (restaurante.mostrador[tipoProducto] >= cantidad - cantidadRecogida) {
                restaurante.mostrador[tipoProducto] -= cantidad - cantidadRecogida;
                cantidadRecogida = cantidad;
            } else {
                cantidadRecogida += restaurante.mostrador[tipoProducto];
                restaurante.mostrador[tipoProducto] = 0;
            }

            if (cantidadRecogida == cantidad) {
                System.out.println("El cliente esta satisfecho, paga y se va");
            } else {
                System.out.println("El cliente no esta satisfecho y espera");
            }

            // El release se hace dentro del bucle porque se tiene que dejar tiempo para que los cocineros
            // preparen los productos. Si se hiciera fuera, nunca tendrian espacio para ponerlos.
            restaurante.semaforosMostrador[tipoProducto].release();
        } while (cantidadRecogida < cantidad);

        

        try {

            // Se paga
            restaurante.semaforoDineroRecaudado.acquire();
            restaurante.dineroRecaudado += precio * cantidad;
            System.out.println("Dinero recaudado: " + restaurante.dineroRecaudado);
            restaurante.semaforoDineroRecaudado.release();
            
            // Se suma la cantidad de producto vendido
            if (tipoProducto == 0) {

                restaurante.semaforoPizzasVendidas.acquire();
                restaurante.pizzasVendidas += cantidad;
                System.out.println("Pizzas vendidas: " + restaurante.pizzasVendidas);
                restaurante.semaforoPizzasVendidas.release();

            } else {

                restaurante.semaforoBocadillosVendidos.acquire();
                restaurante.bocadillosVendidos += cantidad;
                System.out.println("Bocadillos vendidos: " + restaurante.bocadillosVendidos);
                restaurante.semaforoBocadillosVendidos.release();

            }

            // Se marcha el cliente
            restaurante.semaforoClientes.acquire();
            restaurante.numClientes--;
            System.out.println("Clientes restantes: " + restaurante.numClientes);
            restaurante.semaforoClientes.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
