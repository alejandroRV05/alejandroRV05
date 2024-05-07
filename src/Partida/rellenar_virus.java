package Partida;

public class rellenar_virus {

	public static Virus [] main(String[] args) {
		Virus[] viruses = new Virus [4];
		int i = 0;
		String id = "";
		String[] colores = new String [] {"azul", "rojo", "verde", "amarillo"};
		String[] imperio = new String [] {"Darth Sidious", "Darth Vader", "General Grievous", "Darth Maul"};

	
		for(i = 0; i < 4; i++) {
			id = "";
			viruses[i] = new Virus();
			viruses[i].setcolor(colores[i]);
			viruses[i].setnombre(imperio[i]);
			id = id + i;
			viruses[i].setidentificador(id);	
		}
	
		
		/*for(i = 0; i < 4; i++) {
			System.out.println(viruses[i].getcolor());
			System.out.println(viruses[i].getidentificador());
			System.out.println(viruses[i].getnombre());
			System.out.println();
		}*/
		
		return viruses;

	}

}
