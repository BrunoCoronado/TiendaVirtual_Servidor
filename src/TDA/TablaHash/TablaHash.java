/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA.TablaHash;

/**
 *
 * @author bruno
 */
public class TablaHash {
    Producto [] productos;
    int numElementos=0;
    int capacidad;
    
    public TablaHash(int capacidadInicial_){
        this.capacidad=capacidadInicial_;
        productos=new Producto[capacidadInicial_];
    }
    
    void insertar(Producto p){
        int indice=fcHash(fcPlegamiento(p.codigo));
        int i=1;
        System.out.println("Funcion Hash: "+indice);
        if(productos[indice]==null){
            productos[indice]=p;
        }
        else{
            while(i<100){
                int nIndice=indice+(int)Math.pow(i,2);
                System.out.println("Nuevo indice: "+nIndice);
                    while(nIndice>=capacidad){
                        nIndice=nIndice-capacidad;
                    }
                    if(productos[nIndice]==null){
                        productos[nIndice]=p;
                        break;
                    }
                    i++;
            }
            if(i==100){System.out.println("Un valor no se inserto");}
        }
        numElementos++;
        System.out.println("--------------------------");
        verificarRehash();
        System.out.println("--------------------------");
    }
    void eliminar(String codigo){
        int indice=fcHash(fcPlegamiento(codigo));
        int i=1;
        int nIndice=indice;
        
        System.out.println("Funcion Hash: "+nIndice);
        
        while(i<100){
            System.out.println(i);
            if(productos[nIndice]!=null){
                if(productos[nIndice].codigo.equalsIgnoreCase(codigo)){
                productos[nIndice]=null;
                //reubicar;
                break;
                
                }
                else{
                    nIndice=indice+(int)Math.pow(i,2);
                    while(nIndice>=capacidad){
                        nIndice=nIndice-capacidad;
                    }
                }
            }
            else{
                nIndice=indice+(int)Math.pow(i,2);
                while(nIndice>=capacidad){
                    nIndice=nIndice-capacidad;
                }    
            }
            i++;
        }
        
        if(i==100){
            System.out.println("No se encuentra el elemento buscado");
        }
    }
    Producto buscar(String codigo){
        Producto pd=null;
        
        int indice=fcHash(fcPlegamiento(codigo));
        int i=1;
        int nIndice=indice;
        
        System.out.println("Funcion Hash: "+nIndice);
        
        while(/*productos[nIndice]!=null&&*/i<100){
            if(productos[nIndice]!=null){
                if(productos[nIndice].codigo.equalsIgnoreCase(codigo)){
                pd=productos[nIndice];
                //reubicar;
                break;
                
                }
                else{
                    nIndice=indice+(int)Math.pow(i,2);
                    while(nIndice>=capacidad){
                        nIndice=nIndice-capacidad;
                    }
                }
            }
            else{
                nIndice=indice+(int)Math.pow(i,2);
                while(nIndice>=capacidad){
                    nIndice=nIndice-capacidad;
                }    
            }
            i++;
        }
        
        if(productos[nIndice]==null){
            System.out.println("No se encuentra el elemento buscado");
            pd=null;
        }
        return pd;
    }
    int fcHash(int codigo){
        int clave=codigo%23;
        return clave;
    }
    int fcPlegamiento(String codigo){
        System.out.println("Codigo: "+codigo);
        
        int clave=0;
        String aux="";
        int aux2=0;
        
        
        for(int i=codigo.length()-1; i>=0;i--){
            aux2++;
            aux=codigo.charAt(i)+aux;
            if(aux2%3==0){
                //System.out.println("aux: "+aux);
                clave=clave+Integer.parseInt(aux);
                //System.out.println("Calve parcial: "+aux);
                aux="";
                aux2=0;
            }
        }
        aux="";
        aux2=0;
        System.out.println("Clave: "+clave);
        //Quitar el acarreo
        String aux3= Integer.toString(clave);
        
        if(aux3.length()%3!=0){
            clave=0;
            System.out.println("Eliminar acarreo");
            for(int i=aux3.length()-1; i>=0;i--){
                aux2++;
                aux=aux3.charAt(i)+aux;
                if(aux2%3==0){
                    //System.out.println("aux: "+aux);
                    clave=clave+Integer.parseInt(aux);
                    //System.out.println("Calve parcial: "+aux);
                    aux="";
                    aux2=0;
                }
            }
            aux="";
            aux2=0;
            System.out.println("Clave: "+clave);
        }
    
        return clave;
    }
        void verificarRehash(){
        float porcentaje= (float)numElementos/capacidad;
        System.out.println("Porcentaje actual utilizado: "+porcentaje+" numE: "+numElementos);
        if(porcentaje>=0.8){
            System.out.println("Rehasing");
            
            Producto[] aux=productos;
            productos= new Producto[capacidad*2];
            int oldCapacidad=capacidad;
            capacidad=capacidad*2;
            numElementos=0;
            
            for(int i=0;i<oldCapacidad;i++){
                if(aux[i]!=null){
                    insertar(aux[i]);
                }
            }
            System.out.println("Nueva capacidad");
        }
    }
    
    void mostrar(){
        //Contenido
        System.out.print("[");
        for(int i=0; i<capacidad;i++){
            if(productos[i]!=null){
                System.out.print(productos[i].codigo+" ");
            }
        }
        System.out.println("]");
        //Claves
        System.out.print("[");
        for(int i=0; i<capacidad;i++){
            if(productos[i]!=null){
                System.out.print(i+" ");
            }
        }
        System.out.println("]");
    }
    void dibujar()
    {
        System.out.println("Dibujando");
        
            try{
                archivo = new File("D:\\crist\\Documents\\NetBeansProjects\\TablaHash\\Reportes\\tablaHashProductos.txt");//Como crear una ruta relativa
                fw = new FileWriter(archivo,true);
                bw= new BufferedWriter(fw);
                
                bw.write("digraph TablaHash{\n");
                //contenido
                //bw.write("rankdir=LR;\n");
                bw.write("node[shape=record];\n");
                for(int i=0; i<capacidad; i++){
                    //crear tabla
                    bw.write("Tabla"+i+"[label=\""+i+"\"]\n");
                }
                for(int h=0; h<capacidad-1; h++){
                    //crear tabla
                    bw.write("Tabla"+h+"->Tabla"+(h+1)+";\n");
                }
                bw.write("\n");
                for(int j=0;j<capacidad;j++){
                    //crear nodos
                    if(productos[j]!=null){
                        bw.write("nodo"+j+"[label=\""+productos[j].codigo+"\"];\n");
                    }
                }
                for(int k=0;k<capacidad;k++){
                    //enlzar los nodos
                    if(productos[k]!=null){
                        bw.write("Tabla"+k+"->nodo"+k+";\n");
                        bw.write("{rank=same;Tabla"+k+";nodo"+k+"}\n");
                    }
                }
                bw.write("\n}");
                bw.close();
                
                try {
                    String comando= "dot -Tsvg D:\\crist\\Documents\\NetBeansProjects\\TablaHash\\Reportes\\tablaHashProductos.txt -o D:\\crist\\Documents\\NetBeansProjects\\TablaHash\\Reportes\\tablaHashProductos.svg";
                    Runtime.getRuntime().exec(comando);
                    //String comando2="D:\\crist\\Documents\\NetBeansProjects\\TablaHash\\Reportes\\tablaHashProductos.svg";
                    //Runtime.getRuntime().exec(comando2);
                } 
                catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
            catch(IOException e)
            {
                System.out.println("Error: "+e.toString());

            }
    }
    
    
}
