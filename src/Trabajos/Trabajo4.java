package Trabajos;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Trabajo4 {

	public static void main(String[] args) throws FileNotFoundException {

		// EJERCICIO 1A
		int[] numeros = new int[3];
		char orden = ' ';
		if (args.length == 4) {
			numeros[0] = Integer.parseInt(args[0]);
			numeros[1] = Integer.parseInt(args[1]);
			numeros[2] = Integer.parseInt(args[2]);
			orden = args[3].charAt(0);
		} else {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Ingrese el número 1: ");
			numeros[0] = scanner.nextInt();
			System.out.print("Ingrese el número 2: ");
			numeros[1] = scanner.nextInt();
			System.out.print("Ingrese el número 3: ");
			numeros[2] = scanner.nextInt();
			System.out.print("Ingrese 'a' para orden ascendente o 'd' para orden descendente: ");
			orden = scanner.next().charAt(0);
			scanner.close();
		}
		Arrays.sort(numeros);
		if (orden == 'd') {
			for (int i = 0; i < numeros.length / 2; i++) {
				int temporal = numeros[i];
				numeros[i] = numeros[numeros.length - 1 - i];
				numeros[numeros.length - 1 - i] = temporal;
			}
		}
		System.out.println(Arrays.toString(numeros));
		
		/// EJERCICIO 3///////

		Path ruta = Paths.get("C:\\Users\\trave\\eclipse-workspace\\ArgentinaPrograma\\Archivo.txt");

		try {

			Files.write(ruta,
					"EJERCICIO 3: Franco;\nLuis;\nMariano;\nAlba;\nSantiago;\nSol;\nVirginia;\nGiuliana;\nTamara;\nFlorentina;"
							.getBytes());

			List<String> VerArchivo = Files.readAllLines(ruta, StandardCharsets.UTF_8);
			VerArchivo.forEach(line -> System.out.println(line));

		} catch (IOException e) {

			System.out.println("Se detecto un error en el archivo");

		}
		
	}

}
