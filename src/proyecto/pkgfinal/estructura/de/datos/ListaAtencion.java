/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
            
            if (inicio == null){
                fin = null;
            }
            return p;
        }
        return null;
    }
    
    public Paciente buscarId(String id){
        NodoPaciente aux = inicio;
        while(aux!=null){
            if (aux.getDato().getId().equals(id)){
                return aux.getDato();
            }
            aux=aux.getSig();
        }
        return null;
    }
    
        public String toArchivo(){
        String r = "";
        NodoPaciente aux = inicio;
        while(aux != null){
            Paciente p = aux.getDato();
            r += p.toArchivo() + "\n";
            aux = aux.getSig();
            
        }
        return r;

    }
        
    public Paciente mayorEsperaActual(){ //pacientes que tienen mayor tiempo de espera y aun están en esperando atención al momento de consulta
        NodoPaciente aux = inicio;
        if(aux==null)return null;
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        
        Paciente mayor = null;
        int max = -1;  // crear registro de mayor espera, el max se va remplazando con el que tenga mas tiempo de espera
        
        while (aux != null){
            Paciente p = aux.getDato();
            LocalTime llegada = LocalTime.parse(p.toArchivo().split(";")[5], formato);  //se va a la linea 5 del p.toArchivo y saca la hora de ahi
            int espera = LocalTime.now().toSecondOfDay() - llegada.toSecondOfDay();
            
            if (espera > max){
                max = espera;
                mayor = p;
            }
            aux = aux.getSig();
        }
        return mayor;
    }
    
        @Override
    public String toString() {
        String r="";
        NodoPaciente aux=inicio;
        while(aux!=null){
            r+=aux.getDato()+"\n";
            aux=aux.getSig();
        }
        return r;
    }
    
}
