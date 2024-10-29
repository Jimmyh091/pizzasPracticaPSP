/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicapizza;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    private Semaphore[] semaforosMostrador; // haria falta no
    private Semaphore semaforoNumClientes;
    
    public Restaurante(int n){
        numClientes = n;
        
        pizzasVendidas = 0;
        bocadillosVendidos = 0;
        dineroRecaudado = 0;
        
        mostrador = new int[2]; // 0 = pizzas, 1 = bocadillos
    }

    /* he intentado hacerlo asi pero creo que no es una manera correcta
    public synchronized void servirPizza(){
        mostrador[0]++;
    }
    */
    public synchronized void servirPizza(){
        mostrador[0]++;
    }
    public synchronized void recogerPizza(){
        mostrador[0]--;
        pizzasVendidas++;
    }
    
    public synchronized void servirBocadillo(){
        mostrador[1]++;
    }
    public synchronized void recogerBocadillo(){
        mostrador[1]--;
        bocadillosVendidos++;
    }
    
    public synchronized void entrarCliente(){
        numClientes++;
    }
    
    public synchronized void pagar(int precio){
        dineroRecaudado += precio;
    }
    
    // --- //

    public synchronized int[] getMostrador() {
        return mostrador;
    }
    
    public synchronized int getNumClientes() {
        return numClientes;
    }
    
}
