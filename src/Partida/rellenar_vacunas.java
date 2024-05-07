package Partida;

public class rellenar_vacunas {


	public static Vacunas [] main(String[] args) {
			
		Vacunas [] vacuna = new Vacunas [4];
		int i = 0;
		String[] colores = new String [] {"azul", "rojo", "verde", "amarillo"};
		String[] imperio = new String [] {"Inutilización de Darth Sidious", "Inutilización de Darth Vader", "Inutilización de General Grievous", "Inutilización de Darth Maul"};
			
		for(i = 0; i < 4; i++) {
			vacuna[i] = new Vacunas();
			vacuna[i].setcolor(colores[i]);
			vacuna[i].setporcentaje(0);
			vacuna[i].setnombre(imperio[i]);
		}
		
		/*for(i = 0; i < 4; i++) {
			System.out.println(vacuna[i].getcolor());
			System.out.println(vacuna[i].getnombre());
			System.out.println(vacuna[i].getporcentaje());
							System.out.println();
		}*/
			
		return vacuna;
		
		}
		
	

}
