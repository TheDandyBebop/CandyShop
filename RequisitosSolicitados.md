Requisitos: 
1
PROYECTO 1 – CANDY SHOP
Nos piden que implementemos el software para una máquina de vending.
Cada producto tiene un nombre y un precio. A continuación, se te proporciona el código donde se almacenan los productos y su precio.
String[][] productos = {
{"Lacasitos", "Chicles de fresa", "KitKat", "Palotes"},
{"Oreo", "Bolsa Haribo", "Chetoos", "Twix"},
{" M&M'S ", " Kinder Bueno ", "Papa Delta", "Chicles de menta"},
{"Lacasitos", "Crunch", "Milkybar", "KitKat"}
};
double[][] precio = {
{1.5, 0.8, 1.1, 0.9},
{1.8, 1, 1.2, 1},
{1.8, 1.3, 1.2, 0.8},
{1.5, 1.1, 1.1, 1.1}
};


También tendrán una cantidad inicial, que en principio será de 3 para todos los productos de la máquina.
Tendremos un pequeño menú con las siguientes opciones:
1) Pedir golosina: el usuario indicará la posición de la golosina que quiere sacar. Cada posición, viene asociada por un código de letras que se corresponde con una fila y una columna. Por ejemplo, si el usuario teclea la posición CA, esto significa que está pidiendo la golosina correspondiente a la fila C (fila 2) columna A (columna 0). Cuando no haya más golosinas se le indicará al usuario. Solo puede pedir una golosina y supondremos que el usuario siempre tiene dinero al elegir. Recuerda de disminuir la cantidad la pedir.


Ejemplo:
2
Selecciona una opción:
1
Introduce la posicion de la golosina:
11
Ha indicado una posicion incorrecta
*** Opciones ***
1. Pedir golosina
2. Mostrar golosinas
3. Submenu administrador
Selecciona una opción:
1
Introduce la posicion de la golosina:
AB
Puede retirar su producto
2) Mostrar golosinas: mostrara todas las golosinas disponibles. Mostrará el nombre de la golosina, el código que debe introducir el usuario, y el precio. La cantidad no se mostrará.


Ejemplo:
El listado de productos es:
-------------------------------------------------
| KitKat | Posición: AA | Precio: 1.1€ |
-------------------------------------------------
| Chicles de fresa | Posición: AB | Precio: 0.8€ |
-------------------------------------------------
| Lacasitos | Posición: AC | Precio: 1.5€ |
-------------------------------------------------
...

3) Submenú administrador: este es un menú exclusivo al que solo puede acceder un técnico por lo que nos pedirá una contraseña. Si el usuario escribe “DAM” podrá acceder a dicho menú.
3.1) Cambiar contraseña: Permite cambiar la contraseña de acceso del administrador. El administrador, introducirá la nueva contraseña y la volverá a escribir para verificarla. Si la nueva contraseña y la verificación coinciden, cambia la contraseña para futuros accesos.

Ejemplo:
3
Introduzca la NUEVA contrasennia:
programacion
REPITA la NUEVA contrasennia:
prog
El password nuevo y su confirmacion NO COINCIDEN. Se mantiene el que ya habia.
...
Seleciona un opción:
1
Introduzca la NUEVA contrasennia:
programacion
REPITA la NUEVA contrasennia:
programacion
¡¡¡ Password cambiado !!!


3.2) Rellenar golosinas: Pedirá la posición de la golosina y la cantidad que quiere añadir. La máquina solo tiene capacidad para alojar hasta 5 golosinas en cada compartimento. Si el técnico desea introducir más golosinas de las que se pueden alojar, la máquina deberá informar de ello indicando un error.

Ejemplo:
Supongamos que el producto que esta en la posición AC tiene ya 3 unidades y el técnico quiere introducir 5 unidades (5 + 3 = 8 pero el cajetín solo admite 5 unidades. Informará del error y no se rellenará)
Supongamos ahora que para el producto que esta en la posición AC (y que tiene 3 unidades) y el técnico quiere introducir 2 unidades (2 + 3 = 5 y el cajetín admite hasta 5 unidades. Se produce la reposición y se informa de que se ha rellenado el cajetín)

3.3) Cambiar precio: Permite cambiar el precio de un producto que haya en la máquina. Si un producto aparece en varias posiciones de la máquina (ejemplo, AA y DB), el precio es actualizado en TODAS las posiciones (no sería lógico tener un KitKat a 0,9€ en una posición y a 1,1€ en otra).

3.4) Cambiar producto: Permite cambiar el nombre del producto que hay en una posición determinada de la máquina. Para ello, el administrador indicará la posición del producto y a continuación, el nuevo nombre producto que se ubicará en esa posición, el precio y las unidades con las que lo rellena. (Nota: fíjate que si al indicar el nuevo precio, hay en la maquina en otra posición un producto que se llama igual, también deberá cambiar el precio para ese producto).
4


3.5) Ranking más vendidos: Permite conocer el ranking de los 3 productos que hasta el momento han sido más vendidos, indicando el nombre del producto y la cantidad de ventas que han tenido. Si solo se han producido ventas de dos productos hasta el momento, se muestran solo los dos que se han vendido; si solo se ha producido la venta de un producto, en más vendidos solo se muestra ese producto; si no se ha producido ninguna venta de ningún producto, no se muestra ningún producto y se informa de que 
aún no ha habido ventas. Debe indicarse en cada caso de cuántos elementos se compone la lista.

Ejemplo: Si no hay varios productos con la misma cantidad vendida (se muestran 3 como máximo)
Selecciona una opción: 5
Los productos MAS vendidos son:
Producto 1: Lacasitos y cantidad: 3
Producto 2: Oreo y cantidad: 2
Producto 3: M&M'S y cantidad: 1
Si no hay varios productos con la misma cantidad vendida (pueden entonces aparecer mostrados más de 3 productos en la lista)
Selecciona una opción: 5
Los productos MAS vendidos son:
Producto 1: Bolsa Haribo y cantidad: 2
Producto 2: Lacasitos y cantidad: 1
Producto 3: Chicles de fresa y cantidad: 1
Producto 4: KitKat y cantidad: 1
Producto 5: Palotes y cantidad: 1
Producto 6: Oreo y cantidad: 1
Producto 7: Chetoos y cantidad: 1
Producto 8: Twix y cantidad: 1
5


3.6) Menos vendido: Permite conocer el producto/s menos vendido/s, indicando el nombre del producto y la cantidad de ventas que han tenido. Si hay varios productos con la misma mínima venta, se muestran dichos productos, la cantidad vendida y por cuántos elementos está compuesta esa lista.


Ejemplo:
Selecciona una opción: 6
Los productos MENOS vendidos son:
Producto 1: Milkybar y cantidad: 0
Producto 2: Crunch y cantidad: 0
Producto 3: Chicles de menta y cantidad: 0
Producto 4: Papa Delta y cantidad: 0
Producto 5: Kinder Bueno y cantidad: 0
Producto 6: Twix y cantidad: 0
Producto 7: Chetoos y cantidad: 0
Producto 8: Bolsa Haribo y cantidad: 0
Producto 9: Palotes y cantidad: 0
Producto 10: KitKat y cantidad: 0
Producto 11: Chicles de fresa y cantidad: 0

En este caso hay 11 productos de los que aun no se ha producido ninguna venta. Si te fijas bien, no está en dicha lista ninguno de los productos que han salido antes en los más vendidos.


3.7) Info productos: se muestran los productos ordenados alfabéticamente, y por cada producto se muestra el precio que tiene en la máquina, la cantidad de unidades que hay disponibles y las ventas que se han producido (¡¡¡Recuerda!!! Si hay varios productos del mismo tipo en dispensadores diferentes, se muestra ese nombre una sola vez, pero la cantidad total de unidades y las ventas, deben ser la suma de lo que se ha producido en los distintos dispensadores).


Ejemplo: Lacasitos hay en la posición AA (3 unidades) y en la posición DA (3 unidades). Con el Kit-Kat sucede lo mismo, está en la posición AC y DD, con 3 unidades, en cada uno pero se produce una venta de este producto, y consultamos la info del la máquina…
Selecciona una opción: 7
...
---------------------------------------------------------------------
| Kinder Bueno | Precio: 1.3€ | U. disponibles: 3 | Ventas: 0 |
---------------------------------------------------------------------
| KitKat | Precio: 1.1€ | U. disponibles: 5 | Ventas: 1 |
---------------------------------------------------------------------
| Lacasitos | Precio: 1.5€ | U. disponibles: 6 | Ventas: 0 |
---------------------------------------------------------------------
| M&M'S | Precio: 1.8€ | U. disponibles: 3 | Ventas: 0 |
---------------------------------------------------------------------
6
...
3.8) Ventas totales: Mostrará cuál es la recaudación que lleva hasta el momento la máquina.
3.9) Cerrar sesión administrador: sale del submenú de administración al menú normal de usuario.
3.10) Apagar la máquina: permite finalizar el programa de una forma controlada.
¡¡¡ IMPORTANTE !!!
• El programa debe ser modularizado, es decir, todas las funciones que veas que sean necesarias debes crearlas, así como todas aquellas acciones que veas que se repitan. Piensa que funciones pueden ser, puesto que tendrás varias.
• Las funciones deben ser lo más genéricas posibles.
• El programa debe estar preparado para que cualquier parámetro erróneo que le sea introducido, pueda controlarlo (letras en lugar de números, números negativos…) y NO FALLE O DE RESULTADOS INCOHERENTES (número de productos negativos, etc.)
• Debe contar con los menús que se han comentado en los párrafos anteriores, así como nombres de variables y funciones descriptivos, comentarios, respeto de las convenciones de Java. No respetar estos aspectos conlleva penalización.
