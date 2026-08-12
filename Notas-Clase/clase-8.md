# Clase 8: Recursión de cola, acumuladores y `@tailrec`

# 1. Introducción

En una función recursiva tradicional, después de realizar una llamada recursiva pueden quedar operaciones pendientes.

Por ejemplo:

```scala
def factorial(numero: Int): Int =
  if numero == 0 then
    1
  else
    numero * factorial(numero - 1)
```

Al evaluar:

```scala
factorial(4)
```

se obtiene:

```text
factorial(4)
→ 4 * factorial(3)
→ 4 * (3 * factorial(2))
→ 4 * (3 * (2 * factorial(1)))
→ 4 * (3 * (2 * (1 * factorial(0))))
```

La multiplicación no puede completarse inmediatamente porque primero debe obtenerse el resultado de cada llamada recursiva.

Esto significa que existen **operaciones pendientes**.

La recursión de cola busca evitar esta situación.

---

# 2. ¿Qué es la recursión de cola?

Una función posee **recursión de cola** (*tail recursion*) cuando la llamada recursiva es la última operación que realiza la función.

Después de la llamada recursiva no debe quedar ninguna operación pendiente.

Ejemplo:

```scala
def cuentaRegresiva(numero: Int): Unit =
  if numero <= 0 then
    println("Fin")
  else
    println(numero)
    cuentaRegresiva(numero - 1)
```

La última operación es:

```scala
cuentaRegresiva(numero - 1)
```

No existe ningún cálculo pendiente después de esa llamada.

---

# 3. Recursión tradicional frente a recursión de cola

Considere nuevamente:

```scala
def factorial(numero: Int): Int =
  if numero == 0 then
    1
  else
    numero * factorial(numero - 1)
```

La llamada recursiva aparece dentro de:

```scala
numero * factorial(numero - 1)
```

Después de obtener el resultado de `factorial(numero - 1)`, todavía debe realizarse una multiplicación.

Por esta razón, esta versión **no es recursión de cola**.

---

## Comparación conceptual

### Recursión tradicional

```text
factorial(4)
↓
4 * factorial(3)
↓
4 * 3 * factorial(2)
↓
4 * 3 * 2 * factorial(1)
↓
4 * 3 * 2 * 1 * factorial(0)
↓
Resolver operaciones pendientes
```

### Recursión de cola

```text
factorial(4, 1)
↓
factorial(3, 4)
↓
factorial(2, 12)
↓
factorial(1, 24)
↓
factorial(0, 24)
↓
24
```

En la segunda versión, el resultado parcial se transporta durante las llamadas.

---

# 4. Acumuladores

Un **acumulador** es un parámetro utilizado para almacenar el resultado parcial de un cálculo.

En lugar de dejar una operación pendiente, el resultado se calcula antes de realizar la siguiente llamada recursiva.

Por ejemplo, para calcular:

$4! = 4 \times 3 \times 2 \times 1$

puede utilizarse inicialmente:

```text
acumulador = 1
```

Después:

```text
numero = 4
acumulador = 1

↓

numero = 3
acumulador = 4

↓

numero = 2
acumulador = 12

↓

numero = 1
acumulador = 24

↓

numero = 0
acumulador = 24
```

Cuando se alcanza el caso base, el acumulador contiene directamente la respuesta.

---

# 5. Factorial con recursión de cola

Una posible implementación es:

```scala
def factorial(numero: Int): Int = {

  def factorialAux(numero: Int, acumulador: Int): Int =
    if numero == 0 then
      acumulador
    else
      factorialAux(numero - 1, acumulador * numero)

  factorialAux(numero, 1)
}
```

La función auxiliar recibe:

* `numero`: valor que todavía debe procesarse.
* `acumulador`: resultado parcial.

La llamada:

```scala
factorialAux(numero - 1, acumulador * numero)
```

es la última operación realizada.

Por tanto, la función es recursiva de cola.

---

# 6. Funciones auxiliares internas

Una función recursiva de cola suele requerir parámetros adicionales que no deberían formar parte de la interfaz utilizada por quien llama a la función.

Por ejemplo, no sería conveniente obligar al usuario a escribir:

```scala
factorial(5, 1)
```

El valor inicial del acumulador es un detalle interno del algoritmo.

Por esta razón puede utilizarse una función auxiliar:

```scala
def factorial(numero: Int): Int = {

  def factorialAux(numero: Int, acumulador: Int): Int =
    if numero == 0 then
      acumulador
    else
      factorialAux(numero - 1, acumulador * numero)

  factorialAux(numero, 1)
}
```

Desde el exterior solamente se utiliza:

```scala
factorial(5)
```

Resultado:

```text
120
```

La función auxiliar solamente existe dentro de `factorial`.

---

# 7. Seguimiento del factorial

Considere:

```scala
factorial(4)
```

La función auxiliar inicia con:

```text
factorialAux(4, 1)
```

Primera llamada:

```text
factorialAux(3, 4)
```

Segunda:

```text
factorialAux(2, 12)
```

Tercera:

```text
factorialAux(1, 24)
```

Cuarta:

```text
factorialAux(0, 24)
```

Se alcanza el caso base y se devuelve:

```text
24
```

No existen multiplicaciones pendientes.

---

# 8. La anotación `@tailrec`

Scala proporciona la anotación:

```scala
@tailrec
```

para verificar que una función realmente utiliza recursión de cola.

Debe importarse desde:

```scala
import scala.annotation.tailrec
```

Ejemplo:

```scala
import scala.annotation.tailrec

def factorial(numero: Int): Int = {

  @tailrec
  def factorialAux(numero: Int, acumulador: Int): Int =
    if numero == 0 then
      acumulador
    else
      factorialAux(numero - 1, acumulador * numero)

  factorialAux(numero, 1)
}
```

Si la función no cumple las condiciones necesarias para ser recursiva de cola, el compilador generará un error.

---

# 9. Ejemplo de uso incorrecto de `@tailrec`

Considere:

```scala
import scala.annotation.tailrec

@tailrec
def factorial(numero: Int): Int =
  if numero == 0 then
    1
  else
    numero * factorial(numero - 1)
```

Esta función no puede verificarse como recursiva de cola.

La razón es que después de:

```scala
factorial(numero - 1)
```

todavía debe realizarse:

```scala
numero *
```

La llamada recursiva no es la última operación.

---

# 10. Suma con acumulador

Considere una función tradicional:

```scala
def sumarHasta(numero: Int): Int =
  if numero == 0 then
    0
  else
    numero + sumarHasta(numero - 1)
```

Esta versión deja sumas pendientes.

Una versión con acumulador puede escribirse como:

```scala
import scala.annotation.tailrec

def sumarHasta(numero: Int): Int = {

  @tailrec
  def sumarAux(numero: Int, acumulador: Int): Int =
    if numero == 0 then
      acumulador
    else
      sumarAux(numero - 1, acumulador + numero)

  sumarAux(numero, 0)
}
```

Para:

```scala
sumarHasta(4)
```

se obtiene:

```text
sumarAux(4, 0)
→ sumarAux(3, 4)
→ sumarAux(2, 7)
→ sumarAux(1, 9)
→ sumarAux(0, 10)
→ 10
```

---

# 11. Potencia con recursión de cola

La potencia tradicional puede escribirse:

```scala
def potencia(base: Int, exponente: Int): Int =
  if exponente == 0 then
    1
  else
    base * potencia(base, exponente - 1)
```

En esta versión existe una multiplicación pendiente.

Una versión con acumulador sería:

```scala
import scala.annotation.tailrec

def potencia(base: Int, exponente: Int): Int = {

  @tailrec
  def potenciaAux(exponente: Int, acumulador: Int): Int =
    if exponente == 0 then
      acumulador
    else
      potenciaAux(exponente - 1, acumulador * base)

  potenciaAux(exponente, 1)
}
```

Ejemplo:

```text
potencia(2, 4)

potenciaAux(4, 1)
→ potenciaAux(3, 2)
→ potenciaAux(2, 4)
→ potenciaAux(1, 8)
→ potenciaAux(0, 16)
→ 16
```

---

# 12. Máximo Común Divisor

No todas las funciones recursivas necesitan un acumulador.

El algoritmo de Euclides puede escribirse:

```scala
import scala.annotation.tailrec

@tailrec
def mcd(a: Int, b: Int): Int =
  if b == 0 then
    a
  else
    mcd(b, a % b)
```

La llamada:

```scala
mcd(b, a % b)
```

es la última operación realizada.

Por tanto, esta función ya es recursiva de cola.

Ejemplo:

```text
mcd(48, 18)

→ mcd(18, 12)
→ mcd(12, 6)
→ mcd(6, 0)
→ 6
```

---

# 13. No toda recursión de cola necesita acumuladores

Los acumuladores son una herramienta frecuente para transformar funciones recursivas tradicionales.

Sin embargo, no son un requisito obligatorio.

Por ejemplo:

```scala
@tailrec
def mcd(a: Int, b: Int): Int =
  if b == 0 then
    a
  else
    mcd(b, a % b)
```

No utiliza acumulador.

La característica importante es que:

> La llamada recursiva sea la última operación.

---

# 14. ¿Por qué es importante la recursión de cola?

En una recursión tradicional, el programa puede necesitar mantener información sobre llamadas anteriores.

Por ejemplo:

```text
factorial(4)
4 * factorial(3)

factorial(3)
3 * factorial(2)

factorial(2)
2 * factorial(1)

...
```

Cada llamada tiene operaciones pendientes.

Cuando el número de llamadas es muy grande, puede consumirse una cantidad considerable de memoria de la pila de ejecución.

Esto puede provocar un:

```text
StackOverflowError
```

---

# 15. Optimización de llamadas recursivas

Cuando una función es recursiva de cola, Scala puede optimizar su ejecución.

Conceptualmente, en lugar de conservar todas las llamadas anteriores:

```text
llamada 1
  llamada 2
    llamada 3
      llamada 4
```

puede reutilizar el mismo espacio de ejecución para continuar con los nuevos parámetros.

Por este motivo, las funciones recursivas de cola pueden ejecutar una gran cantidad de llamadas sin incrementar continuamente el tamaño de la pila.

La anotación:

```scala
@tailrec
```

permite verificar que esta optimización pueda aplicarse.

---

# 16. Identificación de recursión de cola

Considere:

```scala
def funcion(numero: Int): Int =
  if numero == 0 then
    0
  else
    funcion(numero - 1)
```

La llamada recursiva es la última operación.

Es recursión de cola.

---

Considere ahora:

```scala
def funcion(numero: Int): Int =
  if numero == 0 then
    0
  else
    1 + funcion(numero - 1)
```

Después de la llamada recursiva todavía debe realizarse:

```text
+ 1
```

Por tanto, no es recursión de cola.

---

Otro ejemplo:

```scala
def funcion(numero: Int): Int =
  if numero == 0 then
    1
  else {
    val resultado = funcion(numero - 1)
    resultado * numero
  }
```

La llamada recursiva tampoco es la última operación.

Después deben realizarse otras operaciones.

---

# 17. Acumuladores como estado explícito

En programación imperativa, un resultado parcial puede almacenarse utilizando una variable mutable:

```scala
var resultado = 1
```

Posteriormente:

```scala
resultado = resultado * numero
```

En programación funcional se puede representar ese estado mediante un parámetro:

```scala
factorialAux(numero, acumulador)
```

Cada llamada recibe un nuevo valor del acumulador:

```text
acumulador = 1
↓
acumulador = 4
↓
acumulador = 12
↓
acumulador = 24
```

No se modifica el acumulador anterior.

Se crea un nuevo valor para la siguiente llamada.

Esto mantiene la **inmutabilidad**.

---

# 18. Más de un acumulador

Algunos problemas necesitan conservar más de una pieza de información.

Por ejemplo, para calcular Fibonacci de manera eficiente pueden transportarse los dos últimos términos.

Una posible implementación es:

```scala
import scala.annotation.tailrec

def fibonacci(numero: Int): Int = {

  @tailrec
  def fibonacciAux(
    restante: Int,
    anterior: Int,
    actual: Int
  ): Int =
    if restante == 0 then
      anterior
    else
      fibonacciAux(
        restante - 1,
        actual,
        anterior + actual
      )

  fibonacciAux(numero, 0, 1)
}
```

Ejemplo conceptual:

```text
fibonacci(6)

restante   anterior   actual

6          0          1
5          1          1
4          1          2
3          2          3
2          3          5
1          5          8
0          8          13
```

Resultado:

```text
8
```

Esta versión evita recalcular repetidamente los mismos términos.

---

# 19. Fibonacci tradicional frente a Fibonacci de cola

La versión recursiva tradicional:

```scala
def fibonacci(numero: Int): Int =
  if numero <= 1 then
    numero
  else
    fibonacci(numero - 1) + fibonacci(numero - 2)
```

realiza múltiples llamadas.

Por ejemplo:

```text
fib(5)
├── fib(4)
│   ├── fib(3)
│   └── fib(2)
└── fib(3)
    ├── fib(2)
    └── fib(1)
```

Existen cálculos repetidos.

La versión con acumuladores transporta los resultados necesarios:

```text
(0,1)
→ (1,1)
→ (1,2)
→ (2,3)
→ (3,5)
→ (5,8)
```

Esto reduce significativamente la cantidad de trabajo.

---

# 20. Estructura típica de una función recursiva de cola

Un patrón frecuente es:

```scala
def funcion(datos): Resultado = {

  @tailrec
  def auxiliar(
    datosRestantes,
    acumulador
  ): Resultado =
    if casoBase then
      acumulador
    else
      auxiliar(
        nuevosDatos,
        nuevoAcumulador
      )

  auxiliar(datosIniciales, valorInicial)
}
```

Los elementos principales son:

1. Función pública.
2. Función auxiliar.
3. Caso base.
4. Acumulador.
5. Actualización del problema.
6. Actualización del acumulador.
7. Llamada recursiva como última operación.

---

# 21. Cómo transformar una función a recursión de cola

Considere una función recursiva tradicional.

```scala
def factorial(numero: Int): Int =
  if numero == 0 then
    1
  else
    numero * factorial(numero - 1)
```

Para transformarla:

### Paso 1

Identificar la operación pendiente:

```text
numero *
```

### Paso 2

Crear un acumulador para almacenar el resultado de esa operación.

```text
acumulador
```

### Paso 3

Realizar el cálculo antes de la llamada recursiva.

```scala
acumulador * numero
```

### Paso 4

Pasar el nuevo resultado a la siguiente llamada.

```scala
factorialAux(numero - 1, acumulador * numero)
```

### Paso 5

Cuando se alcance el caso base, devolver directamente el acumulador.

```scala
if numero == 0 then acumulador
```

---

# 22. Comparación final

| Recursión tradicional                                     | Recursión de cola                                               |
| --------------------------------------------------------- | --------------------------------------------------------------- |
| Puede dejar operaciones pendientes                        | No deja operaciones pendientes                                  |
| Puede necesitar conservar llamadas anteriores             | Puede optimizarse reutilizando la ejecución                     |
| No siempre utiliza acumuladores                           | Frecuentemente utiliza acumuladores                             |
| La llamada recursiva puede estar dentro de otra expresión | La llamada recursiva debe ser la última operación               |
| Puede producir `StackOverflowError` con entradas grandes  | Puede evitar el crecimiento de la pila cuando Scala la optimiza |

---

# 23. Buenas prácticas

Al desarrollar funciones recursivas de cola:

* Utilizar `val` antes que `var`.
* Mantener funciones puras siempre que sea posible.
* Utilizar funciones auxiliares para ocultar acumuladores.
* Dar nombres descriptivos a los acumuladores.
* Identificar claramente el valor inicial del acumulador.
* Garantizar que cada llamada se acerque al caso base.
* Utilizar `@tailrec` para verificar la función.
* Evitar colocar operaciones después de la llamada recursiva.
* Declarar explícitamente tipos de parámetros y retorno.

---

# Resumen

Una función es **recursiva de cola** cuando la llamada recursiva es la última operación realizada.

```text
Recursión tradicional

operación + llamadaRecursiva(...)
             ↓
     quedan operaciones pendientes
```

```text
Recursión de cola

llamadaRecursiva(nuevosDatos, nuevoAcumulador)
             ↓
       última operación
```

Los **acumuladores** permiten transportar resultados parciales sin utilizar variables mutables.

Las funciones auxiliares permiten mantener esos acumuladores como detalles internos de implementación.

Scala proporciona:

```scala
import scala.annotation.tailrec
```

y:

```scala
@tailrec
```

para comprobar que una función realmente cumple las condiciones necesarias para la optimización de recursión de cola.

La idea fundamental es:

> En lugar de esperar el resultado de una llamada recursiva para continuar calculando, la recursión de cola realiza el cálculo primero y transporta el resultado hacia la siguiente llamada.
