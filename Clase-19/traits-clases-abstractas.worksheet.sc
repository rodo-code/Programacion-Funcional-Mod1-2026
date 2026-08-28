// TRAITS

trait Figura {
  val dimension: Int
  def area(): Double
  def perimetro(): Double
  def nombreFigura(): String = { "Sin nombre" }
}

class Cuadrado(
  val lado: Double,
  val dimension: Int = 2
) extends Figura {
  def area(): Double = {
    lado*lado
  }
  def perimetro(): Double = {
    4*lado
  }
  override def nombreFigura(): String = { "Cuadrado" }
}

class Rectangulo(
  val base: Double,
  val altura: Double,
  val dimension: Int = 2
) extends Figura {
  def area(): Double = {
    base*altura
  }
  def perimetro(): Double = {
    2*(base + altura)
  }
  override def nombreFigura(): String = { "Rectangulo" }
}

class Circunferencia(
  val radio: Double,
  val dimension: Int = 2
) extends Figura {
  def area(): Double = {
    3.1416*radio*radio
  }
  def perimetro(): Double = {
    3.1416*2*radio
  }
  override def nombreFigura(): String = { "Circunferencia" }
}

val circunferencia = new Circunferencia(5)
circunferencia.area()
circunferencia.perimetro()
circunferencia.nombreFigura()
val cuadrado = new Cuadrado(4)
cuadrado.area()
cuadrado.nombreFigura()

// EJERCICIO 1 HASTA LAS 9:10 enviar por TELEGRAM

/* 
  CREAR UNA CLASE TRIANGULO que tenga 5 atributos
  - lado1
  - lado2
  - lado3
  - base
  - altura
  que use el Trait Figura y pueda calcular el perimetro, el area
  y el nombreFigura
*/

// TRAIT MULTIPLE
trait Identificable {
  val id: Int
}

trait Describible {
  val descripcion: String
}

class Producto(
  val id: Int,
  val nombre: String,
  val descripcion: String
) extends Identificable with Describible {

}

// CLASES ABSTRACTAS
abstract class Animal(
  val nombre: String
) {
  def sonido(): String = {""}
}

trait Acuatico {
  def nadar(): String = {"Estoy nadando"}
}
trait Volador {
  def volar(): String = {"Estoy volando"}
}

class Perro (
  nombre: String
) extends Animal(nombre) {
  override def sonido(): String = {"guau"}
}

class Gato (
  nombre: String
) extends Animal(nombre) {
  override def sonido(): String = {"miau"}
}

class Pato(
  nombre: String
) extends Animal(nombre) with Acuatico with Volador {
  override def sonido(): String = {"cuack"}
}

val perro = new Perro("Firulais")
perro.sonido()

val gato = new Gato("Mishifus")
gato.sonido()

val pato = new Pato("Lucas")
pato.sonido()
pato.nadar()
pato.volar()

// Polimorfismo

val animal1: Animal = new Perro("Igui")
val animal2: Animal = new Gato("Morocho")
val animal3: Animal = new Pato("Donald")
animal1.sonido()
animal2.sonido()
animal3.sonido()

def escuchar(animal: Animal): String = {
  s"Estoy escuchando a ${animal.nombre} decir ${animal.sonido()}"
}

escuchar(animal1)
escuchar(animal2)
escuchar(animal3)

// El polimorfismo tambien funciona con Trait

class PezPayaso(
  nombre: String
) extends Animal(nombre) with Acuatico

val animalAcuatico1: Acuatico = new Pato("Juan")
val animalAcuatico2: Acuatico = new PezPayaso("Nemo")
animalAcuatico1.nadar()
animalAcuatico2.nadar()