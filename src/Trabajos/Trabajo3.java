package Trabajos;


public class Trabajo3 { 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String textUno= "Buen día Profe! ";
//		String textDos= "Gracias por compartir tus conocimientos con tanta dedicación";
//		boolean textIgual= textUno.equals(textDos);
//		System.out.println("Texto 1 es igual a Texto 2? " + textIgual);
//		
//		String textTres= textUno + textDos;
//		System.out.println(textTres);
//		
//		String text4 = textUno.toLowerCase() + " " + textDos.toUpperCase();
//		System.out.println(text4);
//		
//		String text5 = textUno.replace("Profe", "Neli");
//		System.out.println(text5);
//		
//		boolean text6 =  textUno.contains("buen");
//		System.out.println(text6);
//		
//		boolean text7 = text4.endsWith("IÓN");
//		System.out.println(text7);
		
		//EJERCICIO 1A ///////
		
		String palabra = "Me encanta comer chocolate";
		char caracter = 'e';

		int cantidadCoincidencias = 0;
		String palabra_minus = palabra.toLowerCase();
		int posicionCaracter = palabra_minus.indexOf(caracter);

		if (posicionCaracter != -1) {
		    for (int i = 0; i < palabra.length(); i++) {
		        if (palabra_minus.charAt(i) == caracter) {
		            cantidadCoincidencias++;
		        }
		    }
		    System.out.println("EJERCICIO A)La cantidad de coincidencias es: " + cantidadCoincidencias);
		} else {
		    System.out.println("No hay coincidencia");
		}

		
		//EJERCICIO 1B //////////
		
		boolean ordenAscendente = false;

		int[] vectorNumeros = {2, 540, 98};

		int numMayor = 0, numMedio = 0, numMenor = 0, 
		    numAux1 = 0, numAux2 = 0, mayorValor = 0;

		numMayor = vectorNumeros[0];
		numMedio = vectorNumeros[1];
		numMenor = vectorNumeros[2];

		System.out.println(numMayor + " " + numMedio + " " + numMenor);

		if ((numMayor > numMedio) && (numMayor > numMenor)) {
		    mayorValor = numMayor;
		    if (numMedio > numMenor) {
		        numAux1 = numMedio;
		        numAux2 = numMenor;
		    } else {
		        numAux1 = numMenor;
		        numAux2 = numMedio;
		    }
		} else {
		    if ((numMedio > numMenor) && (numMedio > numMayor)) {
		        mayorValor = numMedio;
		        if (numMayor > numMenor) {
		            numAux1 = numMayor;
		            numAux2 = numMenor;
		        } else {
		            numAux1 = numMenor;
		            numAux2 = numMayor;
		        }
		    } else {
		        mayorValor = numMenor;
		        if (numMedio > numMayor) {
		            numAux1 = numMedio;
		            numAux2 = numMayor;
		        } else {
		            numAux1 = numMayor;
		            numAux2 = numMedio;
		        }
		    }
		}

		System.out.println("EJERCICIO B) El mayor valor es: " + mayorValor);

		int[] vectorOrdenado = new int[3];

		if (ordenAscendente) {
		    vectorOrdenado[0] = mayorValor;
		    vectorOrdenado[1] = numAux1;
		    vectorOrdenado[2] = numAux2;
		} else {
		    vectorOrdenado[0] = numAux2;
		    vectorOrdenado[1] = numAux1;
		    vectorOrdenado[2] = mayorValor;
		}

		for (int i = 0; i < vectorOrdenado.length; i++) {
		    System.out.print(vectorOrdenado[i] + "-" );
		}
		
		//EJERCICIO 1C //////
		
		int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int x = 3;
		int suma =0;
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] > x) {
                suma += numeros[i];
                
            }
        }
        
		System.out.println("\nEJERCICIO C) La suma de números > X es: "+ suma); 
		
		////// EJERCICIO 2 /////////
		
		String frase="Franco;Luis;Mariano;Alba;Santiago;Sol;Virginia;Giuliana;Tamara;Florentina. ";

		String fraseSplit[] = frase.split(";");

		for (String elemento : fraseSplit) { 

		System.out.println("EJERCICIO 2 )" + elemento);

		}
   } 
}



