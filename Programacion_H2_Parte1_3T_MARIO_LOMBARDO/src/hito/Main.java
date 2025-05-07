package hito;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        // Mostramos el menú
        do {
            System.out.println("\n------ MENÚ ------");
            System.out.println("1 – Ver películas");
            System.out.println("2 – Agregar película");
            System.out.println("3 – Eliminar película");
            System.out.println("4 – Modificar película");
            System.out.println("5 – Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = 0;
            try {
            	// Leemos la opción del usuario
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                // Mostramos un error si el usuario ingresa un valor inválido
                System.out.println("Error: Debes ingresar un número válido.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    // Ver películas
                    Cine.mostrarPeliculas();
                    break;
                case 2:
                	// Agregar película
                	try {
                		System.out.print("ID película: ");
                		int idPelicula = scanner.nextInt();
                		scanner.nextLine();
                		
                		System.out.print("Título: ");
                		String titulo = scanner.nextLine();
                		
                		System.out.print("Director: ");
                		String director = scanner.nextLine();
                		
                		System.out.print("Duración (en minutos): ");
                		int duracion = scanner.nextInt();
                		scanner.nextLine();
                		
                		System.out.print("ID del Género: ");
                		int idGenero = scanner.nextInt();
                		scanner.nextLine();
                		
                        Cine.agregarPelicula(idPelicula, titulo, director, duracion, idGenero);
                    	break;
                	} catch (InputMismatchException e) {
                        // Mostramos un error si el usuario ingresa un valor inválido
                        System.out.println("Error: Debes ingresar un número válido.");
                        scanner.nextLine();
                        continue;
                    }
                case 3:
                	// Eliminar película
                	try {
                		System.out.print("ID película: ");
                		int idPelicula = scanner.nextInt();
                		scanner.nextLine();
                		
                		Cine.eliminarPelicula(idPelicula);
                		break;
                	} catch (InputMismatchException e) {
                        // Mostramos un error si el usuario ingresa un valor inválido
                        System.out.println("Error: Debes ingresar un número válido.");
                        scanner.nextLine();
                        continue;
                	}
                case 4:
                	// Modificar película
                	try {
                		System.out.print("ID de la película a modificar: ");
                		int idPelicula = scanner.nextInt();
                		scanner.nextLine();
                		
                		System.out.print("Nuevo título: ");
                		String titulo = scanner.nextLine();
                		
                		System.out.print("Nuevo director: ");
                		String director = scanner.nextLine();
                		
                		Cine.modificarPelicula(idPelicula, titulo, director);
                		break;
                	} catch (InputMismatchException e) {
                        // Mostramos un error si el usuario ingresa un valor inválido
                        System.out.println("Error: Debes ingresar un número válido.");
                        scanner.nextLine();
                        continue;
                	}
                case 5:
                    // Salir
                    continuar = false;
                    System.out.println("Saliendo...");
                    break;
                default:
                    // Mostramos un mensaje si la opción no es válida
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (continuar);
        
        // Cerramos el scanner
        scanner.close();
    }
}