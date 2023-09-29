package Trabajos;

import java.util.Scanner;

public class Calculadora {
	public static void main(String[] args) {
		Calculadora calcular = new Calculadora();
		Scanner scanner = new Scanner(System.in);

		double resultado;
		boolean salir = false;

		while (!salir) {
			System.out.println("Calculadora");
			System.out.println("1. Sumar");
			System.out.println("2. Restar");
			System.out.println("3. Multiplicar");
			System.out.println("4. Dividir");
			System.out.println("5. Limpiar");
			System.out.println("6. Salir");
			System.out.print("Ingrese su opción: ");

			int opcion = scanner.nextInt();

			switch (opcion) {
			case 1:
				System.out.print("Ingrese un número: ");
				double num1 = scanner.nextDouble();
				scanner.nextLine();
				System.out.print("Ingrese otro número: ");
				double num2 = scanner.nextDouble();
				scanner.nextLine();
				resultado = calcular.sumar(num1, num2);
				System.out.println("El resultado es: " + resultado);
				break;
			case 2:
				System.out.print("Ingrese un número: ");
				num1 = scanner.nextDouble();
				scanner.nextLine();
				System.out.print("Ingrese otro número: ");
				num2 = scanner.nextDouble();
				scanner.nextLine();
				resultado = calcular.restar(num1, num2);
				System.out.println("El resultado es: " + resultado);
				break;
			case 3:
				System.out.print("Ingrese un número: ");
				num1 = scanner.nextDouble();
				System.out.print("Ingrese otro número: ");
				num2 = scanner.nextDouble();
				resultado = calcular.multiplicar(num1, num2);
				System.out.println("El resultado es: " + resultado);
				break;
			case 4:
				System.out.print("Ingrese un número: ");
				num1 = scanner.nextDouble();
				System.out.print("Ingrese otro número: ");
				num2 = scanner.nextDouble();
				resultado = calcular.dividir(num1, num2);
				System.out.println("El resultado es: " + resultado);
				break;
			case 5:
				resultado = 0;
				System.out.println("Se ha limpiado el resultado");
				break;
			case 6:
				salir = true;
				System.out.println("Hasta luego");
				break;
			default:
				System.out.println("Opción inválida");
			}

			System.out.println();
		}

		scanner.close();
	}

	private double sumar(double num1, double num2) {
        return num1 + num2;
    }

    private double restar(double num1, double num2) {
        return num1 - num2;
    }

    private double multiplicar(double num1, double num2) {
        return num1 * num2;
    }

    private double dividir(double num1, double num2) {
        if (num2 != 0) {
            return num1 / num2;
        } else {
            return Double.NaN; // si se intenta dividir por cero
        }
	}

}
