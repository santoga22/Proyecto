/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;

/**
 *
 * @author usuario
 */
public class Cola {
    
    private NodoPaciente ini, fin;
    
    public boolean esVacia(){
        return ini == null;
    }
    
    public void encolar(Paciente p){
        NodoPaciente nuevo = new NodoPaciente (p);
        
        
        if (esVacia()){
            ini = fin = nuevo;
        }else{
            fin.setSig(nuevo);
            fin = nuevo;
        }
    }
    
    public Paciente desencolar(){
        if (!esVacia()){
            Paciente p = ini.getDato();
            ini = ini.getSig();
            return p;
        }
        return null;
    }
    
    public String toArchivo(){
        String r = "";
        NodoPaciente aux = ini;
        while(aux != null){
            Paciente p = aux.getDato();
            r += p.toArchivo() + "\n";
            aux = aux.getSig();
            
        }
        return r;

    }
    
    

    @Override
    public String toString() {
        String r = "";
        NodoPaciente aux = ini;
        
        while (aux !=null){
            r += aux + "\n";
            aux = aux.getSig();
        }
        
        return r;
    }
    
    
}
