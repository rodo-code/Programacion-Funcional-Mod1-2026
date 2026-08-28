object Configuracion {
  val nombreSistema: String = "Mi poderoso sistema"
  val version: String = "1.0"
  
  def obtenerNombreCompletoApp: String = {
    s"$nombreSistema $version"
  }
}

Configuracion.nombreSistema
Configuracion.version
Configuracion.obtenerNombreCompletoApp

object Calculadora {
  val suma = (a:Int, b:Int) => (a+b)
  val resta = (a:Int, b:Int) => (a-b)
  val multiplicacion = (a:Int, b:Int) => (a*b)
  val division = (a:Int, b:Int) => (a/b)
  def apply(a: Int, b: Int): Int = { a+b }
}

Calculadora.suma(5,3)
Calculadora(4,9)
// OBJETOS ACOMPAÑANTES

class Producto private(
  val nombre: String,
  val precio: Double,
  val cantidad: Int
){
  def precioConImpuesto(): Double = {
    precio + (precio*Producto.IVA)
  }
  def actualizarCantidad(nuevaCantidad: Int): Producto = {
    Producto.crearSoloCantidad(this,nuevaCantidad)
  }
  def aplicarDescuento(descuento: Double): Producto = {
    val nuevoPrecio = precio - (precio*descuento)
    Producto.crearSoloPrecio(this,nuevoPrecio)
  }
}

object Producto {
  val IVA: Double = 0.13
  // METODOS DE FABRICA - "CONSTRUCTORES"
  def apply(nombre: String, precio: Double, cantidad: Int): Producto = {
    new Producto(
      nombre,
      if precio < 0 then 0 else precio,
      if cantidad < 0 then 0 else cantidad
    )
  }
  def crearSoloCantidad(producto: Producto, nuevaCantidad: Int): Producto = {
    Producto(producto.nombre,producto.precio,nuevaCantidad)
  }
  def crearSoloPrecio(producto: Producto, precio: Double): Producto = {
    Producto(
      producto.nombre,
      precio,
      producto.cantidad
    )
  }
}

val producto = Producto("Empanadas",10,7)
producto.precioConImpuesto()
val producto2 = producto.actualizarCantidad(10)
val producto3 = producto2.aplicarDescuento(0.2)
val productoGenial = Producto("Caramelo",1,1)

// SOBRECARGA DE OPERADORES

class Vector(
  val x: Double,
  val y: Double
) {

  def +(vector2: Vector): Vector = {
    new Vector(x+vector2.x, y+vector2.y)
  }

  def -(vector2: Vector): Vector = {
    new Vector(x=vector2.x, y-vector2.y)
  }

  def *(vector2: Vector): Double = {
    (x*vector2.x)+(y*vector2.y)
  }
}

val vector1 = new Vector(4,9)
val vector2 = new Vector(2,3)
val vectorSuma = vector1 + vector2
vectorSuma.x
vectorSuma.y
val vectorResta = vector1 - vector2
vectorResta.x
vectorResta.y
vector1 * vector2

// EJERCICIO 2 hasta 8:15

/* 
  Crear una clase Fracción, que tenga dos parametros numerador y denominador
  Crear las 4 operaciones usando sobrecarga de operadores
*/

class Fraccion(
  val numerador: Int,
  val denominador: Int
) {
  def +(fraccion: Fraccion): Fraccion = {
    val nuevoDenominador = Matematica.mcm(denominador, fraccion.denominador)
    val nuevoNumerador = (nuevoDenominador/denominador*numerador) + (nuevoDenominador/fraccion.denominador*fraccion.numerador)
    new Fraccion(nuevoNumerador,nuevoDenominador)
  }
  def -(fraccion: Fraccion): Fraccion = {
    val nuevoDenominador = Matematica.mcm(denominador, fraccion.denominador)
    val nuevoNumerador = (nuevoDenominador/denominador*numerador) - (nuevoDenominador/fraccion.denominador*fraccion.numerador)
    new Fraccion(nuevoNumerador,nuevoDenominador)
  }
  def *(fraccion: Fraccion): Fraccion = {
    val nuevoDenominador = denominador * fraccion.denominador
    val nuevoNumerador = numerador * fraccion.numerador
    new Fraccion(nuevoNumerador,nuevoDenominador)
  }
  def /(fraccion: Fraccion): Fraccion = {
    val nuevoDenominador = denominador * fraccion.numerador
    val nuevoNumerador = numerador * fraccion.denominador
    new Fraccion(nuevoNumerador,nuevoDenominador)
  }

}
object Matematica {
  def mcd(a: Int, b: Int): Int = {
    if b==0 then a
    else mcd(b,a%b)
  }
  def mcm(a: Int, b: Int): Int = {
    a*b/mcd(a,b)
  }
}

val frac1 = new Fraccion(3,5)
val frac2 = new Fraccion(2,3)
val sumaFrac = frac1+frac2
val restaFrac = frac2 - frac1
val multFrac = frac1*frac2
val divFrac = frac1 / frac2
divFrac.numerador
divFrac.denominador

