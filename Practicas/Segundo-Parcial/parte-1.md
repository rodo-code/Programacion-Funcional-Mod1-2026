# Parte 1: Funciones de Orden Superior en Scala

Esta guía contiene 10 ejercicios progresivos para practicar:

- Funciones como valores de primera clase.
- Funciones anónimas o lambda.
- Funciones que reciben otras funciones como parámetros.
- Funciones que retornan funciones.
- Aplicación parcial.
- Currificación.
- Composición de funciones.
- Abstracción de operaciones repetitivas.
- Funciones genéricas de sumatoria, producto, transformación y búsqueda.

---

## Ejercicio 1. Operaciones como valores

Defina tres funciones utilizando `val`:

```scala
val duplicar: Int => Int
val cuadrado: Int => Int
val esPar: Int => Boolean
```

Utilícelas con diferentes valores.

### Ejemplos

```text
duplicar(5) = 10
cuadrado(4) = 16
esPar(8) = true
```

**Temas:** funciones como valores de primera clase, tipos función.

---

## Ejercicio 2. Lambdas matemáticas

Defina utilizando únicamente **funciones lambda**:

```scala
val cubo: Int => Int
val promedio: (Double, Double) => Double
val mayor: (Int, Int) => Int
val esMultiploDeTres: Int => Boolean
```

No utilice `def` para estas funciones.

**Temas:** funciones anónimas, lambdas, tipos función.

---

## Ejercicio 3. Aplicar una transformación

Implemente:

```scala
def transformar(
  numero: Int,
  operacion: Int => Int
): Int
```

Utilice la función para:

- duplicar un número;
- calcular su cuadrado;
- sumar `10`;
- obtener su valor negativo.

### Ejemplos

```text
transformar(5, x => x * 2) = 10

transformar(5, x => x * x) = 25
```

**Temas:** funciones como parámetros, lambdas.

---

## Ejercicio 4. Calculadora flexible

Implemente:

```scala
def calcular(
  a: Double,
  b: Double,
  operacion: (Double, Double) => Double
): Double
```

Utilícela para realizar:

- suma;
- resta;
- multiplicación;
- división;
- promedio.

Todas las operaciones deberán enviarse como funciones.

**Temas:** funciones de orden superior, abstracción de operaciones repetitivas.

---

## Ejercicio 5. Crear multiplicadores

Implemente una función que retorne otra función:

```scala
def crearMultiplicador(
  factor: Int
): Int => Int
```

A partir de ella cree:

```scala
val duplicar
val triplicar
val porDiez
```

### Ejemplos

```text
duplicar(7) = 14

triplicar(7) = 21

porDiez(7) = 70
```

**Temas:** funciones que retornan funciones, closures.

---

## Ejercicio 6. Aplicación parcial con descuentos

Considere:

```scala
def aplicarDescuento(
  precio: Double,
  porcentaje: Double
): Double
```

Cree funciones especializadas:

```scala
val descuento10: Double => Double
val descuento20: Double => Double
val descuento50: Double => Double
```

Cada función deberá fijar previamente el porcentaje correspondiente.

### Ejemplos

```text
descuento10(500) = 450

descuento20(500) = 400
```

**Temas:** aplicación parcial, especialización de funciones.

---

## Ejercicio 7. Currificación de operaciones

Implemente utilizando currificación:

```scala
def elevar(
  exponente: Int
)(
  base: Int
): Int
```

A partir de ella cree:

```scala
val cuadrado
val cubo
val cuartaPotencia
```

### Ejemplos

```text
cuadrado(5) = 25

cubo(3) = 27

cuartaPotencia(2) = 16
```

**Temas:** currificación, aplicación parcial.

---

## Ejercicio 8. Composición de funciones

Defina:

```scala
val limpiar: String => String
val convertirMinusculas: String => String
val longitud: String => Int
val esLongitudValida: Int => Boolean
```

Luego componga las funciones para construir:

```scala
val validarTexto: String => Boolean
```

La función deberá:

1. eliminar espacios al inicio y al final;
2. convertir el texto a minúsculas;
3. obtener su longitud;
4. determinar si tiene al menos `5` caracteres.

Utilice `andThen` o `compose`.

**Temas:** composición de funciones, compatibilidad de tipos.

---

## Ejercicio 9. Sumatoria y producto genéricos

Implemente:

```scala
def sumar(
  desde: Int,
  hasta: Int,
  transformar: Int => Int
): Int
```

La función deberá sumar los valores transformados desde `desde` hasta `hasta`.

Utilícela para calcular:

```text
1 + 2 + 3 + ... + n

1² + 2² + 3² + ... + n²

1³ + 2³ + 3³ + ... + n³
```

Luego implemente una función equivalente:

```scala
def producto(
  desde: Int,
  hasta: Int,
  transformar: Int => Int
): Int
```

### Ejemplos

```text
sumar(1, 4, x => x) = 10

sumar(1, 3, x => x * x) = 14

producto(1, 4, x => x) = 24
```

**Temas:** funciones genéricas de sumatoria y producto, abstracción funcional.

---

## Ejercicio 10. Transformación y búsqueda genérica

Implemente las siguientes funciones utilizando recursión y funciones de orden superior:

```scala
def transformarTexto(
  texto: String,
  transformacion: Char => Char
): String
```

y:

```scala
def buscarCaracter(
  texto: String,
  condicion: Char => Boolean
): Boolean
```

`transformarTexto` deberá aplicar una función a cada carácter.

`buscarCaracter` deberá determinar si existe al menos un carácter que cumpla una condición.

### Ejemplos

```text
transformarTexto("scala", c => c.toUpper) = "SCALA"

buscarCaracter(
  "Scala2026",
  c => c.isDigit
) = true

buscarCaracter(
  "Scala",
  c => c == 'z'
) = false
```

**Temas:** transformación genérica, búsqueda genérica, funciones como parámetros, abstracción de algoritmos repetitivos.
