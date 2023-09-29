package Trabajos;

public class Trabajo1 {

	public static void main(String[] args) {

	    int numInicio = 5;
	    int numFinal = 14;
	    boolean numPares = false;
	    boolean numImpares = false;
	    /*EJERCICIO 2 */
	    float ingresos = 1500340;
	    int superficie = 85;
	    int energia = 8000;
	    // Bucle para imprimir los números pares
	    while (numInicio <= numFinal) {
	        if (numInicio % 2 == 0) {
	            System.out.println(numInicio + "");
	            numPares = true;
	        }
	        numInicio++;
	    }

	    // Restablece el valor de numInicio al valor inicial
	    numInicio = 5;

	    // Bucle para imprimir los números impares
	    while (numInicio <= numFinal) {
	        if (numInicio % 2 != 0) {
	            System.out.print(numInicio + "-");
	            numImpares = true;
	        }
	        numInicio++;
	    }

	    // Imprimir una línea en blanco si se imprimieron números pares e impares
	    if (numPares && numImpares) {
	        System.out.println();
	    }
	    
	    /*EJERCICIO 2 */

	    if ( (ingresos <= 748382.07) &&
	    ( superficie <= 30) &&
	    (energia <= 3330) ) {
	    System.out.println("Categoría A");
	    } else if((ingresos <= 1112459.83) &&
	    (superficie <= 45) &&
	    (energia <= 5000) ) {
	    System.out.println("Categoría B");
	    }
	    if ( (ingresos <= 1557445.70) &&
	    	    ( superficie <= 60) &&
	    	    (energia <= 6700) ) {
	    	    System.out.println("Categoría C");
	    	    } else if((ingresos <= 1912459.04) &&
	    	    (superficie <= 85) &&
	    	    (energia <= 10000) ) {
	    	    System.out.println("Categoría D");
	    	    }

	}


}

		
	


		

