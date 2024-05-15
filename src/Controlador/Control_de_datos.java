package Controlador;

import java.util.ArrayList;

import Pandemic.leerCiudades;

public class Control_de_datos {

	//Atributos
	String url;
	String user;
	String password;
	String ficheroTxt;
	String ficheroBin;
	String ficheroXML;
	
	
	//Funciones
	
	//Las tres funciones siguientes pueden ser de tipo ArrayList, de manera que devuelven el ArrayList completo
	// o de tipo void.
	public ArrayList<String> cargarCiudades() {
		
		ArrayList<String> ciudad = new ArrayList<String>();
		
		ciudad.add("Hola");
		//Cargar las ciudades del fichero txt
		
		return ciudad;
		
	}
	
	public ArrayList<String> cargarVacunas(){
		
		ArrayList<String> vacuna = new ArrayList<String>();
		
		vacuna.add("hola");
		
		return vacuna;
		
	}
	
	public ArrayList<String> cargarVirus(){
		
		ArrayList<String> virus = new ArrayList<String>();
		
		virus.add("Hola");
		
		return virus;
		
	}
	
	
	public void cargarPartida() {
		
	}
	
	public void guardarPartida() {
		
	}
	
	public void cargarRecord() {
		
	}
	
	public void guardarRecord() {
		
	}
	
	
	
	
	
}
