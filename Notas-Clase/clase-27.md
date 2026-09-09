# Clase 27: Listas inmutables y procesamiento básico en Scala

## Estructura conceptual

Esta sesión introduce las listas como estructuras de datos inmutables y prepara el camino para operaciones funcionales como `map`, `filter` y `fold`.

Conceptos principales:

- Listas inmutables.
- Construcción con `Nil` y `::`.
- Cabeza y cola (`head` y `tail`).
- Pattern matching sobre listas.
- Recursión sobre listas.
- Transformación y filtrado manual de datos.

---

# Listas inmutables

Una lista representa una secuencia ordenada de elementos del mismo tipo.

```scala
val numeros = List(10, 20, 30)
```

Las listas en Scala son inmutables. Esto significa que una lista creada no cambia después de su creación.

Cuando necesitamos una modificación, generamos una nueva lista.

```scala
val nuevosNumeros = 0 :: numeros
```

La lista original permanece igual.

---

# Construcción de listas

Una lista puede representarse mediante dos elementos fundamentales:

- `Nil`: lista vacía.
- `::`: agrega un elemento al inicio.

Ejemplo:

```scala
val numeros = 1 :: 2 :: 3 :: Nil
```

Representación conceptual:

```
1 :: (2 :: (3 :: Nil))
```

---

# Cabeza y cola

Una lista puede dividirse en:

- `head`: primer elemento.
- `tail`: resto de la lista.

Ejemplo:

```scala
val numeros = List(10,20,30)
```

Resultado:

```
head = 10
tail = List(20,30)
```

---

# Pattern matching sobre listas

Una lista tiene dos posibilidades:

```text
Lista vacía
Lista con elementos
```

Por eso podemos escribir:

```scala
lista match
  case Nil =>
    ...

  case cabeza :: cola =>
    ...
```

Cuando usamos:

```scala
case cabeza :: cola
```

Scala separa la lista en su primer elemento y el resto.

---

# Recursión sobre listas

El procesamiento de listas sigue normalmente este patrón:

1. Resolver el caso base.
2. Procesar un elemento.
3. Llamar recursivamente con el resto.

Ejemplo:

```scala
def sumar(lista: List[Int]): Int =
  lista match
    case Nil =>
      0

    case cabeza :: cola =>
      cabeza + sumar(cola)
```

---

# Ejercicio 1

Implemente funciones que permitan:

- Generar un string con los elementos de una lista
- Encontrar el menor elemento de una lista

---

# Transformación de listas

Una transformación crea una nueva lista sin modificar la original.

Ejemplo:

```scala
def duplicar(lista: List[Int]): List[Int] =
  lista match
    case Nil =>
      Nil

    case cabeza :: cola =>
      (cabeza * 2) :: duplicar(cola)
```

---

# Ejercicio 2

Implemente una función que reciba una lista de enteros y devuelva una nueva lista donde cada valor haya sido dividido por 10.

---

# Filtrado de listas

Filtrar significa conservar únicamente elementos que cumplen una condición.

Ejemplo conceptual:

```
Lista original
      ↓
Aplicar condición
      ↓
Nueva lista con elementos válidos
```

Ejemplo:

```scala
def pares(lista: List[Int]): List[Int] =
  lista match
    case Nil =>
      Nil

    case cabeza :: cola =>
      if cabeza % 2 == 0 then
        cabeza :: pares(cola)
      else
        pares(cola)
```

---

# Ejercicio 3

Implemente una función que reciba una lista de enteros y un límite. Debe devolver una nueva lista con los valores mayores al límite.

---

# Operaciones básicas de listas

Scala incluye operaciones comunes:

## length

Obtiene la cantidad de elementos.

```scala
lista.length
```

## contains

Comprueba si existe un elemento.

```scala
lista.contains(valor)
```

## isEmpty

Comprueba si la lista está vacía.

```scala
lista.isEmpty
```

---

# Relación con programación funcional

El procesamiento de listas utiliza:

```
Inmutabilidad
      ↓
Funciones puras
      ↓
Recursión
      ↓
Nuevas estructuras de datos
```

Más adelante estas operaciones serán abstraídas mediante:

- `map`
- `filter`
- `flatMap`
- `foldLeft`

---

# Ejercicio integrador

Dada la siguiente estructura:

```scala
case class Venta(
  producto: String,
  precio: Double
)
```

Implemente funciones puras para:

- Calcular el total de ventas.
- Obtener ventas mayores a un valor determinado.
- Crear una lista con los nombres de productos.

Restricciones:

- Utilizar únicamente `val`.
- No modificar listas existentes.
- Resolver utilizando procesamiento funcional.
