/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA.ArbolAVL;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;

/**
 *
 * @author bruno
 */
public class ArbolAVL {
    private NodoAVL raiz;
    
    public void insertar(int id){
        if(raiz != null)
            insertar(id, raiz);
        else
            raiz = new NodoAVL(id);
    }
    
    private void insertar(int id, NodoAVL tmp){
        if(id < tmp.getId()){
            if(tmp.getIzquierda() != null)
                insertar(id, tmp.getIzquierda());
            else{
                tmp.setIzquierda(new NodoAVL(id, tmp));
                balancear(tmp);
            }
        }else if(id > tmp.getId()){
            if(tmp.getDerecha()!= null)
                insertar(id, tmp.getDerecha());
            else{
                tmp.setDerecha(new NodoAVL(id, tmp));
                balancear(tmp);
            }
        }
    }
    
    private void balancear(NodoAVL tmp){
        tmp.setAltura(1 + Math.max(alturaNodo(tmp.getDerecha()), alturaNodo(tmp.getIzquierda())));
        tmp.setFe(alturaNodo(tmp.getDerecha())-alturaNodo(tmp.getIzquierda()));
        
        if(tmp.getFe() == -2){
            if(alturaNodo(tmp.getIzquierda().getIzquierda()) >= alturaNodo(tmp.getIzquierda().getDerecha()))
                tmp = rotacionDerecha(tmp);
            else{
                tmp.setIzquierda(rotacionIzquierda(tmp.getIzquierda()));
                tmp = rotacionDerecha(tmp);
            }
        }else if(tmp.getFe() == 2){
            if(alturaNodo(tmp.getDerecha().getDerecha()) >= alturaNodo(tmp.getDerecha().getIzquierda()))
                tmp = rotacionIzquierda(tmp);
            else{
                tmp.setDerecha(rotacionDerecha(tmp.getDerecha()));
                tmp = rotacionIzquierda(tmp);
            }
        }
        if(tmp.getPadre() != null)
            balancear(tmp.getPadre());
        else
            raiz = tmp;
    }
    
    private NodoAVL rotacionDerecha(NodoAVL tmp){
        NodoAVL n1 = tmp.getIzquierda();
        //NodoAVL n2 = n1.getDerecha();
        n1.setPadre(tmp.getPadre());
        //n1.setDerecha(tmp);
        tmp.setIzquierda(n1.getDerecha());
        if(tmp.getIzquierda() != null)
            tmp.getIzquierda().setPadre(tmp);
        n1.setDerecha(tmp);
        tmp.setPadre(n1);
        if(n1.getPadre() != null){
            if(n1.getPadre().getDerecha() == tmp)
                n1.getPadre().setDerecha(n1);
            else
                n1.getPadre().setIzquierda(n1);
        }
        tmp.setAltura(1 + Math.max(alturaNodo(tmp.getDerecha()), alturaNodo(tmp.getIzquierda())));
        tmp.setFe(alturaNodo(tmp.getDerecha())-alturaNodo(tmp.getIzquierda()));
        n1.setAltura(1 + Math.max(alturaNodo(n1.getDerecha()), alturaNodo(n1.getIzquierda())));
        n1.setFe(alturaNodo(n1.getDerecha())-alturaNodo(n1.getIzquierda()));
        return n1;
    }
    
    private NodoAVL rotacionIzquierda(NodoAVL tmp){
        NodoAVL n1 = tmp.getDerecha();
        n1.setPadre(tmp.getPadre());
        tmp.setDerecha(n1.getIzquierda());
        if(tmp.getDerecha() != null)
            tmp.getDerecha().setPadre(tmp);
        n1.setIzquierda(tmp);
        tmp.setPadre(n1);
        if(n1.getPadre() != null){
            if(n1.getPadre().getDerecha() == tmp)
                n1.getPadre().setDerecha(n1);
            else
                n1.getPadre().setIzquierda(n1);
        }        
        tmp.setAltura(1 + Math.max(alturaNodo(tmp.getDerecha()), alturaNodo(tmp.getIzquierda())));
        tmp.setFe(alturaNodo(tmp.getDerecha())-alturaNodo(tmp.getIzquierda()));
        n1.setAltura(1 + Math.max(alturaNodo(n1.getDerecha()), alturaNodo(n1.getIzquierda())));
        n1.setFe(alturaNodo(n1.getDerecha())-alturaNodo(n1.getIzquierda()));
        return n1;
    }
    
    int alturaNodo(NodoAVL nodoAVL){
        if(nodoAVL == null)
            return -1;
        return nodoAVL.getAltura();
    }
    
    String contenido = "";
    String nulos = "";
    String relaciones = "";
    String relacionesNulos = "";
    int contadorNull = 0;
    public void graficarArbol(){
        try{
            contenido = "";relaciones = "";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("AVL.txt", false))) {
                writer.write("digraph G {\n");
                contenido(raiz);
                writer.write(contenido);
                writer.write(nulos);
                writer.write(relaciones);
                writer.write(relacionesNulos);
                writer.write("}");
            }
            Runtime.getRuntime().exec("dot.exe -Tsvg AVL.txt -o AVL.svg");
            String [] cmd = {"cmd.exe", "/c", "start", "AVL.svg" };
            Runtime.getRuntime().exec(cmd);
        }catch(IOException ex){}
    }
    
    private void contenido(NodoAVL tmp){
        if(tmp != null){
            contenido += "nodo"+tmp.getId()+"[label = \"ID: "+tmp.getId()+" \\nFE: "+tmp.getFe()+" \\nH: "+tmp.getAltura()+"  \"]\n";
            if(tmp.getIzquierda() != null)
                relaciones += "\"nodo"+tmp.getId()+"\"" + "->"+"\"nodo"+tmp.getIzquierda().getId()+"\"\n";
            else{
                contenido += "null"+contadorNull+" [shape=point];\n";
                relacionesNulos += "\"nodo"+tmp.getId()+"\"" + "->"+"\"null"+contadorNull+++"\"\n";
            }
            if(tmp.getDerecha() != null)
                relaciones += "\"nodo"+tmp.getId()+"\"" + "->"+"\"nodo"+tmp.getDerecha().getId()+"\"\n";
            else{
                nulos += "null"+contadorNull+" [shape=point];\n";
                relacionesNulos += "\"nodo"+tmp.getId()+"\"" + "->"+"\"null"+contadorNull+++"\"\n";
            }
            contenido(tmp.getIzquierda());
            contenido(tmp.getDerecha());
        }   
    }
}
