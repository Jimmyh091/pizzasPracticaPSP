/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicapizza;

// IMPLEMENTAR JAVAFX.PAIR
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author EAG
 */

// Esta clase es el hilo que se dedica a preparar los productos del restaurante. 
// El hilo ira indicando cada accion que realiza mediante un sout. El cocinero estara
// preparando su producto indicado mientras haya clientes. Luego, lo servira.
public class Cocinero extends Thread{
    
    // El cocinero puede preparar pizzas o bocadillos, y eso se indicara en la variable
    // tipoProducto con un 0 con pizzas y un 1 en bocadillos.
    private int tipoProducto;
    private Restaurante restaurante;
    
    private Par acciones;
    
    // La clase Par ha sido creada para almacenar las acciones junto a los segundos que tiene
    // que esperar. Hubiera utilizado la clase Pair pero soy bobo y lo voy a hacer en clase
    public class Par{
        
        String[] acciones;
        int segundos;
        
        public Par(String[] d, int s){
            acciones = d;
            segundos = s;
        }
        
        public void ejectuar(){
            try {
                
                for (String accion : acciones) {
                    System.out.println(accion);
                    Thread.sleep(segundos * 1000);
                }
                
            } catch (InterruptedException ex) {
                System.out.println("El hilo no quiere dormir");
            }
        }
    }
    
    public Cocinero(Restaurante r, int t){
        restaurante = r;
        tipoProducto = t;
    }
    
    @Override
    public void run(){
        
        // Va a trabajar mientras haya algun cliente
        while(restaurante.numClientes > 0){            
            
            /*
            METER CLASE PAIR !!!
            */
            
            // Despues de preparar su producto, lo intenta servir con el semaforo asignado.
            // Tambien muestra la cantidad de productos en el mostrador
            try {
                
                String linea = (tipoProducto == 0) ? "El pizzero " : "El bocatero ";
                linea += "va a servir su producto";                
                System.out.println(linea);
                
                restaurante.semaforosMostrador[tipoProducto].acquire();                
                restaurante.mostrador[tipoProducto]++;
                System.out.println("Hay " + restaurante.mostrador[tipoProducto] + "");
                restaurante.semaforosMostrador[tipoProducto].release();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Cocinero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Cuando se vaya a acabar el programa, se mostrara un resumen del dia,
        // y el pizzero o bocatero llamara al metodo del restaurante resumen para verlo.
        // (El programa acaba cuando no quedan clientes).
        restaurante.mostrarResultados();
    }
    
    // Este metodo existe para aliviar visualmente el codigo: en vez de tener que poner el try
    // de 5 lineas, llamas al metodo y punto
    public void dormir(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            System.out.println("El hilo no quiere dormir");
        }
    }
}