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
        System.out.println("Dinero recaudado: " + dineroRecaudado + "\nPizzas vendidas: " + pizzasVendidas + "\nBocadillos vendidos: " + bocadillosVendidos);
    }
    
    // --- // --- //
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cuantos clientes quieres que entren?");
        int numClientes = sc.nextInt();
        
        Restaurante restaurante = new Restaurante(numClientes);
        Preparador pizzero = new Preparador(restaurante, 0);
        Preparador bocatero = new Preparador(restaurante, 1);
        
        pizzero.start();
        bocatero.start();
        
        for (int i = 0; i < numClientes; i++) {
            Cliente c = new Cliente(restaurante);
            c.start();
        }
    }
}
