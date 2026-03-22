/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;

import javax.swing.JOptionPane;

/**
 *
 * @author usuario 
 */
public class ProyectoFinal {

    /**
     * @param args the command line arguments
     */
    
    private Sistema sistema = new Sistema();
    
    public static void main(String[] args) {
        ProyectoFinal p = new ProyectoFinal();
        p.MenuPrincipal();
    }
    
    
    
    private void MenuPrincipal(){
        int opcion;
        
        do {
           String menu =  """
                MENU PRINCIPAL - Sistema Hospitalario
                1 - Módulo de Emergencias
                2 - Módulo de Farmacia
                3 - Salir
                """;
           
           try {
               opcion = Integer.parseInt(
                       JOptionPane.showInputDialog(null, menu)
                );
               
               switch(opcion){
                   case 1:
                       emergencias();
                       break;
                       
                   case 2:
                       farmacia();
                       break;
                       
                   case 3:
                       JOptionPane.showMessageDialog(null, "Saliendo");
                       break;

                   default:
                       JOptionPane.showMessageDialog(null, "Opción no válida");
                        
               }
           }catch(Exception e){
               JOptionPane.showMessageDialog(null, "Entrada inválida");
               opcion=0;
           }
        }while (opcion!=3);       
    }

    
    private void emergencias(){
        int opcion;
        
        do {
           String menu =  """
                MENU PARA PACIENTES EN EMERGENCIAS
                1 - Registro de Pacientes
                2 - Atender Paciente
                3 - Mostrar colas de pacientes
                4 - Volver al Menú Principal
                """;
           
           try {
               opcion = Integer.parseInt(
                       JOptionPane.showInputDialog(null, menu)
                );
               
               switch(opcion){
                   case 1:
                       registrarPaciente();
                       break;
                       
                   case 2:
                       atenderPaciente();
                       break;
                       
                   case 3:
                       JOptionPane.showMessageDialog(null,
                                sistema.toString());
                       break;
                       
                    case 4:
                       JOptionPane.showMessageDialog(null, "Volviendo al Menú Principal");
                       break;

                   default:
                       JOptionPane.showMessageDialog(null, "Opción no válida");
                        
               }
           }catch(Exception e){
               JOptionPane.showMessageDialog(null, "Entrada inválida");
               opcion=0;
           }
        }while (opcion!=4);      
    }
        
        
    
    private void registrarPaciente(){
        
        String nombre = JOptionPane.showInputDialog("Nombre:");
        String id = JOptionPane.showInputDialog("ID:");        
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));        
        String motivo = JOptionPane.showInputDialog("Motivo:");        
        String urgencia = JOptionPane.showInputDialog("Nivel de urgencia (ALTA, MEDIA, BAJA):").toUpperCase();
        String hora = JOptionPane.showInputDialog("Hora de llegada:");
        
        if (!urgencia.equals("ALTA") &&
            !urgencia.equals("MEDIA") &&    
            !urgencia.equals("BAJA")){
        
        JOptionPane.showMessageDialog(null, "ERROR: debe ingresar ALTA, MEDIA o BAJA como urgencia");
        return;
    }
    Paciente paciente = new Paciente(
            nombre, id, edad, motivo, urgencia, hora
    );       
    
    boolean registrado  =sistema.registrarPaciente(paciente);
    
    if (registrado)
        JOptionPane.showMessageDialog(null, 
                "Paciente registrado Con éxito");
    
    else
        JOptionPane.showMessageDialog(null, 
                "Error de registro");        
        
    }
    
    private void atenderPaciente(){
        Paciente p = sistema.atenderPaciente();
        
        if (p != null){
            JOptionPane.showMessageDialog(null,
                    "Atendiendo:\n"+p);
        }else{
            JOptionPane.showMessageDialog(null,
                    "No hay pacientes en espera");            
        }
    }
    
    
    private void farmacia(){
        int opcion;
        
        do {
           String menu =  """
                MENÚ DE FARMACIA
                1 - Agregar receta
                2 - Despachar receta
                3 - Mostrar recetas
                4 - Volver al Menú Principal
                """;
           
           try {
               opcion = Integer.parseInt(
                       JOptionPane.showInputDialog(null, menu)
                );
               
               switch(opcion){
                   case 1:
                       String id = JOptionPane.showInputDialog("ID (cédula) del paciente: ");
                       String nombre = JOptionPane.showInputDialog("Nombre del paciente: ");                       
                       String medicamento = JOptionPane.showInputDialog("Medicamento(s) recetados al paciente: ");
                       
                       Receta r = new Receta(id, nombre, medicamento);
                       sistema.subirReceta(r);
                       JOptionPane.showMessageDialog(null, "Receta Agregada a la Pila");
                       
                       break;
                       
                   case 2:
                       Receta entregada = sistema.entregarReceta();
                       
                       if(entregada !=null)
                           JOptionPane.showMessageDialog(null, "Entregado:\n " + entregada);
                       
                       else
                           JOptionPane.showMessageDialog(null, "Sin Recetas Pendientes");
                       break;
                       
                   case 3:
                       JOptionPane.showMessageDialog(null,
                                sistema.mostrarRecetas());
                       break;
                       
                    case 4:
                       JOptionPane.showMessageDialog(null, "Volviendo al Menú Principal");
                       break;

                   default:
                       JOptionPane.showMessageDialog(null, "Opción no válida");
                        
               }
           }catch(Exception e){
               JOptionPane.showMessageDialog(null, "Entrada inválida");
               opcion=0;
           }
        }while (opcion!=4);       
    }        
    
}
