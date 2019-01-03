/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import TDA.ArbolAVL.ArbolAVL;
import java.math.MathContext;
import java.util.Random;

/**
 *
 * @author bruno
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArbolAVL avl = new ArbolAVL();
        Random numeroAleatorio = new Random();
        int i;
        for (int j = 0; j < 10; j++) {
            i = numeroAleatorio.nextInt(100);
            avl.insertar(i);
            System.out.println("insertando: "+i);
        }
        avl.graficarArbol();
    }
    
}
