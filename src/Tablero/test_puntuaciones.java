package Tablero;

import java.util.Scanner;

public class test_puntuaciones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);


        System.out.println("Bienvenido al juego de calcular la puntuación.");
        System.out.println("Por favor, introduce el número de ciudades infectadas al inicio:");
        int ciudadesInfectadasInicio = scanner.nextInt();

        System.out.println("Por favor, introduce el número de ciudades infectadas en esta ronda:");
        int ciudadesInfectadasRonda = scanner.nextInt();

        System.out.println("Por favor, introduce el número de enfermedades activas para perder:");
        int enfermedadesActivasPerder = scanner.nextInt();

        System.out.println("Por favor, introduce el número de brotes activos para perder:");
        int brotesActivosPerder = scanner.nextInt();

        System.out.println("Por favor, introduce el porcentaje de desarrollo de la vacuna:");
        int porcentajeDesarrolloVacuna = scanner.nextInt();

        System.out.println("Por favor, introduce el número de rondas:");
        int rondas = scanner.nextInt();

        double puntuacion = calcularPuntuacion(ciudadesInfectadasInicio, ciudadesInfectadasRonda,
                                                enfermedadesActivasPerder, brotesActivosPerder,
                                                porcentajeDesarrolloVacuna, rondas);

        System.out.println("Tu puntuación final es: " + puntuacion);

        scanner.close();
    }

    public static double calcularPuntuacion(int ciudadesInfectadasInicio, int ciudadesInfectadasRonda,
                                            int enfermedadesActivasPerder, int brotesActivosPerder,
                                            int porcentajeDesarrolloVacuna, int rondas) {
        // Cálculo de la puntuación según la fórmula dada
        double puntuacion = (ciudadesInfectadasInicio * ciudadesInfectadasRonda)*10000;
        double puntuacionPartida = puntuacion - (puntuacion * (porcentajeDesarrolloVacuna / 100.0))
                                  - (puntuacion * (enfermedadesActivasPerder / 100.0))
                                  - (puntuacion * (brotesActivosPerder / 100.0));
        double puntuacionFinal = puntuacionPartida - (puntuacionPartida * (rondas / 100.0));
        return puntuacionFinal;
    }

}
