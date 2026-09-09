val numeros: List[Int] = List(10, 20, 30)
val numeros2 = 0 :: numeros
numeros2

val vacia = Nil
val llena = 1 :: 2 :: 3 :: vacia
// .head obtiene el primer elemento
llena.head
// .tail la lista sin el primer elemento
llena.tail
// .init la lista sin el ultimo elemento
llena.init
// .last obtiene el ultimo elemento
llena.last

val respuesta = llena match 
  case Nil => "Lista vacia"
  case cabeza :: cola => s"El primer elemento es $cabeza y el resto es $cola"

respuesta

def sumar(lista: List[Int]): Int = {
  lista match
    case Nil => 0
    case cabeza :: cola => cabeza + sumar(cola)
}

sumar(llena)
sumar(numeros2)

/* EJERCICIO 1 

Implemente funciones que permitan:

1. Generar un string con los elementos de una lista
2. Encontrar el mayor elemento de una lista de enteros positivos
*/

// PARTE 1

def toString(lista: List[Int]): String = {
  lista match
    case Nil => ""
    case cabeza :: cola => s"$cabeza ${toString(cola)}"
}

toString(numeros2)

// PARTE 2
def mayorLista(lista: List[Int]): Int = {
  lista match
    case Nil => -1
    case cabeza :: cola => {
      val mayorResto = mayorLista(cola)
      if mayorResto > cabeza then mayorResto else cabeza
    }
}

mayorLista(List(23,12,19,5))

// AGREGAR AL FINAL

val lista3 = List(23,12,19,5)
val lista4 = lista3 :+ 7 :+ 9 :+ 1
lista4

// TRANSFORMACIONES

def duplicar(lista: List[Int]): List[Int] = {
  lista match
    case Nil => Nil
    case cabeza :: cola => (cabeza*2) :: duplicar(cola)
}
duplicar(lista4)

// EJERCICIO 2

// Implemente una función que reciba una lista de enteros 
// y devuelva una nueva lista donde cada valor haya sido dividido por 10.

// FILTRADO
// De una lista de enteros sacar solo los pares

def soloPares(lista: List[Int]): List[Int] = {
  lista match
    case Nil => Nil
    case cabeza :: cola => {
      if cabeza%2==0 then cabeza :: soloPares(cola)
      else soloPares(cola)
    }
}

soloPares(List(1,2,3,4,5,6,7))

// EJERCICIO 3

// Implemente una función que reciba una lista de enteros y un límite. 
// Debe devolver una nueva lista con los valores mayores al límite.

// mayorLimite(List(4,9,8,3,7),7) ==> List(9,8)

// LISTAS DE OBJETOS

case class Venta(
  producto: String,
  precio: Double
)

val carritoVenta = List(Venta("Pizza",30),Venta("Cafe",10), Venta("Agua",5))

def obtenerPrecioTotal(listaVenta: List[Venta]): Double = {
  listaVenta match 
    case Nil => 0
    case cabeza :: cola => (cabeza.precio) + obtenerPrecioTotal(cola)
}

obtenerPrecioTotal(carritoVenta)