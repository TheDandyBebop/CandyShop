package candyShop;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class CandyShop {
	/*
	 * Debajo de esta seccion definire las funciones necesarias para el desarrollo del proyecto 
	 */
	static int[] elementoArray(String codigo) {
		//El objetivo de esta funcion es convertir la posicion que se introduzca en la posicion correspondiente dentro de la matriz.
		/* 
		 * Funcionamiento: a la funcion se le introducira un string que corresponda con una posicion dentro de la maquina (AA,CD,BA,ETC)
		 * la funcion consta de un bucle que se repite 2 veces (1 por cada char del codigo) y guardara el valor de la Fila/ Columna
		 * correspondiente dentro de un nuevo array (posicionM). Adicionalmente a esto tambien guardara un tercer valor (posicionM[2])
		 * Que lo voy a utilizar para comprobar que el codigo fue correcto FUERA de la funcion. 
		 * (cuando posicionM[2]=1 Codigo incorrecto, pero si = 0 Codigo correcto)
		 *
		 * */
		int posicionM[] = new int[3];
		for (int i = 0; i <2; i++) {
			if (posicionM[2] != 0) {
				break;
			}
			switch(codigo.charAt(i)) {
			case 'A': 
				posicionM[i] = 0;
				posicionM[2] = 0;
				break;
			case 'B':
				posicionM[i] = 1;
				posicionM[2] = 0;
				break;
			case 'C':
				posicionM[i] = 2;
				posicionM[2] = 0;
				break;
			case 'D':
				posicionM[i] = 3;
				posicionM[2] = 0;
				break;
			default:
				System.out.println("El codigo introducido no es correcto");
				posicionM[2] = 1;
				break;
			}
			
		}
		return posicionM;
	}
	static void pedirGolosinas(int codigo[], int cantidad[][], String producto[][], int venta[][]) {
		if(cantidad[codigo[0]][codigo[1]] > 0) {
			System.out.println("Disfrute de su: " + producto[codigo[0]][codigo[1]]);
			cantidad[codigo[0]][codigo[1]]--;
			venta[codigo[0]][codigo[1]]++;
		}
		else {
			System.out.println("Disculpe, no disponemos de ese producto.");
		}
	}
	public static void main (String args[]) {
		Scanner s = new Scanner (System.in);
		//Ahora definire los booleanos para los menus
		boolean salirMenuP = false;
		//Menu Principal
		boolean salirMenuA = false;
		//Menu admin
		
		//Definicion de matrices
		//Productos disponibles
		String[][] productos = {
				{"Lacasitos", "Chicles de fresa", "KitKat", "Palotes"},
				{"Oreo", "Bolsa Haribo", "Chetoos", "Twix"},
				{" M&M'S ", " Kinder Bueno ", "Papa Delta", "Chicles de menta"},
				{"Lacasitos", "Crunch", "Milkybar", "KitKat"}
				};
		//Precio de los productos. El Precio correspode con el producto de la matriz producto en la misma posicion.
		double[][] precio = {
				{1.5, 0.8, 1.1, 0.9},
				{1.8, 1, 1.2, 1},
				{1.8, 1.3, 1.2, 0.8},
				{1.5, 1.1, 1.1, 1.1}
				};
		//Matriz de Cantidades, Cada posicion correspondera con la cantidad de los productos(Matriz productos) en la misma posicion
		int[][] cantidad = {
				{3, 3, 3, 3},
				{3, 3, 3, 3},
				{3, 3, 0, 3},
				{3, 3, 3, 3},
				};
		//Matriz de ventas, Cada posicion correspondera con la cantidad de ventas de los productos(Matriz productos) en la misma posicion
		int[][] ventas = {
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				};
		String posicion;//String que corresponde a la posicion introducida en forma de Char
		int fila;
		int columna;
		while(!salirMenuP) {
			System.out.println("Bienvenido Al CandyShop de Eric Sanzeri, Seleccione la accion a realizar: ");
			System.out.println(" 1: Seleccionar Golosina\n 2: Mostrar Golosinas\n 3: Menu Administrador\n 4: Apagar sistema");
			try {
				int opciones = s.nextInt();
				switch (opciones) {
				case 1: 
					System.out.println("Introduzca la posicion del producto: ");
					posicion= s.next();
					//Array que guarda las posiciones del array
					int[] posMat= elementoArray(posicion);
					//Compruebo fuera de la duncion que el codigo fue correcto.
					if (posMat[2]==0) {
					pedirGolosinas(posMat, cantidad, productos, ventas);
					}
				break;
				case 2: 
					
				break;
				case 3: 
					
				break;
				case 4: 
					//Opcion de apagar el sistema, cambio el boolean para salir.
					salirMenuP=true;
					System.out.println("¡Gracias por sus servicios, hasta pronto!");
				break;
				default:
					//Si se introduce un numero que no corresponda a nada
					System.err.println("Introduzca un numero valido.");
				}
			} catch (InputMismatchException e) {
				//Si se introduce un valor no valido:
				System.err.println("INTRODUZCA VALORES VALIDOS");
				s.next();
			}
		} 
	
	}
	
	
	
}
