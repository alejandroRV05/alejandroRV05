package Modelo;

public class Ciudad {

	String nombre;
	int [] coordenadas;
	String enfermedad;
	int infeccion;
	String [] ciudadesColindantes;
	
	
	// Getters
	public String getnombre() {
		return this.nombre;
	}
	public int[] getcoordenadas() {
		return this.coordenadas;
	}
	public String getenfermedad() {
		return this.enfermedad;
	}
	public int getinfeccion() {
		return this.infeccion;
	}
	public String[] getciudadesColindantes() {
		return this.ciudadesColindantes;
	}
	
	
	// Setters
	public void setnombre(String n) {
		this.nombre = n;
	}
	public void setcoordenadas(int[] c) {
		this.coordenadas= c;
	}
	public void setcolor(String e) {
		this.enfermedad = e;
	}
	public void setinfeccion(int i) {
		this.infeccion = i;
	}
	public void setciudadesColindantes(String[] cc) {
		this.ciudadesColindantes = cc;
	}

	
	// Funciones
	public void aumentarinfeccion() {

	}
	
	public void disminuirinfeccion() {

	}
	
	public void propagarinfeccion() {

	} 
}
