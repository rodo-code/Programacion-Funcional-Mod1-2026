# Clase 4: Definición de funciones, parámetros, tipos de retorno, bloques y alcance

# 1. ¿Qué es una función?

Una función es un bloque de código reutilizable que recibe datos de entrada, realiza una tarea específica y produce un resultado.

Las funciones permiten evitar la repetición de código y hacen que los programas sean más fáciles de comprender, mantener y reutilizar.

Por ejemplo, si un programa necesita calcular el promedio de cien estudiantes, no es conveniente escribir la misma operación cien veces. En su lugar, se define una función y se reutiliza cada vez que sea necesario.

En matemáticas una función puede representarse como:

```text
f(x) = x + 1
```

Si el valor de entrada es:

```text
x = 5
```

Entonces el resultado será:

```text
6
```

En programación la idea es exactamente la misma: una función recibe información, realiza un procesamiento y devuelve un resultado.

---

# 2. Definición de una función

En Scala las funciones se definen utilizando la palabra reservada `def`.

La estructura general es la siguiente:

```scala
def nombreFuncion(parametros): TipoRetorno =
  expresion
```

Un ejemplo sencillo es:

```scala
def incrementar(numero: Int): Int =
  numero + 1
```

Esta función recibe un número entero y devuelve otro entero cuyo valor es una unidad mayor.

Para utilizar la función basta con invocarla indicando el valor que recibirá como entrada.

```scala
incrementar(5)
```

Resultado:

```text
6
```

Otro ejemplo:

```scala
incrementar(18)
```

Resultado:

```text
19
```

Una misma función puede utilizarse tantas veces como sea necesario.

---

# 3. Partes de una función

Consideremos nuevamente la siguiente función.

```scala
def incrementar(numero: Int): Int =
  numero + 1
```

Cada elemento tiene un propósito específico.

## 3.1 Palabra reservada `def`

```scala
def
```

Indica que se está definiendo una función.

---

## 3.2 Nombre de la función

```scala
incrementar
```

Es el identificador de la función.

Debe describir claramente la tarea que realiza.

Se recomienda utilizar nombres descriptivos como:

* calcularPromedio
* obtenerArea
* convertirMayusculas
* esPar
* saludar

---

## 3.3 Parámetros

```scala
(numero: Int)
```

Los parámetros representan la información que la función necesita para realizar su trabajo.

En este caso la función necesita un número entero.

---

## 3.4 Tipo de retorno

```scala
: Int
```

Indica el tipo de dato que devolverá la función.

En este ejemplo el resultado será un valor de tipo `Int`.

---

## 3.5 Cuerpo de la función

```scala
numero + 1
```

Corresponde al procesamiento realizado por la función.

En Scala el resultado de la última expresión constituye automáticamente el valor que devuelve la función.

---

# 4. Parámetros

Los parámetros son los datos de entrada que recibe una función.

Una función puede recibir uno o varios parámetros.

## Un parámetro

```scala
def cuadrado(numero: Int): Int =
  numero * numero
```

Uso:

```scala
cuadrado(6)
```

Resultado:

```text
36
```

---

## Dos parámetros

```scala
def sumar(a: Int, b: Int): Int =
  a + b
```

Uso:

```scala
sumar(10, 5)
```

Resultado:

```text
15
```

---

## Tres parámetros

```scala
def promedio(nota1: Double, nota2: Double, nota3: Double): Double =
  (nota1 + nota2 + nota3) / 3
```

Uso:

```scala
promedio(80, 75, 90)
```

Resultado:

```text
81.66666666666667
```

Cada parámetro posee:

* un nombre
* un tipo de dato

---

# 5. Tipo de retorno

Toda función produce un resultado.

El tipo de retorno indica qué clase de dato devolverá la función.

## Función que devuelve un entero

```scala
def doble(numero: Int): Int =
  numero * 2
```

---

## Función que devuelve un decimal

```scala
def areaRectangulo(base: Double, altura: Double): Double =
  base * altura
```

---

## Función que devuelve un valor lógico

```scala
def aprobo(nota: Double): Boolean =
  nota >= 60
```

---

## Función que devuelve un texto

```scala
def saludar(nombre: String): String =
  s"Hola $nombre"
```

---

# 6. Inferencia del tipo de retorno

Scala puede determinar automáticamente el tipo que devuelve una función.

Por ejemplo:

```scala
def triple(numero: Int) =
  numero * 3
```

Scala infiere que el resultado es de tipo `Int`.

Aunque esta forma es válida, durante el curso se recomienda indicar siempre el tipo de retorno.

```scala
def triple(numero: Int): Int =
  numero * 3
```

Esta práctica mejora la legibilidad del código y facilita la detección de errores.

---

# 7. Funciones que devuelven valores booleanos

Es muy frecuente que una función devuelva únicamente `true` o `false`.

Ejemplo:

```scala
def esMayorDeEdad(edad: Int): Boolean =
  edad >= 18
```

Uso:

```scala
esMayorDeEdad(20)
```

Resultado:

```text
true
```

Otro ejemplo:

```scala
esMayorDeEdad(15)
```

Resultado:

```text
false
```

Estas funciones suelen utilizarse posteriormente en estructuras condicionales.

---

# 8. Bloques

Cuando una función requiere varias instrucciones, se utilizan llaves para formar un bloque.

```scala
def promedio(nota1: Double, nota2: Double, nota3: Double): Double = {
  val suma = nota1 + nota2 + nota3
  val promedio = suma / 3

  promedio
}
```

Dentro del bloque pueden declararse nuevos valores y realizar cálculos intermedios.

---

## La última expresión

En Scala la última expresión del bloque es el valor que devuelve la función.

En el ejemplo anterior:

```scala
promedio
```

es el resultado que devuelve la función.

No es necesario escribir:

```scala
return promedio
```

De hecho, en programación funcional se evita el uso de `return` siempre que sea posible.

Otro ejemplo:

```scala
def areaRectangulo(base: Double, altura: Double): Double = {
  val area = base * altura

  area
}
```

La última expresión del bloque es `area`, por lo tanto ese será el resultado de la función.

---

# 9. Alcance (Scope)

El alcance determina dónde puede utilizarse un valor.

Los valores únicamente existen dentro del bloque donde fueron declarados.

Consideremos el siguiente ejemplo.

```scala
val universidad = "UPB"

def mostrarInformacion(): Unit = {
  val carrera = "Ingeniería de Sistemas Computacionales"

  println(universidad)
  println(carrera)
}
```

Dentro de la función pueden utilizarse tanto `universidad` como `carrera`.

Sin embargo, fuera de la función solamente existe `universidad`.

El siguiente código produce un error.

```scala
println(carrera)
```

Porque `carrera` fue declarada dentro de la función y dejó de existir cuando terminó el bloque.

---

## Alcance dentro de un bloque

El alcance también aplica para bloques internos.

```scala
def ejemplo(numero: Int): String = {
  if numero >= 60 then {
    val mensaje = "Aprobado"

    mensaje
  }
  else {
    val mensaje = "Reprobado"

    mensaje
  }
}
```

Cada variable `mensaje` solamente existe dentro del bloque donde fue declarada.

---

# 10. Variables con el mismo nombre

Es posible declarar una variable con el mismo nombre dentro de otro bloque.

```scala
val numero = 10

def ejemplo(): Unit = {
  val numero = 20

  println(numero)
}
```

Dentro de la función, el valor utilizado será `20`.

El valor externo queda oculto mientras se ejecuta ese bloque.

Aunque esta práctica es válida, generalmente se recomienda utilizar nombres diferentes para facilitar la lectura del programa.

---

# 11. Buenas prácticas

Durante este curso se recomienda seguir las siguientes reglas.

* Utilizar nombres descriptivos para las funciones.
* Preferir siempre `val` antes que `var`.
* Declarar explícitamente el tipo de retorno.
* Escribir una función para cada tarea específica.
* Evitar funciones demasiado largas.
* Mantener nombres claros para los parámetros.

---

# Ejercicio para desarrollar en clase

Desarrolla un programa que cumpla las siguientes instrucciones.

1. Crear una función que calcule el doble de un número entero.
2. Crear una función que calcule el triple de un número entero.
3. Crear una función que reciba la base y la altura de un rectángulo y devuelva su área.
4. Crear una función que reciba tres notas y calcule el promedio.
5. Crear una función que reciba un promedio y determine si el estudiante aprobó considerando una nota mínima de `60`.
6. Utilizar las funciones anteriores para evaluar al menos tres estudiantes diferentes.
7. Mostrar para cada estudiante el promedio obtenido y si aprobó o reprobó.
8. Utilizar únicamente valores declarados con `val`.

---

# Ejercicio para casa

Desarrolla un programa que permita generar un reporte académico.

El programa debe cumplir las siguientes instrucciones.

1. Crear una función para calcular el promedio de tres notas.
2. Crear una función que determine la categoría académica del estudiante según el promedio obtenido.
3. Considerar las siguientes categorías:

|                   Promedio | Categoría      |
| -------------------------: | -------------- |
|               Menor que 60 | REPROBADO      |
| Desde 60 hasta menos de 70 | NOTA REGULAR   |
| Desde 70 hasta menos de 85 | NOTA BUENA     |
|         Desde 85 hasta 100 | NOTA EXCELENTE |

4. Crear una función que reciba el nombre del estudiante, el promedio y la categoría, y genere un mensaje descriptivo.
5. Probar el programa con al menos cinco estudiantes diferentes.
6. Mostrar todos los resultados utilizando interpolación de cadenas.
7. Utilizar únicamente valores declarados con `val`.

---

# Preguntas de repaso

1. ¿Qué es una función?
2. ¿Para qué sirve una función?
3. ¿Qué palabra reservada se utiliza para definir funciones en Scala?
4. ¿Qué representan los parámetros?
5. ¿Qué indica el tipo de retorno?
6. ¿Qué devuelve una función cuando no se utiliza `return`?
7. ¿Qué es un bloque?
8. ¿Qué determina el alcance de una variable?
9. ¿Puede utilizarse una variable declarada dentro de una función desde fuera de ella?
10. ¿Por qué se recomienda declarar explícitamente el tipo de retorno de una función durante el aprendizaje?

---

# Resumen

```scala
def calcularPromedio(nota1: Double, nota2: Double, nota3: Double): Double = {
  val suma = nota1 + nota2 + nota3
  val promedio = suma / 3

  promedio
}

def aprobo(promedio: Double): Boolean =
  promedio >= 60
```

Las ideas principales de esta clase son:

* Una función encapsula una tarea reutilizable.
* Las funciones pueden recibir parámetros.
* Toda función produce un resultado.
* El tipo de retorno indica el tipo del resultado producido.
* La última expresión de una función corresponde al valor que devuelve.
* Los bloques permiten organizar cálculos más complejos.
* Las variables solo existen dentro del bloque donde fueron declaradas.
* Se recomienda utilizar `val` y declarar explícitamente el tipo de retorno de las funciones.
