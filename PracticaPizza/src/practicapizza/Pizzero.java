/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicapizza;

/**
 *
 * @author jaime
 */
public class Pizzero extends Cocinero{

    private boolean masaEstirada;
    private boolean ingredientesPuestos;
    private boolean pizzaCocinada;
    
    public Pizzero(Restaurante r){
        super(r);
    }
    
    @Override
    public void cocinar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
