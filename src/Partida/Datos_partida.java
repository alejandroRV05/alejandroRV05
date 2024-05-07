package Partida;

import java.util.ArrayList;

public class Datos_partida {

	ArrayList<String> ciudades;
	ArrayList<String> virus;
	ArrayList<String> vacunas;
	int brotes;
	int rondas;
	float pDesarrollo;
	int acciones;
	
	// Getters
		public ArrayList<String> getciudades() {
			return this.ciudades;
		}
		public ArrayList<String> getvirus() {
			return this.virus;
		}
		public ArrayList<String> getvacunas() {
			return this.vacunas;
		}
		public int getbrotes() {
			return this.brotes;
		}
		public int getrondas() {
			return this.rondas;
		}
		public int getacciones() {
			return this.acciones;
		}
		public float getpDesarrollo() {
			return this.pDesarrollo;
		}

		
		
		// Setters
		public void setciudades(ArrayList<String> c) {
			this.ciudades = c;
		}
		public void setvirus(ArrayList<String> v) {
			this.virus = v;
		}
		public void setvacunas(ArrayList<String> va) {
			this.vacunas = va;
		}
		public void setbrotes(int b) {
			this.brotes= b;
		}
		public void setrondas(int r) {
			this.rondas= r;
		}
		public void setacciones(int a) {
			this.acciones= a;
		}
		public void setcoordenadas(float pD) {
			this.pDesarrollo= pD;
		}
		
		// Funciones
		public void modificarCiudad() {
			
		}
		public void modificarVacuna() {
			
		}
		public void cargarDatos() {
			
		}
}