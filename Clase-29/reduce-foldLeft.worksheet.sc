// REDUCIR

val numeros: List[Double] = List(23.8,12.2,25.5,1.5)
numeros.reduce((a,b) => a+b)

val numeros2: List[Int] = List(4,2,7,5)
numeros2.reduce((a,b) => a*b)

// Ejercicio 1
// Usar reduce para de una lista de Int obtener el menor elemento

val numeros3: List[Int] = List(1,5,2,9)
numeros3.reduce((a,b) => if a<b then a else b)

// Reduce tiene un error cuando se trata de listas vacias

// FOLD LEFT
val numeros4: List[Int] = List(2,1,8,6)
val suma = numeros4.foldLeft(0)((acumulador, elemento) => acumulador+elemento)
suma

// De una lista de Strings, sumar el tamaño de cada elemento
val frutas = List("manzana","pera","kiwi","fresa","naranja")

// OPCION 1: map y reduce
frutas.map(fruta => fruta.length).reduce((a,b)=>a+b)

// OPCION 2: foldLeft
frutas.foldLeft(0)((acumulador, elemento) => acumulador + elemento.length)

// OPCION 3: reduce y luego length
frutas.reduce((a,b) => a+b).length

// EJERCICIO 2
// De una lista de Int, obtener el número con mas digitos, si hay empates
// obtener el menor de entre los empatados
// Ej 
// List(123,23,2,87,43,27) => 123
// List(345,123,34,9,1,567) => 123

def contarDigitos(num: Int): Int = {
  if num == 0 then 0
  else 1+contarDigitos(num/10)
}
contarDigitos(230)

val numeros5 = List(345,127,34,9,1,567)
numeros5.foldLeft(0)((acumulador, elemento)=>{
  val digitosAcumulador = contarDigitos(acumulador)
  val digitosElemento = contarDigitos(elemento)
  if digitosElemento > digitosAcumulador then elemento
  else if digitosElemento == digitosAcumulador then math.min(acumulador,elemento)
  else acumulador
})


