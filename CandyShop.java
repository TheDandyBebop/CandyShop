package candyShop;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
public class CandyShop {
				
	//Debajo de esta seccion definire las funciones necesarias para el desarrollo del proyecto
		
	static boolean comprobarCodigo(String codigo){
		if(codigo.length()==2 && (codigo.charAt(0) == 'A' || codigo.charAt(0) == 'B' || codigo.charAt(0) == 'C' || codigo.charAt(0) == 'D') && (codigo.charAt(1) == 'A' || codigo.charAt(1) == 'B' || codigo.charAt(1) == 'C' || codigo.charAt(1) == 'D')) {
			return true;
		}
		else{
			return false;
		}
	}	
	/*
	 * Funcion encargada de leer los productos para saber su longitud (De caracteres) y asignarle esa misma longitud al resto.
	 * Esto lo hago para que al mostrar los productos por consola queden mejor ordenados y visualmente mas bonito
	 */
	static void embellecerNombres(String productos[][]) {
		//Leo todos los productos para saber para saber cual es el mas largo y guardo su longitud en la variable
		int longitud=0;
		for (int filas=0;filas<4;filas++) {
			for(int columnas=0;columnas<4;columnas++) {
				if((productos[filas][columnas].length())>longitud) {
					longitud=(productos[filas][columnas].length());
				}
				}
			}
		//Asigno los espacios correspondientes a los productos de acorde con la nueva longitud para que midan todos iguales
		for (int filas=0;filas<4;filas++) {
			for(int columnas=0;columnas<4;columnas++) {
				int espacios= longitud - productos[filas][columnas].length();
				while(espacios > 0) {
					productos[filas][columnas] = productos[filas][columnas] +" ";
					espacios--;
				}
				}
			}
	}
			
		/*
	 * Funcion diseñada para darle valores predefinidos a las Matrices de "ventas" y "cantidades"
		 * */
	static void inicializarMatriz(int matriz[][],int valor) {
		//Recorro Filas
		for (int filas = 0; filas < 4; filas++) {
			// Recorro Columnas
			for (int columnas = 0; columnas < 4; columnas++) {
				//Asigno el valor introducido a cada elemento de la matriz
				matriz[filas][columnas]= valor;
				}
			}
		}

		/* 
	 * Funcion encargada de comprobar que las contraseñas escritas sean las misma.
		 *	En caso de que sea asi devuelve la nueva contraseña, cambiandose por la anterior.
		 *	Si las contraseñas no coinciden devolvera la contraseña original para que no se produzcan cambios.
		 **/
	static String comprobarPassword(String contraseña,String nuevaContraseña,String repetirContraseña){
		if (repetirContraseña.equals(nuevaContraseña)) { //Compruebo si introdujo 2 veces la misma contraseña
			System.out.println("¡Contraseña cambiada con exito!");
			return nuevaContraseña;// si coinciden devuelve la nueva contraseña
		} else {
			System.out.println("Las contraseñas no coinciden, vuelva a intentarlo");
			return contraseña;//si no coinciden Devuelve la contraseña original.
		}
	
	}
		
		/*
	 * Funcion encargada de ordenar un array de menor a mayor. Lo utilizo en los rankings
		 * */
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
	static int[] elementoArray(String codigo) {
		int posicionM[] = new int[2];	//Creo el array que devolvera las posiciones
		for (int i = 0; i < 2; i++) {	//Creo este bucle para que recorra las 2 posiciones del String ("AA")
			switch (codigo.charAt(i)) {	//En caso de que todo fuera bien, compuruebo las posiciones y le asigno su fila/columna correspondiente
			case 'A':
				posicionM[i] = 0;
				
				break;
			case 'B':
				posicionM[i] = 1;
				
				break;
			case 'C':
				posicionM[i] = 2;
				
				break;
			case 'D':
				posicionM[i] = 3;
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
	static double pedirGolosinas(int codigo[], int cantidad[][], String producto[][], int venta[][], double ventasTotales, double precio[][]) {
		// SI hay existencias
		if (cantidad[codigo[0]][codigo[1]] > 0) {
			// le quito una cantidad
			cantidad[codigo[0]][codigo[1]]--;
			// aumento en 1 la venta en cada posicion donde se encuentre el producto
			ventasTotales= ventasTotales + precio[codigo[0]][codigo[1]];
			//Sumo la venta al total de ventas
			for (int filas=0;filas<4;filas++) {
				for(int columnas=0;columnas<4;columnas++) {
					if (producto[codigo[0]][codigo[1]].equals(producto[filas][columnas])) {			
						venta[filas][columnas]++;
					}
				}
			}
		} 
		return ventasTotales;//devuelvo el valor de las ventas totales ya sumandole esta venta si es que tenia cantidades disponibles, sino, devolvera el valor original
	}

	/*
	 * 
	 * Funcionamiento: Se recorren 3 bucles. El primero corresponde a las filas de
	 * las matrices, el segundo a las columnas y el ultimo lo utilizo para convertir
	 * las posiciones de las filas y columnas a su correspondiente codigo con el
	 * Switch case. La funcion es de tipo String ya que solo tiene que recorrer las
	 * matrices y devolver los elementos de estas. Esta resolucion tiene la
	 * limitacion de que el maximo de filas/ columnas es 4 ya que en el ejercicio es
	 * asi. El dia de mañana si se quisiera ampliar se tendria que modificar
	 * 
	 */
	static String mostrarProductos(String producto[][], double precio[][]) {
		String resultado="";
		// recorro las filas
		for (int filas = 0; filas < 4; filas++) {
			// recorro columnas
			for (int columnas = 0; columnas < 4; columnas++) {
				// la siguiente variable contendra el codigo en letras. La pongo aca para que se vuelva a iniciar cada vez que cambio de columna
				String codigo = "";
				// bucle para transformar filas/columnas en codigo
				for (int i = 0; i < 2; i++) {
					// array que utilizo para el switch. Tambien podria haber utilizad 2 switch, uno para filas y otro para columnas ahorrando el array pero era repetir codigo
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
				resultado= resultado + "---------------------------------------------------------- \n";
				resultado= resultado + "| Producto: " + producto[filas][columnas] + " | Codigo: " + codigo + " | Precio: " + precio[filas][columnas] + "€ | \n";
			}
		}
		return resultado;
	}

	
	/*
	 * Funcionamiento: Se le pedira al usuario que indique que producto quiere
	 * rellenar (Con su correspondiente codigo) Se transformara eso en la posicion
	 * correspondiente y luego se le pedira al usuario que indique la cantidad a
	 * aumentar si la suma de la cantidad existente y la nueva cantidad es < 5 Se
	 * realizara. Caso contrario no se cambiara y se le dira al usuario que el valor
	 * es incorrecto
	 */
	static String rellenarProductos(int cantidad[][] , int agregar, String codigo) {
		String resultado="";
		// compruebo si el codigo fue correcto
		try {
			if (comprobarCodigo(codigo)) {
				// convierto el codigo de la maquina en las posiciones
				int[] posiciones = elementoArray(codigo);
				// SI la suma es menor o igual que 5 Y ademas si el valor es positivo:
				if ((cantidad[posiciones[0]][posiciones[1]] + agregar) <= 5 && agregar > 0) {
					// realizo la suma y muestro el resultado
					cantidad[posiciones[0]][posiciones[1]] = cantidad[posiciones[0]][posiciones[1]] + agregar;
					resultado = resultado + "Se Ha realizado la operacion con exito, ahora el cajetin posee "+ cantidad[posiciones[0]][posiciones[1]] + " Productos";
				} else {
					// Caso contrario muestro el msj de error
					resultado = resultado + "Imposible realizar operacion, limite superado o valor erroneo";
				}
			}
			else {
				resultado = "Introduzca un codigo valido";
			}
			// si el codigo es incorrecto directamente no entrara y el msj de error se mostrara en su correspondiente funcion
		} catch (InputMismatchException e) {
			System.err.println("Por favor introduzca un valor numerico");
		}
		return resultado;
	}

	
	/*
	 * Funcionamiento: La siguiente funcion sera la encargada de revisar en la matriz las posiciones donde se encuentra el producto y, al
	 * encontrarla/s modificara su coste
	 * */
	static String cambiarPrecio(double precio[][], String producto[][], String codigo, double precioNuevo) {
		String resultado ="";
		//Compruebo que la posicion sea correcta 
		if (comprobarCodigo(codigo)) {
			//Guardo la posicion solicitada
			int posiciones[] = elementoArray(codigo);
			resultado = resultado +("El producto es: "+ producto[posiciones[0]][posiciones[1]] + " con un costo de: " + precio[posiciones[0]][posiciones[1]] +"€\n");
			try {
				 //Compruebo que el precio sea un valor positivo
			if(precioNuevo>0) {
				//Recorro el bucle buscando las posiciones donde se encuentra el producto
			for (int filas =0; filas< 4; filas++) {
				for(int columnas=0; columnas<4; columnas++) {
					//Al encontrar el producto modifico el valor de su precio por el nuevo
					if (producto[posiciones[0]][posiciones[1]].equals(producto[filas][columnas])) {
						precio[filas][columnas]= precioNuevo;
					}
				}
			}
			//Mensajes en funcion de los resultados
			resultado = resultado + ("Operacion realizada, Ahora " +producto[posiciones[0]][posiciones[1]] + " Cuesta " + precio[posiciones[0]][posiciones[1]] +"€" );
			}
			else {
				resultado = ("El precio no puede ser negativo");
			}
			} catch (Exception c) {
				resultado = ("Introduzca valores numericos");
			}
		}
		else {
			resultado = "Introduzca un codigo valido";
		}
		return resultado;
	}

	/*
	 * Funcionamiento: A partir de un nuevo nombre de producto, su coste, la cantidad a introducir y la posicion que se desea modificar, La funcion
	 * modificara el nombre de esa determianda posicion, sus cantidades y sus ventas. Ademas de esto la funcion buscara si ese nuevo producto ya se encuentra en
	 * la matriz, si es asi modificara tambien su precio en cada posicion donde se encuentre.
	 * */
	static String cambiarProductos(String producto[][], double precio[][], int cantidad[][], int ventas[][], String codigo, String nuevoProducto, int cantidadNueva,double nuevoPrecio) {
		String resultado="";
		//Compruebo que las posiciones hayan sido correctas
		if (comprobarCodigo(codigo)) {
			//Guardo las posiciones en el array
			int posiciones[] = elementoArray(codigo);
			resultado = resultado + ("Usted va a modificar " + producto[posiciones[0]][posiciones[1]].trim() + " que se encuentra en la posicion: " + codigo + "\n");
			try {
				//Compruebo que las cantidades sean positivas y menores a 5
			if (cantidadNueva>0 &&  cantidadNueva<=5) {
				//Compruebo que el valor del precio es positivo
			if(nuevoPrecio>0) {
				//Al cumplir con todos los requisitos modifico los valores de los productos, las cantidades y pongo sus ventas a 0
				producto[posiciones[0]][posiciones[1]] = nuevoProducto;
				//Ejecuto esta funcion para que todos los productos midan igual (En longitud)
				embellecerNombres(producto);
				cantidad[posiciones[0]][posiciones[1]] = cantidadNueva;
				ventas[posiciones[0]][posiciones[1]] = 0;
				//Compruebo en toda la matriz para ver si hay mas posiciones donde este el producto y en cada una modifico su precio
				for (int filas =0; filas< 4; filas++) {
					for(int columnas=0; columnas<4; columnas++) {
						if (producto[posiciones[0]][posiciones[1]].equals(producto[filas][columnas])) {
							precio[filas][columnas]= nuevoPrecio;
						}
					}
				}
				//Mensajes en funcion de los resultados
				resultado = resultado +("Operacion realizada, Ahora " +producto[posiciones[0]][posiciones[1]].trim() + " Cuesta " + precio[posiciones[0]][posiciones[1]] +"€" + " y posee " + cantidad[posiciones[0]][posiciones[1]] + " Unidades" );
				}
				}
			else {
				resultado = resultado +("La cantidad no puede ser mayor a 5");
			}
			}
			 catch (InputMismatchException a) {
				 resultado = resultado +("Introduzca un valor valido");
			}
		}
		else {
			resultado = resultado +("Introduzca un codigo valido");
		}
		return resultado;
	}

	/*
	 * Funcionamiento: La siguiente funcion recorrera las ventas y las guardara en un array para poder ordenarlas con el algoritmo de la burbuja.
	 * Una vez hecho esto se recorrera la matriz "ventas" buscando el valor correspondiente con el Mayor de los valores del array ordenado.
	 * Una vez encuentra el valor correspondiente se verifica un array de Strings que contendra todos los productos que ya fueron mostrados
	 * Si el producto no fue mostrado Y ademas se encuentra dentro de los 3 mas vendidos (No esta fuera del contador) lo mostrara por pantalla.
	 * en cuanto el array de ventas ordenadas avance a la siguiente posicion Y esta sea distinta, aumentara el contador. EJ ArrayOrdenado= {0,0,0,0,1,1,2,3,3,3,4,4}
	 * En el array anterior SOLO aumentara el contador de los 3 mas vendido al cambiar a un valor MENOR al anterior, osea, al pasar del 4 ->3, 3->2, etc
	 * Al final sigo recorriendo el bucle en busca de los otros productos que correspondan con esas posiciones Y si se llega al final del array pero no se encontro ningun producto que
	 * no este repetido y ademas coincida con esa ventas, se pasa al siguiente valor del array ordenado.
	 * 
	 * */
	static String masVendidos(int ventas[][], String producto[][]) {
		String resultado ="";
		int contador =0;
		String repetido[]= {"","","","","","","","","","","","","","","",""};//Array que contendra los productos ya mostrados para no repetirlos
		int arrayOrdenado[]=new int[16];//Array de ventas
		for (int filas = 0; filas < 4; filas++) {//Relleno el array anterior con las ventas de la matriz
			for (int columnas = 0; columnas < 4; columnas++) {
				arrayOrdenado[contador]=ventas[filas][columnas];
				contador++;
				}
			}
		boolean estaRepetido=false;//variable para determinar si un producto esta repetido mas adelante
		ordenarBurbuja(arrayOrdenado);//Aplico Ordenacion a las ventas
		contador=0;//contador que utilizo para los 2 mas vendidos
		int contadorProductos=0;//contador auxiliar que utilizo para el array de productos repetidos
		int finalArray=(arrayOrdenado.length-1);//variable para almacenar el final del array de ventas ordenadas(Mayor ventas)
		int ventasRepetidas=arrayOrdenado[finalArray];//variable auxiliar que utilizare para comparar si el producto anterior del array de ventas ordenadas era mayor
		if(arrayOrdenado[finalArray]==0) {//Comprobacion por si no hay ventas
			resultado = resultado + ("No hay ventas que mostrar aun \n");
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
						resultado = resultado +("Producto: " + producto[filas][columnas]+ " Ventas " + ventas[filas][columnas] +"\n");//MUESTRO EL PRODUCTO Y SUS VENTAS
						repetido[contadorProductos]=producto[filas][columnas];//Guardo el producto repetido
						contadorProductos++;//incremento el contador de productos mostrados
						filas=-1;//Reinicio la busqueda
						columnas=4;
						ventasRepetidas=arrayOrdenado[finalArray];
						finalArray--;
					}
					if(ventasRepetidas!=arrayOrdenado[finalArray]) {//aumento el contador de los mas vendidos si el siguiente valor del array ordenado es distinto
						contador++;
					}
					estaRepetido=false;//vuelvo a dejarlo en false
				}
				if(filas==3 && columnas==3) {
					finalArray--;
					filas=-1;//Reinicio la busqueda
					columnas=4;
					contador++;//Aumento el contador ya que se recorrio todo el array y no hay otro valor que coincida y no este repetido
				}	
			}
			if(contador>3) {//al mostrar ya los 3 primeros productos muestro un msj y rompo el bucle
				resultado = resultado + ("Fin del ranking\n");
				break;
			}
		}
	}
		return resultado;
	}
	/*
	 * Funcionamiento: La siguiente funcion guardara las ventas tambien en un array auxiliar las cuales ordenara
	 * con el algoritmo de la burbuja pero esta vez solo mostrara las que correspondan con el valor menos vendido.
	 * Si hay varias con el mismo vaor menos vendido entonces las mostrara tambien pero al encontrar en el array ordenado
	 * un valor mayor esta funcion finalizara.
	 * 
	 * */
	static String menosVendidos(int ventas[][], String producto[][]) {
		String resultado ="";
		String repetido[]= {"","","","","","","","","","","","","","","",""};//Array de String que almacenara los productos que ya fueron mostrados
		int arrayOrdenado[]=new int[16];//Array que contendra las ventas para luego ordenarlas
		int contador =0;//contador auxiliar
		for (int filas = 0; filas < 4; filas++) {
			for (int columnas = 0; columnas < 4; columnas++) {
				arrayOrdenado[contador]=ventas[filas][columnas];//recorro el array de ventas y almacenos sus valores en el array de productos
				contador++;}
			}
		ordenarBurbuja(arrayOrdenado); //ordeno el array de ventasOrdenadas
		contador=0;//Ahora el contador lo utilizare tanto para las posiciones del arrayOrdenado como para las posiciones del array de Strings
		boolean estaRepetido=false;//Booleano por si se repite
		int ventasRepetidas=arrayOrdenado[contador];//Esta variable contendra la ultima posicion del array de ventas Usada a fin de compararlas para ver si la siguiente es mayor
		for(int filas=0;filas<4;filas++) {//recorro filas
			for(int columnas=0;columnas<4;columnas++) {//recorro columnas
				if (ventasRepetidas < arrayOrdenado[contador]) { //SI la siguiente posicion del array ordenado es mayor
					break;//Salgo del bucle
				}
				if(ventas[filas][columnas]==arrayOrdenado[contador]) {//SI encuentro una posicion dela matriz que corresponda con la posicion de la venta:
					for(int recorrerProductos=0; recorrerProductos<repetido.length;recorrerProductos++) {//RECORRO LA LISTA DE PRODUCTOS YA MOSTRADOS
						if(producto[filas][columnas].equals(repetido[recorrerProductos])){//SI YA FUE MOSTRADO 
							estaRepetido=true;//DEVUELVE TRUE, ESTA REPETIDO
							break;
						}
					}
					if(!estaRepetido) {//si no esta repetido
					resultado = resultado + ("Producto: " + producto[filas][columnas] + " Ventas " +  ventas[filas][columnas] +"\n");//muestro el producto
						repetido[contador]=producto[filas][columnas];//lo guardo en el array de repetidos
						ventasRepetidas=arrayOrdenado[contador];//guardo esa posicion del array en Ventas ordenadas
						contador++;//aumento el contador
					}
					estaRepetido=false;
				}
				
			}
			if (ventasRepetidas < arrayOrdenado[contador]) {//SI la nueva posicion a recorrer ES mayor que la anterior
				break;//salgo del bucle
			}
		}
		return resultado;
	}
	/*
	 * 
	 * Funcionamiento: La funcion tiene como objetivo mostrar los productos en orden alfabetico junto con sus ventas y sus
	 * cantidades restantes. Para esto almaceno los productos en un array provicional y lo ordeno utilizando Arrays.Sort
	 * luego, voy recorriendo la matriz de productos en busca de los productos que se encuentran dentro del array ordenado. Al encontralo
	 * y ademas si este no es IGUAl al anterior (El producto aparece mas de una vez) muestra sus cantidades (Si el producto se repite las muestra) y sus ventas 
	 * 
	 * 
	 * */
	static String informacionProductos(int ventas[][], String productos[][], int cantidades[][], double precio[][]) {
		String imprimir="";
		String productosOrdenados[]=new String[16];//String que contendra los productos ordenados
		int contador =0;//contador auxiliar paraa las posiciones del array
		for (int filas = 0; filas < 4; filas++) {
			for (int columnas = 0; columnas < 4; columnas++) {
				productosOrdenados[contador] =productos[filas][columnas];//almaceno los productos en el array
				contador++;
			}
		}
		contador=0;
		Arrays.sort(productosOrdenados); //Ahora ordeno el array de productos por orden alfabetico
		String aux="";
		int sumaCantidades = 0;
		for (int filas = 0; filas < 4; filas++) {//Recorro filas
			for (int columnas = 0; columnas < 4; columnas++) {//Recorro columnas
				if (productosOrdenados[contador].equals(aux) && contador<15) {//SI en el array de productos el producto actual es igual al anterior:
					contador++;//Aumento el contador para pasar al siguiente producto
				}
				sumaCantidades=0;
				if (productosOrdenados[contador].equals(productos[filas][columnas]) && !productosOrdenados[contador].equals(aux)) {//SI el producto del array ordenado y el de la matriz son los mismo pero ademas este no es igual al anterior:
					if (!productosOrdenados[contador].equals(aux) && contador<15) {
					if (productos[filas][columnas].equals(productosOrdenados[contador+1]))//Miro si el siguiente se llama igual para sumar sus cantidades antes de mostrarlas 
						{
						for (int filas2 =0; filas2< 4; filas2++) {//SI EL SIGUIENTE ES EL MISMO PRODUCTO, Busco donde se encuentra en la matriz y sumo sus valores en la variable Suma cantidades
							for(int columnas2=0; columnas2<4; columnas2++) {
								if (productos[filas][columnas].equals(productos[filas2][columnas2])) {
									sumaCantidades= sumaCantidades + cantidades[filas2][columnas2]; 
								}
							}
						}
					}
					else {//Si solo hay un producto con ese nombre solo utilizo sus cantidades
						sumaCantidades = cantidades[filas][columnas];
					}
					}
					aux=productos[filas][columnas]; //guardo el producto actual en un auxiliar que es el que utilizo para comparar si hay varios con el mismo nombre
					imprimir= imprimir +"----------------------------------------------------------------\n";
					imprimir= imprimir + "| "+productos[filas][columnas] + " | Precio " + precio[filas][columnas] + " | Cantidades Disp: " + sumaCantidades + " | Ventas " + ventas[filas][columnas] +"|\n";
					filas =-1;//reseteo la busqueda
					columnas=4;
					if (contador<15) {//aumento el contador para ir con el siguiente producto de la lista
					contador++;}
				}
				
			}
		}
		return imprimir;
	}

	//Definire las variables y matrices fuera del main
	
	// Productos disponibles		
	static String[][] matrizProducto = { 
					{ "Lacasitos", "Chicles de fresa", "KitKat", "Palotes" },
					{ "Oreo", "Bolsa Haribo", "Chetoos", "Twix" },
					{ "M&M'S ", "Kinder Bueno ", "Papa Delta", "Chicles de menta" },
					{ "Lacasitos", "Crunch", "Milkybar", "KitKat" } };
	// Precio de los productos. El Precio correspode con el producto de la "matriz producto" en la misma posicion.
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
	static double ventasTotales=0;
	/**Matriz de ventas para testear
	static int[][] matrizVenta = { 
			{ 3, 0, 2, 0}, 
			{ 3, 2, 0, 1}, 
			{ 0, 1, 0, 2},
			{ 3, 0, 1, 2} };**/
	
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		// Ahora definire los booleanos para los menus
		
		//Asigno mediante una funcion valores a ambas matrices
		inicializarMatriz(matrizVenta, 0);
		inicializarMatriz(matrizCantidad, 3);
		//Hago que los nombres midan todos los mismos caracteres
		embellecerNombres(matrizProducto);
	
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
					if (comprobarCodigo(posicion)) {//compruebo que el codigo sea correcto antes de hacer nada
					int[] posMatriz = elementoArray(posicion);//transformo las posiciones
					double ventasaux= pedirGolosinas(posMatriz, matrizCantidad, matrizProducto, matrizVenta,ventasTotales,matrizPrecio);//guardo el resultado en una var
					if (ventasTotales < ventasaux) {//Compruebo que se haya realizado con exito osea, si habia cantidades disponibles
						System.out.println("Disfrute de su " + matrizProducto[posMatriz[0]][posMatriz[1]]);
						ventasTotales = ventasTotales + ventasaux;
					}
						else {
							System.out.println("No se poseen mas cantidades de ese producto");		
						}
					}
					else {
						System.err.println("Debe Introducir posiciones correctas");
					}
					break;
				case 2:
					System.out.println(mostrarProductos(matrizProducto, matrizPrecio));
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
									System.out.println(rellenarProductos(matrizCantidad, agregar,posicion));
									break;
									
								case 3:
									System.out.println("Indique el codigo del producto que quiera modificar su coste");
									posicion=s.next();
									System.out.println("Indique el nuevo precio del producto");
									nuevoPrecio=s.nextDouble();
									System.out.println( cambiarPrecio(matrizPrecio, matrizProducto,posicion,nuevoPrecio));
									break;
									
								case 4:
									System.out.println("Indique el producto que desea modificar: ");
									posicion = s.next();
									System.out.println("Introduzca el nombre del nuevo producto: ");
									s.nextLine();
									String nuevoProducto=s.nextLine();
									System.out.println("Introduzca la cantidad de elementos que contendra el cajetin: ");
									int cantidadNueva =s.nextInt();
									System.out.println("Introduzca el precio del producto: ");
									nuevoPrecio=s.nextDouble();
									System.out.println(cambiarProductos(matrizProducto, matrizPrecio, matrizCantidad, matrizVenta,posicion,nuevoProducto,cantidadNueva,nuevoPrecio));
									break;
								case 5:
									System.out.println( masVendidos(matrizVenta,matrizProducto));
									break;
								case 6:
									System.out.println( menosVendidos(matrizVenta, matrizProducto));
									break;
								case 7:
									System.out.println(informacionProductos(matrizVenta, matrizProducto, matrizCantidad, matrizPrecio));
									break;
								case 8:
									System.out.println("Se ha recaudado hasta la fecha: "+  ventasTotales +"€");
									break;
								case 9:
									System.out.println("Saliendo del menu Administrador");
									salirMenuA = true;
									break;
								case 10:
									s.close();
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
