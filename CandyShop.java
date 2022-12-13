package candyShop;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import javax.management.AttributeChangeNotification;
public class CandyShop {
	
		//Definire las variables y matrices fuera del main
		// Definicion de matrices
		// Productos disponibles		
		static String[][] matrizProducto = { 
						{ "Lacasitos", "Chicles de fresa", "KitKat", "Palotes" },
						{ "Oreo", "Bolsa Haribo", "Chetoos", "Twix" },
						{ " M&M'S ", " Kinder Bueno ", "Papa Delta", "Chicles de menta" },
						{ "Lacasitos", "Crunch", "Milkybar", "KitKat" } };
		// Precio de los productos. El Precio correspode con el producto de la matriz producto en la misma posicion.
		static double[][] matrizPrecio = { 
						{ 1.5, 0.8, 1.1, 0.9 }, 
						{ 1.8, 1, 1.2, 1 }, 
						{ 1.8, 1.3, 1.2, 0.8},
						{ 1.5, 1.1, 1.1, 1.1} };
		// Matriz de Cantidades, Cada posicion correspondera con la cantidad de los productos(Matriz productos) en la misma posicion
		static int[][] matrizCantidad = new int[4][4];	
		// Matriz de ventas, Cada posicion correspondera con la cantidad de ventas de los productos(Matriz productos) en la misma posicion
		static int[][] matrizVenta = new int[4][4];
		static String posicion;// String que corresponde a la posicion introducida en forma de Char
		static String contraseña =  "DAM" ;// Contraseña del admin.
		static boolean salirMenuP = false;// Principal
		static double nuevoPrecio;//var que usare para los precios nuevos
		
		/**Matriz de ventas para testear
		static int[][] matrizVenta = { 
				{ 3, 0, 2, 0}, 
				{ 3, 2, 0, 1}, 
				{ 0, 1, 0, 2},
				{ 3, 0, 1, 2} };**/
		
		
		
	/*
	 * Debajo de esta seccion definire las funciones necesarias para el desarrollo
	 * del proyecto
	 */
	static void inicializarMatriz(int matriz[][],int valor) {
		for (int filas = 0; filas < 4; filas++) {
			// recorro columnas
			for (int columnas = 0; columnas < 4; columnas++) {
				matriz[filas][columnas]= valor;
				}
			}
		}
	
	
	static String comprobarPassword(String contraseña,String nuevaContraseña,String repetirContraseña){
		if (repetirContraseña.equals(nuevaContraseña)) { //Compruebo si introdujo 2 veces la misma contraseña
			System.out.println("¡Contraseña cambiada con exito!");
			return nuevaContraseña;
		} else {
			System.out.println("Las contraseñas no coinciden, vuelva a intentarlo");
			return contraseña;
		}
	
	}
	
	static int[] ordenarBurbuja(int array[]) {
		int aux;
		boolean salir=true;
		while(salir) {
			salir = false;
			for (int i=1; i < array.length; i++) {
				if (array[i] < (array[i-1]) ) {
					aux = array[i-1];
					array[i-1] = array[i];
					array[i] = aux;
					salir = true;
				}
			}
		}
		return array;
	}
	
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

	/*
	 * Funcionamiento: Ya con las posiciones adquiridas en la funcion
	 * "elementoArray" miro si el producto introducido posee existencias y, si es el
	 * caso, decremento una unidad en cantidad pero aumento 1 en ventas. El resto de
	 * elementos que introduzco en la funcion son las matrices que tengo que mirar y
	 * modificar.
	 */
	static void pedirGolosinas(int codigo[], int cantidad[][], String producto[][], int venta[][]) {
		// SI hay existencias
		if (cantidad[codigo[0]][codigo[1]] > 0) {
			// te muestro el producto
			System.out.println("Disfrute de su: " + producto[codigo[0]][codigo[1]]);
			// le quito una cantidad
			cantidad[codigo[0]][codigo[1]]--;
			// aumento en 1 la venta
			for (int filas=0;filas<4;filas++) {
				for(int columnas=0;columnas<4;columnas++) {
					if (producto[codigo[0]][codigo[1]].equals(producto[filas][columnas])) {			
						venta[filas][columnas]++;
					}
				}
			}
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

	static void rellenarProductos(int cantidad[][] , int agregar, String codigo) {
		/*
		 * Funcionamiento: Se le pedira al usuario que indique que producto quiere
		 * rellenar (Con su correspondiente codigo) Se transformara eso en la posicion
		 * correspondiente y luego se le pedira al usuario que indique la cantidad a
		 * aumentar si la suma de la cantidad existente y la nueva cantidad es < 5 Se
		 * realizara. Caso contrario no se cambiara y se le dira al usuario que el valor
		 * es incorrecto
		 */
		// convierto el codigo en las posiciones
		int[] posiciones = elementoArray(codigo);
		// compruebo si el codigo fue correcto
		try {
			if (posiciones[2] == 0) {
				// SI la suma es menor o igual que 5 y ademas si el valor es positivo:
				if ((cantidad[posiciones[0]][posiciones[1]] + agregar) <= 5 && agregar > 0) {
					// realizo la suma y muestro el resultado
					cantidad[posiciones[0]][posiciones[1]] = cantidad[posiciones[0]][posiciones[1]] + agregar;
					System.out.println("Se Ha realizado la operacion con exito, ahora el cajetin posee "+ cantidad[posiciones[0]][posiciones[1]] + " Productos");
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

	static void cambiarPrecio(double precio[][], String producto[][], String codigo, double precioNuevo) {
		int posiciones[] = elementoArray(codigo);
		if (posiciones[2]==0) {
			System.out.println("El producto es: "+ producto[posiciones[0]][posiciones[1]] + " con un costo de: " + precio[posiciones[0]][posiciones[1]] +"€");
			try {
			if(precioNuevo>0) {
			for (int filas =0; filas< 4; filas++) {
				for(int columnas=0; columnas<4; columnas++) {
					if (producto[posiciones[0]][posiciones[1]].equals(producto[filas][columnas])) {
						precio[filas][columnas]= precioNuevo;
					}
				}
			}
			System.out.println("Operacion realizada, Ahora " +producto[posiciones[0]][posiciones[1]] + " Cuesta " + precio[posiciones[0]][posiciones[1]] +"€" );
			}
			else {
				System.out.println("El precio no puede ser negativo");
			}
			} catch (Exception c) {
				System.err.println("Introduzca valores numericos");
			}
		}
	}

	static void cambiarProductos(String producto[][], double precio[][], int cantidad[][], int ventas[][], String codigo, String nuevoProducto, int cantidadNueva,double nuevoPrecio) {
		int posiciones[] = elementoArray(codigo);
		if (posiciones[2]==0) {
			System.out.println("Usted va a modificar " + producto[posiciones[0]][posiciones[1]] + " que se encuentra en la posicion: " + codigo);
			try {
			if (cantidadNueva>0 &&  cantidadNueva<=5) {
			if(nuevoPrecio>0) {
				producto[posiciones[0]][posiciones[1]] = nuevoProducto;
				cantidad[posiciones[0]][posiciones[1]] = cantidadNueva;
				ventas[posiciones[0]][posiciones[1]] = 0;
				for (int filas =0; filas< 4; filas++) {
					for(int columnas=0; columnas<4; columnas++) {
						if (producto[posiciones[0]][posiciones[1]].equals(producto[filas][columnas])) {
							precio[filas][columnas]= nuevoPrecio;
						}
					}
				}
				System.out.println("Operacion realizada, Ahora " +producto[posiciones[0]][posiciones[1]] + " Cuesta " + precio[posiciones[0]][posiciones[1]] +"€" + " y posee " + cantidad[posiciones[0]][posiciones[1]] + " Unidades" );
				}
				}
			else {
				System.out.println("La cantidad no puede ser mayor a 5");
			}
			}
			 catch (InputMismatchException a) {
					System.err.println("Introduzca un valor valido");
			}
		}
	}
	//CONTINUAR POR ACA.
	static void masVendidos(int ventas[][], String producto[][]) {
		int contador =0;
		String repetido[]= {"","","","","","","","","","","","","","","",""};//Ver como solucionarlo
		int arrayOrdenado[]=new int[16];
		for (int filas = 0; filas < 4; filas++) {
			for (int columnas = 0; columnas < 4; columnas++) {
				arrayOrdenado[contador]=ventas[filas][columnas];
				contador++;
				}
			}
		boolean estaRepetido=false;
		ordenarBurbuja(arrayOrdenado);
		contador=0;
		int contadorProductos=0;
		int finalArray=(arrayOrdenado.length-1);
		int ventasRepetidas=arrayOrdenado[finalArray];
		if(arrayOrdenado[finalArray]==0) {
			System.out.println("No hay ventas que mostrar aun \n");
		}
		else {
		for (int filas =0; filas < 4; filas++) {//RECORRO FILAS MATRIZ
			for (int columnas =0; columnas < 4; columnas++) {//RECORRO COLUMNAS MATRIZ
				if (ventas[filas][columnas]==arrayOrdenado[finalArray]) {//SI LAS VENTAS COINCIDEN CON LA POSICION EN LA MATRIZ
					for(int recorrerProductos=0; recorrerProductos<repetido.length;recorrerProductos++) {//RECORRO LA LISTA DE PRODUCTOS YA MOSTRADOS
						if(producto[filas][columnas].equals(repetido[recorrerProductos])){//SI YA FUE MOSTRADO 
							estaRepetido=true;//DEVUELVE TRUE, ESTA REPETIDO
							break;
						}
					}
					if (!estaRepetido && arrayOrdenado[finalArray]!=0 && contador<3) {//SI NO ESTA REPETIDO y el valor no es 0
						System.out.println("Producto: " + producto[filas][columnas]+ " Ventas " + ventas[filas][columnas]);//MUESTRO EL PRODUCTO Y SUS VENTAS
						repetido[contadorProductos]=producto[filas][columnas];//Guardo el producto repetido
						contadorProductos++;//incremento el contador de productos mostrados
						filas=-1;//Reinicio la busqueda
						columnas=4;
						ventasRepetidas=arrayOrdenado[finalArray];
						finalArray--;
					}
					if(ventasRepetidas!=arrayOrdenado[finalArray]) {
						contador++;
					}
					estaRepetido=false;//vuelvo a dejarlo en false
				}
				if(filas==3 && columnas==3) {
					finalArray--;
					filas=-1;//Reinicio la busqueda
					columnas=4;
					contador++;//Aumento el contador ya que se recorrio todo el array y no hay otro valor que coincia y no este repetido
				}	
			}
			if(contador>3) {//al mostrar ya los 3 primeros productos muestro un msj y rompo el bucle
				System.out.println("Fin del ranking");
				break;
			}
		}
	}
		
		
		
		/*
		while(arrayOrdenado[finalArray]!=0 && contador<3) {
		for (int filas = 0; filas < 4; filas++) {
			for (int columnas = 0; columnas < 4; columnas++) {
			if(arrayOrdenado[finalArray] == ventas[filas][columnas] && contador <3) {
					for(int inicio=0; inicio<repetido.length;inicio++) {
						if(repetido[inicio].equals(producto[filas][columnas])) {
							estaRepetido=true;
						}
					}
					if(!estaRepetido&&arrayOrdenado[finalArray]!=0) {
						System.out.println("Producto: " + producto[filas][columnas] + " Ventas: "+ ventas[filas][columnas]);
						repetido[contadorProductos]=producto[filas][columnas];
						contadorProductos++;
						filas=0;
						columnas=0;
						ventasRepetidas=arrayOrdenado[finalArray];
						if (finalArray>0){
						finalArray--;}
						}
					estaRepetido=false;
					}
				}
			if(ventasRepetidas != arrayOrdenado[finalArray]) {
				contador++;
				ventasRepetidas=arrayOrdenado[finalArray];
				}
			if (contador==3) {
				break;
			}

			}
		}*/
	
	}

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		// Ahora definire los booleanos para los menus
		
		//Asigno mediante una funcion valores a ambas matrices
		inicializarMatriz(matrizVenta, 0);
		inicializarMatriz(matrizCantidad, 3);
		
	
		while (!salirMenuP) {
			boolean salirMenuA = false;//Condicion para el manu del Admin
			System.out.println("Bienvenido Al CandyShop de Eric Sanzeri, Seleccione la accion a realizar: ");
			System.out.println(
					" 1: Seleccionar Golosina\n 2: Mostrar Golosinas\n 3: Menu Administrador");
			try {
				int opciones = s.nextInt();
				//selector de opciones
				switch (opciones) {
				case 1:
					System.out.println("Introduzca la posicion del producto: ");
					posicion = s.next();
					// Array que guarda las posiciones del array
					int[] posMatriz = elementoArray(posicion);
					// Compruebo fuera de la duncion que el codigo fue correcto.
					if (posMatriz[2] == 0) {
						pedirGolosinas(posMatriz, matrizCantidad, matrizProducto, matrizVenta);
					}
					break;
					
				case 2:
					mostrarProductos(matrizProducto, matrizPrecio);
					break;
				case 3:
					//MENU ADMINISTRADOR
					System.out.println("Introduzca su contraseña: ");
					if (s.next().equals(contraseña)) {
						System.out.println("Contraseña Correcta");
						/*
						 * 
						 * Funcionamiento: Una vez que el usuario introduzca correctamente su contraseña, entrara en este menu. Desde este dispondra de todas las
						 * opciones y estas llamaran a sus correspondientes funcioones para que las realicen. La unica excepcion es el cambio de contraseña
						 * ya que se trataba de algo simple y que no me era necesario llamar a una nueva funcion.
						 * 
						 * */
						while (!salirMenuA) {
							System.out.println("Bienvenido al menu del administrador, ¿Que desea hacer?");
							System.out.println(" 1: Cambiar contraseña\n 2: Rellenar Productos \n 3: Cambiar precio\n 4: Remplazar un producto\n 5: Ranking mas vendidos\n 6: Ranking menos vendidos\n 7: Informacion de productos\n 8: Ventas totales\n 9: Cerrar sesion\n 10: Apagar sistema");
							
							try {
								 opciones = s.nextInt();
								switch (opciones) {
								case 1:
									System.out.println("Introduzca la nueva contraseña: ");
									String nuevaContraseña = s.next();
									System.out.println("Vuelva a repetir la contraseña: ");
									String contraseñaaRepe = s.next();
									contraseña = comprobarPassword(contraseña,nuevaContraseña, contraseñaaRepe);
									
									break;
								case 2:
									System.out.println("Indique el codigo del producto que quiera rellenar");
									posicion=s.next();
									System.out.println("Indique la cantidad de producto que quiera agregar");
									int agregar=s.nextInt();
									rellenarProductos(matrizCantidad, agregar,posicion);
									break;
									
								case 3:
									System.out.println("Indique el codigo del producto que quiera modificar su coste");
									posicion=s.next();
									System.out.println("Indique el nuevo precio del producto");
									nuevoPrecio=s.nextDouble();
									cambiarPrecio(matrizPrecio, matrizProducto,posicion,nuevoPrecio);
									break;
									
								case 4:
									System.out.println("Indique el producto que desea modificar: ");
									posicion = s.next();
									System.out.println("Introduzca el nombre del nuevo producto: ");
									String nuevoProducto=s.next();
									System.out.println("Introduzca la cantidad de elementos que contendra el cajetin: ");
									int cantidadNueva =s.nextInt();
									System.out.println("Introduzca el precio del producto: ");
									nuevoPrecio=s.nextDouble();
									cambiarProductos(matrizProducto, matrizPrecio, matrizCantidad, matrizVenta,posicion,nuevoProducto,cantidadNueva,nuevoPrecio);
									break;
								case 5:
									masVendidos(matrizVenta,matrizProducto);
									break;
								case 6:

									break;
								case 7:

									break;
								case 8:

									break;
								case 9:
									//Salir menu administrador
									System.out.println("Saliendo del menu Administrador");
									salirMenuA = true;
									break;
								case 10:
									// Opcion de apagar el sistema, cambio el boolean para salir.
									salirMenuA = true;
									salirMenuP = true;
									System.out.println("¡Gracias por sus servicios, hasta pronto!");
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

					 else {
						System.out.println("La contraseña no es correcta, vuelva a intentarlo.");
					}

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
