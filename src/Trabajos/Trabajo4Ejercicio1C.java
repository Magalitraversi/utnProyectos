package Trabajos;

import java.util.Arrays;
import java.util.Scanner;

public class Trabajo4Ejercicio1C {
	public static void main(String[] args) {
		int[] numeros = null;
		char orden = ' ';
		if (args.length == 4) {
			numeros = new int[3];
			for (int i = 0; i < 3; i++) {
				numeros[i] = Integer.parseInt(args[i]);
			}
			orden = args[3].charAt(0);
		} else {
			Scanner scanner = new Scanner(System.in);
			numeros = new int[3];
			for (int i = 0; i < 3; i++) {
				System.out.print("Ingrese el número " + (i + 1) + ": ");
				numeros[i] = scanner.nextInt();
			}
			System.out.print("Ingrese la letra 'A' para ordenar ascendente o 'D' para ordenar descendente: ");
			orden = scanner.next().charAt(0);
			scanner.close();
		}

		// Ordena según el orden especificado
		if (orden == 'A') {
			Arrays.sort(numeros);
		} else if (orden == 'D') {
			Arrays.sort(numeros);
			for (int i = 0; i < numeros.length / 2; i++) {
				int temporal = numeros[i];
				numeros[i] = numeros[numeros.length - 1 - i];
				numeros[numeros.length - 1 - i] = temporal;
			}
		} else {
			System.out.println("Error: el orden especificado no es válido.");
			return;
		}

		// Muestra los números ordenados
		System.out.print("Números ordenados: ");
		for (int i = 0; i < numeros.length; i++) {
			System.out.print(numeros[i] + " ");
		}
		System.out.println();

	}
}
