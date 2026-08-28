# Parte 2: Sistema de Reservas de Cine

Desarrolle un sistema sencillo para representar funciones de cine y reservas.

El ejercicio deberá utilizar:

- clases;
- objetos acompañantes;
- métodos de fábrica;
- método `apply`;
- atributos inmutables con `val`;
- métodos que devuelvan nuevos objetos en lugar de modificar los existentes.

---

## 1. Clase `Pelicula`

Cree la clase:

```scala
class Pelicula(
  val titulo: String,
  val duracionMinutos: Int,
  val clasificacion: String
)
```

Cree también su objeto acompañante:

```scala
object Pelicula
```

El objeto acompañante deberá implementar:

```scala
def apply(
  titulo: String,
  duracionMinutos: Int,
  clasificacion: String
): Pelicula
```

La duración de una película no podrá ser menor o igual a `0`.

Si se recibe una duración inválida, deberá utilizarse:

```text
90 minutos
```

### Ejemplo

```scala
val pelicula =
  Pelicula(
    "Interstellar",
    169,
    "PG-13"
  )
```

---

## 2. Clase `FuncionCine`

Cree la clase:

```scala
class FuncionCine(
  val pelicula: Pelicula,
  val sala: Int,
  val hora: String,
  val precioEntrada: Double,
  val asientosDisponibles: Int
)
```

Cree su objeto acompañante:

```scala
object FuncionCine
```

El objeto deberá contener un método:

```scala
def apply(
  pelicula: Pelicula,
  sala: Int,
  hora: String,
  precioEntrada: Double,
  asientosDisponibles: Int
): FuncionCine
```

Se deberán aplicar las siguientes reglas:

```text
precioEntrada >= 0
asientosDisponibles >= 0
sala >= 1
```

Si alguno de estos valores es inválido, deberá utilizarse:

```text
precioEntrada = 0.0
asientosDisponibles = 0
sala = 1
```

---

## 3. Método `puedeReservar`

Dentro de `FuncionCine`, implemente:

```scala
def puedeReservar(
  cantidad: Int
): Boolean
```

El método deberá devolver `true` cuando existan suficientes asientos disponibles.

### Ejemplo

```scala
funcion.puedeReservar(4)
```

Si existen `20` asientos disponibles:

```text
true
```

---

## 4. Método `costoReserva`

Implemente:

```scala
def costoReserva(
  cantidad: Int
): Double
```

El método deberá calcular:

```text
cantidad de entradas × precio de cada entrada
```

### Ejemplo

Si:

```text
Precio = 35 Bs
Cantidad = 4
```

el resultado será:

```text
140 Bs
```

---

## 5. Método `reservar`

Implemente:

```scala
def reservar(
  cantidad: Int
): FuncionCine
```

Si existen suficientes asientos, deberá devolver una nueva `FuncionCine` con la cantidad de asientos actualizada.

El objeto original no deberá modificarse.

Si no existen suficientes asientos, deberá devolver el objeto con la misma cantidad de asientos disponibles.

### Ejemplo

Estado inicial:

```text
Asientos disponibles = 20
```

Operación:

```scala
val funcion2 =
  funcion1.reservar(4)
```

Resultado:

```text
funcion1.asientosDisponibles = 20
funcion2.asientosDisponibles = 16
```

---

## 6. Método `descripcion`

Implemente:

```scala
def descripcion: String
```

El resultado deberá contener:

```text
Película
Sala
Hora
Precio
Asientos disponibles
```

### Ejemplo

```text
Interstellar
Sala: 3
Hora: 20:30
Precio: 35.0 Bs
Asientos disponibles: 20
```

---

## 7. Método de fábrica `funcionEstandar`

Dentro del objeto acompañante:

```scala
object FuncionCine
```

cree:

```scala
def funcionEstandar(
  pelicula: Pelicula,
  sala: Int,
  hora: String
): FuncionCine
```

Este método deberá crear una función utilizando automáticamente:

```text
Precio: 30 Bs
Asientos disponibles: 50
```

### Ejemplo

```scala
val funcion =
  FuncionCine.funcionEstandar(
    pelicula,
    2,
    "18:30"
  )
```

---

## 8. Objetos de prueba

Cree:

```scala
val pelicula1 =
  Pelicula(
    "Dune",
    155,
    "PG-13"
  )
```

Luego:

```scala
val funcion1 =
  FuncionCine(
    pelicula1,
    3,
    "20:30",
    35.0,
    40
  )
```

Cree también:

```scala
val funcion2 =
  FuncionCine.funcionEstandar(
    pelicula1,
    1,
    "16:00"
  )
```

---

## 9. Pruebas requeridas

Ejecute:

```scala
println(
  funcion1.descripcion
)
```

Compruebe:

```scala
println(
  funcion1.puedeReservar(5)
)
```

Calcule:

```scala
println(
  funcion1.costoReserva(5)
)
```

Realice una reserva:

```scala
val funcionReservada =
  funcion1.reservar(5)
```

Compruebe:

```scala
println(
  funcion1.asientosDisponibles
)

println(
  funcionReservada.asientosDisponibles
)
```

Resultado esperado:

```text
40
35
```

---

## 10. Restricciones

- Utilizar únicamente `val`.
- No utilizar `var`.
- Crear objetos utilizando los objetos acompañantes.
- Utilizar `apply`.
- Utilizar al menos un método de fábrica adicional.
- No modificar objetos existentes.
- Los cambios deberán devolver nuevos objetos.
- Los métodos de cálculo no deberán utilizar `println`.
- Todos los parámetros deberán declarar explícitamente su tipo.
- Todos los métodos deberán declarar explícitamente su tipo de retorno.
