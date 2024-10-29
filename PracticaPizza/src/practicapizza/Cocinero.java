/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicapizza;

/**
 *
 * @author jaime
 */
public abstract class Cocinero extends Thread{
    
    private Restaurante restaurante;
    
    public Cocinero(Restaurante r){
        restaurante = r;
        this.start();
    }
    
    @Override
    public void run(){
        while(restaurante.numClientes > 0){
            cocinar();
            servir();
        }
    }
    
    public abstract void cocinar();
    public abstract void servir();
    
    // --- //    

    public Restaurante getRestaurante() {
        return restaurante;
    }    
}
