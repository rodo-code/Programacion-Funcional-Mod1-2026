abstract class Animal(
  val nombre: String,
  val sonido: String
) {
  def obtenerSonido() = sonido
}

class Perro(
  nombre: String,
  sonido: String,
  cantidadVacunas: Int
) extends Animal(nombre,sonido)

class Vaca(
  nombre: String,
  sonido: String,
  peso: Double
) extends Animal(nombre,sonido)

// POLIFORMISMO POR SUBTIPADO
def emitirSonido(entidad: Animal): String = {
  s"Emitiendo el sonido ${entidad.obtenerSonido()}"
}

val perro = new Perro("Firulais","Guau",0)
emitirSonido(perro)
val vaca = new Vaca("Lola","Muuu",100)
emitirSonido(vaca)

// POLIMORFISMO PARAMETRICO
def identidad[A](a: A): A = {
  a
}

identidad[String]("82")
identidad[Int](12)
identidad[Double](15.3)

def swap[A,B](a: A, b: B): (B,A) = {
  (b,a)
}

swap[Int,Int](6,1)
swap[String,String]("Juan","Pedro")
swap[Int,String](6,"Pedro")

// CLASES GENERICAS
class Caja[A](
  val contenido: A
) {
  def obtenerContenido(): A = contenido
  def combinarContenido[B](valor: B): (A,B) = {
    (contenido,valor)
  }
}

val cajaEntero = new Caja[Int](9)
cajaEntero.obtenerContenido()
cajaEntero.combinarContenido[String]("Nueve")
val cajaAnimal = new Caja[Animal](vaca)
cajaAnimal.obtenerContenido()
val cajaString = new Caja[String]("Libros")
cajaString.obtenerContenido()
cajaString.combinarContenido[Double](67.2)
def duplicar(a: Caja[Int]): Any = {
  2*a.contenido
}

duplicar(cajaEntero)

// ERROR SI QUISIERAMOS USAR ANY
class CajaSencilla(
  val contenido: Any
)
val cajaDouble = new CajaSencilla(9.5)
val cajaBoolean = new CajaSencilla(false)

/*
esto no compilara
def duplicarSencillo(a: CajaSencilla): Int = {
  2*a.contenido
}*/

/* 
  EJERCICIO 1
  Crear una clase generica llamada CajaGrande, que reciba 3 tipos de dato
  y los guarde en compartimento1, compartimiento2, compartimiento3

  Luego haga una funcion que cambie el orden de los compartimientos de la
  siguiente manera:
  compartimiento1 -> compartimiento2
  compartimeinto2 -> compartimiento3
  compartimiento3 -> compartimiento1
*/

class CajaGrande[A,B,C](
  val compartimiento1: A,
  val compartimiento2: B,
  val compartimiento3: C
) {
  def rotar(): CajaGrande[C,A,B] = {
    new CajaGrande[C,A,B](compartimiento3,compartimiento1,compartimiento2)
  }
}

val cajaGrande = new CajaGrande(12,16.55,"Juanito")
cajaGrande.rotar()

// LIMITES DE TIPO

// A <: B, significa que A es un subtipo de B

class Refugio[+A <: Animal]( // Con + al lado de A, genero covarianza
  val animal: A
)

val refugioPerro = new Refugio[Perro](perro)
val refugioVaca = new Refugio[Vaca](vaca)
val refugioAnimal = new Refugio[Animal](perro)

// A >: B, significa que B es un subtipo de A
class Contenedor[A >: Perro <: Animal](
  val contenido: A
)

val contenedorPerro = new Contenedor(perro)
val contenedorAnimal = new Contenedor[Animal](vaca)


// VARIANZA 
// INVARIANZA, no existe relacion entre clases genericas de subtipos
// val refugioHuellitas: Refugio[Animal] = refugioPerro

// COVARIANZA, si un Perro es un Animal, entonces un Refugio Perro es un
// refugio de Animales
val refugioHuellitas: Refugio[Animal] = refugioPerro

// CONTRAVARIANZA 
// si una Vaca es un Animal

class Consumidor[-A <: Animal](
){
  def consumir(animal: A) = {
    s"Consumiendo Animal ${animal.nombre}"
  }
}
val consumidorAnimal = new Consumidor[Animal]()
val consumidorVaca: Consumidor[Vaca] = consumidorAnimal
