/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;

/**
 *
 * @author usuario
 */
public class NodoReceta {
    
    private Receta dato;
    private NodoReceta sig;

    public NodoReceta(Receta dato) {
        this.dato = dato;

    }

    public Receta getDato() {
        return dato;
    }



    public NodoReceta getSig() {
        return sig;
    }
    
    public void setSig(NodoReceta sig) {
        this.sig = sig;
    }   

    @Override
    public String toString() {
        return dato.toString();
    }
    
    
}
