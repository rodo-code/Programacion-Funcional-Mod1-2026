# Clase 3: Valores, variables, tipos, operadores y expresiones en Scala

## 1. Valores y variables

Para almacenar información durante la ejecución de un programa, Scala permite declarar valores inmutables y variables mutables.

### 1.1 Valores inmutables con `val`

La palabra reservada `val` permite declarar un valor que no puede ser reasignado después de su creación.

```scala
val nombre = "Andrea"
val edad = 20
val promedio = 78.5
```

Después de declarar un valor con `val`, no es posible asignarle otro contenido:

```scala
val edad = 20
edad = 21 // Error
```

Aunque el valor original no pueda modificarse, puede utilizarse para calcular nuevos valores:

```scala
val edadActual = 20
val edadSiguiente = edadActual + 1
```

En este caso:

* `edadActual` continúa siendo `20`.
* Se crea un nuevo valor llamado `edadSiguiente`.
* `edadSiguiente` contiene `21`.

En Scala se recomienda utilizar `val` siempre que sea posible, especialmente cuando se desarrolla con un enfoque funcional.

---

### 1.2 Variables mutables con `var`

La palabra reservada `var` permite declarar una variable cuyo contenido puede ser reasignado.

```scala
var contador = 0
contador = contador + 1
```

También se puede utilizar:

```scala
contador += 1
```

La diferencia principal es:

| Declaración | ¿Puede reasignarse? | Uso recomendado                                |
| ----------- | ------------------: | ---------------------------------------------- |
| `val`       |                  No | Opción predeterminada                          |
| `var`       |                  Sí | Cuando la modificación sea realmente necesaria |

Durante el curso se preferirá el uso de `val` para reducir cambios de estado y facilitar la comprensión del programa.

---

## 2. Tipos de datos

Scala es un lenguaje de **tipado estático**. Esto significa que cada valor posee un tipo conocido antes de la ejecución del programa.

Los tipos permiten determinar:

* Qué clase de información almacena un valor.
* Qué operaciones pueden realizarse.
* Qué valores pueden combinarse.
* Qué errores deben detectarse durante la compilación.

---

### 2.1 `Int`

Representa números enteros de uso común.

```scala
val cantidad: Int = 35
val temperatura: Int = -5
val semestre: Int = 4
```

---

### 2.2 `Long`

Representa números enteros de mayor tamaño.

```scala
val poblacion: Long = 12000000L
```

La letra `L` indica que el número debe interpretarse como un valor de tipo `Long`.

---

### 2.3 `Double`

Representa números decimales y es el tipo más utilizado para cálculos con decimales.

```scala
val precio: Double = 19.99
val promedio: Double = 76.5
val altura: Double = 1.72
```

---

### 2.4 `Float`

También representa números decimales, pero tiene menor precisión que `Double`.

```scala
val temperatura: Float = 22.5F
```

La letra `F` indica que el número es de tipo `Float`.

---

### 2.5 `Boolean`

Representa valores lógicos.

Solo puede contener:

```scala
true
false
```

Ejemplos:

```scala
val estudianteActivo: Boolean = true
val tieneDescuento: Boolean = false
val aprobado: Boolean = true
```

---

### 2.6 `Char`

Representa un solo carácter y utiliza comillas simples.

```scala
val inicial: Char = 'R'
val opcion: Char = 'A'
val simbolo: Char = '#'
```

Un `Char` debe contener únicamente un carácter.

---

### 2.7 `String`

Representa una cadena de caracteres o texto y utiliza comillas dobles.

```scala
val nombre: String = "María"
val materia: String = "Programación Funcional"
val universidad: String = "Universidad"
```

La diferencia entre `Char` y `String` es:

```scala
'A'   // Char
"A"   // String
```

---

### 2.8 `Unit`

`Unit` representa la ausencia de un resultado útil.

Se utiliza normalmente en métodos que realizan una acción, pero cuyo resultado no será utilizado posteriormente.

```scala
def mostrarMensaje(): Unit = {
  println("Hola desde Scala")
}
```

`Unit` es similar a `void` en Java.

---

## 3. Inferencia de tipos

Scala puede determinar automáticamente el tipo de un valor a partir de la información asignada.

```scala
val edad = 20
val precio = 15.5
val nombre = "Lucía"
val activo = true
```

Scala infiere los siguientes tipos:

```text
edad: Int
precio: Double
nombre: String
activo: Boolean
```

Esta característica se denomina **inferencia de tipos**.

---

### 3.1 Anotación explícita de tipos

También es posible indicar directamente el tipo:

```scala
val edad: Int = 20
val precio: Double = 15.5
val nombre: String = "Lucía"
val activo: Boolean = true
```

La estructura general es:

```text
nombreDelValor: Tipo = contenido
```

Ejemplo:

```scala
val cantidadEstudiantes: Int = 30
```

---

### 3.2 Errores de tipo

El valor asignado debe ser compatible con el tipo declarado.

```scala
val edad: Int = "veinte"
```

Este código produce un error porque `"veinte"` es un `String`, no un `Int`.

Otro ejemplo incorrecto:

```scala
val aprobado: Boolean = 75
```

El número `75` no puede almacenarse directamente como un valor booleano.

La forma correcta sería construir una comparación:

```scala
val nota = 75
val aprobado = nota >= 60
```

---

### 3.3 El tipo de una variable no cambia

Incluso cuando se utiliza `var`, el tipo permanece fijo.

```scala
var cantidad: Int = 10
cantidad = 15
```

Esto es válido porque ambos valores son enteros.

En cambio:

```scala
cantidad = "quince"
```

No es válido porque `"quince"` es un texto.

---

## 4. Operadores aritméticos

Los operadores aritméticos permiten realizar cálculos numéricos.

| Operador | Operación      | Ejemplo  |
| -------- | -------------- | -------- |
| `+`      | Suma           | `10 + 5` |
| `-`      | Resta          | `10 - 5` |
| `*`      | Multiplicación | `10 * 5` |
| `/`      | División       | `10 / 5` |
| `%`      | Residuo        | `10 % 3` |

Ejemplo:

```scala
val numero1 = 10
val numero2 = 3

val suma = numero1 + numero2
val resta = numero1 - numero2
val multiplicacion = numero1 * numero2
val division = numero1 / numero2
val residuo = numero1 % numero2
```

---

### 4.1 División entera

Cuando los dos operandos son enteros, Scala produce un resultado entero.

```scala
val resultado = 10 / 3
```

El resultado es:

```text
3
```

La parte decimal se descarta.

Para obtener un resultado decimal, al menos uno de los operandos debe ser decimal:

```scala
val resultadoDecimal = 10.0 / 3
```

También puede convertirse un entero a `Double`:

```scala
val resultadoDecimal = 10.toDouble / 3
```

---

### 4.2 Operador residuo

El operador `%` devuelve el residuo de una división.

```scala
val residuo = 10 % 3
```

El resultado es:

```text
1
```

Puede utilizarse para comprobar si un número es par:

```scala
val numero = 8
val esPar = numero % 2 == 0
```

---

## 5. Operadores relacionales

Los operadores relacionales comparan valores y producen un resultado de tipo `Boolean`.

| Operador | Significado       |
| -------- | ----------------- |
| `==`     | Igual a           |
| `!=`     | Diferente de      |
| `>`      | Mayor que         |
| `<`      | Menor que         |
| `>=`     | Mayor o igual que |
| `<=`     | Menor o igual que |

Ejemplos:

```scala
val nota = 75

val esIgualASetentaYCinco = nota == 75
val esDiferenteDeCincuenta = nota != 50
val esMayorQueSesenta = nota > 60
val esMenorQueCien = nota < 100
val estaAprobado = nota >= 60
val esNotaValida = nota <= 100
```

Cada comparación produce `true` o `false`.

---

### 5.1 Diferencia entre `=` y `==`

El operador `=` se utiliza en una declaración o asignación:

```scala
val edad = 20
```

El operador `==` se utiliza para comparar:

```scala
val tieneVeinteAnios = edad == 20
```

---

## 6. Operadores lógicos

Los operadores lógicos permiten combinar o negar expresiones booleanas.

| Operador | Significado |   |          |
| -------- | ----------- | - | -------- |
| `&&`     | Y lógico    |   |          |
| `        |             | ` | O lógico |
| `!`      | Negación    |   |          |

---

### 6.1 Operador `&&`

Produce `true` cuando ambas condiciones son verdaderas.

```scala
val edad = 20
val tieneDocumento = true

val puedeIngresar = edad >= 18 && tieneDocumento
```

---

### 6.2 Operador `||`

Produce `true` cuando al menos una condición es verdadera.

```scala
val esEstudiante = true
val esDocente = false

val perteneceUniversidad = esEstudiante || esDocente
```

---

### 6.3 Operador `!`

Invierte un valor booleano.

```scala
val activo = true
val inactivo = !activo
```

El valor de `inactivo` será `false`.

---

## 7. Cadenas de texto

Los textos se representan utilizando el tipo `String`.

```scala
val nombre = "Rodolfo"
val materia = "Programación Funcional"
```

Scala ofrece diferentes operaciones para trabajar con cadenas.

---

### 7.1 Concatenación

El operador `+` permite unir textos.

```scala
val nombre = "Ana"
val saludo = "Hola, " + nombre
```

También permite combinar texto y números:

```scala
val edad = 20
val mensaje = "Tengo " + edad + " años"
```

---

### 7.2 Interpolación de cadenas

La interpolación permite insertar valores dentro de un texto de forma más clara.

```scala
val nombre = "Lucía"
val edad = 21

val mensaje = s"Mi nombre es $nombre y tengo $edad años"
```

La letra `s` antes de las comillas activa la interpolación.

También pueden incluirse expresiones:

```scala
val mensajeFuturo = s"El próximo año tendré ${edad + 1} años"
```

Cuando se utiliza una expresión, debe colocarse dentro de `${}`.

---

### 7.3 Convertir a mayúsculas

El método `toUpperCase` convierte todos los caracteres de una cadena a mayúsculas.

```scala
val materia = "Programación Funcional"
val materiaMayuscula = materia.toUpperCase
```

El resultado será:

```text
PROGRAMACIÓN FUNCIONAL
```

---

### 7.4 Convertir a minúsculas

El método `toLowerCase` convierte todos los caracteres de una cadena a minúsculas.

```scala
val universidad = "UNIVERSIDAD MAYOR"
val universidadMinuscula = universidad.toLowerCase
```

El resultado será:

```text
universidad mayor
```

---

### 7.5 Obtener el tamaño de una cadena

La propiedad `length` devuelve la cantidad de caracteres de un texto.

```scala
val nombre = "Scala"
val cantidadCaracteres = nombre.length
```

El resultado será:

```text
5
```

Los espacios también se cuentan como caracteres:

```scala
val mensaje = "Hola Scala"
val tamanioMensaje = mensaje.length
```

El resultado será `10`.

---

### 7.6 Comparar cadenas

Las cadenas también pueden compararse:

```scala
val nombre1 = "Ana"
val nombre2 = "Ana"

val sonIguales = nombre1 == nombre2
```

El resultado será `true`.

La comparación distingue mayúsculas de minúsculas:

```scala
val resultado = "Scala" == "scala"
```

El resultado será `false`.

Una forma de comparar ignorando mayúsculas y minúsculas es normalizar ambos textos:

```scala
val texto1 = "Scala"
val texto2 = "scala"

val sonIguales =
  texto1.toLowerCase == texto2.toLowerCase
```

---

## 8. Precedencia de operadores

Scala respeta un orden de prioridad al evaluar operadores.

```scala
val resultado = 2 + 3 * 4
```

Primero se realiza la multiplicación:

```text
2 + 12 = 14
```

Para cambiar el orden se utilizan paréntesis:

```scala
val resultado = (2 + 3) * 4
```

El resultado será:

```text
20
```

Cuando una expresión pueda resultar difícil de interpretar, es recomendable utilizar paréntesis.

---

## 9. Expresiones

Una expresión es una construcción que produce un valor.

Ejemplos:

```scala
2 + 3
```

Produce `5`.

```scala
10 > 4
```

Produce `true`.

```scala
"Hola " + "Scala"
```

Produce `"Hola Scala"`.

Una expresión puede asignarse a un `val`:

```scala
val resultado = 2 + 3
```

La expresión `2 + 3` se evalúa y su resultado se almacena en `resultado`.

---

## 10. Expresiones condicionales

En Scala, una estructura `if` produce un valor.

```scala
val edad = 20

val mensaje =
  if edad >= 18 then
    "Mayor de edad"
  else
    "Menor de edad"
```

El resultado de la condición se asigna directamente a `mensaje`.

También puede escribirse en una sola línea:

```scala
val mensaje =
  if edad >= 18 then "Mayor de edad" else "Menor de edad"
```

Esta forma permite evitar una variable mutable.

En lugar de escribir:

```scala
var mensaje = ""

if edad >= 18 then
  mensaje = "Mayor de edad"
else
  mensaje = "Menor de edad"
```

Es preferible escribir:

```scala
val mensaje =
  if edad >= 18 then
    "Mayor de edad"
  else
    "Menor de edad"
```

---

### 10.1 Condiciones compuestas

Pueden utilizarse operadores lógicos dentro de un `if`.

```scala
val nota = 75
val asistencia = 85

val puedeAprobar =
  nota >= 60 && asistencia >= 80
```

También puede construirse un mensaje:

```scala
val resultado =
  if nota >= 60 && asistencia >= 80 then
    "Aprobado"
  else
    "Reprobado"
```

---

### 10.2 Condiciones encadenadas

Cuando existen más de dos resultados posibles, se puede utilizar `else if`.

```scala
val num = -2

val signo =
  if num < 0 then
    -1
  else if num > 0 then
    1
  else
    0
```

Las condiciones se evalúan de arriba hacia abajo. Cuando una condición es verdadera, Scala utiliza su resultado y deja de evaluar las siguientes.

---

## 11. Bloques de expresiones

Un bloque agrupa varias expresiones entre llaves.

```scala
val area = {
  val base = 10
  val altura = 5

  base * altura
}
```

El resultado del bloque corresponde a la última expresión:

```scala
base * altura
```

Por tanto, `area` contiene `50`.

Otro ejemplo:

```scala
val total = {
  val precio = 15.5
  val cantidad = 3
  val subtotal = precio * cantidad

  subtotal
}
```

La última expresión del bloque se convierte en el valor de `total`.

---

### 11.1 Alcance dentro de un bloque

Los valores declarados dentro de un bloque solo existen dentro de ese bloque.

```scala
val resultado = {
  val numero1 = 10
  val numero2 = 5

  numero1 + numero2
}
```

Fuera del bloque no puede utilizarse directamente `numero1` ni `numero2`.

Sí puede utilizarse `resultado`, porque fue declarado fuera del bloque.

---

## 12. Conversión básica de tipos

En algunas operaciones es necesario convertir un valor a otro tipo.

### Convertir a `Double`

```scala
val numero = 10
val numeroDecimal = numero.toDouble
```

### Convertir a `Int`

```scala
val precio = 15.8
val precioEntero = precio.toInt
```

Al convertir de `Double` a `Int`, la parte decimal se descarta.

```text
15.8 → 15
```

### Convertir a `String`

```scala
val edad = 20
val edadTexto = edad.toString
```

Estas conversiones son útiles cuando se combinan cálculos o se preparan datos para mostrarlos.

---

# Ejercicio para desarrollar en clase

## Promedio de un estudiante

Desarrolla un programa en un worksheet que cumpla las siguientes instrucciones:

1. Declara el nombre de un estudiante.
2. Declara tres notas utilizando valores de tipo `Double`.
3. Calcula el promedio de las tres notas.
4. Determina si el estudiante aprobó o reprobó.
5. Considera que una nota igual o superior a `60` es aprobatoria.
6. Guarda el resultado de aprobación en un valor de tipo `Boolean`.
7. Construye un mensaje que contenga `"APROBADO"` o `"REPROBADO"`.
8. Muestra el nombre del estudiante.
9. Muestra las tres notas.
10. Muestra el promedio.
11. Muestra el resultado final.
12. Utiliza interpolación de cadenas para presentar la información.
13. Utiliza `val` en todas las declaraciones.
14. Prueba el programa con al menos tres conjuntos diferentes de notas.

---

# Ejercicio para casa

## Clasificación del nivel académico

Desarrolla un programa en un worksheet que calcule el promedio de un estudiante y lo clasifique en una de las siguientes categorías:

|                       Promedio | Categoría        |
| -----------------------------: | ---------------- |
|                 Menor que `60` | `REPROBADO`      |
| Desde `60` hasta menos de `70` | `NOTA REGULAR`   |
| Desde `70` hasta menos de `85` | `NOTA BUENA`     |
|         Desde `85` hasta `100` | `NOTA EXCELENTE` |

El programa debe cumplir las siguientes instrucciones:

1. Declarar el nombre completo del estudiante.
2. Declarar tres notas de tipo `Double`.
3. Calcular el promedio.
4. Verificar que las notas se encuentren entre `0` y `100`.
5. Determinar la categoría académica correspondiente.
6. Mostrar el nombre completo en mayúsculas.
7. Mostrar el nombre completo en minúsculas.
8. Mostrar la cantidad de caracteres que contiene el nombre.
9. Mostrar las tres notas.
10. Mostrar el promedio.
11. Mostrar la categoría obtenida.
12. Utilizar interpolación de cadenas.
13. Utilizar expresiones condicionales encadenadas.
14. Utilizar `val` en todas las declaraciones.
15. Probar el programa con un estudiante de cada categoría.
