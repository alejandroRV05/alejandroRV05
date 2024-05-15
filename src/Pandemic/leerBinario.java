//Autors: Alejandro Rodríguez i Andreu Zapater
package Pandemic;

import java.io.*;
import java.util.ArrayList;

public class leerBinario {

	public static void main(String[] args) {
		File NomFit = new File("CCP.bin");
		File ciudades = new File("ciudades.txt");
		File ciudadEnf = new File("ciudades-enfermedad.bin");
		ArrayList<String> imperio = new ArrayList<String>();
		int [] val = new int[1];
		int i = 0;
		int j = 0;
		String texto = "";
		String ciudad = "";
		
		//Se añaden los siguientes valores al arrayList
		imperio.add("Darth Sidious");
		imperio.add("Darth Vader");
		imperio.add("General Grievous");
		imperio.add("Darth Maul");
		
		//Leemos el fichero CCP
		try {
			DataInputStream leerFichero = new DataInputStream(new FileInputStream(NomFit));
			
			
			System.out.println(leerFichero.readUTF());
			System.out.println(leerFichero.readInt());
			System.out.println(leerFichero.readUTF());
			System.out.println(leerFichero.readUTF());
				
			System.out.println(leerFichero.readInt());
			System.out.println(leerFichero.readUTF());
			System.out.println(leerFichero.readUTF());
				
			System.out.println(leerFichero.readInt());
			System.out.println(leerFichero.readUTF());
			System.out.println(leerFichero.readUTF());
				
			System.out.println(leerFichero.readInt());
			System.out.println(leerFichero.readUTF());
			System.out.println(leerFichero.readUTF());
				
			System.out.println(leerFichero.readInt());
			System.out.println(leerFichero.readInt());
			leerFichero.close();
			
					
		}catch(EOFException e) {
			System.out.println("Final fichero");
		} catch (IOException e) {
			System.out.println("Error I/O: " + e);
		}
		
		//Escribimos en el fichero ciudades-enfermedad
		try {
			DataOutputStream escribeFichero = new DataOutputStream(new FileOutputStream(ciudadEnf,false));

			
			try {
				FileReader fr = new FileReader(ciudades);
				BufferedReader br = new BufferedReader(fr);
				
				//Bucle para obtener el nombre de cada ciudade del fichero ciudades
				do {
					val[0] = 0;
					texto = br.readLine();
					if(texto != null) {
						ciudad = devolverCiudad(texto, val);
						ciudad = ciudad + "-" + imperio.get(j);
						escribeFichero.writeUTF(ciudad);
						//System.out.println(texto);
					}
					i++;
					//Cambiamos la palabra del arrayList cada 12 ciudades
					if((i%12) == 0) {
						j++;
					}
				}while(texto != null);
				
				fr.close();
				br.close();
				
			}catch (IOException e) {
				System.out.println("Error E/S: " + e);
			}
			escribeFichero.close();
					
		}catch(IOException e) {
			System.out.println("Error I/O: " + e);
		}
		
		//Leemos el fichero que hemos generado
		try {
			DataInputStream leerFichero = new DataInputStream(new FileInputStream(ciudadEnf));
			i = 0;
			do{
				System.out.println(leerFichero.readUTF());
			}while(i != 48);
			
			
			leerFichero.close();
			
					
		}catch(EOFException e) {
			System.out.println("Final fichero");
		} catch (IOException e) {
			System.out.println("Error I/O: " + e);
		}
		
		
		

	}
	
	//************************************************************************************
	//** Nombre de la función: devolverCiudad
	//** Explicación del que hace la función: Devuelve el nombre de la ciudad
	//** Parámetros de entrada: String total, int [] val
	//** Parámetros de salida: String nombreCiudad
	//************************************************************************************	
	public static String devolverCiudad(String total, int [] val) {
		String nombreCiudad = "";
		int i = 0;
		
		for(i = 0; total.charAt(i) != ';'; i++) {
			nombreCiudad = nombreCiudad + total.charAt(i);
		}
		val[0] = i + 1;
		
		
		
		return nombreCiudad;
	}

}
