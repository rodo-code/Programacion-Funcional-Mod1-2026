val cuadrado: Int => Int = numero => numero * numero

val longitud: String => Int = texto => texto.length

val doble: Int => Int = numero => numero * 2

// Una función de orden superior, es una función que cumple al menos 
// una de las siguientes caracteristicas:
// 1. Recibe una o mas funciones como parámetro
// 2. Devuelve una función como resultado

def aplicarOperacion(operacion: Int => Int, numero: Int): Int = {
  operacion(numero)
}

// No podria enviar la funcion longitud a aplicarOperacion
aplicarOperacion(cuadrado, 5)
aplicarOperacion(doble, 5)
aplicarOperacion(numero => numero*3, 5)

def verificar(numero: Int, condicion: Int => Boolean): Boolean = {
  condicion(numero)
}

val esPar: Int => Boolean = numero => numero%2==0
val esPositivo: Int => Boolean = numero => numero > 0
verificar(7,esPar)
verificar(5,esPositivo)
verificar(6, numero => numero%3==0)

// EJERCICIO 1
// Crear una funcion de orden superior llamada calcular que reciba 3 parametros
// 1. Funcion del tipo (Int, Int) => Int
// 2. Un Int
// 3. Un Int
// La función calcular debe aplicar la función del primer parametro a los otros
// 2 parametros
// EJEMPLOS
// calcular(suma,4,9) => 13
// calcular(resta,3,1) => 2
// calcular(multiplicacion,8,5) => 40
// calcular(division,7,3) => 2
// calcular(modulo,7,3) => 1

val suma: (Int, Int) => Int = (a,b) => a+b
val resta: (Int, Int) => Int = (a,b) => a-b
val multiplicacion: (Int, Int) => Int = (a,b) => a*b
val division: (Int, Int) => Int = (a,b) => a/b
val modulo: (Int, Int) => Int = (a,b) => a%b

def calcular(operacion: (Int,Int)=>Int, a: Int, b: Int): Int = {
  operacion(a,b)
}
calcular(suma,4,9)
calcular(resta,3,1)
calcular(multiplicacion,8,5)
calcular(division,7,3)
calcular(modulo,7,3)

// CONVERTIR BOLIVIANOS A DOLARES
def convertirBolivianosADolares(monto: Double, obtenerTipoDeCambio: () => Double): Double = {
  val tipoCambio = obtenerTipoDeCambio()
  monto/tipoCambio
}
// Funcion que no recibe nada
val obtenerTipoDeCambio: () => Double = () => 11.52

convertirBolivianosADolares(1000,obtenerTipoDeCambio)

// EJEMPLO 
// En una empresa se desea calcular el salario de sus trabajadores
// bajo las siguientes reglas
// Si un trabajador, trabaja al menos 5 años pero menos de 10 recibe un bono del 10%
// Si un trabajador, trabaja al menos 10 años pero menos de 20, recibe un bono del 20%
// Si un trabajador, trabaja mas de 20 años recibe un bono del 30%

def calcularSalario(salario: Double, antiguedad: Int): Double = {
  if antiguedad<5 then salario
  else if antiguedad>=5 && antiguedad<10 then salario*1.1
  else if antiguedad>=10 && antiguedad<20 then salario*1.2
  else salario*1.3
}

def calcularSalarioV2(salario: Double, bonificacion: Double => Double) = {
  salario + bonificacion(salario)
}

calcularSalarioV2(1000,salario => salario*0.1)



