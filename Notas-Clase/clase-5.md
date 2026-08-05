# Clase 5: Funciones puras, inmutabilidad, efectos secundarios, modelo de sustitución y evaluación

# 1. Introducción

Uno de los principales objetivos de la programación funcional es desarrollar programas más fáciles de comprender, probar y mantener.

Para lograrlo, este paradigma promueve el uso de **funciones puras**, la **inmutabilidad** y la reducción de los **efectos secundarios**.

Estos principios permiten que el comportamiento de un programa sea más predecible, ya que una función siempre producirá el mismo resultado cuando reciba las mismas entradas.

---

# 2. Funciones puras

Una función pura es una función que cumple las siguientes propiedades:

1. Siempre devuelve el mismo resultado cuando recibe los mismos parámetros.
2. No modifica información externa ni produce efectos secundarios.

Por ejemplo:

```scala
def cuadrado(numero: Int): Int =
  numero * numero
```

Si la función recibe el valor `5`, siempre devolverá `25`.

```scala
cuadrado(5)
```

Resultado:

```text
25
```

No importa cuántas veces se ejecute.

```scala
cuadrado(5)
cuadrado(5)
cuadrado(5)
```

El resultado siempre será el mismo.

---

## Otro ejemplo

```scala
def calcularAreaRectangulo(base: Double, altura: Double): Double =
  base * altura
```

Uso:

```scala
calcularAreaRectangulo(5, 8)
```

Resultado:

```text
40.0
```

La función depende únicamente de sus parámetros.

---

# 3. Funciones impuras

Una función impura es aquella que no cumple alguna de las propiedades de una función pura.

Por ejemplo, una función que modifica una variable externa.

```scala
var contador = 0

def incrementarContador(): Int = {
  contador = contador + 1
  contador
}
```

Cada vez que se ejecuta:

```scala
incrementarContador()
```

El resultado cambia.

```text
1
2
3
4
...
```

La función depende del estado del programa.

---

Otro ejemplo corresponde a una función que imprime información.

```scala
def saludar(nombre: String): Unit =
  println(s"Hola $nombre")
```

Aunque siempre reciba el mismo nombre, produce un efecto observable al escribir en la consola.

---

# 4. Características de una función pura

Las funciones puras presentan varias ventajas.

* Son fáciles de comprender.
* Son fáciles de probar.
* Son fáciles de reutilizar.
* No dependen del estado del programa.
* Siempre producen resultados predecibles.
* Permiten razonar sobre el programa con mayor facilidad.

---

# 5. Inmutabilidad

La inmutabilidad significa que un valor no cambia después de haber sido creado.

En Scala, esto se logra utilizando `val`.

```scala
val nota = 80.0
```

Después de declarar el valor, no puede modificarse.

```scala
nota = 90.0
```

El código anterior produce un error.

Si se necesita representar un nuevo valor, simplemente se crea otro.

```scala
val notaOriginal = 80.0
val notaFinal = notaOriginal + 5
```

Ambos valores continúan existiendo.

---

## Mutabilidad

La mutabilidad permite modificar el contenido de una variable.

```scala
var contador = 10

contador = contador + 1
```

Ahora `contador` contiene otro valor.

En programación funcional se procura minimizar el uso de variables mutables.

---

## Comparación

### Enfoque mutable

```scala
var total = 100

total = total + 50
```

El valor original desaparece.

---

### Enfoque inmutable

```scala
val subtotal = 100
val total = subtotal + 50
```

El valor inicial continúa disponible.

---

# 6. Efectos secundarios

Un efecto secundario es cualquier acción realizada por una función que modifica el entorno o produce un resultado observable diferente al valor que devuelve.

Algunos ejemplos son:

* Mostrar información en pantalla.
* Leer información del teclado.
* Leer o escribir archivos.
* Modificar una base de datos.
* Enviar información por internet.
* Modificar variables externas.
* Generar números aleatorios.
* Obtener la fecha y hora actuales.

---

## Ejemplo

```scala
def mostrarMensaje(nombre: String): Unit =
  println(s"Hola $nombre")
```

El propósito principal de esta función es imprimir información.

---

En cambio, la siguiente función devuelve un valor.

```scala
def generarSaludo(nombre: String): String =
  s"Hola $nombre"
```

El resultado puede utilizarse posteriormente.

```scala
val saludo = generarSaludo("Ana")

println(saludo)
```

En este caso, la función es responsable únicamente de construir el mensaje.

La impresión se realiza fuera de la función.

---

# 7. Separación entre lógica y presentación

Una buena práctica consiste en separar los cálculos de la presentación de resultados.

Por ejemplo:

```scala
def calcularArea(base: Double, altura: Double): Double =
  base * altura
```

Posteriormente:

```scala
val area = calcularArea(5, 8)

println(s"Área: $area")
```

La función únicamente calcula el resultado.

La impresión ocurre al finalizar el programa.

Esta separación facilita las pruebas y la reutilización del código.

---

# 8. Modelo de sustitución

El modelo de sustitución es una técnica para comprender cómo se evalúan las funciones.

Consiste en reemplazar los parámetros por los valores recibidos y evaluar las expresiones paso a paso.

Considere la siguiente función.

```scala
def cuadrado(numero: Int): Int =
  numero * numero
```

Al ejecutar:

```scala
cuadrado(4)
```

Puede evaluarse de la siguiente manera.

```text
cuadrado(4)

↓

4 * 4

↓

16
```

---

## Otro ejemplo

```scala
def sumar(a: Int, b: Int): Int =
  a + b

def duplicar(numero: Int): Int =
  numero * 2
```

Al ejecutar:

```scala
duplicar(sumar(3, 2))
```

La evaluación es:

```text
duplicar(sumar(3,2))

↓

duplicar(5)

↓

5 * 2

↓

10
```

---

## Funciones con condicionales

El modelo también puede utilizarse con expresiones condicionales.

```scala
def valorAbsoluto(numero: Int): Int =
  if numero >= 0 then numero else -numero
```

Evaluación:

```text
valorAbsoluto(-7)

↓

if -7 >= 0 then -7 else 7

↓

if false then -7 else 7

↓

7
```

---

# 9. Evaluación por valor

En Scala, los parámetros de una función se evalúan por defecto mediante **evaluación por valor** (*Call by Value*).

Esto significa que el argumento se calcula antes de ejecutar la función.

Considere la función:

```scala
def cuadrado(numero: Int): Int =
  numero * numero
```

Llamada:

```scala
cuadrado(2 + 3)
```

Proceso de evaluación:

```text
cuadrado(2 + 3)

↓

cuadrado(5)

↓

25
```

Primero se evalúa la expresión `2 + 3`.

Después se ejecuta la función.

---

# 10. Evaluación por nombre

Scala también permite utilizar **evaluación por nombre** (*Call by Name*).

En este caso, el argumento no se evalúa inmediatamente.

La evaluación ocurre cada vez que el parámetro es utilizado dentro de la función.

La sintaxis utiliza `=>`.

```scala
def imprimirDosVeces(valor: => Int): Unit = {
  println(valor)
  println(valor)
}
```

Considere ahora:

```scala
def obtenerNumero(): Int = {
  println("Calculando...")
  10
}
```

Al ejecutar:

```scala
imprimirDosVeces(obtenerNumero())
```

La salida será:

```text
Calculando...
10
Calculando...
10
```

La función `obtenerNumero()` se ejecuta dos veces porque el parámetro se evalúa cada vez que es utilizado.

---

# 11. Comparación entre evaluación por valor y evaluación por nombre

| Evaluación por valor                                 | Evaluación por nombre                                 |
| ---------------------------------------------------- | ----------------------------------------------------- |
| El argumento se evalúa antes de ejecutar la función. | El argumento se evalúa cuando la función lo necesita. |
| El resultado se calcula una sola vez.                | Puede calcularse varias veces.                        |
| Es el comportamiento por defecto en Scala.           | Debe indicarse utilizando `=>`.                       |

---

# 12. Buenas prácticas

Durante el desarrollo de programas funcionales es recomendable:

* Preferir siempre `val` antes que `var`.
* Diseñar funciones pequeñas y con una única responsabilidad.
* Evitar modificar variables externas.
* Evitar imprimir información dentro de las funciones de cálculo.
* Recibir toda la información mediante parámetros.
* Declarar explícitamente los tipos de retorno.
* Utilizar nombres descriptivos para funciones y parámetros.
* Separar la lógica del programa de la presentación de resultados.

---

# Resumen

Una función pura:

* depende únicamente de sus parámetros;
* siempre produce el mismo resultado;
* no modifica información externa;
* no produce efectos secundarios.

La programación funcional promueve la **inmutabilidad**, ya que los valores no cambian después de ser creados.

Los **efectos secundarios** deben reducirse y mantenerse separados de la lógica principal del programa.

El **modelo de sustitución** permite comprender el funcionamiento de las funciones evaluando paso a paso cada expresión.

Scala utiliza por defecto **evaluación por valor**, aunque también permite **evaluación por nombre** cuando el programador lo requiere.

Aplicar estos principios permite desarrollar programas más claros, reutilizables y fáciles de mantener.
