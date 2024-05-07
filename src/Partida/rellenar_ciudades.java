package Partida;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class rellenar_ciudades {

	public static Ciudad [] main(String[] args) {
		// TODO Auto-generated method stub
		Ciudad[] ciudades = new Ciudad[48];
		File ciu = new File("ciudades.txt");
		String texto = "";
		String ciudad = "";
		ArrayList<String> imperio = new ArrayList<String>();
		String [] colindantes;
		int i = 0;
		int j = 0;
		int z = 0;
		int comas = 0;
		int [] val = new int[1];
		int [] coord = new int[2];
		imperio.add("Darth Sidious");
		imperio.add("Darth Vader");
		imperio.add("General Grievous");
		imperio.add("Darth Maul");
		
	
		try {
			FileReader fr = new FileReader(ciu);
		    BufferedReader br = new BufferedReader(fr); 
		     while(texto != null) {
		    	coord = new int [2];
		    	ciudades[i] = new Ciudad();
		    	ciudades[i].coordenadas = new int[2];
				texto = br.readLine();
				val[0] = 0;
				if(texto != null) {
					ciudad = devolverCiudad(texto, val);
					ciudades[i].setnombre(ciudad);
					
                    //Número 1
					val[0] = val[0] + 2;
                    //Número 2
                    coord[0] = Integer.valueOf(devolverCoordenada(texto, val));
                    //Número 3
                    coord[1] = Integer.valueOf(devolverCoordenada(texto, val));                    
                    
					ciudades[i].setcoordenadas(coord);
					ciudades[i].setcolor(imperio.get(j));
					ciudades[i].setinfeccion(0);
					
					comas = 0;
					for(z = 0; z != texto.length(); z++) {
						if(texto.charAt(z) == ',')
							comas++;
					}

					colindantes = new String [comas + 1];
					
					comas = 0;

					while(val[0] != texto.length()) {
						ciudad = "";
						for(z = val[0]; texto.charAt(z) != ',' && texto.charAt(z) != ';'; z++) {
							ciudad = ciudad +  texto.charAt(z);
						}
						val[0] = z + 1;
						colindantes[comas] = ciudad;
						comas++;
					}
					ciudades[i].setciudadesColindantes(colindantes);
					
				}
				
				//Cambiamos la palabra del arrayList cada 12 ciudades
				if(((i+1)%12) == 0 && i != 0) {
					j++;
				}
			    
			    if(i != 48) {
					i++;
				}
	        }
		    br.close();
		    fr.close();
		} catch (Exception e) {
				// TODO: handle exception
		}
				
		
	
		/*for(i = 0; i < 48; i++) {
			System.out.println(ciudades[i].getnombre());
			System.out.println(ciudades[i].getenfermedad());
			System.out.println(ciudades[i].getinfeccion());
			//int[] coordenadas = ciudades[i].getcoordenadas();
		    //System.out.println("Coordenadas: (" + coordenadas[0] + ", " + coordenadas[1] + ")");
			System.out.println(ciudades[i].coordenadas[0]);
			System.out.println(ciudades[i].coordenadas[1]);
			for(j = 0; j < ciudades[i].ciudadesColindantes.length; j++) {
				System.out.println(ciudades[i].ciudadesColindantes[j]);
			}
			System.out.println();
		}*/
		return ciudades;
		
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
		
		for(i = 0; total.charAt(i) != ';' && total.charAt(i) != ','; i++) {
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

}
