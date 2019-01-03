/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA.ArbolAVL;

/**
 *
 * @author bruno
 */
public class NodoAVL {
    private int id;
    private int fe;
    private int altura;
    private NodoAVL izquierda;
    private NodoAVL derecha;
    private NodoAVL padre;

    public NodoAVL(int id, NodoAVL padre) {
        this.id = id;
        this.padre = padre;
    }

    public NodoAVL(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NodoAVL getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoAVL izquierda) {
        this.izquierda = izquierda;
    }

    public NodoAVL getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoAVL derecha) {
        this.derecha = derecha;
    }

    public NodoAVL getPadre() {
        return padre;
    }

    public void setPadre(NodoAVL padre) {
        this.padre = padre;
    }
    
   
}
