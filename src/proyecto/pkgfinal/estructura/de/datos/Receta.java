/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;

/**
 *
 * @author usuario
 */
public class Receta {
    private String id;
    private String nombre;
    private String medicamento;

    public Receta(String id, String nombre, String medicamento) {
        this.id = id;
        this.nombre = nombre;
        this.medicamento = medicamento;
    }
    
public String toArchivo(){
    return id + ";" + nombre + ";" + medicamento;
}    

    @Override
    public String toString() {
        return "Paciente: " + nombre +
                "| ID del Paciente: " + id +
                "| Medicamento requerido: " + medicamento;
    }    
    
    
}
