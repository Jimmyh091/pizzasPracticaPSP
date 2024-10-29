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
    
    private Semaphore[] semaforosMostrador; // haria falta no
    
    public Restaurante(int n){
        numClientes = n;
        
        pizzasVendidas = 0;
        bocadillosVendidos = 0;
        dineroRecaudado = 0;
        
        mostrador = new int[]{0, 0}; // 0 = pizzas, 1 = bocadillos
    }

    /* funciona de esta manera pero creo que no es la forma correcta
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
        numClientes--;
    }
    
    private void mostrarResultados(){
        System.out.println("Dinero recaudado: " + dineroRecaudado + "\nPizzas vendidas: " + pizzasVendidas + "\nBocadillos vendidos: " + bocadillosVendidos);
    }
    
    // --- //

    public synchronized int[] getMostrador() {
        return mostrador;
    }
    
    public synchronized int getNumClientes() {
        return numClientes;
    }
    
}
