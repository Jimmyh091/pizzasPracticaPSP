/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicapizza;

import java.util.Scanner;

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
        Pizzero pizzero = new Pizzero(restaurante);
        Bocatero bocatero = new Bocatero(restaurante);
        for (int i = 0; i < numClientes; i++) {
            new Cliente(restaurante);
        }
    }
    
}
