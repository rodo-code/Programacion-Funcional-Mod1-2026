# Clase 15: Funciones que Retornan Funciones, Aplicación Parcial y Currying

# 1. Recordatorio: funciones de orden superior

En la sesión anterior vimos que una función de orden superior puede recibir otra función como parámetro.

Por ejemplo:

```scala
def aplicarOperacion(
  numero: Int,
  operacion: Int => Int
): Int =
  operacion(numero)
```

Podemos utilizarla:

```scala
aplicarOperacion(
  5,
  numero => numero * 2
)
```

Resultado:

```text
10
```

Sin embargo, una función de orden superior también puede:

> **devolver otra función como resultado.**

---

# 2. Funciones que retornan funciones

Considere la siguiente función:

```scala
def crearMultiplicador(factor: Int): Int => Int =
  numero => numero * factor
```

La función `crearMultiplicador`:

* recibe un valor de tipo `Int`;
* devuelve una función de tipo `Int => Int`.

Observe su tipo de retorno:

```scala
Int => Int
```

Esto significa que el resultado de `crearMultiplicador` no es directamente un número.

El resultado es **otra función**.

---

# 3. Utilizando una función retornada

Podemos ejecutar:

```scala
val duplicar: Int => Int =
  crearMultiplicador(2)
```

La expresión:

```scala
crearMultiplicador(2)
```

devuelve conceptualmente:

```scala
numero => numero * 2
```

Por tanto:

```scala
duplicar(5)
```

produce:

```text
10
```

También podemos crear:

```scala
val triplicar: Int => Int =
  crearMultiplicador(3)
```

Y utilizar:

```scala
triplicar(5)
```

Resultado:

```text
15
```

---

# 4. Una función puede crear diferentes funciones

Considere nuevamente:

```scala
def crearMultiplicador(factor: Int): Int => Int =
  numero => numero * factor
```

Podemos crear varias funciones:

```scala
val duplicar = crearMultiplicador(2)

val triplicar = crearMultiplicador(3)

val porDiez = crearMultiplicador(10)
```

Ahora:

```scala
duplicar(4)
```

devuelve:

```text
8
```

```scala
triplicar(4)
```

devuelve:

```text
12
```

```scala
porDiez(4)
```

devuelve:

```text
40
```

Una sola función permitió crear varios comportamientos especializados.

---

# 5. Leer tipos función como retorno

Considere:

```scala
def crearFuncion(numero: Int): Int => Int
```

La firma puede interpretarse como:

```text
Entrada:
Int

Salida:
Int => Int
```

Es decir:

> Recibe un entero y devuelve una función que recibe un entero y devuelve otro entero.

---

Otro ejemplo:

```scala
def crearComparador(limite: Int): Int => Boolean
```

Significa:

```text
Entrada:
Int

Salida:
Int => Boolean
```

La función retornada recibe un entero y devuelve un valor booleano.

---

# 6. Crear funciones de validación

Podemos escribir:

```scala
def crearComparador(limite: Int): Int => Boolean =
  numero => numero > limite
```

Luego:

```scala
val mayorQue10: Int => Boolean =
  crearComparador(10)
```

Ahora:

```scala
mayorQue10(15)
```

Resultado:

```text
true
```

Mientras que:

```scala
mayorQue10(7)
```

Resultado:

```text
false
```

También podríamos crear:

```scala
val mayorQue100 =
  crearComparador(100)
```

---

# 7. Closures

Considere:

```scala
def crearMultiplicador(factor: Int): Int => Int =
  numero => numero * factor
```

La lambda:

```scala
numero => numero * factor
```

utiliza dos valores:

* `numero`, que pertenece a la propia lambda;
* `factor`, que fue recibido por `crearMultiplicador`.

Cuando hacemos:

```scala
val duplicar =
  crearMultiplicador(2)
```

la función retornada recuerda que:

```text
factor = 2
```

Aunque la ejecución de `crearMultiplicador` ya haya terminado.

Este comportamiento se denomina:

> **Closure**

Una closure es una función que puede utilizar o recordar valores definidos en el entorno donde fue creada.

---

# 8. Ejemplo de closure

```scala
def crearSaludo(saludo: String): String => String =
  nombre => s"$saludo, $nombre"
```

Podemos crear:

```scala
val saludarFormal =
  crearSaludo("Buenos días")
```

Y ejecutar:

```scala
saludarFormal("Ana")
```

Resultado:

```text
Buenos días, Ana
```

La función `saludarFormal` recuerda:

```text
saludo = "Buenos días"
```

---

# 9. Crear funciones especializadas

Una función que retorna otra función permite tomar una operación general y crear versiones más específicas.

Ejemplo:

```scala
def aplicarPorcentaje(
  porcentaje: Double
): Double => Double =
  monto => monto * porcentaje
```

Podemos crear:

```scala
val calcular10PorCiento =
  aplicarPorcentaje(0.10)

val calcular20PorCiento =
  aplicarPorcentaje(0.20)
```

Luego:

```scala
calcular10PorCiento(500)
```

Resultado:

```text
50.0
```

Y:

```scala
calcular20PorCiento(500)
```

Resultado:

```text
100.0
```

---

# 10. Aplicación parcial

La **aplicación parcial** consiste en tomar una función que necesita varios valores y fijar algunos de ellos para obtener una función más especializada.

Considere:

```scala
def calcularPrecio(
  precio: Double,
  descuento: Double
): Double =
  precio - precio * descuento
```

La función necesita:

```text
precio
descuento
```

Podemos crear una función especializada:

```scala
val descuento10: Double => Double =
  precio => calcularPrecio(precio, 0.10)
```

Ahora el porcentaje ya está definido.

La nueva función solamente necesita:

```text
precio
```

---

# 11. Ejemplo de aplicación parcial

```scala
val descuento20: Double => Double =
  precio => calcularPrecio(precio, 0.20)
```

Podemos utilizar:

```scala
descuento20(1000)
```

Resultado:

```text
800.0
```

Hemos convertido conceptualmente:

```text
(precio, descuento)
```

en:

```text
precio
```

porque el descuento ya fue fijado.

---

# 12. Funciones con múltiples listas de parámetros

Scala permite definir funciones utilizando varias listas de parámetros.

Una función tradicional:

```scala
def sumar(a: Int, b: Int): Int =
  a + b
```

también puede escribirse como:

```scala
def sumar(a: Int)(b: Int): Int =
  a + b
```

Observe la diferencia:

```scala
sumar(a, b)
```

frente a:

```scala
sumar(a)(b)
```

La segunda forma utiliza varias listas de parámetros.

---

# 13. Currying

El proceso de transformar una función que recibe varios parámetros en una secuencia de funciones que reciben los parámetros de manera progresiva se conoce como:

> **Currying**

Ejemplo:

```scala
def sumar(a: Int)(b: Int): Int =
  a + b
```

Podemos ejecutar:

```scala
sumar(5)(3)
```

Resultado:

```text
8
```

---

# 14. Entendiendo currying paso a paso

Considere:

```scala
def sumar(a: Int)(b: Int): Int =
  a + b
```

Al ejecutar:

```scala
sumar(5)
```

todavía no tenemos el resultado final.

Conceptualmente obtenemos:

```scala
b => 5 + b
```

Es decir, una nueva función de tipo:

```text
Int => Int
```

Por tanto:

```scala
val sumarCinco: Int => Int =
  sumar(5)
```

---

# 15. Utilizando una función parcialmente aplicada

Ahora podemos utilizar:

```scala
sumarCinco(3)
```

Resultado:

```text
8
```

También:

```scala
sumarCinco(10)
```

Resultado:

```text
15
```

```scala
sumarCinco(100)
```

Resultado:

```text
105
```

La función:

```scala
sumar(5)
```

ha creado una función especializada que siempre suma `5`.

---

# 16. Ejemplo con multiplicación

```scala
def multiplicar(a: Int)(b: Int): Int =
  a * b
```

Podemos crear:

```scala
val duplicar: Int => Int =
  multiplicar(2)

val triplicar: Int => Int =
  multiplicar(3)

val multiplicarPorDiez: Int => Int =
  multiplicar(10)
```

Ahora:

```scala
duplicar(8)
```

Resultado:

```text
16
```

```scala
triplicar(8)
```

Resultado:

```text
24
```

```scala
multiplicarPorDiez(8)
```

Resultado:

```text
80
```

---

# 17. Ejemplo con descuentos

Podemos definir:

```scala
def aplicarDescuento(
  porcentaje: Double
)(
  precio: Double
): Double =
  precio - precio * porcentaje
```

Ahora podemos crear:

```scala
val descuento10: Double => Double =
  aplicarDescuento(0.10)

val descuento20: Double => Double =
  aplicarDescuento(0.20)

val descuento50: Double => Double =
  aplicarDescuento(0.50)
```

---

# 18. Utilizando las funciones de descuento

```scala
descuento10(500)
```

Resultado:

```text
450.0
```

```scala
descuento20(500)
```

Resultado:

```text
400.0
```

```scala
descuento50(500)
```

Resultado:

```text
250.0
```

La función general es:

```scala
aplicarDescuento
```

Mientras que:

```text
descuento10
descuento20
descuento50
```

son funciones especializadas.

---

# 19. Currying con Strings

También podemos trabajar con textos.

```scala
def agregarPrefijo(
  prefijo: String
)(
  texto: String
): String =
  prefijo + texto
```

Podemos crear:

```scala
val agregarError =
  agregarPrefijo("ERROR: ")

val agregarAdvertencia =
  agregarPrefijo("ADVERTENCIA: ")
```

Luego:

```scala
agregarError("Archivo no encontrado")
```

Resultado:

```text
ERROR: Archivo no encontrado
```

---

# 20. Currying con predicados

Podemos crear funciones que produzcan condiciones.

```scala
def esMayorQue(
  limite: Int
)(
  numero: Int
): Boolean =
  numero > limite
```

Podemos crear:

```scala
val mayorQue10 =
  esMayorQue(10)

val mayorQue100 =
  esMayorQue(100)
```

Ahora:

```scala
mayorQue10(15)
```

Resultado:

```text
true
```

```scala
mayorQue100(15)
```

Resultado:

```text
false
```

---

# 21. Función tradicional frente a función curried

## Función tradicional

```scala
def sumar(a: Int, b: Int): Int =
  a + b
```

Uso:

```scala
sumar(5, 3)
```

---

## Función curried

```scala
def sumar(a: Int)(b: Int): Int =
  a + b
```

Uso:

```scala
sumar(5)(3)
```

La segunda versión permite fácilmente crear funciones especializadas:

```scala
val sumarCinco =
  sumar(5)
```

---

# 22. Relación entre currying y funciones que retornan funciones

Considere:

```scala
def multiplicar(a: Int)(b: Int): Int =
  a * b
```

Conceptualmente puede entenderse como:

```scala
def multiplicar(a: Int): Int => Int =
  b => a * b
```

Ambas ideas están muy relacionadas.

En los dos casos:

```scala
multiplicar(2)
```

produce una función:

```scala
b => 2 * b
```

---

# 23. Diferencia entre aplicación parcial y currying

Los conceptos están relacionados, pero no son exactamente lo mismo.

### Currying

Consiste en estructurar una función para recibir sus parámetros en diferentes etapas.

Ejemplo:

```scala
def multiplicar(a: Int)(b: Int): Int =
  a * b
```

---

### Aplicación parcial

Consiste en proporcionar solamente una parte de los argumentos para obtener una función especializada.

Ejemplo:

```scala
val duplicar =
  multiplicar(2)
```

Aquí:

```text
2
```

ya fue proporcionado.

Todavía falta el segundo valor.

---

# 24. Ejemplo completo

Considere:

```scala
def calcularImpuesto(
  porcentaje: Double
)(
  monto: Double
): Double =
  monto * porcentaje
```

Podemos crear:

```scala
val impuesto10 =
  calcularImpuesto(0.10)

val impuesto15 =
  calcularImpuesto(0.15)

val impuesto20 =
  calcularImpuesto(0.20)
```

Luego:

```scala
impuesto10(1000)
```

Resultado:

```text
100.0
```

```scala
impuesto15(1000)
```

Resultado:

```text
150.0
```

```scala
impuesto20(1000)
```

Resultado:

```text
200.0
```

---

# 25. Ejemplo con conversión

Podemos construir:

```scala
def crearConversor(
  factor: Double
): Double => Double =
  valor => valor * factor
```

Luego:

```scala
val metrosACentimetros =
  crearConversor(100)

val kilometrosAMetros =
  crearConversor(1000)
```

Uso:

```scala
metrosACentimetros(2.5)
```

Resultado:

```text
250.0
```

---

# 26. Ejercicios de práctica

## Ejercicio 1. Crear multiplicadores

Desarrolle:

```scala
def crearMultiplicador(
  factor: Int
): Int => Int
```

Utilícela para crear:

```text
duplicar
multiplicarPorCinco
multiplicarPorDiez
```

### Ejemplos

```text
duplicar(6) = 12

multiplicarPorCinco(6) = 30

multiplicarPorDiez(6) = 60
```

---

## Ejercicio 2. Crear validadores

Desarrolle:

```scala
def crearValidadorMinimo(
  minimo: Int
): Int => Boolean
```

La función retornada deberá indicar si un valor es mayor o igual al mínimo especificado.

### Ejemplo

```scala
val mayorDeEdad =
  crearValidadorMinimo(18)
```

```text
mayorDeEdad(20) = true

mayorDeEdad(16) = false
```

---

## Ejercicio 3. Aplicar descuentos

Desarrolle utilizando currying:

```scala
def aplicarDescuento(
  porcentaje: Double
)(
  precio: Double
): Double
```

Cree las siguientes funciones:

```text
descuento5
descuento10
descuento25
```

Y utilícelas con diferentes precios.

---

## Ejercicio 4. Agregar prefijos

Desarrolle:

```scala
def agregarPrefijo(
  prefijo: String
)(
  texto: String
): String
```

Cree:

```text
mensajeError
mensajeAdvertencia
mensajeExito
```

Ejemplo:

```text
mensajeError("Archivo no encontrado")
```

Resultado:

```text
ERROR: Archivo no encontrado
```

---

## Ejercicio 5. Comparadores

Desarrolle:

```scala
def crearComparador(
  limite: Int
): Int => Boolean
```

Utilícela para crear:

```text
mayorQue10
mayorQue50
mayorQue100
```

---

# 27. Errores frecuentes

## Error 1. Pensar que la función devuelve directamente un valor numérico

Considere:

```scala
def crearMultiplicador(factor: Int): Int => Int =
  numero => numero * factor
```

La llamada:

```scala
crearMultiplicador(2)
```

no devuelve:

```text
2
```

ni devuelve directamente un resultado de multiplicación.

Devuelve:

```text
una función Int => Int
```

---

## Error 2. Olvidar ejecutar la función retornada

```scala
val duplicar =
  crearMultiplicador(2)
```

Para obtener un resultado debemos posteriormente hacer:

```scala
duplicar(5)
```

---

## Error 3. Confundir una función curried con una función tradicional

Si definimos:

```scala
def sumar(a: Int)(b: Int): Int =
  a + b
```

su uso es:

```scala
sumar(5)(3)
```

No:

```scala
sumar(5, 3)
```

---

## Error 4. Confundir la función con su resultado

```scala
val duplicar =
  multiplicar(2)
```

`duplicar` es una función.

Mientras que:

```scala
duplicar(10)
```

es el resultado de ejecutar esa función.

---

# 28. Buenas prácticas

Al trabajar con funciones que retornan funciones y currying:

* Utilizar nombres descriptivos.
* Declarar los tipos función cuando ayuden a comprender el código.
* Mantener las funciones puras siempre que sea posible.
* Utilizar `val` en lugar de `var`.
* Crear funciones especializadas cuando exista un comportamiento general reutilizable.
* Mantener las lambdas simples.
* Separar valores de configuración de valores que cambian constantemente.

---

# 29. Resumen

Una función puede devolver otra función:

```scala
def crearMultiplicador(
  factor: Int
): Int => Int =
  numero => numero * factor
```

Podemos crear:

```scala
val duplicar =
  crearMultiplicador(2)
```

Una función retornada puede recordar valores del entorno donde fue creada. Esto se conoce como:

```text
Closure
```

Una función puede escribirse utilizando múltiples listas de parámetros:

```scala
def multiplicar(a: Int)(b: Int): Int =
  a * b
```

Esto permite:

```scala
val duplicar =
  multiplicar(2)
```

El concepto de **currying** permite transformar una función con varios parámetros en una secuencia de aplicaciones.

La **aplicación parcial** permite fijar algunos valores para obtener funciones más especializadas.

La idea fundamental de esta sesión es:

> **Las funciones pueden construirse a partir de otras funciones y configurarse progresivamente para crear comportamientos especializados y reutilizables.**
