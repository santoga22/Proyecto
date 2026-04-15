/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;

import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class Sistema {
    
    private Cola alta = new Cola(); // Módulo para poder encolar por orden de prioridades o urgencia
    private Cola media = new Cola();  // Nodo
    private Cola baja = new Cola();
    private ListaAtencion enAtencion = new ListaAtencion();
    private ListaHistorial historial = new ListaHistorial();

    public Sistema() {
        cargarPacientes();
        cargarRecetas();
        cargarEnAtencion();
        cargarHistorial();        
    }
    
    private PilaRecetas farmacia = new PilaRecetas(); // Pila
    
    public boolean registrarPaciente(Paciente p){

        
        switch (p.getUrgecia().toUpperCase()){
            case "ALTA":
                alta.encolar(p);
                manejoArchivos.escribir(manejoArchivos.ArchivoPacientes,
                p.toArchivo());
                return true;
            case "MEDIA":
                media.encolar(p);
                manejoArchivos.escribir(manejoArchivos.ArchivoPacientes,
                p.toArchivo());                
                return true;
            case "BAJA":
                baja.encolar(p);
                manejoArchivos.escribir(manejoArchivos.ArchivoPacientes,
                p.toArchivo());                
                return true;
            default:
                return false;                   
        }
    }
    
    public Paciente atenderPaciente(){
        Paciente atendido = null;
        
        if (!alta.esVacia()) {
            atendido = alta.desencolar();
        } else if (!media.esVacia()) {
            atendido = media.desencolar();
        } else if (!baja.esVacia()) {
            atendido = baja.desencolar();
        }
        if (atendido != null) {
            enAtencion.insertar(atendido);

            manejoArchivos.reescribir(
                    manejoArchivos.ArchivoEnAtencion,
                    enAtencion.toArchivo()
            );

            actualizarArchivoPt();
        }

        return atendido; 
    }


    
    
    
    
    public void subirReceta(Receta r){ // Usa el apliar
        farmacia.apilar(r);
        manejoArchivos.escribir("recetas.txt", r.toArchivo()) ; 
    }
    
    public Receta entregarReceta(){
       
        Receta r = farmacia.desApilar();  // usa desapilar
        if(r !=null){
         manejoArchivos.reescribir("recetas.txt", farmacia.toArchivo()) ;              
        }
        return r;
  
    }
    
    public String mostrarRecetas(){
        return farmacia.toString(); // usa toString para imprimir receta actual
    }
    
    
    private void actualizarArchivoPt(){
        
        StringBuilder datos = new StringBuilder();
        datos.append(alta.toArchivo());
        datos.append(media.toArchivo());
        datos.append(baja.toArchivo());
        
        
        manejoArchivos.reescribir(manejoArchivos.ArchivoPacientes, datos.toString());
    }
    
    public Paciente Atencion(){
        Paciente p = enAtencion.eliminarPrimero();
        if (p != null){
            historial.insertar(p);
            manejoArchivos.reescribir(manejoArchivos.ArchivoEnAtencion, enAtencion.toArchivo());
            manejoArchivos.reescribir(manejoArchivos.ArchivoHistorial, historial.toArchivo());       
        }
        return p;
    }
        public String mostrarListaAtencion(){
            if (enAtencion.esVacia()){
                return "No hay pacientes en atención pendiente";
            }
        return enAtencion.toString();
    }
    
      public String mostrarListaHistorial(){
            if (historial.esVacia()){
                return "No hay pacientes en atención pendiente";
            }          
        return historial.toString();
    }  
    
    public Paciente buscarPacienteID(String id){
        return enAtencion.buscarId(id);
    }  

    private void cargarPacientes(){
        String datos = manejoArchivos.consultar(manejoArchivos.ArchivoPacientes);
        
        if(datos==null || datos.isEmpty())
            return;
        String [] lineas = datos.split("\n");
        
        for (String linea : lineas){
            String[] info = linea.split(";");
            
            if (info.length == 6){
                Paciente p = new Paciente(
                info[0],
                info[1],                
                Integer.parseInt(info[2]),
                info[3],                        
                info[4],                        
                info[5]                        
                );
                        switch (p.getUrgecia().toUpperCase()){
            case "ALTA":
                alta.encolar(p);
                break;
            case "MEDIA":
                media.encolar(p);             
                break;
            case "BAJA":
                baja.encolar(p);           
                break;
            }
                    
        }
        
        
    }
            }
    
    private void cargarEnAtencion() {
        String datos = manejoArchivos.consultar(manejoArchivos.ArchivoEnAtencion);

        if (datos == null || datos.isEmpty()) {
            return;
        }
        String[] lineas = datos.split("\n");

        for (String linea : lineas) {
            String[] info = linea.split(";");

            if (info.length == 6) {
                Paciente p = new Paciente(
                        info[0],
                        info[1],
                        Integer.parseInt(info[2]),
                        info[3],
                        info[4],
                        info[5]
                );

                enAtencion.insertar(p);
            }

        }
    }

    private void cargarHistorial() {
        String datos = manejoArchivos.consultar(manejoArchivos.ArchivoHistorial);

        if (datos == null || datos.isEmpty()) {
            return;
        }
        String[] lineas = datos.split("\n");

        for (String linea : lineas) {
            String[] info = linea.split(";");

            if (info.length == 6) {
                Paciente p = new Paciente(
                        info[0],
                        info[1],
                        Integer.parseInt(info[2]),
                        info[3],
                        info[4],
                        info[5]
                );

                historial.insertar(p);
            }

        }
    }    

    
    private void cargarRecetas() {
        String datos = manejoArchivos.consultar(manejoArchivos.ArchivoRecetas);

        if (datos == null || datos.isEmpty()) {
            return;
        }
        String[] lineas = datos.split("\n");

        for (String linea : lineas) {
            String[] info = linea.split(";");

            if (info.length == 3) {
                Receta r = new Receta(
                        info[0],
                        info[1],
                        info[2]

                );

                farmacia.apilar(r);
            }

        }
    }  
    
    @Override
    public String toString() {
        return "--- Urgencia Alta ---\n" + alta +
               "\n--- Urgencia Media ---\n" + media + 
               "\n--- Urgencia Baja ---\n" + baja;                 
    }
    
    
}
