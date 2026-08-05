// FUNCION PURA
def cubo(num: Int): Int = {
  num * num * num
}

cubo(2)
cubo(10)

// FUNCION IMPURA
var descuento: Double = 0.1
def calcularDescuento(precio: Double): Double = {
  precio * descuento
}
calcularDescuento(90)
descuento = 0.2
calcularDescuento(90)

// FUNCION IMPURA
var contador: Int = 1
def aumentarContador(aumento: Int): Int = {
  contador = contador + aumento
  contador
}
aumentarContador(3)
aumentarContador(3)

// FUNCION IMPURA POR EFECTO SECUNDARIO

def imprimirSaludo(nombre: String): Unit = {
  println(s"Hola $nombre")
}

imprimirSaludo("Rodo")
imprimirSaludo("Tapuna")

// FUNCION PURA QUE LUEGO IMPRIME EL SALUDO

def mensajeSaludo(nombre: String): String = {
  s"Hola $nombre"
}

println(mensajeSaludo("Efrain"))

// MODELO DE SUSTITUCION

def doble(num: Int): Int = {
  num * num
}

def suma(a: Int, b: Int): Int = {
  a + b
}

doble(suma(5,3))

// EVALUACION POR NOMBRE

def imprimirDosVeces(valor: => Int): Unit = {
  println(valor)
  println(valor)
}

def obtenerNumero(num: Int): Int = {
  println("Obteniendo numero...")
  num 
}

imprimirDosVeces(obtenerNumero(6))