/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicapizza;


/**
 *
 * @author EAG
 */
public class Preparador extends Thread{
    
    private Restaurante restaurante;
    private int tipoProducto;
    
    public Preparador(Restaurante r, int t){
        restaurante = r;
        tipoProducto = t;
    }
    
    @Override
    public void run(){
        while(restaurante.numClientes > 0){
            
        }
    }
    
    public void dormir(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            System.out.println("El hilo no quiere dormir");
        }
    }
}
