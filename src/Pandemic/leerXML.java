//Autors: Alejandro Rodríguez Vallín y Andreu Zapater Fillat
package Pandemic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class leerXML {

	public static void main(String[] args) {

		
		File NomFit = new File("parametros.xml");
		
		//linea1 se usa para guardar la linea con los parámetros modificados del usuario
		String linea1 = "";
		//linea se usa para leer el fichero
		String linea = "";
		//LineaTot se usa para guardar todas las líneas modificadas del fichero
		String lineaTot = "";
		//usuario se usa para guardar lo que introduce el usuario
		String usuario = "";
		//opcion se usa para guardar la opcion que escoje el usuario
		int opcion = 0;
		Scanner entrada= new Scanner(System.in);
		
		//Bucle donde se ejecuta el programa hasta que el usuario introduzca 3
		do {
			
			System.out.println("MENÚ\n");
			System.out.println("1. Mostrar información fichero | 2. Modificar fichero | 3. Salir");
			System.out.print("Opción: ");
			//Miramos si el usuario ha introducido un entero
			if(entrada.hasNextInt()) {
				
				opcion = entrada.nextInt();
				//Miramos si es la opción 1 o 2
				if(opcion >= 1 && opcion <= 2) {
					
					switch(opcion) {
						case 1:
							//Leemos el fichero
							System.out.println();
							try {
								FileReader fr = new FileReader(NomFit);
						        BufferedReader br = new BufferedReader(fr);
	
						        do {
									linea = br.readLine();
									if(linea != null) {
										System.out.println(linea);
									}
								}while(linea != null);
						        
						        br.close();
						        fr.close();
							} catch (Exception e) {
								System.out.println("Error E/S: " + e);
							}
									
							break;
							
						case 2:
							//Modificamos el fichero
							System.out.println();
							
							try {
								FileReader fr = new FileReader(NomFit);
								BufferedReader br = new BufferedReader(fr);
								
								//Obtenemos primero todo el fichero modificado
								lineaTot = lineaCompleta(entrada, lineaTot, usuario, lineaTot, linea1, fr, br);
								
								FileWriter fw = new FileWriter(NomFit,false);
								BufferedWriter bw = new BufferedWriter(fw);
								
								//Escribimos el fichero modificado
								bw.write(lineaTot);
								
								bw.close();
								fw.close();
								br.close();
								fr.close();
								
							} catch (Exception e) {
								System.out.println("Error E/S: " + e);
							}
							break;
							
					}	
					
				//Miramos que la opción no sea la 3
				}else if(opcion != 3){
					System.out.println("\n¡ERROR! La opción debe ser 1, 2 o 3");
				}
				
			}else {
				entrada.next();
				System.out.println("\n¡ERROR! Formato no válido");
			}
			
			
			System.out.println();
			
		}while(opcion != 3);
		
		
		entrada.close();		
		
	}
	
	//************************************************************************************
	//** Nombre de la función: devolverXml
	//** Explicación del que hace la función: Devuelve en string la línea con el valor introducido por el usuario
	//** Parámetros de entrada: String usuario, String linea, String linea1, Scanner entrada
	//** Parámetros de salida: String linea1
	//************************************************************************************
	public static String devolverXml(String usuario, String linea, String linea1, Scanner entrada) {
		int i = 0;
		int j = 0;
		linea1 = "";
		 
		//For para copiar todo el string hasta '>'
		for(i = 0; linea.charAt(i) != '>'; i++) {
			linea1 = linea1 + linea.charAt(i);
		}
		i++;
		//Cerramos el atributo de XML y añadimos el valor del usuario
		linea1 = linea1 + '>' + usuario;
		
		while(linea.charAt(i) != '<') {
			i++;
		}
		
		//For para copiar todo el string hasta '>'
		for(j = i; linea.charAt(j) != '>'; j++) {
			linea1 = linea1 + linea.charAt(j);
		}
		//Añadimos '>'
		linea1 = linea1 + '>';
		
		return linea1;
		
	}
	
	
	//************************************************************************************
	//** Nombre de la función: lineaCompleta
	//** Explicación del que hace la función: Devuelve en string todo el fichero modificado con los valores del usuario
	//** Parámetros de entrada: Scanner entrada, String lineaTot, String usuario, String linea, String linea1, FileReader fr, BufferedReader br
	//** Parámetros de salida: String lineaTot
	//************************************************************************************
	public static String lineaCompleta(Scanner entrada, String lineaTot, String usuario, String linea, String linea1, FileReader fr, BufferedReader br) {
		
		try {
			linea = br.readLine();
			lineaTot = linea + "\n";
			linea = br.readLine();
			lineaTot = lineaTot + linea + "\n";
			
			System.out.println("Introduce el número de ciudades infectadas al inicio: ");
			usuario = entrada.next();		
			linea = br.readLine();
			linea1 = devolverXml(usuario, linea, linea1, entrada);
			lineaTot = lineaTot + linea1 + "\n";
			
			
			System.out.println("Introduce el número de ciudades infectadas por ronda: ");
			usuario = entrada.next();
			linea = br.readLine();
			linea1 = devolverXml(usuario, linea, linea1, entrada);
			lineaTot = lineaTot + linea1 + "\n";
			
			
			System.out.println("Introduce el número de enfermedades activas para perder: ");
			usuario = entrada.next();
			linea = br.readLine();
			linea1 = devolverXml(usuario, linea, linea1, entrada);
			lineaTot = lineaTot + linea1 + "\n";
			
			
			System.out.println("Introduce el número de brotes para perder: ");
			usuario = entrada.next();
			linea = br.readLine();
			linea1 = devolverXml(usuario, linea, linea1, entrada);
			lineaTot = lineaTot + linea1 + "\n";
			
			
			linea = br.readLine();
			lineaTot = lineaTot + linea + "\n";
		} catch (Exception e) {
			System.out.println("Error E/S: " + e);
		}
		
		return lineaTot;
		
	}

}
