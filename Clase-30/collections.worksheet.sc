import scala.collection.mutable.TreeSet
val numeros: Vector[Int] = Vector(1,7,2,6,5,9,3)
numeros.filter(x => x%2==0)
// Acceso por indices
numeros(0)
numeros(1)
numeros(2)

// OJO NO ES UNA FUNCION PURA
def imprimirVector(vector: Vector[Int], pos: Int): Unit = {
  if pos < vector.length then {
    print(s"${vector(pos)} ")
    imprimirVector(vector,pos+1)
  }
}

// imprimirVector(numeros,0)

val numeros2 = numeros.appended(100)
numeros2

numeros2.find(x => x>20)
numeros2.findLast(x => x<7)

// EJERCICIO 1
// Dada un vector de temperaturas en grados Celsius
// Generar otro vector con las temperaturas 
// convertidas a grados Farenheit, F = C * 1.8 + 32
val temperaturas: Vector[Double] = Vector(23.5, 12.3, 21.7)
temperaturas.map(C => C * 1.8 + 32)

// SEQUENCIA
// Seq es el super tipo de List y Vector

def mostrarSecuencia(elementos: Seq[String]): String = {
  elementos.mkString(", ")
}

mostrarSecuencia(List("manzana","kiwi","pera"))
mostrarSecuencia(Vector("mango","naranja","lima"))

// SET (Conjunto) Set => HashSet, TreeSet
// 1. No repite elementos
val lenguajes = Set("Java","C++","Python","Scala","Prolog","C++")
lenguajes.mkString(", ")
// 2. Se puede hacer map, filter, reduce, foldLeft
val numeros3 = TreeSet(3,1,9,8,5,-3,-8)
numeros3
numeros3.map(x => x*x)
val numeros4 = Set(1,2,7,5)
// Operaciones sobre conjuntos
numeros3 union numeros4
numeros3 intersect numeros4
numeros3 diff numeros4

// EJERCICIO 2
val inscritosScala =
  Set("Ana", "Luis", "Pedro", "Sofia")

val inscritosJava =
  Set("Luis", "Maria", "Pedro", "Carlos")

// 1. Estudiantes estan inscritos en al menos un curso
inscritosScala union inscritosJava
// 2. Estudiantes estan inscritos en ambos cursos
inscritosJava intersect inscritosScala
// 3. Estudiantes que estan inscritos en Java pero no en Scala
inscritosJava diff inscritosScala

// MAPAS

val edades = Map(
  "Sebastian" -> 19,
  "Abdul" -> 20,
  "Rodolfo" -> 18
)
edades.get("Abdul")
edades.get("Mateo")

edades.getOrElse("Sebastian",-1)

edades.filter((nombre,edad) => edad>18)

edades.map((nombre,edad) => (nombre,edad+1))

// EJERCICIO 3
// Crear un mapa con llave String y valor String
// "INF101" -> "Programación I"
// "INF202" -> "Estructuras de Datos"
// "INF301" -> "Programación Funcional"

// Obtener
// Una lista con el codigo de materias
// Una lita con los nombres de las materias

val materias = Map(
  "INF101" -> "Programación I",
  "INF202" -> "Estructuras de Datos",
  "INF301" -> "Programación Funcional"
)

materias.map((codigo, nombre) => codigo)
materias.map((codigo, nombre) => nombre)

