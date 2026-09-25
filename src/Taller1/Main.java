package Taller1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    static Scanner scanner = new Scanner(System.in);
    
    // datos de alumnos
    static String[] nombresAlumnos = new String[100];
    static String[] apellidosAlumnos = new String[100];
    static String[] rutAlumnos = new String[100];
    static String[] paralelosAlumnos = new String[100];
    static int cantidadAlumnos = 0;

    // datos de solicitudes
    static String[] nombresSolicitudes = new String[100];
    static String[] apellidosSolicitudes = new String[100];
    static int cantidadSolicitudes = 0;
    
    //datos de admitidos
    static String[] nombresAdmitidos = new String[100];
    static String[] apellidosAdmitidos = new String[100];
    static String[] rutAdmitidos = new String[100];
    static String[] paralelosAdmitidos = new String[100];
    static int cantidadAdmitidos = 0;
    
    //datos de rechazados
    static String[] nombresRechazados = new String[100];
    static String[] apellidosRechazados = new String[100];
    static int cantidadRechazados = 0;
    static String[] rutRechazados = new String[100];
    
    
    static int numeroReporte = 1;

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
                cargarAlumnos();
                cargarSolicitudes();
                break;

            case 2:
                procesarSolicitudes();
                break;

            case 3:
            	menuInscripcionManual();
                break;

            case 4:
                menuAdministracion();
                break;

            case 5:
                generarReportes();
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
    
    //método para cargar el archivo de Alumnos y almacenar los datos
    public static void cargarAlumnos()
    {    	
    	try 
    	{
    		File archivo = new File("Alumnos.txt");
			Scanner lector = new Scanner(archivo);
			
			cantidadAlumnos = 0;
			
			while (lector.hasNextLine() && cantidadAlumnos < 100)
			{
				String linea = lector.nextLine();
				String[] datos = linea.split(";");
				
				nombresAlumnos[cantidadAlumnos] = datos[0];
				apellidosAlumnos[cantidadAlumnos] = datos[1];
				rutAlumnos[cantidadAlumnos] = datos[2];
				paralelosAlumnos[cantidadAlumnos] = datos[3];
				
				cantidadAlumnos++;				
			}
			
			lector.close();
			
			System.out.println("Alumnos cargados: " + cantidadAlumnos);
			
		} catch (FileNotFoundException e) 
    	{
			System.out.println("Archivo Alumnos.txt no encontrado");
		}
    }
    
  //método para cargar el archivo de Solicitudes y almacenar los datos
    public static void cargarSolicitudes()
    {
    	
		try 
		{
			File archivo = new File("Solicitudes.txt");
			Scanner lector = new Scanner(archivo);
			
			cantidadSolicitudes = 0;
			
			while (lector.hasNextLine() && cantidadSolicitudes < 100)
			{
				String linea = lector.nextLine();
				String[] datos = linea.split("-");
				
				nombresSolicitudes[cantidadSolicitudes] = datos[0];
				apellidosSolicitudes[cantidadSolicitudes] = datos[1];
				
				cantidadSolicitudes++;
			}
			
			lector.close();
			
			System.out.println("Solicitudes cargadas: " + cantidadSolicitudes);
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Archivo Solicitudes.txt no encontrado");
		}
    }
    
    //metodo para procesar solicitudes
    public static void procesarSolicitudes() 
    {
    	cantidadAdmitidos = 0;
    	cantidadRechazados = 0;
    	
    	for (int i = 0; i < cantidadSolicitudes; i++)
    	{
    		boolean encontrado = false;
    		
    		for (int j = 0; j < cantidadAlumnos; j++)
    		{
    			if (nombresSolicitudes[i].equalsIgnoreCase(nombresAlumnos[j]) && apellidosSolicitudes[i].equalsIgnoreCase(apellidosAlumnos[j]))
    			{
    				if (!yaEstaAdmitido(rutAlumnos[j]))
    				{
    					nombresAdmitidos[cantidadAdmitidos] = nombresAlumnos[j];
    					apellidosAdmitidos[cantidadAdmitidos] = apellidosAlumnos[j];
    					rutAdmitidos[cantidadAdmitidos] = rutAlumnos[j];
    					paralelosAdmitidos[cantidadAdmitidos] = paralelosAlumnos[j];

    					cantidadAdmitidos++;
    				}
    					
    				encontrado = true;
    				break;
    			}
    		}
    		
    		if (!encontrado)
    		{
    			nombresRechazados[cantidadRechazados] = nombresSolicitudes[i];
    			apellidosRechazados[cantidadRechazados] = apellidosSolicitudes[i];
    			
    			cantidadRechazados++;
    		}
    	}
    	
    	System.out.println("Admitidos: " + cantidadAdmitidos);
    	System.out.println("Rechazados: " + cantidadRechazados);
    }
    
    //metodo que verifica que un alumno ya esta admitido y permite realizar el filtro automáticoo
    public static boolean yaEstaAdmitido(String rut)
    {
    	for (int i = 0; i < cantidadAdmitidos; i++)
    	{
    		if (rutAdmitidos[i].equalsIgnoreCase(rut))
    		{
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    //metodo para inscribir manualmente a una persona por su nombre y apellido
    public static void inscripcionManualPorNombre()
    {
    	System.out.println("Ingrese nombre: ");
    	String nombre = scanner.nextLine();
    	
    	System.out.println("Ingrese apellido: ");
    	String apellido = scanner.nextLine();
    	
    	boolean encontrado = false;
    	
    	for (int i = 0; i < cantidadAlumnos; i++)
    	{
    		if (nombre.equalsIgnoreCase(nombresAlumnos[i]) && apellido.equalsIgnoreCase(apellidosAlumnos[i]))
    		{
    			encontrado = true;
    			
    			if (!yaEstaAdmitido(rutAlumnos[i]))
				{
					nombresAdmitidos[cantidadAdmitidos] = nombresAlumnos[i];
					apellidosAdmitidos[cantidadAdmitidos] = apellidosAlumnos[i];
					rutAdmitidos[cantidadAdmitidos] = rutAlumnos[i];
					paralelosAdmitidos[cantidadAdmitidos] = paralelosAlumnos[i];

					cantidadAdmitidos++;
					
					System.out.println("Alumnos agregado al grupo");
				} else
				{
					System.out.println("El Alumno ya esta en el grupo");
				}
    			
    			break;
    		}
    	}
    	
    	if (!encontrado)
    	{
    		nombresRechazados[cantidadRechazados] = nombre;
    		apellidosRechazados[cantidadRechazados] = apellido;
    		cantidadRechazados++;
    		
    		System.out.println("La persona no pertenece al curso");
    	}
    }
    
  //metodo para inscribir manualmente a una persona por su rut
    public static void inscripcionManualPorRut()
    {
    	System.out.println("Ingrese RUT (Sin puntos y con guión): ");
    	String rut = scanner.nextLine();
    	
    	boolean encontrado = false;
    	
    	for (int i = 0; i < cantidadAlumnos; i++)
    	{
    		if (rut.equalsIgnoreCase(rutAlumnos[i]))
    		{
    			encontrado = true;
    			
    			if (!yaEstaAdmitido(rutAlumnos[i]))
    			{
    				nombresAdmitidos[cantidadAdmitidos] = nombresAlumnos[i];
					apellidosAdmitidos[cantidadAdmitidos] = apellidosAlumnos[i];
					rutAdmitidos[cantidadAdmitidos] = rutAlumnos[i];
					paralelosAdmitidos[cantidadAdmitidos] = paralelosAlumnos[i];

					cantidadAdmitidos++;
					
					System.out.println("Alumnos agregado al grupo");
    			} else
    			{
    				System.out.println("El Alumno ya esta en el grupo");
    			}
    			break;
    		}   		
    	}
    	
    	if (!encontrado)
    	{
    		System.out.println("No se encontro un alumno con ese Rut");
    	}
    
    }
    
    //sub menu opcion 3 menu principal, llama al metodo dependiendo del tipo de busqueda
    public static void menuInscripcionManual()
    {
    	int opcion;
    	
    	System.out.println("1) Buscar por nombre y apellido");
    	System.out.println("2) Buscar por RUT");
    	System.out.println("Ingrese opcion: ");
    	
    	if (scanner.hasNextLine())
    	{
    		opcion = scanner.nextInt();
    		scanner.nextLine();
    		
    		if (opcion == 1)
    		{
    			inscripcionManualPorNombre();
    		}
    		else if (opcion == 2)
    		{
    			inscripcionManualPorRut();
    		}
    		else
    		{
    			System.out.println("Opcion invalida");
    		}
    	}
    	else
    	{
    		scanner.nextLine();
    		System.out.println("Opcion invalida");
    	}
    }
    
    public static void menuAdministracion()
    {
    	int opcion = 0;
    	
    	do
    	{
    		System.out.println("---Administracion del curso---");
    		System.out.println("1) Cambiar paralelo de un alumno");
    		System.out.println("2) Eliminar alumno del curso");
    		System.out.println("3) inscribir alumno nuevo");
    		System.out.println("4) Volver");
    		System.out.println("Ingrese opcion: ");
    		
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
    			cambiarParalelo();
    			break;
    			
    		case 2:
    			eliminarAlumno();
    			break;
    		case 3:
    			inscribirAlumnoNuevo();
    			break;
    		case 4:
    			System.out.println("Volviendo al menu principal");
    			break;
    		default:
    			System.out.println("Opcion invalida");
    		}
    		
    		System.out.println();
    		
    	} while (opcion != 4);
    }
    
    public static void cambiarParalelo()
    {
    	System.out.println("Ingrese RUT del alumno (Sin puntos y con guión): ");
    	String rut = scanner.nextLine();
    	
    	boolean encontrado = false;
    	
    	for (int i = 0; i < cantidadAlumnos; i++)
    	{
    		if (rut.equalsIgnoreCase(rutAlumnos[i]))
    		{
    			encontrado = true;
    			
    			System.out.println("Alumno: " + nombresAlumnos[i] + " " + apellidosAlumnos[i]);
    			System.out.println("Paralelo actual: " + paralelosAlumnos[i]);
    			System.out.println("Ingrese nuevo paralelo (C1/C2): ");
    			
    			String nuevoParalelo = scanner.nextLine();
    			
    			if (nuevoParalelo.equalsIgnoreCase("C1") || nuevoParalelo.equalsIgnoreCase("C2"))
    			{
    				paralelosAlumnos[i] = nuevoParalelo.toUpperCase();
    				
    				for (int j = 0; j < cantidadAdmitidos; j++)
    				{
    					if (rutAdmitidos[j].equalsIgnoreCase(rut))
    					{
    						paralelosAdmitidos[j] = nuevoParalelo.toUpperCase();
    						break;
    					}
    				}
    				
    				guardarAlumnos();
    				
    				System.out.println("Paralelo actualizado");
    			} else 
    			{
    				System.out.println("Paralelo invalido");
    			}
    			
    			break;
    		}
    	}
    	
    	if (!encontrado)
    	{
    		System.out.println("Alumno no encontrado");
    	}
    }
    
    public static void eliminarAlumno()
    {
    	System.out.println("Ingrese RUT del alumno (Sin puntos y con guión): ");
    	String rut = scanner.nextLine();
    	
    	boolean encontrado = false;
    	
    	for (int i = 0; i < cantidadAlumnos; i++)
    	{
    		if (rut.equalsIgnoreCase(rutAlumnos[i]))
    		{
    			encontrado = true;
    			
    			for (int j = 0; j < cantidadAlumnos; j++)
    			{
    				nombresAlumnos[j] = nombresAlumnos[j+1];
    				apellidosAlumnos[j] = apellidosAlumnos[j+1];
    				rutAlumnos[j] = apellidosAlumnos[j+1];
    				paralelosAlumnos[j] = paralelosAlumnos[j+1];
    			}
    			
    			cantidadAlumnos--;
    			
    			eliminarAdmitido(rut);
    			
    			guardarAlumnos();
    			
    			System.out.println("Alumno eliminado del curso");
    			break;
    		}
    	}
    	
    	if (!encontrado)
    	{
    		System.out.println("Alumno no encontrado");
    	}
    }
    
    public static void eliminarAdmitido(String rut)
    {
    	for (int i = 0; i < cantidadAlumnos; i++)
    	{
    		if (rutAdmitidos[i].equalsIgnoreCase(rut))
    		{
    			for (int j = 0; j < cantidadAlumnos; j++)
    			{
    				nombresAdmitidos[j] = nombresAdmitidos[j+1];
    				apellidosAdmitidos[j] = apellidosAdmitidos[j+1];
    				rutAdmitidos[j] = rutAdmitidos[j+1];
    				paralelosAdmitidos[j] = paralelosAdmitidos[j+1];
    			}
    			
    			cantidadAdmitidos--;
    			break;
    		}
    	}
    }
    
    public static void inscribirAlumnoNuevo()
    {
    	if (cantidadAlumnos >= 100)
    	{
    		System.out.println("No se pueden agregar mas alumnos");
    		return;
    	}
    	
    	System.out.println("Ingrese nombre: ");
    	String nombre = scanner.nextLine();
    	
    	System.out.println("Ingrese apellido: ");
    	String apellido = scanner.nextLine();
    	
    	System.out.println("Ingrese rut (Sin puntos y con guión): ");
    	String rut = scanner.nextLine();
    	
    	for (int i = 0; i < cantidadAlumnos; i++)
    	{
    		if (rut.equalsIgnoreCase(rutAlumnos[i]))
    		{
    			System.out.println("El RUT ya se encuentra registrado");
    			return;
    		}
    	}
    	
    	System.out.println("Ingrese paralelo (C1/C2): ");
    	String paralelo = scanner.nextLine();
    	
    	if (!paralelo.equalsIgnoreCase("C1") && !paralelo.equalsIgnoreCase("C2"))
    	{
    		System.out.println("Paralelo invalido");
    		return;
    	}
    	
    	nombresAlumnos[cantidadAlumnos] = nombre;
    	apellidosAlumnos[cantidadAlumnos] = apellido;
    	rutAlumnos[cantidadAlumnos] = rut;
    	paralelosAlumnos[cantidadAlumnos] = paralelo.toUpperCase();
    	
    	cantidadAlumnos++;
    	
    	guardarAlumnos();
    	
    	System.out.println("Alumno inscritio correctamente");
    }
    
    public static void guardarAlumnos()
    {
    	try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter("Alumnos.txt"));

			for (int i = 0; i < cantidadAlumnos; i++)
			{
				escritor.write(nombresAlumnos[i] + ";" + apellidosAlumnos[i] + ";" + rutAlumnos[i] + ";" + paralelosAlumnos[i] + ";" );
				
				escritor.newLine();
			}
			
			escritor.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al guardar Alumnos.txt");
		}
    }
    
    public static void generarReportes()
    {
    	 try {
			BufferedWriter admitidos = new BufferedWriter(new FileWriter("Admitidos_" + numeroReporte +  ".txt"));
			
			for (int i = 0; i < cantidadAdmitidos; i++)
			{
				admitidos.write(nombresAdmitidos[i] + ";" + apellidosAdmitidos[i] + ";" + rutAdmitidos[i] + ";" + paralelosAdmitidos[i] + ";");
				
				admitidos.newLine();
			}
			
			admitidos.close();
			
			BufferedWriter rechazados = new BufferedWriter(new FileWriter("Rechazados_" + numeroReporte +  ".txt"));
			
			for (int i = 0; i < cantidadRechazados; i++)
			{
				if (rutRechazados[i] != null)
				{
					rechazados.write("Sin nombre registrado, RUT: " + rutRechazados[i]);
				}
				else
				{
					rechazados.write(nombresRechazados[i] + " " + apellidosRechazados[i] + " - No pertenece a ningun paralelo del curso");
				}
				
				rechazados.newLine();
			}
			
			rechazados.close();
			
			System.out.println("Reportes generados con exito");
			
			
			numeroReporte++;
			
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al generar los reportes");
		 }
    }
    
 
    
    
    
    
    
    
    
    
    
    
}