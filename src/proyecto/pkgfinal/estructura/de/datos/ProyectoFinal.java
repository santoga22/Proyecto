/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;

import javax.swing.JOptionPane;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter; // proyecto final

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

    private void MenuPrincipal() {
        int opcion;

        do {
            String menu = """
                MENU PRINCIPAL - Sistema Hospitalario
                1 - Módulo de Emergencias
                2 - Módulo de Farmacia
                3 - Historial Clínico
                4 - Reportes Generales          
                5 - Salir
                """;

            try {
                opcion = Integer.parseInt(
                        JOptionPane.showInputDialog(null, menu)
                );

                switch (opcion) {
                    case 1:
                        emergencias();
                        break;

                    case 2:
                        farmacia();
                        break;

                    case 3:
                        historial();
                        break;

                    case 4:
                        reportes();
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");

                    case 5:
                        JOptionPane.showMessageDialog(null, "Saliendo");
                        break;

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida");
                opcion = 0;
            }
        } while (opcion != 5);
    }

    private void emergencias() {
        int opcion;

        do {
            String menu = """
                MENU PARA PACIENTES EN EMERGENCIAS
                1 - Registro de Pacientes
                2 - Atender Paciente en pre-consulta
                3 - Mostrar colas de pacientes
                4 - Atender Pacientes valorados por prioridad 
                5 - Ver pacientes en atención pendiente
                6 - Ver historial de pacientes atendidos          
                7 - Volver al Menú Principal
                """;

            try {
                opcion = Integer.parseInt(
                        JOptionPane.showInputDialog(null, menu)
                );

                switch (opcion) {
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
                        Paciente atendido = sistema.Atencion();
                        if (atendido != null) {
                            JOptionPane.showMessageDialog(null, "Paciente atendido: " + atendido);
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay pacientes en atencion.");
                        }
                        break;

                    case 5:
                        JOptionPane.showMessageDialog(null, sistema.mostrarListaAtencion());
                        break;
                    case 6:
                        JOptionPane.showMessageDialog(null, sistema.mostrarListaHistorial());
                        break;
                    case 7:
                        JOptionPane.showMessageDialog(null, "Volviendo al Menú Principal");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida");
                opcion = 0;
            }
        } while (opcion != 7);
    }

    private void registrarPaciente() {

        String nombre = JOptionPane.showInputDialog("Nombre:");
        String id = JOptionPane.showInputDialog("ID:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
        String motivo = JOptionPane.showInputDialog("Motivo:");
        String urgencia = JOptionPane.showInputDialog("Nivel de urgencia (ALTA, MEDIA, BAJA):").toUpperCase();
        LocalTime horaSistema = LocalTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        String hora = horaSistema.format(formato);

        if (!urgencia.equals("ALTA")
                && !urgencia.equals("MEDIA")
                && !urgencia.equals("BAJA")) {

            JOptionPane.showMessageDialog(null, "ERROR: debe ingresar ALTA, MEDIA o BAJA como urgencia");
            return;
        }
        Paciente paciente = new Paciente(
                nombre, id, edad, motivo, urgencia, hora
        );

        boolean registrado = sistema.registrarPaciente(paciente);

        if (registrado) {
            JOptionPane.showMessageDialog(null,
                    "Paciente registrado Con éxito");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Error de registro");
        }

    }

    private void atenderPaciente() {
        Paciente p = sistema.atenderPaciente();

        if (p != null) {
            JOptionPane.showMessageDialog(null,
                    "Atendiendo:\n" + p);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No hay pacientes en espera");
        }
    }

    private void historial() {
        int opcion;
        do {
            String menu = """
                Historial Clínico de pacientes atendidos
                1 - Mostrar pacientes
                2 - Buscar pacientes por ID
                3 - Volver
                """;

            try {
                opcion = Integer.parseInt(
                        JOptionPane.showInputDialog(null, menu)
                );

                switch (opcion) {
                    case 1:
                        JOptionPane.showMessageDialog(null, sistema.mostrarArbol());
                        break;

                    case 2:
                        String id = JOptionPane.showInputDialog("Ingrese ID: ");
                        Paciente p = sistema.buscarEnArbol(id);

                        if (p != null) {

                            JOptionPane.showMessageDialog(null, p);
                        } else {
                            JOptionPane.showMessageDialog(null, "Paciente no fue encontrado con ID ingresado");
                        }
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Volviendo...");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida");
                opcion = 0;
            }
        } while (opcion != 3);
    }

    private void farmacia() {
        int opcion;

        do {
            String menu = """
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

                switch (opcion) {
                    case 1:
                        String id = JOptionPane.showInputDialog("Ingrese ID del paciente en atención: ");
                        Paciente paciente = sistema.buscarPacienteID(id);

                        if (paciente == null) {
                            JOptionPane.showMessageDialog(null, "Paciente no está registrado en atención, Intente nuevamente");
                            break;
                        }
                        String medicamento = JOptionPane.showInputDialog("Medicamento(s) recetados al paciente: ");
                        Receta r = new Receta(
                                paciente.getId(),
                                paciente.getNombre(),
                                medicamento
                        );
                        sistema.subirReceta(r);
                        JOptionPane.showMessageDialog(null, "Receta agregada correctamente");

                        break;

                    case 2:
                        Receta entregada = sistema.entregarReceta();

                        if (entregada != null) {
                            JOptionPane.showMessageDialog(null, "Entregado:\n " + entregada);
                        } else {
                            JOptionPane.showMessageDialog(null, "Sin Recetas Pendientes");
                        }
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
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida");
                opcion = 0;
            }
        } while (opcion != 4);
    }

    private void reportes() {
        int op;

        do {
            try {
                op = Integer.parseInt(JOptionPane.showInputDialog("""
                REPORTES GENERALES
                1 - Total de pacientes atendidos
                2 - Cantidad de pacientes por nivel de urgencia
                3 - Medicamentos más entregados
                4 - Mayor tiempo de espera de pacientes en atención pendiente
                5 - Mayor tiempo de espera de pacientes - Registro Final
                6 - Volver
            """));

                switch (op) {
                    case 1:
                        JOptionPane.showMessageDialog(null,
                                "Total atendidos: " + sistema.totalPacientesAtendidos());
                        break;

                    case 2:
                        JOptionPane.showMessageDialog(null,
                                sistema.cantidadUrgencia());
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null,
                                sistema.medicamentosMasUsados());
                        break;

                    case 4:
                        JOptionPane.showMessageDialog(null,
                                "Paciente con mayor espera actual:\n"
                                + sistema.mayorEsperaActual());
                        break;

                    case 5:
                        JOptionPane.showMessageDialog(null,
                                "Paciente con mayor espera histórica:\n"
                                +sistema.mayorEsperaHistorico());
                        break;

                    case 6:
                        JOptionPane.showMessageDialog(null, "Volviendo...");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida");
                op = 0;
            }

        } while (op != 6);
    }

}
