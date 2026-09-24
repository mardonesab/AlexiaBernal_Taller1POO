package Taller1;

import java.util.Scanner;

public class Main
{
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
    	//despliegue de las opciones del menu

        int opcion = 0;

        do
        {
            mostrarMenu();

            System.out.print("Ingrese opcion: ");

            // validacion de entrada

            if (scanner.hasNextInt())
            {
                opcion = scanner.nextInt();
                scanner.nextLine();
            }
            else
            {
                scanner.nextLine();
                opcion = 0;
            }

            switch (opcion)
            {
            case 1:
                System.out.println("Cargar archivos");
                break;

            case 2:
                System.out.println("Procesar solicitudes");
                break;

            case 3:
                System.out.println("Inscripcion manual al grupo");
                break;

            case 4:
                System.out.println("Administracion del curso");
                break;

            case 5:
                System.out.println("Generar reportes");
                break;

            case 6:
                System.out.println("Analisis estadistico");
                break;

            case 7:
                System.out.println("Programa finalizado");
                break;

            default:
                System.out.println("Opcion invalida");
            }

            System.out.println();

        } while (opcion != 7);

        scanner.close();
    }

    //opciones del menu principal

    public static void mostrarMenu()
    {
        System.out.println("==== Sistema de Control del Grupo POO ====");
        System.out.println("1) Cargar archivos (Alumnos y Solicitudes)");
        System.out.println("2) Procesar solicitudes (Filtrado automatico)");
        System.out.println("3) Inscripcion manual al grupo");
        System.out.println("4) Administracion del curso");
        System.out.println("5) Generar reportes");
        System.out.println("6) Analisis estadistico");
        System.out.println("7) Salir");
    }
}