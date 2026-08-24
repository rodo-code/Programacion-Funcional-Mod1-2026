val doble: Int => Int = numero => numero*2
val triple: Int => Int = numero => numero*3
val multiplicar: (Int,Int) => Int = (numero,x) => numero*x

def veces(repeticion: Int): (Int => Int) = {
  (numero: Int) => numero*repeticion
}

val dobleV2 = veces(2)
val tripleV2 = veces(3)

dobleV2(6)
tripleV2(10)

def validarMinimo(minimo: Int): (Int => Boolean) = {
  (numero: Int) => numero >= minimo
}

val minimo10 = validarMinimo(10)
minimo10(20)
minimo10(5)
val minimo0 = validarMinimo(0)
minimo0(4)
minimo0(-1)

/*
  EJERCICIO 1

  Crear una funcion de orden superior llamada elevado
  que reciba un numero y genere una función que reciba un
  Int y devuelva un Int.
  La función que deve devolver elevado debe funcion 
  de la siguiente manera

  val cuadrado = elevado(2)
  val cubo = elevado(3)
  cuadrado(5) => 25
  cubo(4) => 64
*/

def elevado(exponente: Int): (Int => Int) = {
  if exponente == 0 then { numero => 1 }
  else numero => numero * elevado(exponente-1)(numero)
}

val cuadrado = elevado(2)
val cubo = elevado(3)
val elevado20 = elevado(20)

elevado(5)(4)

cuadrado(10)
cubo(5)
elevado20(2)


def crearSaludo(saludo: String): String => String = {
  (nombre: String) => s"$saludo $nombre"
}

val saludoFormal = crearSaludo("Buenos dias")
val saludoInformal = crearSaludo("Hola")
saludoFormal("Rodolfo")
saludoInformal("Rodolfo")

// APLICACIÓN PARCIAL

def aplicarDescuento(precio: Double, descuento: Double): Double = {
  precio - (precio*descuento)
}

/*def crearDescuento(descuento: Double): (Double => Double) = {
  precio => aplicarDescuento(precio,descuento)
}*/

val descuento10: (Double => Double) = {
  precio => aplicarDescuento(precio,0.1)
}

val descuento20: (Double => Double) = {
  precio => aplicarDescuento(precio,0.2)
}

//val descuento30 = crearDescuento(0.3)

aplicarDescuento(120,0.15)
descuento10(120)
descuento20(120)
//descuento30(120)

/* 
EJERCICIO 2

Crear una funcion calcularBonificacion que reciba el sueldo base
de un trabajador y el porcentaje de bonificacion que tiene,
y retorne su sueldo final.

calcularBonificacion(1000,0.1) => 1100
calcularBonificacion(2000,0.3) => 2600 */

def calcularBonificacion(sueldo: Double, porcentaje: Double): Double = {
  sueldo + (sueldo*porcentaje)
}

calcularBonificacion(2000,0.3)

/* Utilice APLICACIÓN PARCIAL para crear dos funciones

1. Una llamada bonificacion10 que recibe un Double y retorna un 
Double y que se usa de la siguiente manera

bonificacion10(1000) => 1100 */

val bonificacion10: Double => Double = (sueldo) => calcularBonificacion(sueldo,0.1)
bonificacion10(1000)

/* 2. Una llamada bonificacion20 que recibe un Double y retorna un 
Double y que se usa de la siguiente manera

bonificacion20(500) => 600

*/
def bonificacion20(sueldo: Double): Double = {
  calcularBonificacion(sueldo,0.2)
}
bonificacion20(500)

// CURRIFICACIÓN / CURRYNG

def suma(a: Double, b: Double): Double = {
  a+b
}

def sumaCurr(a: Double)(b: Double): Double = {
  a+b
}

suma(2,3)
sumaCurr(2)(3)

val funcion = sumaCurr(2)
val suma2: Double => Double = (x) => sumaCurr(2)(x)
suma2(6)
funcion(6)

// CURRIFICANDO LA FUNCION DE BONIFICACION

def calcularBonificacionCurr(porcentaje: Double)(sueldo: Double): Double = {
  sueldo + (sueldo*porcentaje)
}

val bonificacion10Curr = calcularBonificacionCurr(0.1)
bonificacion10Curr(1000)
val bonificacion20Curr = calcularBonificacionCurr(0.2)
bonificacion20Curr(500)

// CURRIFICANDO FUNCIONES DE MAS PARAMETROS
def promedio(nota1: Double)(nota2: Double)(nota3: Double): Double = {
  (nota1*0.3)+(nota2*0.3)+(nota3*0.4)
}

promedio(60)(55)(75)
val calculandoCuantoNecesitoParaElFinal = promedio(50)(70)
calculandoCuantoNecesitoParaElFinal(50)
val calculandoCuantoNecesitoDespuesDelParcial = promedio(70)
calculandoCuantoNecesitoDespuesDelParcial(60)(60)