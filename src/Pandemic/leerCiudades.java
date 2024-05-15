//Autors: Alejandro Rodríguez i Andreu Zapater
package Pandemic;

import java.io.*;
import java.util.ArrayList;
import java.lang.Math;

public class leerCiudades {

	public static void main(String[] args) {
		File NomFit = new File("ciudades.txt");
	    File ciudadesRed = new File("ciudadesRedactadas.txt");
	    int i = 0;
	    int j = 0;
	    String texto = "";
	    double resultado;

	    String nombreCiudad = "";
	    String nombreVecina = "";
	    int [] coordenadas = new int [3];
	    int [] coordenadasVecina = new int[3];
	    ArrayList<String> ciudadVecina = new ArrayList<String>();

	    int [] val = new int [1];

	    //Leemos el fichero ciudades y escribimos en el fichero ciudadesRedactadas
		try {
			FileReader fr = new FileReader(NomFit);
	        BufferedReader br = new BufferedReader(fr);
	        
	        FileWriter fw = new FileWriter(ciudadesRed,false);
	        BufferedWriter bw = new BufferedWriter(fw);
			
	        //Leemos todas las líneas de ciudades
			do {
				//Ponemos val igual a 0 para usarlo en las funciones de obtener la información de las líneas
				val[0] = 0;
				texto = br.readLine();
				//System.out.println(texto);
				if(texto != null) {
					//Obtener el nombre de la ciudad
                    nombreCiudad = devolverCiudad(texto, val);

                    //Número 1
                    coordenadas[0] = Integer.valueOf(devolverCoordenada(texto, val));
                    //Número 2
                    coordenadas[1] = Integer.valueOf(devolverCoordenada(texto, val));
                    //Número 3
                    coordenadas[2] = Integer.valueOf(devolverCoordenada(texto, val));

                    //Obtener el nombre de las ciudades vecinas
                    devolverVecinas(ciudadVecina, texto, val);

                    texto = "La ciudad " +nombreCiudad+ " está en las coordenadas (" +coordenadas[1]+ "," +coordenadas[2]+ ") sus ciudades colindantes son:";

                    bw.write(texto);
                    bw.newLine();
                    val[0] = 0;
                    
                    //Abrimos otro fileReades para buscar la información de las ciudades colindantes
                    try {
                        FileReader fr2 = new FileReader(NomFit);
                        BufferedReader br2 = new BufferedReader(fr2);
                        if (NomFit.length() != 0) {
                            val[0] = 0;
                            do {
                                texto = br2.readLine();
                                if (texto != null) {
                                	//Obtenemos el nombre de la ciudad
                                    nombreVecina = devolverCiudad(texto, val);
                                    for (i = 0; i < ciudadVecina.size(); i++) {
                                    	//Miramos si es alguna de las ciudades colindantes
                                        if (nombreVecina.equals(ciudadVecina.get(i))) {
                                            //Número 1
                                            coordenadasVecina[0] = Integer.valueOf(devolverCoordenada(texto, val));
                                            //Número 2
                                            coordenadasVecina[1] = Integer.valueOf(devolverCoordenada(texto, val));
                                            //Número 3
                                            coordenadasVecina[2] = Integer.valueOf(devolverCoordenada(texto, val));
                                            resultado = (Math.sqrt(  ((coordenadasVecina[1] - coordenadas[1])*(coordenadasVecina[1] - coordenadas[1])) + ((coordenadasVecina[2] - coordenadas[2])*(coordenadasVecina[2] - coordenadas[2]))  ) );
                                            resultado =  Math.floor(resultado * 100) / 100;;

                                            texto = "\t- " +nombreVecina+ ", que está a una distancia de " + resultado;
                                            bw.write(texto);
                                            bw.newLine();
                                        }
                                    }
                                }
                            } while (texto != null);
                            bw.newLine();
                            fr2.close();
                            br2.close();
                        } else {
                            System.out.println("El fichero está vacío");
                        }
                    } catch (Exception e) {
                        System.out.println("Error try vecino: " + e);
                    }
                    
                    ciudadVecina.removeAll(ciudadVecina);

                    val[0] = 0;
                    j++;
				}
			}while(j != 48);
			
			bw.close();
            fw.close();
            br.close();
	        fr.close();
			
		}catch (IOException e) {
			System.out.println("Error E/S: " + e);
		}
		System.out.println();
		leerFichero(ciudadesRed);
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
	
	//************************************************************************************
	//** Nombre de la función: devolverCoordenada
	//** Explicación del que hace la función: Devuelve la coordenada
	//** Parámetros de entrada: String total, int [] val
	//** Parámetros de salida: String coord
	//************************************************************************************	
	public static String devolverCoordenada(String total, int [] val) {
		int i = 0;
		String coord = "";
		
		for(i = val[0] ; total.charAt(i) != ';'; i++) {
			coord = coord + total.charAt(i);
		}
		
		val[0]  = i + 1;
		return coord;
		
		
	}
	
	//************************************************************************************
	//** Nombre de la función: devolverVecinas
	//** Explicación del que hace la función: Devuelve el nombre de las ciudades vecinas
	//** Parámetros de entrada: ArrayList<String> ciudadVecina, String total, int [] val
	//** Parámetros de salida:
	//************************************************************************************	
	public static void devolverVecinas(ArrayList<String> ciudadVecina, String total, int [] val) {
		int i = 0;
		String coord = "";
		
		for(i = val[0] ; total.charAt(i) != ';'; i++) {
			if(total.charAt(i) != ',') {
				coord = coord + total.charAt(i);
			}else {
				//i++;
				ciudadVecina.add(coord);
				coord = "";
			}
			if(total.charAt(i+1) == ';')
				ciudadVecina.add(coord);
			
		}
	}
	
	
	//************************************************************************************
		//** Nombre de la función: leerFichero
		//** Explicación del que hace la función: Muestra por pantalla el contenido del fichero
		//** Parámetros de entrada: File NomFit
		//** Parámetros de salida: 
		//************************************************************************************
		public static void leerFichero(File NomFit) {
			String texto;
			try {
				FileReader fr = new FileReader(NomFit);
				BufferedReader br = new BufferedReader(fr);
				//Miramos si el fichero tiene información
				if(NomFit.length() != 0) {
					do {
						texto = br.readLine();
						if(texto != null) {
							System.out.println(texto);
						}
					}while(texto != null);
				}else {
					System.out.println("El fichero está vacío");
				}
				
				fr.close();
				br.close();
			} catch (IOException e) {
				System.out.println("Error E/S: " + e);
			}
		}
	
	
}
