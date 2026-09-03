# Clase 24: Polimorfismo Paramétrico, Genéricos, Límites de Tipos y Varianza en Scala

# 1. Introducción

Hasta ahora hemos trabajado con diferentes formas de reutilización.

Por ejemplo, una función específica:

```scala
def duplicar(numero: Int): Int =
  numero * 2
```

solamente trabaja con:

```text
Int
```

También vimos polimorfismo por subtipado:

```scala
def mostrar(animal: Animal): String =
  animal.descripcion
```

Esta función puede trabajar con:

```text
Perro
Gato
Pato
```

si todos son subtipos de:

```text
Animal
```

Existe otra forma de polimorfismo muy importante:

> **Polimorfismo paramétrico**

---

# 2. ¿Qué es el polimorfismo paramétrico?

El polimorfismo paramétrico permite escribir código que trabaja con diferentes tipos sin conocer necesariamente cuál será el tipo concreto.

En lugar de escribir:

```scala
def primeroInt(
  a: Int,
  b: Int
): Int =
  a
```

y posteriormente:

```scala
def primeroString(
  a: String,
  b: String
): String =
  a
```

podemos crear una única función:

```scala
def primero[A](
  a: A,
  b: A
): A =
  a
```

Ahora:

```scala
primero(10, 20)
```

devuelve:

```text
10
```

Y:

```scala
primero("Scala", "Java")
```

devuelve:

```text
Scala
```

La misma función funciona con diferentes tipos.

---

# 3. Parámetros de tipo

En:

```scala
def primero[A](
  a: A,
  b: A
): A =
  a
```

`A` es un:

> **Parámetro de tipo**

No representa un valor.

Representa un tipo que será determinado cuando se utilice la función.

Por ejemplo:

```scala
primero(10, 20)
```

Scala interpreta:

```text
A = Int
```

Mientras que:

```scala
primero("Scala", "Java")
```

implica:

```text
A = String
```

---

# 4. Sintaxis de parámetros de tipo

Los parámetros de tipo se escriben entre:

```text
[ ]
```

Ejemplo:

```scala
def identidad[A](
  valor: A
): A =
  valor
```

Podemos utilizar:

```scala
identidad[Int](10)
```

Resultado:

```text
10
```

También:

```scala
identidad[String]("Scala")
```

Resultado:

```text
Scala
```

Normalmente Scala puede inferir el tipo:

```scala
identidad(10)
```

```scala
identidad("Scala")
```

por lo que no siempre necesitamos escribir explícitamente:

```text
[Int]
[String]
```

---

# 5. Ejemplo: función genérica `identidad`

```scala
def identidad[A](
  valor: A
): A =
  valor
```

Uso:

```scala
val numero =
  identidad(100)

val texto =
  identidad("Programación Funcional")

val booleano =
  identidad(true)
```

Los tipos inferidos serán:

```text
numero: Int

texto: String

booleano: Boolean
```

---

# 6. Diferencia entre parámetro normal y parámetro de tipo

Considere:

```scala
def identidad[A](
  valor: A
): A =
  valor
```

Aquí tenemos:

```text
A
```

que representa un **tipo**.

Y:

```text
valor
```

que representa un **dato**.

Podemos verlo como:

```text
[A]
 ↓
Parámetro de tipo

(valor: A)
     ↓
Parámetro de la función
```

---

# 7. Funciones genéricas

Una función genérica utiliza uno o más parámetros de tipo.

Por ejemplo:

```scala
def intercambiar[A](
  primero: A,
  segundo: A
): (A, A) =
  (segundo, primero)
```

Uso:

```scala
intercambiar(10, 20)
```

Resultado:

```text
(20, 10)
```

También:

```scala
intercambiar(
  "Scala",
  "Java"
)
```

Resultado:

```text
("Java", "Scala")
```

---

# 8. Más de un parámetro de tipo

Una función puede utilizar varios parámetros de tipo.

Por ejemplo:

```scala
def crearPar[A, B](
  primero: A,
  segundo: B
): (A, B) =
  (primero, segundo)
```

Uso:

```scala
crearPar(
  "Edad",
  25
)
```

Aquí:

```text
A = String
B = Int
```

El resultado tiene tipo:

```text
(String, Int)
```

---

# 9. Clases genéricas

También podemos parametrizar clases.

Considere:

```scala
class Caja[A](
  val contenido: A
)
```

La clase `Caja` puede almacenar cualquier tipo.

Ejemplo:

```scala
val cajaNumero =
  new Caja[Int](10)
```

También:

```scala
val cajaTexto =
  new Caja[String]("Scala")
```

---

# 10. Inferencia de tipos en clases genéricas

Scala puede inferir:

```scala
val cajaNumero =
  new Caja(10)
```

como:

```text
Caja[Int]
```

Y:

```scala
val cajaTexto =
  new Caja("Scala")
```

como:

```text
Caja[String]
```

---

# 11. ¿Qué significa `Caja[Int]`?

`Caja` por sí sola representa una clase genérica.

Cuando escribimos:

```scala
Caja[Int]
```

estamos indicando que:

```text
A = Int
```

Por tanto:

```scala
class Caja[A](
  val contenido: A
)
```

puede verse conceptualmente como:

```text
Caja[Int]
contenido: Int
```

---

# 12. Métodos dentro de clases genéricas

Una clase genérica puede utilizar su parámetro de tipo dentro de sus métodos.

```scala
class Caja[A](
  val contenido: A
) {

  def obtener: A =
    contenido
}
```

Uso:

```scala
val caja =
  new Caja("Scala")

caja.obtener
```

Resultado:

```text
Scala
```

El método devuelve:

```text
String
```

porque la caja es:

```text
Caja[String]
```

---

# 13. Métodos genéricos dentro de clases

Un método también puede tener sus propios parámetros de tipo.

```scala
class Caja[A](
  val contenido: A
) {

  def combinar[B](
    otro: B
  ): (A, B) =
    (contenido, otro)
}
```

Uso:

```scala
val caja =
  new Caja("Scala")

caja.combinar(2026)
```

Resultado:

```text
("Scala", 2026)
```

Tipos:

```text
A = String
B = Int
```

---

# 14. Ejemplo: Resultado genérico

Podemos representar el resultado de una operación:

```scala
class Resultado[A](
  val valor: A,
  val exitoso: Boolean
)
```

Ahora podemos tener:

```scala
val resultadoNumero =
  new Resultado(
    25,
    true
  )
```

o:

```scala
val resultadoTexto =
  new Resultado(
    "Operación completada",
    true
  )
```

La estructura es la misma.

Solamente cambia el tipo de:

```text
valor
```

---

# 15. ¿Por qué utilizar genéricos?

Sin genéricos podríamos terminar creando:

```text
CajaInt
CajaString
CajaDouble
CajaPersona
```

con prácticamente el mismo código.

Con genéricos:

```scala
Caja[A]
```

podemos reutilizar una única estructura.

Esto proporciona:

* reutilización;
* seguridad de tipos;
* menos duplicación;
* mayor flexibilidad;
* abstracciones más generales.

---

# 16. Seguridad de tipos

Considere:

```scala
val caja: Caja[Int] =
  new Caja(10)
```

Sabemos que:

```scala
caja.contenido
```

es un:

```text
Int
```

No necesitamos convertir desde:

```text
Any
```

Por ejemplo, una solución menos segura podría utilizar:

```scala
class Caja(
  val contenido: Any
)
```

Pero entonces perderíamos información específica sobre el tipo almacenado.

Los genéricos permiten conservar esa información.

---

# 17. Polimorfismo paramétrico frente a polimorfismo por subtipado

## Polimorfismo por subtipado

```scala
def sonido(
  animal: Animal
): String =
  animal.sonido
```

Funciona con tipos que sean subtipos de:

```text
Animal
```

---

## Polimorfismo paramétrico

```scala
def identidad[A](
  valor: A
): A =
  valor
```

Funciona para cualquier tipo `A`.

---

## Comparación

| Polimorfismo por subtipado                 | Polimorfismo paramétrico                 |
| ------------------------------------------ | ---------------------------------------- |
| Utiliza relaciones de herencia/subtipado   | Utiliza parámetros de tipo               |
| Trabaja con una jerarquía concreta         | Puede ser independiente de una jerarquía |
| Ejemplo: `Animal`                          | Ejemplo: `[A]`                           |
| Diferentes subtipos comparten un supertipo | El algoritmo es general respecto al tipo |

---

# 18. Problema: no siempre queremos aceptar cualquier tipo

Considere:

```scala
class Registro[A](
  val dato: A
)
```

Aquí `A` puede ser cualquier tipo.

Pero algunas veces necesitamos restringirlo.

Por ejemplo, queremos una clase que solamente trabaje con animales.

Podemos utilizar un:

> **Límite superior de tipo**

---

# 19. Límites superiores de tipos

La sintaxis es:

```scala
A <: Tipo
```

Se lee:

> A debe ser subtipo de Tipo.

Por ejemplo:

```scala
abstract class Animal(
  val nombre: String
)
```

```scala
class Perro(
  nombre: String
) extends Animal(nombre)
```

```scala
class Gato(
  nombre: String
) extends Animal(nombre)
```

Podemos crear:

```scala
class Refugio[A <: Animal](
  val animal: A
)
```

Ahora `A` solamente puede ser:

```text
Animal
```

o uno de sus subtipos.

---

# 20. Ejemplo de límite superior

Esto es válido:

```scala
val refugioPerro =
  new Refugio(
    new Perro("Rocky")
  )
```

También:

```scala
val refugioGato =
  new Refugio(
    new Gato("Michi")
  )
```

Pero no:

```scala
new Refugio(10)
```

porque:

```text
Int
```

no es subtipo de:

```text
Animal
```

---

# 21. Funciones con límite superior

También podemos restringir funciones.

```scala
def obtenerNombre[
  A <: Animal
](
  animal: A
): String =
  animal.nombre
```

La ventaja es que Scala sabe que cualquier tipo `A` utilizado en esta función posee las características de `Animal`.

Por tanto podemos utilizar:

```scala
animal.nombre
```

---

# 22. ¿Por qué el límite permite utilizar métodos?

Compare:

```scala
def mostrar[A](
  valor: A
): String =
  valor.nombre
```

Esto no es válido porque Scala no sabe que cualquier `A` posee:

```text
nombre
```

En cambio:

```scala
def mostrar[A <: Animal](
  valor: A
): String =
  valor.nombre
```

sí es válido.

Scala sabe que:

```text
A <: Animal
```

por lo que puede utilizar los miembros definidos en `Animal`.

---

# 23. Límites inferiores de tipos

Scala también permite establecer un límite inferior.

La sintaxis es:

```scala
A >: Tipo
```

Se lee:

> A debe ser un supertipo de Tipo.

Por ejemplo:

```scala
A >: Perro
```

significa que `A` puede ser:

```text
Perro
Animal
AnyRef
Any
```

dependiendo de la jerarquía existente.

---

# 24. Ejemplo conceptual de límite inferior

Suponga:

```text
       Animal
       /    \
    Perro   Gato
```

Si escribimos:

```text
A >: Perro
```

entonces `A` debe encontrarse en `Perro` o por encima de él.

Por ejemplo:

```text
Perro
Animal
AnyRef
Any
```

---

# 25. Límites superiores e inferiores

Podemos resumir:

```text
A <: Animal
```

significa:

```text
A está por debajo de Animal
```

Mientras que:

```text
A >: Perro
```

significa:

```text
A está por encima de Perro
```

Representación:

```text
       Animal
         ↑
        Perro
```

Para:

```text
A <: Animal
```

buscamos tipos hacia abajo.

Para:

```text
A >: Perro
```

buscamos tipos hacia arriba.

---

# 26. Intervalos de tipos

Scala permite incluso combinar ambos límites.

Por ejemplo:

```scala
A >: Perro <: Animal
```

Esto significa:

> A debe ser supertipo de `Perro` y subtipo de `Animal`.

En una jerarquía sencilla:

```text
Animal
  ↑
Perro
```

los tipos posibles serían:

```text
Perro
Animal
```

---

# 27. Introducción a la varianza

Suponga:

```text
Perro <: Animal
```

Una pregunta importante es:

> ¿Entonces `Caja[Perro]` también es subtipo de `Caja[Animal]`?

La respuesta depende de cómo esté definida `Caja`.

Esto se denomina:

> **Varianza**

---

# 28. ¿Qué es la varianza?

La varianza describe cómo las relaciones de subtipado entre tipos se trasladan a tipos genéricos.

Partimos de:

```text
Perro <: Animal
```

y analizamos qué ocurre con:

```text
Contenedor[Perro]
Contenedor[Animal]
```

Existen tres posibilidades principales:

* covarianza;
* contravarianza;
* invariancia.

---

# 29. Invariancia

Por defecto, las clases genéricas en Scala son **invariantes**.

Ejemplo:

```scala
class Caja[A](
  val contenido: A
)
```

Aunque:

```text
Perro <: Animal
```

no significa automáticamente:

```text
Caja[Perro] <: Caja[Animal]
```

Ni tampoco:

```text
Caja[Animal] <: Caja[Perro]
```

No existe relación de subtipado entre ambas.

---

# 30. Ejemplo de invariancia

```scala
class Caja[A](
  val contenido: A
)
```

Tenemos:

```scala
val cajaPerro: Caja[Perro] =
  new Caja(
    new Perro("Rocky")
  )
```

Esto no implica que podamos escribir:

```scala
val cajaAnimal: Caja[Animal] =
  cajaPerro
```

porque:

```text
Caja[A]
```

es invariante por defecto.

---

# 31. ¿Por qué existe la invariancia?

Suponga que una caja permite modificar su contenido:

```scala
class Caja[A](
  var contenido: A
)
```

Si permitiéramos:

```text
Caja[Perro] <: Caja[Animal]
```

podríamos hacer:

```scala
val cajaPerro =
  new Caja(
    new Perro("Rocky")
  )

val cajaAnimal: Caja[Animal] =
  cajaPerro
```

Luego:

```scala
cajaAnimal.contenido =
  new Gato("Michi")
```

Pero ahora `cajaPerro` contendría un gato.

Esto rompería la seguridad de tipos.

---

# 32. Covarianza

Un tipo genérico covariante se declara utilizando:

```scala
+A
```

Ejemplo:

```scala
class Caja[+A](
  val contenido: A
)
```

El símbolo:

```text
+
```

indica covarianza.

---

# 33. Relación covariante

Si:

```text
Perro <: Animal
```

y `Caja` es covariante:

```scala
Caja[+A]
```

entonces:

```text
Caja[Perro] <: Caja[Animal]
```

La dirección del subtipado se mantiene.

Podemos verlo como:

```text
Perro ---------> Animal
  |                |
  v                v
Caja[Perro] --> Caja[Animal]
```

---

# 34. Ejemplo de covarianza

```scala
class Caja[+A](
  val contenido: A
)
```

Ahora:

```scala
val cajaPerro: Caja[Perro] =
  new Caja(
    new Perro("Rocky")
  )
```

podemos asignarla a:

```scala
val cajaAnimal: Caja[Animal] =
  cajaPerro
```

Esto es válido.

Una caja que produce un `Perro` también puede verse como una caja que produce un `Animal`.

---

# 35. Covarianza e inmutabilidad

La covarianza funciona especialmente bien con estructuras inmutables.

Por ejemplo:

```scala
class Caja[+A](
  val contenido: A
)
```

La caja solamente permite leer:

```scala
contenido
```

No permite reemplazarlo.

Esto evita introducir un tipo incorrecto dentro de la caja.

---

# 36. Posiciones de salida

Una regla conceptual importante es:

> Los tipos covariantes funcionan naturalmente cuando el tipo aparece como resultado o salida.

Por ejemplo:

```scala
class Productor[+A](
  val valor: A
) {

  def obtener: A =
    valor
}
```

`A` aparece como:

```text
salida
```

del método:

```scala
obtener
```

---

# 37. Problema con parámetros en clases covariantes

Considere:

```scala
class Caja[+A](
  val contenido: A
) {

  def reemplazar(
    nuevo: A
  ): Caja[A] =
    new Caja(nuevo)
}
```

Esto puede generar un problema de varianza porque `A` aparece como parámetro de un método.

Un tipo covariante no puede utilizarse libremente en posiciones de entrada.

---

# 38. Solución mediante un límite inferior

Podemos ampliar el tipo:

```scala
class Caja[+A](
  val contenido: A
) {

  def reemplazar[B >: A](
    nuevo: B
  ): Caja[B] =
    new Caja(nuevo)
}
```

Ahora podemos partir de:

```text
Caja[Perro]
```

y reemplazar con otro tipo compatible.

Por ejemplo, si introducimos un `Gato`, el nuevo tipo común puede convertirse en:

```text
Caja[Animal]
```

La caja original no cambia.

Se crea una nueva caja.

Este patrón combina:

* covarianza;
* límite inferior;
* inmutabilidad.

---

# 39. Contravarianza

Un tipo contravariante utiliza:

```scala
-A
```

Por ejemplo:

```scala
trait Consumidor[-A] {

  def consumir(
    valor: A
  ): Unit
}
```

El símbolo:

```text
-
```

indica contravarianza.

---

# 40. Relación contravariante

Si:

```text
Perro <: Animal
```

y `Consumidor` es contravariante:

```scala
Consumidor[-A]
```

entonces la relación se invierte:

```text
Consumidor[Animal] <: Consumidor[Perro]
```

Representación:

```text
Perro ---------> Animal
  ↑                ↑
  |                |
Consumidor[Perro] <- Consumidor[Animal]
```

---

# 41. ¿Por qué se invierte?

Suponga que necesitamos algo capaz de consumir perros.

Un consumidor de cualquier animal puede perfectamente consumir perros.

Por ejemplo:

```scala
trait Consumidor[-A] {

  def consumir(
    valor: A
  ): String
}
```

Podemos crear:

```scala
class ConsumidorAnimal
  extends Consumidor[Animal] {

  def consumir(
    valor: Animal
  ): String =
    s"Procesando ${valor.nombre}"
}
```

Este consumidor puede recibir:

```text
Perro
Gato
Animal
```

Por tanto puede utilizarse donde solamente necesitábamos un consumidor de perros.

---

# 42. Posiciones de entrada

Una regla conceptual útil:

> La contravarianza funciona naturalmente cuando el tipo se utiliza como entrada.

Por ejemplo:

```scala
trait Validador[-A] {

  def validar(
    valor: A
  ): Boolean
}
```

`A` aparece como parámetro.

El validador consume valores.

---

# 43. Covarianza y contravarianza como productor y consumidor

Una forma práctica de recordarlo es:

## Productor

```scala
trait Productor[+A] {

  def obtener: A
}
```

Produce valores de tipo `A`.

Normalmente:

```text
covariante
```

---

## Consumidor

```scala
trait Consumidor[-A] {

  def procesar(
    valor: A
  ): Unit
}
```

Consume valores de tipo `A`.

Normalmente:

```text
contravariante
```

---

# 44. Regla conceptual PECS

Una forma de recordar la idea es:

```text
Producer → Covariant
Consumer → Contravariant
```

En términos sencillos:

* si principalmente **produce** `A`, suele tener sentido `+A`;
* si principalmente **consume** `A`, suele tener sentido `-A`;
* si hace ambas cosas, frecuentemente será invariante.

---

# 45. Invariancia cuando se produce y consume

Considere:

```scala
class Contenedor[A](
  private var valor: A
) {

  def obtener: A =
    valor

  def guardar(
    nuevo: A
  ): Unit =
    valor = nuevo
}
```

Aquí `A` aparece:

* como salida en `obtener`;
* como entrada en `guardar`.

Por ello la invariancia suele ser la opción natural.

---

# 46. Comparación de las tres variantes

Supongamos:

```text
Perro <: Animal
```

## Covarianza

```scala
Contenedor[+A]
```

Produce:

```text
Contenedor[Perro] <: Contenedor[Animal]
```

---

## Contravarianza

```scala
Contenedor[-A]
```

Produce:

```text
Contenedor[Animal] <: Contenedor[Perro]
```

---

## Invariancia

```scala
Contenedor[A]
```

Produce:

```text
Contenedor[Perro]
```

y:

```text
Contenedor[Animal]
```

sin relación directa de subtipado.

---

# 47. Tabla resumen de varianza

| Declaración | Nombre         | Relación si `Perro <: Animal` |
| ----------- | -------------- | ----------------------------- |
| `[+A]`      | Covarianza     | `C[Perro] <: C[Animal]`       |
| `[-A]`      | Contravarianza | `C[Animal] <: C[Perro]`       |
| `[A]`       | Invariancia    | No existe relación automática |

---

# 48. Las funciones también tienen varianza

En Scala, una función de un parámetro puede representarse conceptualmente mediante:

```scala
Function1[-A, +B]
```

Es decir:

* el parámetro de entrada es contravariante;
* el resultado es covariante.

Por eso una función:

```text
Animal => Perro
```

puede ser compatible en ciertos contextos con funciones cuyos tipos sean más específicos en entrada y más generales en salida.

---

# 49. Entrada contravariante y salida covariante

Considere:

```text
A => B
```

Una función:

* **consume** `A`;
* **produce** `B`.

Por eso:

```text
entrada  → contravariante
salida   → covariante
```

Conceptualmente:

```scala
Function1[-Entrada, +Salida]
```

---

# 50. Ejemplo conceptual con funciones

Tenemos:

```scala
def describirAnimal(
  animal: Animal
): String =
  animal.nombre
```

Esta función puede procesar cualquier animal.

Por tanto, también puede procesar un:

```text
Perro
```

Una función que acepta un tipo más general puede utilizarse en contextos donde se requiere procesar tipos más específicos.

Esto está relacionado con la contravarianza del parámetro de entrada.

---

# 51. Ejemplo integrado: productor

```scala
trait Productor[+A] {

  def producir: A
}
```

```scala
class CriadorPerros
  extends Productor[Perro] {

  def producir: Perro =
    new Perro("Rocky")
}
```

Como:

```text
Perro <: Animal
```

podemos utilizar:

```scala
val productorAnimal:
  Productor[Animal] =
    new CriadorPerros
```

porque:

```text
Productor[Perro] <: Productor[Animal]
```

---

# 52. Ejemplo integrado: consumidor

```scala
trait Consumidor[-A] {

  def consumir(
    valor: A
  ): String
}
```

```scala
class ProcesadorAnimales
  extends Consumidor[Animal] {

  def consumir(
    valor: Animal
  ): String =
    s"Procesando ${valor.nombre}"
}
```

Podemos utilizar:

```scala
val consumidorPerros:
  Consumidor[Perro] =
    new ProcesadorAnimales
```

porque un consumidor capaz de procesar cualquier `Animal` también puede procesar un `Perro`.

---

# 53. Genéricos y programación funcional

Los tipos genéricos son fundamentales en programación funcional.

Muchas estructuras pueden representarse de forma general:

```text
List[A]
Option[A]
Either[A, B]
```

En lugar de crear diferentes estructuras para cada tipo.

Por ejemplo:

```text
List[Int]
List[String]
List[Persona]
```

comparten la misma abstracción:

```text
List[A]
```

---

# 54. Genéricos y funciones de orden superior

También podemos combinar funciones genéricas con funciones de orden superior.

Por ejemplo:

```scala
def transformar[A, B](
  valor: A,
  funcion: A => B
): B =
  funcion(valor)
```

Uso:

```scala
transformar(
  10,
  numero => numero.toString
)
```

Aquí:

```text
A = Int
B = String
```

Resultado:

```text
"10"
```

---

# 55. Función genérica de búsqueda

Podemos crear:

```scala
def cumple[A](
  valor: A,
  condicion: A => Boolean
): Boolean =
  condicion(valor)
```

Uso:

```scala
cumple(
  10,
  numero => numero > 5
)
```

También:

```scala
cumple(
  "Scala",
  texto => texto.length > 3
)
```

La misma abstracción funciona con diferentes tipos.

---

# 56. Función genérica de transformación

```scala
def transformar[A, B](
  valor: A,
  funcion: A => B
): B =
  funcion(valor)
```

Ejemplos:

```scala
transformar(
  10,
  x => x * 2
)
```

Resultado:

```text
20
```

Y:

```scala
transformar(
  "Scala",
  texto => texto.length
)
```

Resultado:

```text
5
```

---

# 57. ¿Para qué sirve esto en un proyecto real?

Los genéricos permiten crear componentes reutilizables.

Por ejemplo, un sistema puede necesitar representar resultados:

```scala
Resultado[Usuario]
Resultado[Producto]
Resultado[Factura]
```

En lugar de crear:

```text
ResultadoUsuario
ResultadoProducto
ResultadoFactura
```

podemos diseñar:

```scala
class Resultado[A](
  val valor: A
)
```

---

# 58. Ejemplo: Repositorio genérico

En una aplicación podemos pensar en:

```scala
trait Repositorio[A] {

  def guardar(
    valor: A
  ): Unit

  def buscar(
    id: Int
  ): A
}
```

Después podríamos tener:

```text
Repositorio[Usuario]
Repositorio[Producto]
Repositorio[Pedido]
```

La estructura general del repositorio permanece igual.

El tipo almacenado cambia.

---

# 59. Ejemplo: Respuesta genérica de una API

Podemos representar:

```scala
class Respuesta[A](
  val datos: A,
  val codigo: Int,
  val mensaje: String
)
```

Entonces:

```text
Respuesta[Usuario]
Respuesta[Producto]
Respuesta[String]
```

pueden utilizar la misma estructura.

Esto reduce duplicación y mantiene seguridad de tipos.

---

# 60. Buenas prácticas

Al trabajar con genéricos:

* Utilizar nombres de parámetros de tipo claros.
* `A`, `B`, `T` son comunes cuando el significado es general.
* Utilizar nombres más descriptivos cuando ayuden a comprender el código.
* No utilizar `Any` cuando un parámetro de tipo pueda conservar mejor la información.
* Agregar límites solamente cuando sean necesarios.
* Utilizar covarianza cuando una estructura principalmente produce valores.
* Utilizar contravarianza cuando principalmente consume valores.
* Mantener invariancia cuando el tipo aparece tanto como entrada como salida y no exista una razón clara para otra variante.
* Favorecer estructuras inmutables cuando se utiliza covarianza.

---

# 61. Ejercicios de práctica

## Ejercicio 1. Clase genérica `Caja`

Implemente:

```scala
class Caja[A](
  val contenido: A
)
```

Agregue:

```scala
def obtener: A
```

y un método genérico:

```scala
def combinar[B](
  otro: B
): (A, B)
```

Pruebe con:

```text
Caja[Int]
Caja[String]
Caja[Boolean]
```

---

# 62. Ejercicio 2. Función genérica

Implemente:

```scala
def seleccionarPrimero[A](
  primero: A,
  segundo: A
): A
```

Pruebe la función utilizando:

```text
Int
String
Double
```

---

# 63. Ejercicio 3. Transformación genérica

Implemente:

```scala
def transformar[A, B](
  valor: A,
  funcion: A => B
): B
```

Utilícela para:

* transformar `Int` a `String`;
* transformar `String` a `Int` mediante su longitud;
* transformar `Double` a `Boolean`.

---

# 64. Ejercicio 4. Límite superior

Cree:

```scala
abstract class Vehiculo(
  val marca: String
)
```

y dos subtipos:

```text
Automovil
Motocicleta
```

Luego implemente:

```scala
class RegistroVehiculo[
  A <: Vehiculo
](
  val vehiculo: A
)
```

El sistema no deberá permitir crear:

```text
RegistroVehiculo[Int]
RegistroVehiculo[String]
```

---

# 65. Ejercicio 5. Covarianza

Suponga:

```text
Perro <: Animal
```

Implemente:

```scala
class Contenedor[+A](
  val valor: A
)
```

Cree:

```scala
val contenedorPerro:
  Contenedor[Perro]
```

y asígnelo a:

```scala
val contenedorAnimal:
  Contenedor[Animal]
```

Compruebe que la asignación sea válida.

---

# 66. Ejercicio 6. Contravarianza

Implemente:

```scala
trait Procesador[-A] {

  def procesar(
    valor: A
  ): String
}
```

Cree una implementación:

```scala
class ProcesadorAnimal
  extends Procesador[Animal]
```

y utilícela como:

```scala
Procesador[Perro]
```

---

# 67. Ejercicio 7. Productor y consumidor

Implemente:

```scala
trait Productor[+A] {

  def producir: A
}
```

y:

```scala
trait Consumidor[-A] {

  def consumir(
    valor: A
  ): String
}
```

Cree implementaciones concretas utilizando una jerarquía:

```text
Animal
├── Perro
└── Gato
```

Compruebe las relaciones de subtipado correspondientes.

---

# 68. Ejercicio 8. Resultado genérico

Cree:

```scala
class Resultado[A](
  val valor: A,
  val exitoso: Boolean,
  val mensaje: String
)
```

Cree instancias:

```text
Resultado[Int]
Resultado[String]
Resultado[Persona]
```

sin duplicar la definición de la clase.

---

# 69. Errores frecuentes

## Error 1. Confundir parámetro de tipo con parámetro normal

```scala
def identidad[A](
  valor: A
): A
```

`A` representa un tipo.

`valor` representa un dato.

---

## Error 2. Pensar que todos los genéricos son covariantes

Esto:

```scala
class Caja[A]
```

es invariante.

Para covarianza debemos declarar explícitamente:

```scala
class Caja[+A]
```

---

## Error 3. Pensar que `+` significa que se pueden agregar elementos

En:

```scala
class Caja[+A]
```

el símbolo:

```text
+
```

no representa suma.

Indica:

```text
covarianza
```

---

## Error 4. Pensar que `-` significa quitar elementos

En:

```scala
trait Consumidor[-A]
```

el símbolo:

```text
-
```

indica:

```text
contravarianza
```

No representa una operación matemática.

---

## Error 5. Confundir límites de tipos

```text
A <: Animal
```

significa:

```text
A debe ser Animal o un subtipo
```

Mientras que:

```text
A >: Perro
```

significa:

```text
A debe ser Perro o un supertipo
```

---

# 70. Resumen

El **polimorfismo paramétrico** permite escribir código que funciona con diferentes tipos:

```scala
def identidad[A](
  valor: A
): A =
  valor
```

Una clase puede ser genérica:

```scala
class Caja[A](
  val contenido: A
)
```

Podemos restringir un parámetro utilizando límites.

Límite superior:

```scala
A <: Animal
```

significa:

> A debe ser subtipo de `Animal`.

Límite inferior:

```scala
A >: Perro
```

significa:

> A debe ser supertipo de `Perro`.

La **varianza** determina cómo se relacionan los tipos genéricos cuando sus parámetros tienen relaciones de subtipado.

Si:

```text
Perro <: Animal
```

la covarianza:

```scala
Contenedor[+A]
```

mantiene la dirección:

```text
Contenedor[Perro]
    <:
Contenedor[Animal]
```

La contravarianza:

```scala
Consumidor[-A]
```

invierte la dirección:

```text
Consumidor[Animal]
    <:
Consumidor[Perro]
```

La invariancia:

```scala
Contenedor[A]
```

no establece ninguna relación automática entre ambos tipos.

Una forma práctica de recordarlo es:

```text
Produce A
   ↓
Covarianza [+A]

Consume A
   ↓
Contravarianza [-A]

Produce y consume A
   ↓
Normalmente invariancia [A]
```

La idea fundamental de esta sesión es:

> **Los genéricos permiten crear código reutilizable manteniendo la información de tipos, mientras que los límites y la varianza permiten controlar de manera segura cómo esos tipos genéricos interactúan con las jerarquías de subtipado de Scala.**
