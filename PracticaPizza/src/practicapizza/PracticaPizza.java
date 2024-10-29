/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicapizza;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EAG
 */
public class PracticaPizza {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cuantos clientes quieres que entren?");
        int numClientes = sc.nextInt();
        
        Restaurante restaurante = new Restaurante(numClientes);
        Preparador pizzero = new Preparador(restaurante, 0);
        Preparador bocatero = new Preparador(restaurante, 1);
        
        for (int i = 0; i < numClientes; i++) {
            Cliente c = new Cliente(restaurante);
            c.start();
        }
    }
    
}
