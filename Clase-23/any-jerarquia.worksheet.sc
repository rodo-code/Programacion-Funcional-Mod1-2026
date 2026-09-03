val edad: Any = "diez"

val estatura: AnyVal = 1.76

class Estudiante(
  nombre: String
){

}

val estudiante: AnyRef = new Estudiante("Pedrito")
val estudianteInexistente: AnyRef = null

def mostrarDato(
  dato: Any
): Any = {
  val mensaje = s"El dato es $dato"
  println(mensaje)
}

mostrarDato("Pedro")
mostrarDato(12)
mostrarDato(12.6)

val estudiante2: Estudiante = null


def dividir(a: Int, b: Int): Int = {
  if b == 0 then 
    throw new IllegalArgumentException("Division por cero")
  else a/b
}

dividir(6,3)

def error(mensaje: String): Nothing = throw new RuntimeException(mensaje)


val saldo = 90
val saldoInicial: Double = if saldo >= 0 then saldo else throw new IllegalArgumentException("No se puede tener saldo negativo")

abstract class Animal(
  val nombre: String
)

abstract class Mamifero(
  val altura: Double,
  nombre: String
) extends Animal(nombre)

abstract class Ave(
  val cantidadAlas: Int,
  nombre: String
) extends Animal(nombre)

class Perro(
  val cantidadVacunas: Int,
  altura: Double,
  nombre: String
) extends Mamifero(altura,nombre)

class Vaca(
  val peso: Double,
  altura: Double,
  nombre: String
) extends Mamifero(altura,nombre)

val mamifero: Mamifero = new Perro(2,0.7,"Chico")
val mamifero2: Mamifero = new Vaca(200,1.4,"Lola")

