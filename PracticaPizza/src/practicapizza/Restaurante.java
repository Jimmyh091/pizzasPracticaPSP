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
    
    public int numClientes;
    
    private int pizzasVendidas;
    private int bocadillosVendidos;
    private int dineroRecaudado;
    
    public int[] mostrador;
    public Semaphore[] semaforosMostrador;
    
    public Restaurante(int n){
        numClientes = n;
        
        pizzasVendidas = 0;
        bocadillosVendidos = 0;
        dineroRecaudado = 0;
        
        mostrador = new int[]{0, 0}; // 0 = pizzas, 1 = bocadillos
    }
    
    
    public void servirPizza(){
        mostrador[0]++;
    }
    public void recogerPizza(){
        mostrador[0]--;
        pizzasVendidas++;
    }
    
    public void servirBocadillo(){
        mostrador[1]++;
    }
    public void recogerBocadillo(){
        mostrador[1]--;
        bocadillosVendidos++;
    }
    
    public void entrarCliente(){
        numClientes++;
    }
    
    public void pagar(int precio){
        dineroRecaudado += precio;
        numClientes--;
    }
    
    public void mostrarResultados(){
        System.out.println("Dinero recaudado: " + dineroRecaudado + "\nPizzas vendidas: " + pizzasVendidas + "\nBocadillos vendidos: " + bocadillosVendidos);
    }
    
    // --- //

    public int[] getMostrador() {
        return mostrador;
    }
    
    public int getNumClientes() {
        return numClientes;
    }
    
}
