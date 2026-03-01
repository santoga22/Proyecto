/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;

/**
 *
 * @author usuario
 */
public class PilaRecetas {
    
 
    private NodoReceta top; 
    
    public boolean esVacia(){
        return top == null;
    }
    
    public void apilar (Receta r){
        NodoReceta nuevo = new NodoReceta(r); //Para apilar la receta desde lo ingresado con la clase receta
        
        if (esVacia()){
         top = nuevo;   
        }else{
            nuevo.setSig(top);
            top = nuevo;
        }
    }
    
    public Receta desApilar(){        //se usa Receta en vez de void para devolver la receta en si
        if (!esVacia()){
            Receta r = top.getDato();  // Guarda el dato de top en r
            top = top.getSig();    
            return r;
        }
        return null;
    }
    public String toArchivo(){
        String r = "";
        NodoReceta aux = top;
        while(aux != null){
            Receta receta = aux.getDato();
            r += receta.toArchivo() + "\n";
            aux = aux.getSig();
            
        }
        return r;

    }
    
    @Override
    public String toString() {
        String r="Receta en la Farmacia: \n";
        NodoReceta aux=top;
        while(aux!=null){
            r+=aux+"\n";
            aux=aux.getSig();
        }
        return r;
    }
    
}
