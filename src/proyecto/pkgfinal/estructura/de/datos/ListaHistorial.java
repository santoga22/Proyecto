/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author yeiko
 */
public class ListaHistorial {
    
    private NodoPaciente inicio,fin;
    
    public boolean esVacia(){
        return inicio == null;
    }
    
    public void insertar(Paciente p){
        NodoPaciente nuevo = new NodoPaciente(p);
        
        if (esVacia()){
            inicio = fin = nuevo;
        }else {
            NodoPaciente aux = inicio;
            while(aux.getSig() != null){
                aux = aux.getSig();
            }
            aux.setSig(nuevo);
        }
        
    }
    
    public String mayorEsperaFinal() {  // Muestra los pacientes que mas esperaron atencion en general al final de ser atendidos
        NodoPaciente aux = inicio;
        if (aux == null) {
            return "Sin datos";
        }

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");

        ArrayList<Paciente> lista = new ArrayList<>();
        while (aux != null) {
            lista.add(aux.getDato());
            aux = aux.getSig();
        }

        lista.sort((p1, p2) -> {
            LocalTime llegada1 = LocalTime.parse(p1.toArchivo().split(";")[5], formato);
            LocalTime at1 = LocalTime.parse(p1.toArchivo().split(";")[6], formato);

            LocalTime llegada2 = LocalTime.parse(p2.toArchivo().split(";")[5], formato);
            LocalTime at2 = LocalTime.parse(p2.toArchivo().split(";")[6], formato);

            int espera1 = at1.toSecondOfDay() - llegada1.toSecondOfDay();
            int espera2 = at2.toSecondOfDay() - llegada2.toSecondOfDay();

            return espera2 - espera1; // orden descendente para que imprima el que lleva más espera, al que menos
        });

        String r = "";
        for (Paciente p : lista) {
            r += p + "\n";
        }

        return r;
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
    
    @Override
    public String toString(){
        String r = "";
        NodoPaciente aux = inicio;
        
        while(aux != null){
            r += aux + "\n";
            aux = aux.getSig();
        }
        
        return r;
    }   
       
}
