# Clase 14: Funciones de Orden Superior

# 1. Recordatorio: funciones como valores

En la sesión anterior vimos que las funciones pueden ser tratadas como valores.

Por ejemplo:

```scala
val doble: Int => Int =
  numero => numero * 2
```

La variable `doble` almacena una función que:

* recibe un `Int`;
* devuelve un `Int`.

Podemos utilizarla:

```scala
doble(5)
```

Resultado:

```text
10
```

También vimos que una función puede definirse utilizando `def`:

```scala
def cuadrado(numero: Int): Int =
  numero * numero
```

Estas ideas permiten dar el siguiente paso:

> Una función puede enviarse como argumento a otra función.

---

# 2. ¿Qué es una función de orden superior?

Una **función de orden superior** es una función que cumple al menos una de las siguientes condiciones:

* recibe una o más funciones como parámetros;
* devuelve una función como resultado.

En esta sesión nos concentraremos principalmente en:

> **Funciones que reciben otras funciones como parámetros.**

---

# 3. Una función como parámetro

Considere la siguiente función:

```scala
def aplicarOperacion(
  numero: Int,
  operacion: Int => Int
): Int =
  operacion(numero)
```

Esta función recibe dos parámetros:

```text
numero
operacion
```

El primero:

```scala
numero: Int
```

es un valor normal.

El segundo:

```scala
operacion: Int => Int
```

es una función.

Esto significa que `operacion` debe recibir un `Int` y devolver otro `Int`.

---

# 4. Utilizando una función definida con `def`

Podemos definir:

```scala
def doble(numero: Int): Int =
  numero * 2
```

Y luego enviarla:

```scala
aplicarOperacion(5, doble)
```

La ejecución puede entenderse como:

```text
aplicarOperacion(5, doble)

↓

doble(5)

↓

10
```

Resultado:

```text
10
```

---

# 5. Cambiar el comportamiento sin cambiar la función principal

Podemos crear otra función:

```scala
def cuadrado(numero: Int): Int =
  numero * numero
```

Y utilizar:

```scala
aplicarOperacion(5, cuadrado)
```

Resultado:

```text
25
```

La función:

```scala
aplicarOperacion
```

no cambió.

Solamente cambiamos la función enviada como argumento.

Esto permite crear programas más flexibles y reutilizables.

---

# 6. Diferencia entre enviar un valor y enviar una función

Considere:

```scala
aplicarOperacion(5, doble)
```

Aquí:

```text
5
```

es un valor.

Mientras que:

```text
doble
```

representa una función.

La función `aplicarOperacion` decide cuándo ejecutar esa función:

```scala
operacion(numero)
```

---

# 7. Enviar una lambda directamente

No es obligatorio definir previamente la función utilizando `def`.

Podemos enviar una función anónima directamente.

Por ejemplo:

```scala
aplicarOperacion(
  5,
  numero => numero * 3
)
```

Resultado:

```text
15
```

La lambda:

```scala
numero => numero * 3
```

tiene tipo:

```text
Int => Int
```

---

# 8. Más ejemplos con lambdas

## Duplicar

```scala
aplicarOperacion(
  10,
  numero => numero * 2
)
```

Resultado:

```text
20
```

---

## Calcular el cuadrado

```scala
aplicarOperacion(
  6,
  numero => numero * numero
)
```

Resultado:

```text
36
```

---

## Incrementar

```scala
aplicarOperacion(
  7,
  numero => numero + 1
)
```

Resultado:

```text
8
```

---

# 9. Funciones que reciben condiciones

Una función también puede recibir otra función que devuelva un `Boolean`.

Por ejemplo:

```scala
def verificar(
  numero: Int,
  condicion: Int => Boolean
): Boolean =
  condicion(numero)
```

El parámetro:

```scala
condicion: Int => Boolean
```

representa una función que:

* recibe un `Int`;
* devuelve `true` o `false`.

---

# 10. Ejemplo: verificar si un número es par

Podemos definir:

```scala
def esPar(numero: Int): Boolean =
  numero % 2 == 0
```

Luego:

```scala
verificar(8, esPar)
```

Resultado:

```text
true
```

También podemos utilizar una lambda:

```scala
verificar(
  8,
  numero => numero % 2 == 0
)
```

Resultado:

```text
true
```

---

# 11. Cambiar la condición

La misma función:

```scala
verificar
```

puede utilizar diferentes condiciones.

## Número positivo

```scala
verificar(
  10,
  numero => numero > 0
)
```

Resultado:

```text
true
```

## Número múltiplo de 5

```scala
verificar(
  20,
  numero => numero % 5 == 0
)
```

Resultado:

```text
true
```

## Número mayor que 100

```scala
verificar(
  75,
  numero => numero > 100
)
```

Resultado:

```text
false
```

La función principal no cambia.

Solo cambia el comportamiento recibido.

---

# 12. Funciones con dos parámetros

También podemos enviar funciones que reciban más de un parámetro.

Por ejemplo:

```scala
def calcular(
  a: Int,
  b: Int,
  operacion: (Int, Int) => Int
): Int =
  operacion(a, b)
```

El parámetro:

```scala
operacion: (Int, Int) => Int
```

representa una función que:

* recibe dos `Int`;
* devuelve un `Int`.

---

# 13. Diferentes operaciones

## Suma

```scala
calcular(
  10,
  5,
  (a, b) => a + b
)
```

Resultado:

```text
15
```

## Resta

```scala
calcular(
  10,
  5,
  (a, b) => a - b
)
```

Resultado:

```text
5
```

## Multiplicación

```scala
calcular(
  10,
  5,
  (a, b) => a * b
)
```

Resultado:

```text
50
```

La función `calcular` permanece igual.

---

# 14. Utilizando funciones previamente definidas

También podemos escribir:

```scala
def sumar(a: Int, b: Int): Int =
  a + b

def multiplicar(a: Int, b: Int): Int =
  a * b
```

Luego:

```scala
calcular(8, 4, sumar)
```

Resultado:

```text
12
```

Y:

```scala
calcular(8, 4, multiplicar)
```

Resultado:

```text
32
```

---

# 15. Procesamiento de texto

Las funciones de orden superior no están limitadas a números.

Por ejemplo:

```scala
def procesarTexto(
  texto: String,
  transformacion: String => String
): String =
  transformacion(texto)
```

Podemos utilizar:

```scala
procesarTexto(
  "scala",
  texto => texto.toUpperCase
)
```

Resultado:

```text
SCALA
```

---

# 16. Diferentes transformaciones de texto

## Convertir a minúsculas

```scala
procesarTexto(
  "PROGRAMACION",
  texto => texto.toLowerCase
)
```

Resultado:

```text
programacion
```

## Agregar un mensaje

```scala
procesarTexto(
  "Scala",
  texto => s"Lenguaje: $texto"
)
```

Resultado:

```text
Lenguaje: Scala
```

---

# 17. Funciones con diferentes tipos de entrada y salida

La función recibida no tiene que devolver el mismo tipo que recibe.

Por ejemplo:

```scala
def analizarTexto(
  texto: String,
  analisis: String => Int
): Int =
  analisis(texto)
```

Podemos enviar:

```scala
analizarTexto(
  "Programacion",
  texto => texto.length
)
```

Resultado:

```text
12
```

El tipo función es:

```text
String => Int
```

---

# 18. Funciones que reciben predicados

Una función que devuelve un `Boolean` suele utilizarse para expresar una condición.

Por ejemplo:

```scala
Int => Boolean
```

Puede representar:

```scala
numero => numero > 0
```

o:

```scala
numero => numero % 2 == 0
```

o:

```scala
numero => numero < 100
```

A este tipo de función que representa una condición se le suele llamar **predicado**.

Ejemplo:

```scala
def cumpleCondicion(
  numero: Int,
  condicion: Int => Boolean
): Boolean =
  condicion(numero)
```

---

# 19. Separar datos de comportamiento

Considere:

```scala
def calcular(
  a: Int,
  b: Int,
  operacion: (Int, Int) => Int
): Int =
  operacion(a, b)
```

Los datos son:

```text
a
b
```

El comportamiento es:

```text
operacion
```

Podemos cambiar el comportamiento sin modificar los datos ni la estructura general de la función.

Por ejemplo:

```scala
calcular(10, 4, (a, b) => a + b)

calcular(10, 4, (a, b) => a - b)

calcular(10, 4, (a, b) => a * b)
```

Esto es una forma de **abstracción**.

---

# 20. Evitar funciones repetidas

Suponga que escribimos:

```scala
def aplicarDoble(numero: Int): Int =
  numero * 2

def aplicarTriple(numero: Int): Int =
  numero * 3

def aplicarCuadrado(numero: Int): Int =
  numero * numero
```

Existe una estructura repetida.

Podemos crear una función general:

```scala
def transformar(
  numero: Int,
  operacion: Int => Int
): Int =
  operacion(numero)
```

Y utilizar:

```scala
transformar(5, numero => numero * 2)

transformar(5, numero => numero * 3)

transformar(5, numero => numero * numero)
```

La función de orden superior permite separar la estructura común del comportamiento específico.

---

# 21. Ejemplo: aplicar una comisión

Podemos crear:

```scala
def calcularMonto(
  monto: Double,
  calculo: Double => Double
): Double =
  calculo(monto)
```

Luego:

```scala
calcularMonto(
  1000.0,
  monto => monto * 0.10
)
```

Resultado:

```text
100.0
```

O:

```scala
calcularMonto(
  1000.0,
  monto => monto * 0.15
)
```

Resultado:

```text
150.0
```

---

# 22. Función de orden superior con tres parámetros normales

Podemos combinar varios datos con una función.

```scala
def procesarValores(
  a: Double,
  b: Double,
  c: Double,
  operacion: (Double, Double, Double) => Double
): Double =
  operacion(a, b, c)
```

Ejemplo:

```scala
procesarValores(
  10.0,
  20.0,
  30.0,
  (a, b, c) => a + b + c
)
```

Resultado:

```text
60.0
```

---

# 23. Tipos función dentro de parámetros

Es importante aprender a leer la firma completa.

Considere:

```scala
def transformar(
  numero: Int,
  operacion: Int => Int
): Int
```

Significa:

```text
numero
↓
Int
```

```text
operacion
↓
función que recibe Int y devuelve Int
```

Y la función `transformar` completa devuelve:

```text
Int
```

---

Otro ejemplo:

```scala
def verificar(
  texto: String,
  condicion: String => Boolean
): Boolean
```

Significa:

* `texto` es un `String`;
* `condicion` es una función `String => Boolean`;
* `verificar` devuelve `Boolean`.

---

# 24. El tipo de la función enviada debe coincidir

Si tenemos:

```scala
def transformar(
  numero: Int,
  operacion: Int => Int
): Int =
  operacion(numero)
```

podemos enviar:

```scala
numero => numero * 2
```

porque su tipo es:

```text
Int => Int
```

Pero no podemos enviar:

```scala
numero => numero > 0
```

porque su tipo es:

```text
Int => Boolean
```

y la función espera:

```text
Int => Int
```

Los tipos deben ser compatibles.

---

# 25. Función de orden superior y pureza

Una función de orden superior también puede ser pura.

Por ejemplo:

```scala
def aplicarOperacion(
  numero: Int,
  operacion: Int => Int
): Int =
  operacion(numero)
```

Si `operacion` es pura, entonces este código puede mantenerse completamente funcional.

Ejemplo:

```scala
aplicarOperacion(
  5,
  numero => numero * numero
)
```

No:

* modifica variables externas;
* imprime resultados;
* modifica archivos;
* depende de estado externo.

---

# 26. Lambdas de varias líneas como argumentos

También podemos enviar una lambda con un bloque.

```scala
def transformar(
  numero: Int,
  operacion: Int => Int
): Int =
  operacion(numero)
```

Uso:

```scala
transformar(
  10,
  numero => {
    val doble: Int = numero * 2
    val resultado: Int = doble + 5
    resultado
  }
)
```

Resultado:

```text
25
```

El resultado de la lambda corresponde a la última expresión del bloque.

---

# 27. Ejemplo completo

Considere:

```scala
def ejecutarOperacion(
  a: Double,
  b: Double,
  operacion: (Double, Double) => Double
): Double =
  operacion(a, b)
```

Podemos definir:

```scala
val sumar: (Double, Double) => Double =
  (a, b) => a + b

val restar: (Double, Double) => Double =
  (a, b) => a - b

val promedio: (Double, Double) => Double =
  (a, b) => (a + b) / 2
```

Luego:

```scala
ejecutarOperacion(20, 10, sumar)
```

Resultado:

```text
30.0
```

```scala
ejecutarOperacion(20, 10, restar)
```

Resultado:

```text
10.0
```

```scala
ejecutarOperacion(20, 10, promedio)
```

Resultado:

```text
15.0
```

---

# 28. ¿Por qué utilizar funciones de orden superior?

Las funciones de orden superior permiten:

* reutilizar código;
* evitar lógica repetida;
* separar los datos del comportamiento;
* crear funciones más generales;
* construir abstracciones;
* cambiar comportamientos sin modificar una función principal;
* aprovechar que las funciones son valores de primera clase.

---

# 29. Ejercicios de práctica

## Ejercicio 1

Desarrolle:

```scala
def transformar(
  numero: Int,
  operacion: Int => Int
): Int
```

Utilícela para:

* obtener el doble;
* obtener el triple;
* obtener el cubo.

Las operaciones deberán enviarse utilizando lambdas.

---

## Ejercicio 2

Desarrolle:

```scala
def verificar(
  numero: Int,
  condicion: Int => Boolean
): Boolean
```

Utilícela para verificar si un número:

* es positivo;
* es par;
* es múltiplo de 3.

---

## Ejercicio 3

Desarrolle:

```scala
def operar(
  a: Double,
  b: Double,
  operacion: (Double, Double) => Double
): Double
```

Utilícela para realizar:

* suma;
* resta;
* multiplicación;
* promedio.

---

## Ejercicio 4

Desarrolle:

```scala
def procesarTexto(
  texto: String,
  transformacion: String => String
): String
```

Utilícela para:

* convertir un texto a mayúsculas;
* convertir un texto a minúsculas;
* agregar `"Scala: "` al inicio del texto.

---

## Ejercicio 5

Desarrolle:

```scala
def analizarTexto(
  texto: String,
  analisis: String => Int
): Int
```

Utilícela para obtener:

* la longitud del texto;
* la cantidad de letras `a` que contiene.

---

# 30. Errores frecuentes

## Error 1. Ejecutar la función antes de enviarla

Si tenemos:

```scala
def doble(numero: Int): Int =
  numero * 2
```

y:

```scala
def transformar(
  numero: Int,
  operacion: Int => Int
): Int =
  operacion(numero)
```

queremos enviar la función:

```scala
transformar(5, doble)
```

No ejecutar primero:

```scala
transformar(5, doble(5))
```

`doble(5)` produce un `Int`, mientras que `transformar` espera una función:

```text
Int => Int
```

---

## Error 2. Enviar una función de tipo incorrecto

```scala
def transformar(
  numero: Int,
  operacion: Int => Int
): Int =
  operacion(numero)
```

No podemos enviar:

```scala
numero => numero > 0
```

porque devuelve:

```text
Boolean
```

cuando se espera:

```text
Int
```

---

## Error 3. Confundir el parámetro función con su resultado

Dentro de:

```scala
def transformar(
  numero: Int,
  operacion: Int => Int
): Int =
  operacion(numero)
```

`operacion` representa la función.

Mientras que:

```scala
operacion(numero)
```

representa el resultado de ejecutar esa función.

---

# 31. Buenas prácticas

Al utilizar funciones de orden superior:

* Utilizar nombres que expliquen el propósito del parámetro función.
* Declarar claramente los tipos función.
* Preferir lambdas pequeñas y fáciles de leer.
* Mantener las funciones puras siempre que sea posible.
* Utilizar `val` en lugar de `var`.
* Evitar repetir funciones que solo cambian una operación.
* Utilizar funciones de orden superior cuando existe una estructura común y el comportamiento puede variar.

---

# 32. Resumen

Una función normal puede recibir valores:

```scala
def sumar(a: Int, b: Int): Int =
  a + b
```

Una función de orden superior puede recibir también funciones:

```scala
def transformar(
  numero: Int,
  operacion: Int => Int
): Int =
  operacion(numero)
```

Podemos enviar una función definida:

```scala
def doble(numero: Int): Int =
  numero * 2

transformar(5, doble)
```

O una lambda:

```scala
transformar(
  5,
  numero => numero * 2
)
```

También podemos recibir funciones con varios parámetros:

```scala
def calcular(
  a: Int,
  b: Int,
  operacion: (Int, Int) => Int
): Int =
  operacion(a, b)
```

La idea fundamental de esta sesión es:

> **Una función puede recibir otra función para decidir qué comportamiento debe ejecutar.**

Esto permite crear soluciones más generales, reutilizables y flexibles, y constituye una de las herramientas fundamentales de la programación funcional.
