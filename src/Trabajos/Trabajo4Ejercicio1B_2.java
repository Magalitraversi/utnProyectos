package Trabajos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Trabajo4Ejercicio1B_2 {
	public static void main(String[] args) throws FileNotFoundException {

		// EJERCICIO 1B/////

		Scanner scan = new Scanner(System.in);
		int[] otrosNumeros = new int[3];
		for (int i = 0; i < 3; i++) {
			System.out.print("Ingrese el número " + (i + 1) + ": ");
			otrosNumeros[i] = scan.nextInt();
		}
		System.out.print("Ingrese 'a' para orden ascendente o 'd' para orden descendente: ");
		char ordenar = scan.next().charAt(0);
		scan.close();
		if (ordenar == 'a') {
			Arrays.sort(otrosNumeros);
		} else if (ordenar == 'd') {
			Arrays.sort(otrosNumeros);
			int[] numerosDescendentes = new int[3];
			for (int i = 0; i < 3; i++) {
				numerosDescendentes[i] = otrosNumeros[2 - i];
			}
			otrosNumeros = numerosDescendentes;
		} else {
			System.out.println("La letra debe ser 'a' para ascendente o 'd' para descendente");
			return;
		}
		System.out.println(Arrays.toString(otrosNumeros));

		// EJERCICIO 2 ////////

		String rutaArchivo = "numeros.txt";

		File archivoNumeros = new File(rutaArchivo); // crea el objeto

		if (!archivoNumeros.exists()) { // verificar si el archivo existe
			System.out.println("El archivo " + rutaArchivo + " no se encuentra.");
			return;
		}

		char op = 'm'; // ('s' para suma, 'm' para multiplicación)

		int resultado = realizarOperacion(archivoNumeros, op);

		System.out.println("\nEl resultado es: " + resultado);
	}

	private static int realizarOperacion(File archivo, char operacion) throws FileNotFoundException {

		int producto = 1; // valor inicial para la multiplicación
		int suma = 0; // valor inicial para la suma

		try (Scanner scannerNumeros = new Scanner(archivo)) {
			if (operacion == 's') {
				System.out.println("EJERCICIO 2: Realizando suma: ");

				while (scannerNumeros.hasNextInt()) {
					suma += scannerNumeros.nextInt();
				}

				return suma;
			}

			else if (operacion == 'm') {
				System.out.println("EJERCICIO 2:  Realizando multiplicación: ");

				while (scannerNumeros.hasNextInt()) {
					int numero = scannerNumeros.nextInt();

					if (numero == 0) {
						producto = 0;
						break;
					}

					producto *= numero;
				}

				return producto;
			}

			else {
				System.out.println("Operación no válida.");
				return 0;
			}
		}
	}
}
