// Alexia Bernal - 21.505.877-8- ICCI

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
    
    
    static int versionC1 = 1;
    static int versionC2 = 1;
    static int versionRechazados = 1;
    
    static boolean archivosCargados = false;
    
    static int intentosManuales = 0;

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
                boolean alumnosCargados = cargarAlumnos();
                boolean solicitudesCargados = cargarSolicitudes();
                
                archivosCargados = alumnosCargados && solicitudesCargados;
                
                if (!archivosCargados)
                {
                	System.out.println("No se pudieron cargar todos los archivos");
                }
                
                
                break;

            case 2:
                
            	if (!archivosCargados)
            	{
            		System.out.println("Primero debe cargar los archivos");
            	}
            	else 
            	{
            		procesarSolicitudes();
            	}
            	
                break;

            case 3:
            	
            	if (!archivosCargados)
            	{
            		System.out.println("Primero debe cargar los archivos");
            	}
            	else 
            	{
            		menuInscripcionManual();
            	}
            	
                break;

            case 4:
            	
            	if (!archivosCargados)
            	{
            		System.out.println("Primero debe cargar los archivos");
            	}
            	else 
            	{
            		menuAdministracion();
            	}
            	                
                break;

            case 5:
            	
            	if (!archivosCargados)
            	{
            		System.out.println("Primero debe cargar los archivos");
            	}
            	else 
            	{
            		menuReportes();
            	}
            	                
                break;

            case 6:
            	
            	if (!archivosCargados)
            	{
            		System.out.println("Primero debe cargar los archivos");
            	}
            	else 
            	{
            		analisisEstadistico();
            	}
                
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
    public static boolean cargarAlumnos()
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
				
				if (datos.length == 4)
				{
					nombresAlumnos[cantidadAlumnos] = datos[0].trim();
					apellidosAlumnos[cantidadAlumnos] = datos[1].trim();
					rutAlumnos[cantidadAlumnos] = datos[2].trim();
					paralelosAlumnos[cantidadAlumnos] = datos[3].trim();
				
					cantidadAlumnos++;
				}
				
				else
				{
					System.out.println("Linea invalida en Alumnos.txt: " + linea);
				}
				
								
			}
			
			lector.close();
			
			System.out.println("Alumnos cargados: " + cantidadAlumnos);
			
			return true;
			
		} catch (FileNotFoundException e) 
    	{
			System.out.println("Archivo Alumnos.txt no encontrado");
			return false;
		}
    }
    
  //método para cargar el archivo de Solicitudes y almacenar los datos
    public static boolean cargarSolicitudes()
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
				
				if (datos.length == 2)
				{
					nombresSolicitudes[cantidadSolicitudes] = datos[0].trim();
					apellidosSolicitudes[cantidadSolicitudes] = datos[1].trim();
				
					cantidadSolicitudes++;
				}
				else
				{
					System.out.println("Linea invalida en Solicitudes.txt: " + linea);
				}
				
				
			}
			
			lector.close();
			
			System.out.println("Solicitudes cargadas: " + cantidadSolicitudes);
			
			return true;
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Archivo Solicitudes.txt no encontrado");
			
			return false;
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
    			rutRechazados[cantidadRechazados] = null;
    			
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
    	intentosManuales++;
    	
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
    	intentosManuales++;
    	
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
    		nombresRechazados[cantidadRechazados] = "Sin nombre registrado";
    		apellidosRechazados[cantidadRechazados] = "";
    		rutRechazados[cantidadRechazados] = rut;
    		
    		cantidadRechazados++;
    		
    		System.out.println("El RUT no pertenece a ningun paralelo del curso");
    	}
    
    }
    
    //sub menu opcion 3 menu principal, llama al metodo dependiendo del tipo de busqueda
    public static void menuInscripcionManual()
    {
    	int opcion;
    	
    	System.out.println("1) Buscar por nombre y apellido");
    	System.out.println("2) Buscar por RUT");
    	System.out.println("Ingrese opcion: ");
    	
    	if (scanner.hasNextInt())
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
    			
    			for (int j = i; j < cantidadAlumnos - 1; j++)
    			{
    				nombresAlumnos[j] = nombresAlumnos[j+1];
    				apellidosAlumnos[j] = apellidosAlumnos[j+1];
    				rutAlumnos[j] = rutAlumnos[j+1];
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
    	for (int i = 0; i < cantidadAdmitidos; i++)
    	{
    		if (rutAdmitidos[i].equalsIgnoreCase(rut))
    		{
    			for (int j = i; j < cantidadAdmitidos - 1; j++)
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
    	
    	if (nombre.trim().isEmpty() || apellido.trim().isEmpty() || rut.trim().isEmpty())
    	{
    		System.out.println("Los datos no pueden quedar vacios");
    		return;
    	}
    	
    	nombre = nombre.trim();
    	apellido = apellido.trim();
    	rut = rut.trim();
    	
    	
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
				escritor.write(nombresAlumnos[i] + ";" + apellidosAlumnos[i] + ";" + rutAlumnos[i] + ";" + paralelosAlumnos[i]);
				
				escritor.newLine();
			}
			
			escritor.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al guardar Alumnos.txt");
		}
    }
    

    
    public static void menuReportes()
    {
    	int opcion;
    	
    	System.out.println("---Generar reportes---");
    	System.out.println("1) Reporte paralelo C1");
    	System.out.println("2) Reporte paralelo C2");
    	System.out.println("3) Reporte de rechazados");
    	System.out.println("4) volver");
    	System.out.println("Ingrese opcion: ");
    	
    	if (scanner.hasNextInt())
    	{
    		opcion = scanner.nextInt();
    		scanner.nextLine();
    		
    		switch (opcion)
    		{
    		case 1:
    			generarReporteC1();
    			break;
    			
    		case 2:
    			generarReporteC2();
    			break;
    			
    		case 3:
    			generarReporteRechazados();
    			break;
    			
    		case 4:
    			break;
    			
    		default:
    			System.out.println("Opcion invalida");
    		}
    	}
    	
    	else
    	{
    		scanner.nextLine();
    		System.out.println("Opcion invalida");
    	}
    }
    
    public static void generarReporteC1()
    {
    	BufferedWriter escritor;
		try {
			escritor = new BufferedWriter(new FileWriter("ReporteC1-V" + versionC1 + ".txt"));
			
			escritor.write("=== Miembros del grupo - Paralelo C1 ===");
			escritor.newLine();
			
			for (int i = 0; i < cantidadAdmitidos; i++)
			{
				if (paralelosAdmitidos[i].equalsIgnoreCase("C1"))
				{
					escritor.write(nombresAdmitidos[i] + " " + apellidosAdmitidos[i] + " - " + rutAdmitidos[i]);
					
					escritor.newLine();
				}
			}
			
			escritor.close();
			
			System.out.println("Reporte C1 generado");
			versionC1++;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al generar el reporte C1");
		}    	
    }
    
    public static void generarReporteC2()
    {
    	BufferedWriter escritor;
		try {
			escritor = new BufferedWriter(new FileWriter("ReporteC2-V" + versionC2 + ".txt"));
			
			escritor.write("=== Miembros del grupo - Paralelo C2 ===");
			escritor.newLine();
			
			for (int i = 0; i < cantidadAdmitidos; i++)
			{
				if (paralelosAdmitidos[i].equalsIgnoreCase("C2"))
				{
					escritor.write(nombresAdmitidos[i] + " " + apellidosAdmitidos[i] + " - " + rutAdmitidos[i]);
					
					escritor.newLine();
				}
			}
			
			escritor.close();
			
			System.out.println("Reporte C2 generado");
			versionC2++;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al generar el reporte C2");
		}   	
    }
    
    public static void generarReporteRechazados()
    {
    	BufferedWriter escritor;
		try {
			escritor = new BufferedWriter(new FileWriter("Rechazados-V" + versionRechazados + ".txt"));			
			
			for (int i = 0; i < cantidadRechazados; i++)
			{
				if (rutRechazados[i] != null)
				{
					escritor.write("Sin nombre registrado, RUT: " + rutRechazados[i]);
				}
				else
				{
					escritor.write(nombresRechazados[i] + " " + apellidosRechazados[i] + " - No pertenece a ningun paralelo del curso");
				}
				
				escritor.newLine();
			}
			
			escritor.close();
			
			System.out.println("Reporte de rechazados generado");
			versionRechazados++;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al generar el reporte de rechazados");
		}
    	
    	
    }
    
 
    public static void analisisEstadistico()
    {
    	int totalIntentos = cantidadSolicitudes + intentosManuales;
    	
    	if (totalIntentos == 0)
    	{
    		System.out.println("No hay datos para realizar el analisis");
    		return;
    	}
    	
    	double porcentajeRechazo = (cantidadRechazados * 100.0) / totalIntentos;
    	
    	double tasaAdmision = (cantidadAdmitidos * 100.0) / totalIntentos;
    	
    	int admitidosC1 = 0;
    	int admitidosC2 = 0;
    	
    	for (int i = 0; i < cantidadAdmitidos; i++)
    	{
    		if (paralelosAdmitidos[i].equalsIgnoreCase("C1"))
    		{
    			admitidosC1++;
    		}
    		else if (paralelosAdmitidos[i].equalsIgnoreCase("C2"))
    		{
    			admitidosC2++;
    		}
    	}
    	
    	System.out.println("---Analisis Estadistico---");
    	System.out.println("Total de intentos de ingreso: " + totalIntentos);
    	System.out.println("Rechazados: " + cantidadRechazados + " (" + porcentajeRechazo + "%)");
    	System.out.println("Admitidos por paralelo:");
    	System.out.println("- C1: " + admitidosC1);
    	System.out.println("- C2: " + admitidosC2);
    	System.out.println("Tasa de admision: " + tasaAdmision + "%");
    }
    
    
    
    
    
    
    
    
    
}