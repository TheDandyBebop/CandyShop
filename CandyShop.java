package candyShop;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class CandyShop {
	/*
	 * Debajo de esta seccion definire las funciones necesarias para el desarrollo
	 * del proyecto
	 */
	static int[] elementoArray(String codigo) {
		// El objetivo de esta funcion es convertir la posicion que se introduzca en la
		// posicion correspondiente dentro de la matriz.
		/*
		 * Funcionamiento: a la funcion se le introducira un string que corresponda con
		 * una posicion dentro de la maquina (AA,CD,BA,ETC) la funcion consta de un
		 * bucle que se repite 2 veces (1 por cada char del codigo) y guardara el valor
		 * de la Fila/ Columna correspondiente dentro de un nuevo array (posicionM).
		 * Adicionalmente a esto tambien guardara un tercer valor (posicionM[2]) Que lo
		 * voy a utilizar para comprobar que el codigo fue correcto FUERA de la funcion.
		 * (cuando posicionM[2]=1 Codigo incorrecto, pero si = 0 Codigo correcto)
		 *
		 */
		int posicionM[] = new int[3];
		for (int i = 0; i < 2; i++) {
			if(codigo.length()>2) {
				System.out.println("El codigo introducido no es correcto");
				posicionM[2] = 1;
				break;
			}
			if (posicionM[2] != 0) {
				break;
			}
			switch (codigo.charAt(i)) {
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
		//si se introducen mas de 2 valores:
		
		return posicionM;
	}

	static void pedirGolosinas(int codigo[], int cantidad[][], String producto[][], int venta[][]) {
		/*
		 * Funcionamiento: Ya con las posiciones adquiridas en la funcion
		 * "elementoArray" miro si el producto introducido posee existencias y, si es el
		 * caso, decremento una unidad en cantidad pero aumento 1 en ventas. El resto de
		 * elementos que introduzco en la funcion son las matrices que tengo que mirar y
		 * modificar.
		 */

		// SI hay existencias
		if (cantidad[codigo[0]][codigo[1]] > 0) {
			// te muestro el producto
			System.out.println("Disfrute de su: " + producto[codigo[0]][codigo[1]]);
			// le quito una cantidad
			cantidad[codigo[0]][codigo[1]]--;
			// aumento en 1 la venta
			venta[codigo[0]][codigo[1]]++;
		} else {
			// SI NO hay existencias
			System.out.println("Disculpe, no disponemos de ese producto.");
		}
	}

	static void mostrarProductos(String producto[][], double precio[][]) {
		/*
		 * 
		 * Funcionamiento: Se recorren 3 bucles. El primero corresponde a las filas de
		 * las matrices, el segundo a las columnas y el ultimo lo utilizo para convertir
		 * las posiciones de las filas y columnas a su correspondiente codigo con el
		 * Switch case. La funcion es de tipo void ya que solo tiene que recorrer las
		 * matrices y mostrar los elementos de estas. Esta resolucion tiene la
		 * limitacion de que el maximo de filas/ columnas es 4 ya que en el ejercicio es
		 * asi. El dia de mañana si se quisiera ampliar se tendria que modificar
		 * 
		 */
		// recorro las filas
		for (int filas = 0; filas < 4; filas++) {
			// recorro columnas
			for (int columnas = 0; columnas < 4; columnas++) {
				// la siguiente variable contendra el codigo en letras. La pongo aca para que se
				// vuelva a iniciar cada vez que cambio de columna
				String codigo = "";
				// bucle para transformar filas/columnas en cofigo
				for (int i = 0; i < 2; i++) {
					// array que utilizo para el switch. Tambien podria haber hecho 2 switch, uno
					// para filas y otro para columnas ahorrando el array pero era repetir codigo
					int[] posicion = { filas, columnas };
					// compruebo el valor de la posicion.
					switch (posicion[i]) {
					// le asigno su codigo segun corresponda
					case 0:
						codigo = codigo + 'A';
						break;
					case 1:
						codigo = codigo + 'B';
						break;
					case 2:
						codigo = codigo + 'C';
						break;
					case 3:
						codigo = codigo + 'D';
						break;
					}
				}
				// Imprimo los resultados
				System.out.println("-------------------------------------------------");
				System.out.println("| Producto: " + producto[filas][columnas] + " | Codigo: " + codigo + " | Precio: "
						+ precio[filas][columnas] + "€ | ");
			}
		}
		// fin funcion
	}

	static void rellenarProductos(int cantidad[][]) {
		/*
		 * Funcionamiento: Se le pedira al usuario que indique que producto quiere
		 * rellenar (Con su correspondiente codigo) Se transformara eso en la posicion
		 * correspondiente y luego se le pedira al usuario que indique la cantidad a
		 * aumentar si la suma de la cantidad existente y la nueva cantidad es < 5 Se
		 * realizara. Caso contrario no se cambiara y se le dira al usuario que el valor
		 * es incorrecto
		 */
		Scanner s = new Scanner(System.in);
		System.out.println("Indique el producto que quiere rellenar: ");
		String producto = s.next();
		// convierto el codigo en las posiciones
		int[] posiciones = elementoArray(producto);
		// compruebo si el codigo fue correcto
		try {

			if (posiciones[2] == 0) {
				System.out.println("Codigo correcto, Indique la cantidad a agregar: ");
				int sumar = s.nextInt();
				// SI la suma es menor o igual que 5 y ademas si el valor es positivo:
				if ((cantidad[posiciones[0]][posiciones[1]] + sumar) <= 5 && sumar > 0) {
					// realizo la suma y muestro el resultado
					cantidad[posiciones[0]][posiciones[1]] = cantidad[posiciones[0]][posiciones[1]] + sumar;
					System.out.println("Se Ha realizado la operacion con exito, ahora el cajetin posee "
							+ cantidad[posiciones[0]][posiciones[1]] + " Productos");
				} else {
					// Caso contrario muestro el msj de error
					System.out.println("Imposible realizar operacion, limite superado o valor erroneo");
				}
			}
			// si el codigo es incorrecto directamente no entrara y el msj de error se mostrara en su correspondiente funcion
		} catch (InputMismatchException e) {
			System.err.println("Por favor introduzca un valor numerico");
		}
	}

	static void menuAdmin(String producto[][], double precio[][], int venta[][], int cantidad[][], String contraseña[], boolean salir) {
		/*
		 * 
		 * Funcionamiento: Una vez que el usuario introduzca correctamente su contraseña, entrara en este menu. Desde este dispondra de todas las
		 * opciones y estas llamaran a sus correspondientes funcioones para que las realicen. La unica excepcion es el cambio de contraseña
		 * ya que se trataba de algo simple y que no me era necesario llamar a una nueva funcion.
		 * 
		 * */
		while (!salir) {
			System.out.println("Bienvenido al menu del administrador, ¿Que desea hacer?");
			System.out.println(" 1: Cambiar contraseña\n 2: Rellenar Productos \n 3: Cambiar precio\n 4: Remplazar un producto\n 5: Ranking mas vendidos\n 6: Ranking menos vendidos\n 7: Informacion de productos\n 8: Ventas totales\n 9: Cerrar sesion");
			Scanner s = new Scanner(System.in);
			try {
				int opciones = s.nextInt();
				switch (opciones) {
				case 1:
					System.out.println("Introduzca la nueva contraseña: ");
					String nuevaContraseña = s.next();
					System.out.println("Vuelva a repetir la contraseña: ");
					if (s.next().equals(nuevaContraseña)) { //Compruebo si introdujo 2 veces la misma contraseña
						contraseña[0] = nuevaContraseña;
						System.out.println("¡Contraseña cambiada con exito!");
					} else {
						System.out.println("Las contraseñas no coinciden, vuelva a intentarlo");
					}
					break;
				case 2:
					rellenarProductos(cantidad);
					break;
				case 3:

					break;
				case 4:

					break;
				case 5:

					break;
				case 6:

					break;
				case 7:

					break;
				case 8:

					break;
				case 9:
					System.out.println("Saliendo del menu Administrador");
					salir = true;
					break;
				default:
					System.err.println("Introduzca un numero valido");
					break;
				}
			} catch (InputMismatchException a) {
				System.err.println("Introduzca un numero valido");
				s.next();
			}
		}
	}

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		// Ahora definire los booleanos para los menus
		boolean salirMenuP = false;// Principal

		boolean salirMenuA = false;// Admin

		// Definicion de matrices

		// Productos disponibles
		String[][] productos = { { "Lacasitos", "Chicles de fresa", "KitKat", "Palotes" },
				{ "Oreo", "Bolsa Haribo", "Chetoos", "Twix" },
				{ " M&M'S ", " Kinder Bueno ", "Papa Delta", "Chicles de menta" },
				{ "Lacasitos", "Crunch", "Milkybar", "KitKat" } };
		// Precio de los productos. El Precio correspode con el producto de la matriz
		// producto en la misma posicion.
		double[][] precio = { { 1.5, 0.8, 1.1, 0.9 }, { 1.8, 1, 1.2, 1 }, { 1.8, 1.3, 1.2, 0.8 },
				{ 1.5, 1.1, 1.1, 1.1 } };
		// Matriz de Cantidades, Cada posicion correspondera con la cantidad de los
		// productos(Matriz productos) en la misma posicion
		int[][] cantidad = { { 3, 3, 3, 3 }, { 3, 3, 3, 3 }, { 3, 3, 0, 3 }, { 3, 3, 3, 3 }, };
		// Matriz de ventas, Cada posicion correspondera con la cantidad de ventas de
		// los productos(Matriz productos) en la misma posicion
		int[][] ventas = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, };
		String posicion;// String que corresponde a la posicion introducida en forma de Char
		String contraseña[] = { "DAM" };// Contraseña del admin. La defino como array ya que como el menu de admin lo
										// tengo en una funcion no puedo modificarla de otra manera.
		while (!salirMenuP) {
			System.out.println("Bienvenido Al CandyShop de Eric Sanzeri, Seleccione la accion a realizar: ");
			System.out.println(
					" 1: Seleccionar Golosina\n 2: Mostrar Golosinas\n 3: Menu Administrador\n 4: Apagar sistema");
			try {
				int opciones = s.nextInt();
				switch (opciones) {
				case 1:
					System.out.println("Introduzca la posicion del producto: ");
					posicion = s.next();
					// Array que guarda las posiciones del array
					int[] posMat = elementoArray(posicion);
					// Compruebo fuera de la duncion que el codigo fue correcto.
					if (posMat[2] == 0) {
						pedirGolosinas(posMat, cantidad, productos, ventas);
					}
					break;
				case 2:
					mostrarProductos(productos, precio);
					break;
				case 3:
					System.out.println("Introduzca su contraseña: ");
					if (s.next().equals(contraseña[0])) {
						System.out.println("Contraseña Correcta");
						menuAdmin(productos, precio, ventas, cantidad, contraseña, salirMenuA);
					} else {
						System.out.println("La contraseña no es correcta, vuelva a intentarlo.");
					}

					break;
				case 4:
					// Opcion de apagar el sistema, cambio el boolean para salir.
					salirMenuP = true;
					System.out.println("¡Gracias por sus servicios, hasta pronto!");
					break;
				default:
					// Si se introduce un numero que no corresponda a nada
					System.err.println("Introduzca un numero valido.");
				}
			} catch (InputMismatchException e) {
				// Si se introduce un valor no valido:
				System.err.println("INTRODUZCA VALORES VALIDOS");
				s.next();
			}
		}

	}

}
