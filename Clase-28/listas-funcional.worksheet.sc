// DATO CURIOSO: Se pueden crear listas con diferentes tipos de dato
val curioso: List[Any] = List(234,"hola",23.22)
curioso

val numeros: List[Int] = List(2,7,9,1,0,6)

// ASIGNACIÓN O TRANSFORMACIÓN
numeros.map((x)=>(x*2))

numeros.map(x => x+10)

// EJERCICIO 1
// Dado una lista de precios ej. List(40.50, 30.20, 21.80)
// Generar una nueva lista usando map que tenga los precios mas un 10%

val precios: List[Double] = List(40.50, 30.20, 21.80)
precios.map(precio => precio+(precio/10) )

// FILTRADO

numeros.filter(numero => numero%2==0)

def mayorLimite(cotaInferior: Int)(lista: List[Int]): List[Int] = {
  lista.filter(num => num > cotaInferior)
}

mayorLimite(5)(numeros)

val mayor5 = mayorLimite(5)
mayor5(numeros)

val numeros2 = List(7,3,5,4,9,0)
numeros2.filter(x => x%2==1).map(x => x*3)


// EJERCICIO 2
// Dada una lista de numeros, dividirla entre 10 y luego obtener solo
// los números que NO sean multiplos de 3
// Si la lista fuera List(43,29,27,16,62) => (4,2,2,1)
// Usando map y filter

val numeros3 = List(43,29,27,16,62)
numeros3.map(x => x/10).filter(x => x%3!=0)

// FLAT MAP

List(7,3,5,4,9,0).map(x => List(x,x))
List(7,3,5,4,9,0).flatMap(x => List(x,x*2,x*3))

// APLICAMOS A CLASES
case class Producto(
  nombre: String,
  categoria: String,
  precio: Double
)

val productoA = Producto("Masa para Pizza","Alimentos",20)
val productoB = Producto("Coca Cola","Bebidas",8)
val productoC = Producto("Pringles","Alimentos",30)
val productoD = Producto("Fernet","Bebidas",90)

val listaSupermercado = List(productoA,productoB,productoC,productoD)

listaSupermercado.filter(producto => producto.precio>=30)
.map(producto => producto.nombre)

listaSupermercado.flatMap(producto => List(producto.nombre,producto.precio))
