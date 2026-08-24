// RECORDANDO VALORES
val num: Int = 5
val nombre: String = "Rodolfo"

// Guardando métodos en valores
def cuadrado(num: Int): Int = num * num
val square = cuadrado
square(5)

// EJEMPLO de función Int => Int
val doble: Int => Int = numero => numero*2
doble(6)

// EJEMPLO de función Int => Boolean
val esPar: Int => Boolean = numero => numero%2==0
esPar(100)

// Ejemplo de función String => Int
val longitud: String => Int = texto => texto.length
longitud("Programacion Funcional")

// Ejemplo de funcion Double => String
val signo: Double => String = numero => {
  if numero > 0 then "POSITIVO"
  else if numero < 0 then "NEGATIVO"
  else "CERO"
}
signo(0)

// EJERCICIO 1
// Crear una funcion que reciba un entero e indique si es multiplo de 3
val multiplo3: Int => Boolean = numero => numero%3==0
multiplo3(5)

// EJERCICIO 2
// Crear una funcion que reciba un double y devuelva el 10% del valor
val diezPorciento: Double => Double = numero => numero/10
diezPorciento(56)

// EJERCICIO 3
// Crear una funcion que reciba un texto y devuelve el texto 
// sin el ultimo caracter y sin el primer caracter
val cortar: String => String = texto => {
  if texto.length <= 1 then ""
  else texto.init.tail
}
cortar("po")

// EJEMPLO de guardar una función lambda en un valor
val suma = (a: Int, b: Int) => a+b
suma(6,7)

val triplicar: Int => Int = numero => numero*3
val triplicarV2 = (numero: Int) => numero*3

// EJEMPLO funcion que recibe dos parametros
val multiplicar: (Int, Int) => Int = (a,b) => a*b
val multiplicarV2 = (a: Int, b: Int) => a*b
multiplicar(5,8)
multiplicarV2(5,8)

// EJEMPLO promedio
val promedio: (Double, Double, Double) => Double = 
(nota1,nota2,notaFinal) => nota1*0.3 + nota2*0.3 + notaFinal*0.4
val promedioV2 = 
(nota1: Double ,nota2: Double, notaFinal: Double) => nota1*0.3 + nota2*0.3 + notaFinal*0.4
promedio(80,90,67)
promedioV2(80,90,67)

// EJEMPLO Funcion que devuelve el mayor
val max: (Int, Int) => Int = (a,b) => {
  if a>b then a
  else b
}
val maxV2 = (a: Int, b: Int) => {
  if a>b then a
  else b
}
max(5,9)
maxV2(10,6)

// RECURSION
val factorial: Int => Int = numero => {
  if numero == 0 then 1
  else numero*factorial(numero-1)
}
factorial(5)

// SWAP 
val swap: (Int, Int) => (Int, Int) = (a,b) => (b,a)
swap(6,10)

// EJERCICIO 4
// Crear una funcion que reciba 3 parametros Int, el primero representa la hora
// actual, el segundo representa el minuto actual
// y el tercero la cantidad de minutos que quiero aumentar a la hora actual
// esta funcion devuelve la hora y minutos, despues de aumentar los minutos
// especificados en el parametro
// EJEMPLOS
// aumentarTiempo(9,18,5) => (9,23)
// aumentarTiempo(9,58,5) => (10,3)
// aumentarTiempo(23,55,10) => (0,5)
// aumentarTieimpo(9,18,1450) => (9,28)
