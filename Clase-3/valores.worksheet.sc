val x = 4

val nombre:String = "Rodolfo"
val edad:Int = 27
val estatura:Double = 1.67
val respuesta:Boolean = true
val inicial:Char = 'R'
val edadFutura:Int = edad + 1

def imprimirDatos(nombre: String, edad: Int): Unit = {
  println(s"Hola $nombre tienes $edad")
}

imprimirDatos("Pedro",40)

nombre.toLowerCase
nombre.toUpperCase
nombre.length

println("Hola "+nombre+" tienes "+edad)