# Clase 13: Funciones como valores, funciones anónimas y tipos función

# 1. Funciones como valores de primera clase

Hasta ahora hemos definido funciones utilizando `def`.

Por ejemplo:

```scala
def cuadrado(numero: Int): Int =
  numero * numero
```

Podemos utilizarla de la siguiente manera:

```scala
cuadrado(5)
```

Resultado:

```text
25
```

En Scala, una función no solamente puede ser ejecutada.

También puede ser tratada como un **valor**.

Por ejemplo:

```scala
val operacion = cuadrado
```

Ahora podemos utilizar:

```scala
operacion(5)
```

Resultado:

```text
25
```

Esto significa que una función puede almacenarse dentro de un `val`.

---

# 2. ¿Qué significa que una función sea un valor de primera clase?

En Scala podemos almacenar diferentes tipos de valores:

```scala
val edad: Int = 20

val nombre: String = "Ana"

val activo: Boolean = true
```

También podemos almacenar una función:

```scala
val doble: Int => Int =
  numero => numero * 2
```

Por tanto, las funciones pueden tratarse como otros valores del programa.

En programación funcional se dice que:

> Las funciones son valores de primera clase.

Esto permite que las funciones puedan:

* almacenarse en valores;
* enviarse como parámetros;
* devolverse como resultado de otras funciones.

---

# 3. Tipos función

Las funciones también poseen un tipo.

Considere:

```scala
def cuadrado(numero: Int): Int =
  numero * numero
```

Esta función:

* recibe un `Int`;
* devuelve un `Int`.

Su tipo función puede representarse como:

```text
Int => Int
```

Se lee:

> Una función que recibe un `Int` y devuelve un `Int`.

---

# 4. Ejemplos de tipos función

## Entero a entero

```text
Int => Int
```

Ejemplo:

```scala
val doble: Int => Int =
  numero => numero * 2
```

---

## Entero a Boolean

```text
Int => Boolean
```

Ejemplo:

```scala
val esPar: Int => Boolean =
  numero => numero % 2 == 0
```

---

## String a entero

```text
String => Int
```

Ejemplo:

```scala
val longitud: String => Int =
  texto => texto.length
```

---

## Double a String

```text
Double => String
```

Ejemplo:

```scala
val clasificar: Double => String =
  numero =>
    if numero >= 0 then
      "POSITIVO"
    else
      "NEGATIVO"
```

---

# 5. Funciones anónimas o lambdas

Una **función anónima** es una función que no necesita ser definida utilizando `def`.

También se conoce como **lambda**.

Por ejemplo:

```scala
(numero: Int) => numero * 2
```

Esta función:

* recibe un `Int`;
* multiplica el valor por `2`;
* devuelve un `Int`.

No posee un nombre propio.

---

# 6. Almacenar una lambda en un `val`

Podemos almacenar una función anónima:

```scala
val doble: Int => Int =
  (numero: Int) => numero * 2
```

Ahora podemos ejecutarla:

```scala
doble(5)
```

Resultado:

```text
10
```

La estructura general es:

```scala
val nombre: TipoEntrada => TipoSalida =
  parametro => expresion
```

---

# 7. Comparación entre `def` y lambda

Podemos representar la misma operación de dos maneras.

## Utilizando `def`

```scala
def triple(numero: Int): Int =
  numero * 3
```

## Utilizando una lambda

```scala
val triple: Int => Int =
  numero => numero * 3
```

Ambas pueden utilizarse de forma similar:

```scala
triple(5)
```

Resultado:

```text
15
```

Sin embargo, conceptualmente:

* `def` define un método.
* `val` puede almacenar una función como valor.

---

# 8. Inferencia de tipos en lambdas

Considere:

```scala
val cuadrado: Int => Int =
  numero => numero * numero
```

Scala sabe que `numero` es de tipo `Int` porque el tipo de la función ya fue declarado:

```scala
Int => Int
```

Por tanto, no necesitamos escribir:

```scala
(numero: Int)
```

aunque también sería válido:

```scala
val cuadrado: Int => Int =
  (numero: Int) => numero * numero
```

---

# 9. Cuando Scala necesita más información

Esto puede producir un error:

```scala
val cuadrado =
  numero => numero * numero
```

Scala no sabe necesariamente cuál es el tipo de `numero`.

Podemos solucionarlo indicando el tipo del parámetro:

```scala
val cuadrado =
  (numero: Int) => numero * numero
```

Scala puede inferir que el resultado también es un `Int`.

Otra opción es declarar el tipo función:

```scala
val cuadrado: Int => Int =
  numero => numero * numero
```

---

# 10. Lambdas que devuelven Boolean

Las funciones lambda pueden utilizar expresiones booleanas.

Ejemplo:

```scala
val esPositivo: Int => Boolean =
  numero => numero > 0
```

Uso:

```scala
esPositivo(10)
```

Resultado:

```text
true
```

Otro ejemplo:

```scala
val esMultiploDeCinco: Int => Boolean =
  numero => numero % 5 == 0
```

Uso:

```scala
esMultiploDeCinco(20)
```

Resultado:

```text
true
```

---

# 11. Lambdas con `String`

También podemos trabajar con cadenas.

```scala
val convertirMayusculas: String => String =
  texto => texto.toUpperCase
```

Uso:

```scala
convertirMayusculas("scala")
```

Resultado:

```text
SCALA
```

Otro ejemplo:

```scala
val primeraLetra: String => Char =
  texto => texto.head
```

---

# 12. Lambdas con más de un parámetro

Una función puede recibir varios parámetros.

Por ejemplo:

```scala
def sumar(a: Int, b: Int): Int =
  a + b
```

Su tipo función es:

```text
(Int, Int) => Int
```

Una lambda equivalente sería:

```scala
val sumar: (Int, Int) => Int =
  (a, b) => a + b
```

Uso:

```scala
sumar(5, 3)
```

Resultado:

```text
8
```

---

# 13. Tipos función con varios parámetros

Considere:

```text
(Int, Int) => Int
```

Significa:

> Función que recibe dos `Int` y devuelve un `Int`.

Otro ejemplo:

```text
(Double, Double) => Double
```

Ejemplo:

```scala
val promedio: (Double, Double) => Double =
  (a, b) => (a + b) / 2
```

---

Otro tipo:

```text
(String, Char) => Boolean
```

Puede representar una función como:

```scala
val contiene: (String, Char) => Boolean =
  (texto, caracter) => texto.contains(caracter)
```

---

# 14. Funciones con tres parámetros

También es posible utilizar más parámetros.

```scala
val promedioTres: (Double, Double, Double) => Double =
  (a, b, c) => (a + b + c) / 3
```

Uso:

```scala
promedioTres(70, 80, 90)
```

Resultado:

```text
80.0
```

---

# 15. Lambdas con expresiones condicionales

Como `if` es una expresión en Scala, puede utilizarse dentro de una lambda.

```scala
val mayor: (Int, Int) => Int =
  (a, b) =>
    if a > b then
      a
    else
      b
```

Uso:

```scala
mayor(10, 7)
```

Resultado:

```text
10
```

---

# 16. Lambdas de varias líneas

Una lambda puede contener un bloque.

```scala
val calcularPrecioFinal: Double => Double =
  precio => {
    val descuento: Double = precio * 0.10
    precio - descuento
  }
```

Uso:

```scala
calcularPrecioFinal(200)
```

Resultado:

```text
180.0
```

El resultado del bloque corresponde a su última expresión:

```scala
precio - descuento
```

---

# 17. Funciones almacenadas en variables diferentes

Una función puede almacenarse en diferentes valores.

```scala
def cuadrado(numero: Int): Int =
  numero * numero

val operacion1: Int => Int = cuadrado

val operacion2: Int => Int = cuadrado
```

Ambas pueden utilizarse:

```scala
operacion1(4)
operacion2(5)
```

Resultados:

```text
16
25
```

---

# 18. Asignar una función existente

Una función definida previamente también puede ser utilizada como valor.

```scala
def convertir(texto: String): String =
  texto.toUpperCase
```

Podemos escribir:

```scala
val transformar: String => String =
  convertir
```

Ahora:

```scala
transformar("scala")
```

Resultado:

```text
SCALA
```

---

# 19. Leer correctamente un tipo función

Es importante aprender a interpretar los tipos.

## Ejemplo 1

```text
Int => Boolean
```

Significa:

* recibe `Int`;
* devuelve `Boolean`.

---

## Ejemplo 2

```text
String => Int
```

Significa:

* recibe `String`;
* devuelve `Int`.

---

## Ejemplo 3

```text
(Int, Int) => Boolean
```

Significa:

* recibe dos `Int`;
* devuelve `Boolean`.

---

## Ejemplo 4

```text
(String, Int) => String
```

Significa:

* recibe un `String` y un `Int`;
* devuelve un `String`.

---

# 20. Ejemplos completos

## Calcular el cubo

```scala
val cubo: Int => Int =
  numero => numero * numero * numero
```

```scala
cubo(3)
```

Resultado:

```text
27
```

---

## Determinar si un número es negativo

```scala
val esNegativo: Int => Boolean =
  numero => numero < 0
```

---

## Obtener el menor de dos números

```scala
val menor: (Int, Int) => Int =
  (a, b) =>
    if a < b then a else b
```

---

## Calcular el área de un rectángulo

```scala
val areaRectangulo: (Double, Double) => Double =
  (base, altura) => base * altura
```

---

## Contar caracteres de un texto

```scala
val cantidadCaracteres: String => Int =
  texto => texto.length
```

---

# 21. Funciones como datos

Una de las ideas más importantes de programación funcional es que una función puede considerarse un dato.

Por ejemplo:

```scala
val numero: Int = 10
```

almacena un número.

```scala
val mensaje: String = "Hola"
```

almacena un texto.

Y:

```scala
val transformar: Int => Int =
  numero => numero * 2
```

almacena una función.

La diferencia es que la función puede ser ejecutada:

```scala
transformar(10)
```

Resultado:

```text
20
```

---

# 22. ¿Por qué son importantes las funciones como valores?

Poder trabajar con funciones como valores permite construir programas más flexibles.

Más adelante podremos realizar operaciones como:

```text
enviar una función a otra función
```

o:

```text
devolver una función desde otra función
```

Esto permite crear comportamientos reutilizables y construir abstracciones más generales.

Estas ideas son la base de las **funciones de orden superior**.

---

# 23. Errores frecuentes

## Error 1: Confundir entrada y salida

```scala
val funcion: Int => Boolean =
  numero => numero * 2
```

Esto es incorrecto porque:

```text
numero * 2
```

produce un `Int`, pero la función declara que debe devolver `Boolean`.

Una versión correcta podría ser:

```scala
val funcion: Int => Boolean =
  numero => numero > 0
```

---

## Error 2: Cantidad incorrecta de parámetros

Si tenemos:

```scala
val sumar: (Int, Int) => Int =
  (a, b) => a + b
```

debemos enviar dos argumentos:

```scala
sumar(5, 3)
```

No:

```scala
sumar(5)
```

---

## Error 3: No proporcionar suficiente información de tipos

```scala
val doble =
  numero => numero * 2
```

Puede no proporcionar información suficiente para determinar el tipo de `numero`.

Es preferible:

```scala
val doble: Int => Int =
  numero => numero * 2
```

---

# 24. Buenas prácticas

Al trabajar con funciones como valores:

* Utilizar nombres descriptivos.
* Declarar tipos función cuando ayuden a comprender el código.
* Mantener las funciones pequeñas.
* Preferir funciones puras.
* Utilizar `val` antes que `var`.
* Aprovechar la inferencia de tipos cuando el contexto sea claro.
* Evitar lógica innecesariamente compleja dentro de una lambda.

---

# 25. Resumen

Una función definida tradicionalmente:

```scala
def doble(numero: Int): Int =
  numero * 2
```

también puede representarse como una función almacenada en un valor:

```scala
val doble: Int => Int =
  numero => numero * 2
```

El tipo:

```text
Int => Int
```

significa:

> recibe un `Int` y devuelve un `Int`.

Una función anónima:

```scala
numero => numero * 2
```

se conoce también como **lambda**.

Para múltiples parámetros:

```scala
val sumar: (Int, Int) => Int =
  (a, b) => a + b
```

Las funciones son **valores de primera clase**, por lo que pueden almacenarse y utilizarse como otros valores del lenguaje.

Esta característica será fundamental para trabajar posteriormente con **funciones de orden superior**.
