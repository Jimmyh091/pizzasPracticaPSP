/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicapizza;

import java.util.concurrent.Semaphore;

/**
 *
 * @author jaime
 */
public class Restaurante {
    
    private int numClientes;
    
    private int pizzasVendidas;
    private int bocadillosVendidos;
    private int dineroRecaudado;
    
    private int[] mostrador;
    
    private Semaphore[] semaforosMostrador;
    
    public Restaurante(int n){
        numClientes = n;
        
        pizzasVendidas = 0;
        bocadillosVendidos = 0;
        dineroRecaudado = 0;
        
        mostrador = new int[2]; // 0 = pizzas, 1 = bocadillos
    }

    public int getNumClientes() {
        return numClientes;
    }
}
