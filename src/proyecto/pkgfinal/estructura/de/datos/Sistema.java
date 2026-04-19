/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;

import javax.swing.JOptionPane;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

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
    private ArbolPacientes arbol = new ArbolPacientes(); // atributo arbol

    public Sistema() {
        cargarPacientes();
        cargarRecetas();
        cargarEnAtencion();
        cargarHistorial();
        cargarArbol();
    }

    private PilaRecetas farmacia = new PilaRecetas(); // Pila

    public boolean registrarPaciente(Paciente p) {  //preconsulta

        switch (p.getUrgecia().toUpperCase()) {
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

    public Paciente atenderPaciente() { // preconsulta
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

    public void subirReceta(Receta r) { // Usa el apliar
        farmacia.apilar(r);
        manejoArchivos.escribir("recetas.txt", r.toArchivo());
        manejoArchivos.escribir(manejoArchivos.ArchivoHistorialRecetas, r.toArchivo());
    }

    public Receta entregarReceta() {

        Receta r = farmacia.desApilar();  // usa desapilar
        if (r != null) {
            manejoArchivos.reescribir("recetas.txt", farmacia.toArchivo());
        }
        return r;

    }

    public String mostrarRecetas() {
        return farmacia.toString(); // usa toString para imprimir receta actual
    }

    private void actualizarArchivoPt() {

        StringBuilder datos = new StringBuilder();
        datos.append(alta.toArchivo());
        datos.append(media.toArchivo());
        datos.append(baja.toArchivo());

        manejoArchivos.reescribir(manejoArchivos.ArchivoPacientes, datos.toString());
    }

    public Paciente Atencion() { // consulta con el medico
        Paciente p = enAtencion.eliminarPrimero();
        if (p != null) {

            LocalTime ahora = LocalTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm"); // setear hora de atencion del paciente
            p.setHoraAtencion(ahora.format(formato));

            historial.insertar(p);
            arbol.insertar(p); // inserta al arbol

            manejoArchivos.reescribir(manejoArchivos.ArchivoEnAtencion, enAtencion.toArchivo());
            manejoArchivos.reescribir(manejoArchivos.ArchivoHistorial, historial.toArchivo());
            manejoArchivos.reescribir(manejoArchivos.ArchivoArbol, arbol.toArchivo());
        }
        return p;
    }

    public String mostrarListaAtencion() {
        if (enAtencion.esVacia()) {
            return "No hay pacientes en atención pendiente";
        }
        return enAtencion.toString();
    }

    public String mostrarListaHistorial() {
        if (historial.esVacia()) {
            return "No hay pacientes en atención pendiente";
        }
        return historial.toString();
    }

    public Paciente buscarPacienteID(String id) {
        return enAtencion.buscarId(id);
    }

    private void cargarPacientes() {
        String datos = manejoArchivos.consultar(manejoArchivos.ArchivoPacientes);

        if (datos == null || datos.isEmpty()) {
            return;
        }
        String[] lineas = datos.split("\n");

        for (String linea : lineas) {
            String[] info = linea.split(";");

            if (info.length >= 6) {
                Paciente p = new Paciente(
                        info[0],
                        info[1],
                        Integer.parseInt(info[2]),
                        info[3],
                        info[4],
                        info[5]
                );
                if (info.length == 7) {
                    p.setHoraAtencion(info[6]);
                }
                switch (p.getUrgecia().toUpperCase()) {
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

    public int totalPacientesAtendidos() {
        String datos = manejoArchivos.consultar(manejoArchivos.ArchivoHistorial);  // reportes de pacientes atendidos

        if (datos == null || datos.isEmpty()) {
            return 0;
        }
        return datos.split("\n").length; // hace del texto un arreglo y saca la longiud de de cada uno, cuenta cuantos hay
    }

    public String mayorEsperaActual() {
        Paciente p = enAtencion.mayorEsperaActual();
        return (p != null) ? p.toString() : "sin datos disponibles"; // espera al momento de pacientes pendientes de atencion
    }

    public String mayorEsperaHistorico() {
        return historial.mayorEsperaFinal();// espera al finalizar todo en base al registro historico de pacientes atendidos
    }

    public String cantidadUrgencia() {
        String datos = manejoArchivos.consultar(manejoArchivos.ArchivoHistorial);
        int alta = 0, media = 0, baja = 0;

        if (datos == null || datos.isEmpty()) {
            return "sin datos disponibles";
        }

        String[] lineas = datos.split("\n");
        for (String linea : lineas) {
            String[] info = linea.split(";");

            if (info.length >= 6) {
                switch (info[4]) {
                    case "ALTA":
                        alta++;
                        break;
                    case "MEDIA":
                        media++;
                        break;
                    case "BAJA":
                        baja++;
                        break;
                }
            }

        }

        return "ALTA: " + alta
                + "\nMEDIA: " + media
                + "\nBAJA: " + baja;
    }
    
    public String medicamentosMasUsados() {
        String datos = manejoArchivos.consultar(manejoArchivos.ArchivoHistorialRecetas);
        if (datos == null || datos.isEmpty()) {
            return "Sin datos disponibles";
        }

        HashMap<String, Integer> conteo = new HashMap<>(); // Hashmap le asigna un contador a componentes, en este caso, medicamentos con el mismo nombre

        String[] lineas = datos.split("\n");
        for (String linea : lineas) {
            String[] info = linea.split(";");

            if (info.length == 3) {
                String medicamento = info[2];

                conteo.put(medicamento,
                        conteo.getOrDefault(medicamento, 0) + 1);

            }
        }
        String resultado ="";
        
        for(String med : conteo.keySet()){
            resultado += med + ": " + conteo.get(med)+"\n";
        }
        return resultado;
    }
    
    
    
    private void cargarEnAtencion() {
        String datos = manejoArchivos.consultar(manejoArchivos.ArchivoEnAtencion);

        if (datos == null || datos.isEmpty()) {
            return;
        }
        String[] lineas = datos.split("\n");

        for (String linea : lineas) {
            String[] info = linea.split(";");

            if (info.length >= 6) {
                Paciente p = new Paciente(
                        info[0],
                        info[1],
                        Integer.parseInt(info[2]),
                        info[3],
                        info[4],
                        info[5]
                );
                if (info.length == 7) {
                    p.setHoraAtencion(info[6]);
                }
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

            if (info.length >= 6) {
                Paciente p = new Paciente(
                        info[0],
                        info[1],
                        Integer.parseInt(info[2]),
                        info[3],
                        info[4],
                        info[5]
                );
                if (info.length == 7) {
                    p.setHoraAtencion(info[6]);
                }
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

    private void cargarArbol() {
        String datos = manejoArchivos.consultar(manejoArchivos.ArchivoArbol);

        if (datos == null || datos.isEmpty()) {
            return;
        }
        String[] lineas = datos.split("\n");

        for (String linea : lineas) {
            String[] info = linea.split(";");

            if (info.length >= 6) {
                Paciente p = new Paciente(
                        info[0],
                        info[1],
                        Integer.parseInt(info[2]),
                        info[3],
                        info[4],
                        info[5]
                );
                if (info.length == 7) {
                    p.setHoraAtencion(info[6]);
                }

                arbol.insertar(p);
            }

        }
    }

    public String mostrarArbol() {
        return arbol.inOrden();

    }

    public Paciente buscarEnArbol(String id) {
        return arbol.buscar(id);
    }

    @Override
    public String toString() {
        return "--- Urgencia Alta ---\n" + alta
                + "\n--- Urgencia Media ---\n" + media
                + "\n--- Urgencia Baja ---\n" + baja;
    }

}
