package Partida;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

public static void main(String[] args) {
		
		Ciudad[] ciudades = new Ciudad[48];
		Virus[] viruses = new Virus [4];
		Vacunas [] vacuna = new Vacunas [4];
		File NomFit;
		
		//linea1 se usa para guardar la linea con los parámetros modificados del usuario
		String linea1 = "";
		//linea se usa para leer el fichero
		String linea = "";
		//LineaTot se usa para guardar todas las líneas modificadas del fichero
		String lineaTot = "";
		//usuario se usa para guardar lo que introduce el usuario
		String usuario = "";
		
		int i = 0;
		int j = 0;
		int infectadas = 0;
		
		Scanner entrada= new Scanner(System.in);
		
		ciudades = rellenar_ciudades.main(args);
		viruses = rellenar_virus.main(args);
		vacuna = rellenar_vacunas.main(args);
		
		System.out.println("Elige la dificultad: \n1.Fácil | 2.Normal | 3.Difícil | 4.Personalizado");
		linea = entrada.next();
		
		switch (linea) {
			case "1": 
				NomFit = new File("modo_facil.xml");
				linea1 = "";
				try {
					FileReader fr = new FileReader(NomFit);
					BufferedReader br = new BufferedReader(fr);
					
					linea = br.readLine();
					linea = br.readLine();
					linea = br.readLine();
					
					for(i = 0; i < linea.length() - 1; i++) {
						if(linea.charAt(i) == '>') {
							i++;
							while(linea.charAt(i) != '<') {
								linea1 = linea1 + linea.charAt(i);
								i++;
							}
						}
					}		
					
					br.close();
					fr.close();
				} catch (Exception e) {
					System.out.println("Error E/S: " + e);	
				}
				break;
			
			case "2":
				NomFit = new File("modo_normal.xml");
				linea1 = "";
				try {
					FileReader fr = new FileReader(NomFit);
					BufferedReader br = new BufferedReader(fr);
					
					linea = br.readLine();
					linea = br.readLine();
					linea = br.readLine();
					
					for(i = 0; i < linea.length() - 1; i++) {
						if(linea.charAt(i) == '>') {
							i++;
							while(linea.charAt(i) != '<') {
								linea1 = linea1 + linea.charAt(i);
								i++;
							}
						}
					}		
					
					br.close();
					fr.close();
				} catch (Exception e) {
					System.out.println("Error E/S: " + e);	
				}
				break;
				
			case "3":
				NomFit = new File("modo_dificil.xml");
				linea1 = "";
				try {
					FileReader fr = new FileReader(NomFit);
					BufferedReader br = new BufferedReader(fr);
					
					linea = br.readLine();
					linea = br.readLine();
					linea = br.readLine();
					
					for(i = 0; i < linea.length() - 1; i++) {
						if(linea.charAt(i) == '>') {
							i++;
							while(linea.charAt(i) != '<') {
								linea1 = linea1 + linea.charAt(i);
								i++;
							}
						}
					}		
					
					br.close();
					fr.close();
				} catch (Exception e) {
					System.out.println("Error E/S: " + e);	
				}
				break;
				
			case "4":
				//Escribir los parámetros del modo personalizado	
				NomFit = new File("modo_personalizado.xml");
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
				
				
				linea1 = "";
				try {
					FileReader fr = new FileReader(NomFit);
					BufferedReader br = new BufferedReader(fr);
					
					linea = br.readLine();
					linea = br.readLine();
					linea = br.readLine();
					
					for(i = 0; i < linea.length() - 1; i++) {
						if(linea.charAt(i) == '>') {
							i++;
							while(linea.charAt(i) != '<') {
								linea1 = linea1 + linea.charAt(i);
								i++;
							}
						}
					}		
					
					br.close();
					fr.close();
				} catch (Exception e) {
					System.out.println("Error E/S: " + e);	
				}
				break;
		
			default:
				System.out.println("Opción no reconocida");
				break;
			
		}
		
		infectadas = Integer.valueOf(linea1);
		
		i = 0;
		while(j != infectadas) {
			if((int)(Math.random()*20) == 2) {
				
				if(ciudades[i].getinfeccion() == 0) {
					ciudades[i].setinfeccion(ciudades[i].getinfeccion() + 1);
					j++;
				}
			}
			
			if(i == 47) {
				i = 0;
			}else {
				i++;
			}
			
		}
		
		
		for(i = 0; i < 48; i++) {
			if(ciudades[i].getinfeccion() != 0) {
				System.out.println(ciudades[i].getnombre());
				System.out.println(ciudades[i].getenfermedad());
				System.out.println(ciudades[i].getinfeccion());
				System.out.println(ciudades[i].coordenadas[0]);
				System.out.println(ciudades[i].coordenadas[1]);
				for(j = 0; j < ciudades[i].ciudadesColindantes.length; j++) {
					System.out.println(ciudades[i].ciudadesColindantes[j]);
				}
				System.out.println();
			}	
		}
		
		
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
			
			System.out.println("Introduce el porcentaje de desarrollo de la vacuna: ");
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
