/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;

/**
 *
 * @author yeiko
 */
public class ListaAtencion {
    
    private NodoPaciente inicio, fin;
    
    public boolean esVacia(){
        return inicio == null;
    }
    
    public void insertar(Paciente p){
        NodoPaciente nuevo=new NodoPaciente(p);
        if(esVacia()){
            inicio=fin=nuevo;
        }else{
            NodoPaciente aux = inicio;
            while (aux.getSig() != null){
                aux = aux.getSig();
            }
            aux.setSig(nuevo);
        }   
        
    }
    
    public Paciente eliminarPrimero(){
        if(!esVacia()){
            Paciente p = inicio.getDato();
            inicio = inicio.getSig();
            return p;
        }
        return null;
    }
    
        @Override
    public String toString() {
        String r="";
        NodoPaciente aux=inicio;
        while(aux!=null){
            r+=aux+"\n";
            aux=aux.getSig();
        }
        return r;
    }
    
}
