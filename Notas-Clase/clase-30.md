# Clase 30: Colecciones funcionales y For-Comprehensions en Scala

# 1. ¿Por qué existen diferentes colecciones?

Diferentes problemas requieren diferentes formas de organizar datos.

``` text
Historial de ventas → procesamiento secuencial
Jugadores conectados → elementos únicos
Productos por código → asociación clave-valor
Datos por posición → acceso mediante índices
```

> Elegimos una colección según las operaciones que necesitamos realizar.

------------------------------------------------------------------------

# 2. Principales colecciones

  Colección   Característica              Uso
  ----------- --------------------------- ------------------------------
  `List`      Secuencia enlazada          Procesamiento secuencial
  `Vector`    Buen acceso por posición    Uso frecuente de índices
  `Set`       Elementos únicos            Etiquetas, categorías
  `Map`       Clave → valor               Búsqueda por identificadores
  `Seq`       Abstracción de secuencias   Funciones generales

------------------------------------------------------------------------

# 3. List

``` scala
val numeros: List[Int] =
  List(10, 20, 30, 40)
```

Una `List` mantiene orden, permite repetidos y es inmutable.

``` scala
numeros.map(_ * 2)
numeros.filter(_ > 20)
numeros.foldLeft(0)(_ + _)
```

Agregar al inicio mediante `::` es eficiente. El acceso frecuente por
índice no es su principal fortaleza.

------------------------------------------------------------------------

# 4. Vector

``` scala
val numeros: Vector[Int] =
  Vector(10, 20, 30, 40)
```

Podemos acceder por posición:

``` scala
numeros(2)
```

Resultado:

``` text
30
```

También admite operaciones funcionales:

``` scala
val dobles =
  numeros.map(_ * 2)
```

> Si necesitamos utilizar posiciones o índices frecuentemente, `Vector`
> suele ser mejor opción que `List`.

## Ejercicio 1

``` scala
val temperaturas =
  Vector(18.5, 20.0, 23.5, 25.0, 21.5)
```

1.  Obtener la tercera temperatura.
2.  Obtener temperaturas mayores a `20`.
3.  Convertir todas de Celsius a Fahrenheit usando `F = C * 1.8 + 32`.

------------------------------------------------------------------------

# 5. Seq

`Seq` representa el concepto general de una secuencia ordenada.

``` scala
def mostrar(
  elementos: Seq[String]
): String =
  elementos.mkString(", ")
```

Puede recibir:

``` scala
mostrar(List("A", "B", "C"))
mostrar(Vector("A", "B", "C"))
```

Conceptualmente:

``` text
             Seq
            /   \
         List   Vector
```

> Si una función solamente necesita una secuencia, puede recibir `Seq`
> en lugar de exigir específicamente `List`.

------------------------------------------------------------------------

# 6. Set: elementos únicos

``` scala
val lenguajes =
  Set("Scala", "Java", "Python")
```

Los duplicados no representan elementos diferentes:

``` scala
val numeros =
  Set(1, 2, 2, 3, 3, 3)
```

Conceptualmente:

``` text
Set(1, 2, 3)
```

Es útil para etiquetas, categorías, permisos, identificadores o
participantes.

------------------------------------------------------------------------

# 7. Operaciones con conjuntos

``` scala
val backend =
  Set("Java", "Scala", "Python")

val frontend =
  Set("JavaScript", "TypeScript", "Python")
```

Unión:

``` scala
backend union frontend
```

Intersección:

``` scala
backend intersect frontend
```

Diferencia:

``` scala
backend diff frontend
```

## Ejercicio 2

``` scala
val inscritosScala =
  Set("Ana", "Luis", "Pedro", "Sofia")

val inscritosJava =
  Set("Luis", "Maria", "Pedro", "Carlos")
```

Obtén:

1.  Estudiantes inscritos en al menos un curso.
2.  Estudiantes inscritos en ambos.
3.  Estudiantes inscritos en Scala pero no en Java.

------------------------------------------------------------------------

# 8. Map: clave y valor

Un `Map` representa asociaciones entre claves y valores.

``` scala
val edades =
  Map(
    "Ana" -> 20,
    "Luis" -> 22,
    "Pedro" -> 19
  )
```

Conceptualmente:

``` text
"Ana"   → 20
"Luis"  → 22
"Pedro" → 19
```

Su tipo es:

``` scala
Map[String, Int]
```

Las claves son únicas.

------------------------------------------------------------------------

# 9. Consultar un Map

``` scala
edades.get("Ana")
```

Resultado:

``` text
Some(20)
```

Una clave inexistente:

``` scala
edades.get("Carlos")
```

produce:

``` text
None
```

`Option` representa conceptualmente:

``` text
Some(valor) → existe
None        → no existe
```

También podemos proporcionar un valor alternativo:

``` scala
edades.getOrElse("Carlos", 0)
```

------------------------------------------------------------------------

# 10. Map con objetos

``` scala
case class Producto(
  codigo: Int,
  nombre: String,
  precio: Double
)
```

``` scala
val productos =
  Map(
    101 -> Producto(101, "Teclado", 150),
    102 -> Producto(102, "Mouse", 80),
    103 -> Producto(103, "Monitor", 1200)
  )
```

Podemos buscar directamente:

``` scala
productos.get(102)
```

Si nuestra operación principal es buscar por código,
`Map[Int, Producto]` puede representar mejor el problema que
`List[Producto]`.

## Ejercicio 3

Crea un `Map`:

``` text
"INF101" → "Programación I"
"INF202" → "Estructuras de Datos"
"INF301" → "Programación Funcional"
```

Luego:

1.  Buscar una materia existente.
2.  Buscar una inexistente.
3.  Obtener las claves.
4.  Obtener los nombres.

------------------------------------------------------------------------

# 11. Operaciones funcionales sobre colecciones

Las operaciones aprendidas no son exclusivas de `List`.

``` scala
val numeros =
  Vector(1, 2, 3, 4, 5, 6)

val resultado =
  numeros
    .filter(_ % 2 == 0)
    .map(_ * 10)
    .foldLeft(0)(_ + _)
```

Podemos encontrar operaciones como `map`, `filter`, `flatMap` y
`foldLeft` en diferentes colecciones.

------------------------------------------------------------------------

# 12. For-Comprehensions

Scala permite expresar transformaciones utilizando `for`.

``` scala
val numeros =
  List(1, 2, 3, 4)

val cuadrados =
  for
    numero <- numeros
  yield
    numero * numero
```

Resultado:

``` text
List(1, 4, 9, 16)
```

Estructura:

``` scala
for
  elemento <- coleccion
yield
  transformacion
```

`yield` indica qué valor formará parte de la colección resultante.

------------------------------------------------------------------------

# 13. Relación entre for y map

Esto:

``` scala
numeros.map(
  numero => numero * numero
)
```

puede expresarse como:

``` scala
for
  numero <- numeros
yield
  numero * numero
```

Conceptualmente:

``` text
for + yield
     ↓
    map
```

## Ejercicio 4

``` scala
val precios =
  List(100.0, 250.0, 80.0, 500.0)
```

Utiliza un `for`-comprehension para generar una lista aplicando 10% de
descuento.

------------------------------------------------------------------------

# 14. Filtros dentro de for

``` scala
val numeros =
  List(1, 2, 3, 4, 5, 6)

val resultado =
  for
    numero <- numeros
    if numero % 2 == 0
  yield
    numero * 10
```

Resultado:

``` text
List(20, 40, 60)
```

Esto corresponde conceptualmente a:

``` scala
numeros
  .filter(_ % 2 == 0)
  .map(_ * 10)
```

Por tanto:

``` text
for + if + yield
        ↓
   filter + map
```

## Ejercicio 5

Dada:

``` scala
val numeros =
  List(1,2,3,4,5,6,7,8,9,10)
```

Utiliza `for` para seleccionar números pares y elevarlos al cuadrado.

Resultado:

``` text
List(4, 16, 36, 64, 100)
```

------------------------------------------------------------------------

# 15. Combinar colecciones con for

``` scala
val letras =
  List("A", "B")

val numeros =
  List(1, 2, 3)

val combinaciones =
  for
    letra <- letras
    numero <- numeros
  yield
    (letra, numero)
```

Resultado:

``` text
List(
  ("A", 1),
  ("A", 2),
  ("A", 3),
  ("B", 1),
  ("B", 2),
  ("B", 3)
)
```

------------------------------------------------------------------------

# 16. Relación entre for y flatMap

El ejemplo anterior está relacionado conceptualmente con:

``` scala
letras.flatMap(
  letra =>
    numeros.map(
      numero =>
        (letra, numero)
    )
)
```

Podemos resumir:

``` text
Un generador:
for + yield
→ map

Generador + condición:
for + if + yield
→ filter + map

Varios generadores:
for con varias variables
→ flatMap + map
```

------------------------------------------------------------------------

# 17. Ejemplo con objetos

``` scala
case class Estudiante(
  nombre: String,
  materias: List[String]
)
```

``` scala
val estudiantes =
  List(
    Estudiante(
      "Ana",
      List("Scala", "Java")
    ),
    Estudiante(
      "Luis",
      List("Python", "Scala")
    )
  )
```

Podemos obtener las combinaciones estudiante-materia:

``` scala
val resultado =
  for
    estudiante <- estudiantes
    materia <- estudiante.materias
  yield
    (estudiante.nombre, materia)
```

Resultado:

``` text
List(
  ("Ana", "Scala"),
  ("Ana", "Java"),
  ("Luis", "Python"),
  ("Luis", "Scala")
)
```

Cada estudiante puede producir varios resultados. Por eso aparece
conceptualmente `flatMap`.

------------------------------------------------------------------------

# 18. Ejercicio integrador

Considera:

``` scala
case class Producto(
  codigo: Int,
  nombre: String,
  categoria: String,
  precio: Double,
  etiquetas: Set[String]
)
```

``` scala
val productos: Vector[Producto] =
  Vector(
    Producto(
      1,
      "Laptop",
      "Tecnologia",
      5500,
      Set("computadora", "trabajo")
    ),
    Producto(
      2,
      "Mouse",
      "Tecnologia",
      120,
      Set("accesorio")
    ),
    Producto(
      3,
      "Escritorio",
      "Muebles",
      1500,
      Set("oficina", "trabajo")
    ),
    Producto(
      4,
      "Monitor",
      "Tecnologia",
      1800,
      Set("pantalla", "trabajo")
    )
  )
```

Resuelve funcionalmente:

1.  Obtener productos de categoría `"Tecnologia"`.
2.  Obtener nombres de productos con precio mayor a `1000`.
3.  Obtener todas las etiquetas utilizadas sin repetidos.
4.  Calcular el precio total.
5.  Utilizar `for` para obtener pares `(nombre, etiqueta)`.
6.  Obtener solamente esas combinaciones para productos con precio mayor
    a `1000`.

## Restricciones

-   Utilizar datos inmutables.
-   No utilizar `var`.
-   No utilizar ciclos `while`.
-   Utilizar operaciones funcionales cuando corresponda.
-   No modificar las colecciones originales.

------------------------------------------------------------------------

# 19. ¿Qué colección elegir?

## List

Cuando necesitamos procesamiento secuencial y transformaciones
funcionales.

## Vector

Cuando necesitamos una secuencia ordenada y utilizamos posiciones
frecuentemente.

## Set

Cuando necesitamos elementos únicos o realizar operaciones de conjuntos.

## Map

Cuando queremos asociar claves con valores y consultar datos mediante
una clave.

## Seq

Cuando una función necesita una secuencia pero no requiere una
implementación específica.

------------------------------------------------------------------------

# 20. Resumen

``` text
                    COLECCIONES
                         │
        ┌────────────────┼────────────────┐
        ↓                ↓                ↓
   Secuencias           Set              Map
 List / Vector       unicidad        clave → valor
        │                │                │
        └────────────────┼────────────────┘
                         ↓
             Procesamiento funcional
                         ↓
          map / filter / flatMap
                         ↓
            reduce / foldLeft
                         ↓
             for-comprehensions
```

Los `for`-comprehensions están relacionados con operaciones ya
estudiadas:

``` text
for + yield
→ map

for + if + yield
→ filter + map

varios generadores
→ flatMap + map
```

> No elegimos una colección solamente por su sintaxis. Elegimos la
> estructura que representa mejor nuestros datos y las operaciones que
> necesitamos realizar.

La progresión final es:

``` text
Datos inmutables
      ↓
Colecciones
      ↓
Funciones puras
      ↓
map / filter / flatMap
      ↓
fold / reduce
      ↓
for-comprehensions
      ↓
Procesamiento funcional de datos
```
