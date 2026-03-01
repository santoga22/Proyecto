/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;

/**
 *
 * @author usuario
 */
public class NodoPaciente {
    private Paciente dato;
    private NodoPaciente sig;

    public NodoPaciente(Paciente dato) {
        this.dato = dato;
    }

    public Paciente getDato() {
        return dato;
    }

    public NodoPaciente getSig() {
        return sig;
    }

    public void setSig(NodoPaciente sig) {
        this.sig = sig;
    }

    @Override
    public String toString() {
        return dato.toString();
    }
  
}
