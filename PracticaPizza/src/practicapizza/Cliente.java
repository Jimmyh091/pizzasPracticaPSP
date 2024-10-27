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
public class Cliente {
    
    private final boolean pedirPizzas;
    private final int cantidad;
    
    public Cliente(){
        System.out.println("El cliente sopesa sus opciones...");
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        pedirPizzas = (int) Math.random() * 1 == 1;
        cantidad = (int) Math.random() * (4 + 1);
    }
    
}
