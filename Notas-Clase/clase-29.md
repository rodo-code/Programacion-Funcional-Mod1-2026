# Clase 29: Reducción funcional de colecciones en Scala

# 1. De transformar a reducir

En las sesiones anteriores utilizamos `map`, `filter` y `flatMap`. Estas
operaciones normalmente producen nuevas colecciones.

``` scala
val numeros = List(1, 2, 3, 4)

val dobles =
  numeros.map(x => x * 2)

val pares =
  numeros.filter(x => x % 2 == 0)
```

Sin embargo, existen problemas donde queremos combinar todos los
elementos para producir un único resultado:

``` text
List(10, 20, 30, 40)
          ↓
         suma
          ↓
          100
```

A este proceso lo llamamos **reducción**.

------------------------------------------------------------------------

# 2. ¿Qué significa reducir?

Reducir consiste en combinar progresivamente los elementos de una
colección hasta obtener un resultado.

Ejemplos:

``` text
List(1, 2, 3, 4) → suma → 10
List(5, 3, 9, 2) → máximo → 9
List("A","B","C") → concatenación → "ABC"
```

------------------------------------------------------------------------

# 3. La operación `reduce`

`reduce` combina los elementos utilizando una función.

``` scala
val numeros = List(1, 2, 3, 4)

val suma =
  numeros.reduce(
    (a, b) => a + b
  )
```

Resultado:

``` text
10
```

Conceptualmente:

``` text
1 + 2 = 3
3 + 3 = 6
6 + 4 = 10
```

La función utilizada tiene la idea:

``` text
(A, A) => A
```

También puede escribirse:

``` scala
val suma = numeros.reduce(_ + _)
```

## Ejemplo: encontrar el mayor elemento

``` scala
val mayor =
  numeros.reduce(
    (a, b) =>
      if a > b then a else b
  )
```

------------------------------------------------------------------------

## Ejercicio 1

Dada:

``` scala
val numeros =
  List(8, 3, 12, 5, 20)
```

Utiliza `reduce` para obtener:

1.  La suma.
2.  El mayor valor.
3.  El producto de todos los elementos.

------------------------------------------------------------------------

# 4. Limitación de `reduce`

Esto funciona:

``` scala
List(1, 2, 3).reduce(_ + _)
```

Pero una colección vacía no posee un primer elemento:

``` scala
List.empty[Int].reduce(_ + _)
```

Por lo tanto, `reduce` directamente sobre una lista vacía produce un
error.

Esta limitación introduce una operación más flexible: `fold`.

------------------------------------------------------------------------

# 5. `foldLeft`

`foldLeft` permite proporcionar un **valor inicial**.

``` scala
val numeros = List(1, 2, 3, 4)

val suma =
  numeros.foldLeft(0)(
    (acumulador, elemento) =>
      acumulador + elemento
  )
```

Resultado:

``` text
10
```

Estructura general:

``` scala
lista.foldLeft(valorInicial)(
  (acumulador, elemento) =>
    nuevoAcumulador
)
```

Proceso:

``` text
0 + 1 = 1
1 + 2 = 3
3 + 3 = 6
6 + 4 = 10
```

------------------------------------------------------------------------

# 6. El acumulador

El acumulador representa el resultado parcial.

``` scala
val numeros = List(2, 4, 6)

val suma =
  numeros.foldLeft(0)(
    (acc, numero) =>
      acc + numero
  )
```

  Paso        Acumulador   Elemento   Nuevo acumulador
  --------- ------------ ---------- ------------------
  Inicial              0         \-                  0
  1                    0          2                  2
  2                    2          4                  6
  3                    6          6                 12

------------------------------------------------------------------------

# 7. Relación con recursión de cola

Anteriormente:

``` scala
def sumar(
  lista: List[Int],
  acumulador: Int
): Int =
  lista match
    case Nil =>
      acumulador

    case cabeza :: cola =>
      sumar(
        cola,
        acumulador + cabeza
      )
```

Ahora podemos expresar el mismo patrón:

``` scala
lista.foldLeft(0)(
  (acumulador, elemento) =>
    acumulador + elemento
)
```

Conceptualmente:

``` text
Recursión de cola
       +
Acumulador
       ↓
   foldLeft
```

`foldLeft` abstrae un patrón que anteriormente implementábamos
manualmente.

------------------------------------------------------------------------

## Ejercicio 2

Dada:

``` scala
val numeros =
  List(3, 5, 7, 9)
```

Utiliza `foldLeft` para obtener:

1.  La suma.
2.  La cantidad de elementos.
3.  El producto de todos los elementos.

Piensa cuál debería ser el valor inicial en cada caso.

------------------------------------------------------------------------

# 8. El acumulador puede tener otro tipo

Con `foldLeft`, el tipo del resultado no necesariamente tiene que
coincidir con el tipo de los elementos.

Podemos partir de:

``` text
List[Int]
```

y producir:

``` text
String
```

Ejemplo:

``` scala
val numeros = List(1, 2, 3)

val texto =
  numeros.foldLeft("")(
    (acc, numero) =>
      acc + numero.toString
  )
```

Resultado:

``` text
"123"
```

Conceptualmente:

``` text
List[A]
   ↓
foldLeft
   ↓
   B
```

------------------------------------------------------------------------

## Ejercicio 3

Dada:

``` scala
val palabras =
  List("Scala", "es", "funcional")
```

Utiliza `foldLeft` para construir:

``` text
"Scala es funcional"
```

------------------------------------------------------------------------

# 9. `foldRight`

`foldRight` también utiliza un valor inicial, pero conceptualmente
asocia la operación desde la derecha.

``` scala
val numeros = List(1, 2, 3)

val resultado =
  numeros.foldRight(0)(
    (elemento, acumulador) =>
      elemento + acumulador
  )
```

Comparación:

``` text
foldLeft:

(((0 + 1) + 2) + 3)

foldRight:

1 + (2 + (3 + 0))
```

Con la suma ambos producen `6`.

------------------------------------------------------------------------

# 10. Cuando el orden importa

Con operaciones como la resta, los resultados pueden ser diferentes.

## `foldLeft`

``` scala
val resultado =
  List(1, 2, 3).foldLeft(0)(
    (acc, x) => acc - x
  )
```

``` text
((0 - 1) - 2) - 3 = -6
```

## `foldRight`

``` scala
val resultado =
  List(1, 2, 3).foldRight(0)(
    (x, acc) => x - acc
  )
```

``` text
1 - (2 - (3 - 0)) = 2
```

> La dirección de reducción puede cambiar el resultado cuando la
> operación no es asociativa.

------------------------------------------------------------------------

## Ejercicio 4

Dada:

``` scala
val numeros =
  List(10, 5, 2)
```

Calcula manualmente y verifica en Scala:

1.  `foldLeft(0)` utilizando resta.
2.  `foldRight(0)` utilizando resta.

Explica por qué los resultados son diferentes.

------------------------------------------------------------------------

# 11. `reduce` vs `foldLeft`

  -----------------------------------------------------------------------
  Característica          `reduce`                `foldLeft`
  ----------------------- ----------------------- -----------------------
  Valor inicial           No                      Sí

  Lista vacía             Problemática            Puede procesarse

  Acumulador explícito    No                      Sí

  Flexibilidad del        Menor                   Mayor
  resultado                                       

  Uso típico              Combinar elementos del  Acumular un resultado
                          mismo tipo              
  -----------------------------------------------------------------------

Valores iniciales frecuentes:

  Operación                 Valor inicial
  ----------------------- ---------------
  Suma                                `0`
  Producto                            `1`
  Conteo                              `0`
  Concatenación                      `""`
  Construcción de lista      `List.empty`

------------------------------------------------------------------------

# 12. Combinando transformación y reducción

Las operaciones funcionales pueden formar un pipeline.

Queremos:

1.  Conservar números pares.
2.  Elevarlos al cuadrado.
3.  Sumar los resultados.

``` scala
val numeros =
  List(1, 2, 3, 4, 5, 6)

val resultado =
  numeros
    .filter(x => x % 2 == 0)
    .map(x => x * x)
    .foldLeft(0)(
      (acc, x) => acc + x
    )
```

Proceso:

``` text
List(1,2,3,4,5,6)
        ↓ filter
List(2,4,6)
        ↓ map
List(4,16,36)
        ↓ foldLeft
56
```

Por lo tanto:

``` text
filter    → seleccionar
map       → transformar
foldLeft  → reducir
```

------------------------------------------------------------------------

# 13. Ejercicio integrador: procesamiento de ventas

Considere:

``` scala
case class Venta(
  producto: String,
  cantidad: Int,
  precioUnitario: Double
)
```

Datos:

``` scala
val ventas =
  List(
    Venta("Teclado", 2, 150.0),
    Venta("Mouse", 3, 80.0),
    Venta("Monitor", 1, 1200.0),
    Venta("Cable HDMI", 4, 50.0)
  )
```

El ingreso de una venta se calcula como:

``` text
cantidad × precioUnitario
```

Utilizando operaciones funcionales:

1.  Calcular el ingreso total de todas las ventas.
2.  Calcular la cantidad total de unidades vendidas.
3.  Obtener las ventas cuyo ingreso sea mayor a `200`.
4.  Obtener una lista con los nombres de esos productos.
5.  Calcular el ingreso total únicamente de las ventas cuyo ingreso sea
    mayor a `200`.

## Restricciones

-   Utilizar datos inmutables.
-   Utilizar `map`, `filter`, `reduce` o `foldLeft` cuando corresponda.
-   No utilizar `var`.
-   No utilizar ciclos `while`.
-   No modificar la lista original.
-   Las funciones de procesamiento deben ser puras.

------------------------------------------------------------------------

# 14. Resumen

## `map`

Transforma elementos:

``` text
List[A] → List[B]
```

## `filter`

Selecciona elementos:

``` text
List[A] → List[A]
```

## `flatMap`

Transforma y aplana:

``` text
List[A] → List[B]
```

## `reduce`, `foldLeft` y `foldRight`

Combinan elementos para producir un resultado:

``` text
Colección → Resultado
```

La progresión de conceptos del curso es:

``` text
Recursión manual
       ↓
Recursión de cola
       ↓
Acumuladores
       ↓
Funciones de orden superior
       ↓
map / filter / flatMap
       ↓
reduce / foldLeft / foldRight
```

La idea central es:

> `foldLeft` abstrae el patrón funcional de recorrer una colección y
> mantener un acumulador.

En lugar de controlar manualmente **cómo recorrer la colección**,
especificamos **cómo combinar cada elemento con el resultado
acumulado**.
