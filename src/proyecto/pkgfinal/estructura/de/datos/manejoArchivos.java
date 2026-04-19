/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author jorge
 */
public class manejoArchivos {

    public static String ArchivoPacientes = "pacientes.txt";
    public static String ArchivoAtendidos = "atendidos.txt";
    public static String ArchivoRecetas = "recetas.txt";
    public static String ArchivoEnAtencion = "en_atencion.txt";
    public static String ArchivoHistorial= "historial.txt";
    public static String ArchivoArbol = "arbol.txt";
    public static String ArchivoHistorialRecetas = "historial_recetas.txt";
    
    public static void escribir(String nombreArchivo, String info) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            writer.write(info);            
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void reescribir(String nombreArchivo, String info) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(info);

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static String consultar(String nombreArchivo) {
        
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");         
            }
        } catch (IOException e) {
                return "";
        }
        return contenido.toString();
    }

}