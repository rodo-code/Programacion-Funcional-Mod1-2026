# Sesión 22: Transformación funcional de listas en Scala

# 1. Transformación funcional de datos

En programación funcional una colección no se modifica directamente.

En lugar de cambiar una lista existente:

    Lista original
          |
          ↓
    Modificar elementos

se crea una nueva estructura:

    Lista original
          |
          ↓
    Transformación
          |
          ↓
    Nueva lista

Ejemplo:

``` scala
val numeros = List(1,2,3,4)

val duplicados = numeros.map(x => x * 2)
```

Resultado:

    List(2,4,6,8)

------------------------------------------------------------------------

# 2. La operación map

`map` aplica una función a cada elemento de una colección.

Ejemplo:

``` scala
val numeros = List(1,2,3)

val resultado = numeros.map(x => x + 10)
```

Resultado:

    List(11,12,13)

`map` mantiene la cantidad de elementos, pero transforma sus valores.

------------------------------------------------------------------------

# Ejercicio 1

Dada una lista de precios:

``` scala
List(50.0,100.0,150.0)
```

Crear una nueva lista donde cada precio tenga un incremento del 10%.

------------------------------------------------------------------------

# 3. La operación filter

`filter` conserva únicamente los elementos que cumplen una condición.

Ejemplo:

``` scala
val numeros = List(1,2,3,4,5,6)

val pares = numeros.filter(x => x % 2 == 0)
```

Resultado:

    List(2,4,6)

------------------------------------------------------------------------

# Ejercicio 2

Dada una lista de edades:

``` scala
List(12,18,25,15,30)
```

Obtener una nueva lista con edades mayores o iguales a 18.

------------------------------------------------------------------------

# 4. Combinando map y filter

Las operaciones pueden encadenarse:

``` scala
val resultado =
  numeros
    .filter(x => x % 2 == 0)
    .map(x => x * 10)
```

Proceso:

    List(1,2,3,4,5)
            ↓ filter
    List(2,4)
            ↓ map
    List(20,40)

El orden de las operaciones importa.

------------------------------------------------------------------------

# Ejercicio 3

Dada la lista:

``` scala
List(1,2,3,4,5,6)
```

Realizar:

1.  Obtener números pares.
2.  Multiplicar cada número restante por 3.

Resultado esperado:

    List(6,12,18)

------------------------------------------------------------------------

# 5. La operación flatMap

`flatMap` combina transformación y aplanamiento de listas.

Con `map`:

``` scala
List(1,2,3).map(x => List(x,x))
```

Resultado:

    List(List(1,1),List(2,2),List(3,3))

Con `flatMap`:

``` scala
List(1,2,3).flatMap(x => List(x,x))
```

Resultado:

    List(1,1,2,2,3,3)

------------------------------------------------------------------------

# 6. Pipelines funcionales

Un pipeline combina varias transformaciones.

Ejemplo:

``` scala
ventas
  .filter(_.precio > 100)
  .map(_.producto)
```

Cada operación tiene una responsabilidad:

    Datos originales
          ↓
    Filtrar información
          ↓
    Transformar resultado

------------------------------------------------------------------------

# 7. Relación con programación funcional

Estas operaciones siguen el modelo:

    Entrada
      ↓
    Función pura
      ↓
    Nueva salida

No se modifican los datos originales.

------------------------------------------------------------------------

# Ejercicio integrador

Considere:

``` scala
case class Producto(
  nombre:String,
  categoria:String,
  precio:Double
)
```

Implemente funciones puras para:

-   Obtener productos de una categoría específica.
-   Obtener solamente nombres.
-   Aplicar descuentos.
-   Obtener productos mayores a un precio determinado.

Restricciones:

-   Utilizar `map`.
-   Utilizar `filter`.
-   No modificar datos originales.
-   Utilizar valores inmutables.

------------------------------------------------------------------------

# Resumen

## map

Transforma elementos:

    List[A] → List[B]

## filter

Selecciona elementos:

    List[A] → List[A]

## flatMap

Transforma y aplana:

    List[A] → List[B]

La idea principal:

> Las colecciones funcionales se procesan describiendo qué
> transformación queremos aplicar, en lugar de controlar manualmente
> cada paso del recorrido.
