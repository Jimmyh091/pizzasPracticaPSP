/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicapizza;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 *
 * @author jaime
 */

/*pa luego
// Preparando las pizzas
            if (tipoProducto == 0) {
                
                System.out.println("Estirando la masa");
                dormir(2);

                System.out.println("Poniendo los ingredientes");
                dormir(1);

                System.out.println("Cocinando la pizza");
                dormir(5);
                
            // Preparando los bocadillos
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
*/

// El restaurante es la clase que actua como medio entre los hilos. Aqui se manejan
// las acciones y los datos recopilados durante la ejecucion.
public class Restaurante {
    
    public int numClientes;
    
    public int pizzasVendidas;
    public int bocadillosVendidos;
    public int dineroRecaudado;
    
    public int[] mostrador;
    
    public Semaphore[] semaforosMostrador;
    public Semaphore semaforoClientes;
    public Semaphore semaforoPizzasVendidas;
    public Semaphore semaforoBocadillosVendidos;
    public Semaphore semaforoDineroRecaudado;
    
    public Restaurante(int n){
        numClientes = n;
        
        pizzasVendidas = 0;
        bocadillosVendidos = 0;
        dineroRecaudado = 0;
        
        mostrador = new int[]{0, 0}; // 0 = pizzas, 1 = bocadillos
        
        semaforosMostrador = new Semaphore[]{new Semaphore(1), new Semaphore(1)};
        semaforoClientes = new Semaphore(1);
        semaforoPizzasVendidas = new Semaphore(1);
        semaforoBocadillosVendidos = new Semaphore(1);
        semaforoDineroRecaudado = new Semaphore(1);
    }
    
    public void mostrarResultados(){
        System.out.println("\nDinero recaudado: " + dineroRecaudado + 
                "\nPizzas vendidas: " + pizzasVendidas + 
                "\nBocadillos vendidos: " + bocadillosVendidos);
    }
    
    // --- // --- //
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cuantos clientes quieres que entren?");
        int numClientes = sc.nextInt();
        
        Restaurante restaurante = new Restaurante(numClientes);
        
        String[] accionesPizzero = {"Estirando la masa", "Poniendo los ingredientes", "Cocinando la pizza"};
        int[] segundosPizzero = {2, 1, 5};
        
        
        
        String[] accionesBocatero = {"Cortando pan", "Poniendo la mayonesa", "Poniendo los ingredientes", "Envolviendo el bocadillo"};
        int[] segundosBocatero = {1, 2, 2, 3};
        
        Cocinero pizzero = new Cocinero(restaurante, 0, accionesPizzero, segundosPizzero);
        Cocinero bocatero = new Cocinero(restaurante, 1, accionesBocatero, segundosBocatero);
        
        pizzero.start();
        bocatero.start();
        
        for (int i = 0; i < numClientes; i++) {
            Cliente c = new Cliente(restaurante, i);
            c.start();
        }
    }
}
