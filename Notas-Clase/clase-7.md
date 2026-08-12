# Clase 7: Manejo de Strings en Scala y Ejercicios de Recursión

## Manejo de Strings

En Scala, un `String` representa una cadena de caracteres.

```scala
val texto = "Scala"
```

Los índices comienzan desde `0`.

```text
S  c  a  l  a
0  1  2  3  4
```

---

## `.length`

Devuelve la cantidad de caracteres que tiene un `String`.

```scala
val texto = "Scala"

println(texto.length)
```

Resultado:

```text
5
```

---

## `.head`

Devuelve el primer carácter del `String`.

```scala
val texto = "Scala"

println(texto.head)
```

Resultado:

```text
S
```

El resultado es de tipo `Char`.

```scala
val primeraLetra: Char = texto.head
```

---

## `.last`

Devuelve el último carácter del `String`.

```scala
val texto = "Scala"

println(texto.last)
```

Resultado:

```text
a
```

---

## `.init`

Devuelve el `String` sin el último caracter.

```scala
val texto = "Scala"

println(texto.init)
```

Resultado:

```text
Scal
```

---

## `.tail`

Devuelve el `String` sin el primer caracter.

```scala
val texto = "Scala"

println(texto.tail)
```

Resultado:

```text
cala
```

---

## Acceso mediante índice `(index)`

Permite obtener el carácter ubicado en una determinada posición.

```scala
val texto = "Scala"

println(texto(0))
println(texto(1))
println(texto(3))
```

Resultado:

```text
S
c
l
```

Para obtener el último carácter mediante índice:

```scala
val ultimaLetra = texto(texto.length - 1)
```

Los índices válidos van desde:

```text
0 hasta length - 1
```

---

## `.substring()`

Permite obtener una parte de un `String`.

### Indicando inicio y fin

```scala
val texto = "Programacion"

val parte = texto.substring(0, 4)

println(parte)
```

Resultado:

```text
Prog
```

La posición inicial se incluye, pero la posición final no.

```scala
texto.substring(0, 4)
```

obtiene los caracteres ubicados en:

```text
0, 1, 2, 3
```

También se puede indicar solamente la posición inicial:

```scala
val texto = "Programacion"

println(texto.substring(4))
```

Resultado:

```text
ramacion
```

---

## `.contains()`

Permite comprobar si un `String` contiene determinado texto.

```scala
val texto = "Programacion Funcional"

println(texto.contains("Funcional"))
```

Resultado:

```text
true
```

Otro ejemplo:

```scala
println(texto.contains("Java"))
```

Resultado:

```text
false
```

El resultado de `contains` es de tipo `Boolean`.

---

## `.toUpperCase`

Convierte todos los caracteres a mayúsculas.

```scala
val texto = "Scala"

val mayusculas = texto.toUpperCase

println(mayusculas)
```

Resultado:

```text
SCALA
```

---

## `.toLowerCase`

Convierte todos los caracteres a minúsculas.

```scala
val texto = "SCALA"

val minusculas = texto.toLowerCase

println(minusculas)
```

Resultado:

```text
scala
```

---

## Ejemplo completo

```scala
val palabra = "Programacion"

println(s"Palabra: $palabra")
println(s"Longitud: ${palabra.length}")
println(s"Primera letra: ${palabra.head}")
println(s"Ultima letra: ${palabra.last}")
println(s"Caracter en posicion 3: ${palabra(3)}")
println(s"Primeras cuatro letras: ${palabra.substring(0, 4)}")
println(s"Contiene 'gram': ${palabra.contains("gram")}")
println(s"Mayusculas: ${palabra.toUpperCase}")
println(s"Minusculas: ${palabra.toLowerCase}")
```

---

## Resumen

| Operación                      | Descripción                             |
| ------------------------------ | --------------------------------------- |
| `texto.length`                 | Cantidad de caracteres                  |
| `texto.head`                   | Primer carácter                         |
| `texto.last`                   | Último carácter                         |
| `texto(index)`                 | Carácter en una posición                |
| `texto.substring(inicio, fin)` | Obtiene una parte del String            |
| `texto.contains("texto")`      | Comprueba si contiene determinado texto |
| `texto.toUpperCase`            | Convierte a mayúsculas                  |
| `texto.toLowerCase`            | Convierte a minúsculas                  |

> Los `String` en Scala son inmutables. Operaciones como `toUpperCase` o `toLowerCase` generan un nuevo `String`; no modifican el original.

# Ejercicios Clase

# Ejercicio 1. Sucesión de Fibonacci

Desarrolle una función recursiva que reciba un número entero no negativo `n` y devuelva el término correspondiente de la sucesión de Fibonacci.

La sucesión comienza de la siguiente manera:

```text
0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
```

Los primeros términos cumplen:

```text
Fibonacci(0) = 0
Fibonacci(1) = 1
```

A partir de ellos, cada término se obtiene sumando los dos términos anteriores.

### Ejemplos

```text
fibonacci(0) = 0
fibonacci(1) = 1
fibonacci(5) = 5
fibonacci(8) = 21
fibonacci(10) = 55
```

---

# Ejercicio 2. Máximo Común Divisor

Desarrolle una función recursiva que reciba dos números enteros positivos y calcule su **Máximo Común Divisor (MCD)** utilizando el algoritmo de Euclides.

El Máximo Común Divisor de dos números es el mayor número entero que divide exactamente a ambos.

### Ejemplos

```text
mcd(12, 8) = 4
mcd(24, 18) = 6
mcd(48, 18) = 6
mcd(100, 25) = 25
mcd(17, 13) = 1
```

---

# Ejercicio 3. Contar apariciones de un carácter

Desarrolle una función recursiva que reciba:

* un texto de tipo `String`;
* un carácter de tipo `Char`.

La función deberá devolver la cantidad de veces que el carácter aparece dentro del texto.

La comparación deberá distinguir entre letras mayúsculas y minúsculas.

### Ejemplos

```text
contarCaracter("banana", 'a') = 3

contarCaracter("programacion", 'o') = 2

contarCaracter("Scala", 'a') = 2

contarCaracter("Scala", 'S') = 1

contarCaracter("Scala", 's') = 0

contarCaracter("funcional", 'z') = 0
```

