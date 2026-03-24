/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;

/**
 *
 * @author usuario
 */
public class Paciente {
    
    private String nombre;
    private String id;
    private int edad;
    private String motivo;
    private String urgencia;
    private String hora;

    public Paciente(String nombre, String id, int edad, String motivo, String urgecia, String hora) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.motivo = motivo;
        this.urgencia = urgecia;
        this.hora = hora;
    }

    public String getUrgecia() {
        return urgencia;
    }

    public String getId() {
        return id;
    }
    
    public String toArchivo(){
        return nombre + ";"+
               id + ";" +
               edad + ";" +
               motivo + ";" +
               urgencia + ";" +
               hora;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                "| ID: " + id +
                "| Edad: " + edad +
                "| Motivo de consulta: " + motivo +
                "| Nivel de Urgencia: " + urgencia +
                "| Hora: " + hora;
    }
    
    
}
