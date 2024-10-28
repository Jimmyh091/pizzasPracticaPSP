
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author jaime
 */
public class Test {
    
    private static class Sumador extends Thread{
        
        private int num;
        public Sumador(int i){
            num = i;
        }
        
        @Override
        public void run(){
            sumar(num);
        }
    }
    
    private static class Restador extends Thread{
        
        @Override
        public void run(){
            try {
                s.acquire();
                
                s.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    private static Semaphore s;
    private static int a = 0;
    
    public static void main(String[] args) {
        s = new Semaphore(1);
        
        Sumador sum = new Sumador(0);
        Sumador sum1 = new Sumador(1);
        Sumador sum2 = new Sumador(2);
        
        sum.start();
        sum1.start();
        sum2.start();
        
    }
    
    public synchronized static void sumar(int n){
        for (int i = 0; i < 10; i++) {
            System.out.println("Sumando..." + a + " (" + n + ")");
            a++;
        }
    }
}
