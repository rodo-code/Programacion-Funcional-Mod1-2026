# Clase 18: Objetos Acompañantes, Métodos de Fábrica, Métodos como Operadores y Representación Funcional de Datos

---

# 1. Recordatorio: clases y objetos

En la sesión anterior vimos que una clase define la estructura y comportamiento de los objetos.

Por ejemplo:

```scala
class Persona(
  val nombre: String,
  val edad: Int
)
```

Podemos crear una instancia:

```scala
val persona =
  new Persona("Ana", 20)
```

Aquí:

```text
Persona
```

es la clase.

Mientras que:

```text
persona
```

es una instancia de la clase.

---

# 2. ¿Qué es un `object` en Scala?

Scala permite declarar:

```scala
object
```

Un `object` representa un objeto que tiene **una única instancia** durante la ejecución del programa.

Por ejemplo:

```scala
object Configuracion {
  val nombreAplicacion: String = "Mi Sistema"
  val version: String = "1.0"
}
```

Podemos acceder directamente a sus valores:

```scala
Configuracion.nombreAplicacion
```

Resultado:

```text
Mi Sistema
```

También:

```scala
Configuracion.version
```

Resultado:

```text
1.0
```

No necesitamos escribir:

```scala
new Configuracion(...)
```

El objeto ya existe.

---

# 3. `class` frente a `object`

Considere:

```scala
class Usuario(
  val nombre: String
)
```

Podemos crear muchas instancias:

```scala
val usuario1 =
  new Usuario("Ana")

val usuario2 =
  new Usuario("Carlos")

val usuario3 =
  new Usuario("Luis")
```

En cambio:

```scala
object Configuracion
```

representa una sola instancia.

## Comparación

| `class`                                     | `object`                             |
| ------------------------------------------- | ------------------------------------ |
| Define una plantilla                        | Define una única instancia           |
| Puede tener muchos objetos                  | Existe una sola instancia            |
| Normalmente se instancia con `new`          | No necesita `new`                    |
| Cada instancia puede tener datos diferentes | Sus datos pertenecen al único objeto |

---

# 4. Ejemplo de `object`

Podemos utilizar un `object` para agrupar funciones relacionadas.

```scala
object Calculadora {

  def sumar(a: Int, b: Int): Int =
    a + b

  def restar(a: Int, b: Int): Int =
    a - b
}
```

Uso:

```scala
Calculadora.sumar(10, 5)
```

Resultado:

```text
15
```

Y:

```scala
Calculadora.restar(10, 5)
```

Resultado:

```text
5
```

No necesitamos crear:

```scala
new Calculadora()
```

---

# 5. ¿Qué es un objeto acompañante?

Un **objeto acompañante** o *companion object* es un `object` que tiene el mismo nombre que una `class`.

Por ejemplo:

```scala
class Producto(
  val nombre: String,
  val precio: Double
)

object Producto {

}
```

Aquí tenemos:

```text
class Producto
object Producto
```

La clase y el objeto son compañeros.

---

# 6. Regla de los objetos acompañantes

Para que una clase y un objeto sean considerados acompañantes:

* deben tener el mismo nombre;
* deben declararse en el mismo archivo fuente.

Ejemplo:

```scala
class Cuenta(
  val saldo: Double
)

object Cuenta {

}
```

`class Cuenta` y `object Cuenta` forman una pareja.

---

# 7. ¿Para qué sirve un objeto acompañante?

Un companion object suele utilizarse para contener elementos relacionados con la clase, pero que no pertenecen a una instancia específica.

Por ejemplo:

* métodos para crear objetos;
* constantes relacionadas con la clase;
* validaciones;
* valores predeterminados;
* métodos de utilidad relacionados con ese tipo.

Ejemplo:

```scala
class Producto(
  val nombre: String,
  val precio: Double
)

object Producto {

  val IVA: Double = 0.13
}
```

Podemos consultar:

```scala
Producto.IVA
```

sin necesidad de tener un objeto `Producto`.

---

# 8. Métodos de fábrica

Un **método de fábrica** es una función cuya responsabilidad es crear y devolver objetos.

Por ejemplo:

```scala
class Usuario(
  val nombre: String,
  val edad: Int
)

object Usuario {

  def crear(
    nombre: String,
    edad: Int
  ): Usuario =
    new Usuario(nombre, edad)
}
```

Ahora podemos crear:

```scala
val usuario =
  Usuario.crear("Ana", 20)
```

En lugar de:

```scala
val usuario =
  new Usuario("Ana", 20)
```

---

# 9. ¿Por qué usar métodos de fábrica?

Un método de fábrica permite controlar cómo se construyen los objetos.

Por ejemplo, podemos aplicar reglas antes de crear el objeto.

```scala
class Producto(
  val nombre: String,
  val precio: Double
)

object Producto {

  def crear(
    nombre: String,
    precio: Double
  ): Producto =
    if precio < 0 then
      new Producto(nombre, 0.0)
    else
      new Producto(nombre, precio)
}
```

Ahora:

```scala
val producto =
  Producto.crear("Monitor", -500)
```

producirá un producto cuyo precio será:

```text
0.0
```

La lógica de creación está centralizada.

---

# 10. Diferentes métodos de fábrica

Un mismo companion object puede ofrecer diferentes maneras de construir objetos.

```scala
class Cuenta(
  val titular: String,
  val saldo: Double
)

object Cuenta {

  def crear(
    titular: String,
    saldo: Double
  ): Cuenta =
    new Cuenta(titular, saldo)

  def crearVacia(
    titular: String
  ): Cuenta =
    new Cuenta(titular, 0.0)
}
```

Uso:

```scala
val cuenta1 =
  Cuenta.crear(
    "Ana",
    1000.0
  )
```

Y:

```scala
val cuenta2 =
  Cuenta.crearVacia("Carlos")
```

En el segundo caso:

```text
saldo = 0.0
```

---

# 11. El método especial `apply`

Scala posee un método especial denominado:

```scala
apply
```

Cuando un objeto tiene un método `apply`, podemos ejecutarlo utilizando directamente el nombre del objeto.

Ejemplo:

```scala
object Calculadora {

  def apply(
    a: Int,
    b: Int
  ): Int =
    a + b
}
```

Podemos escribir:

```scala
Calculadora(5, 3)
```

Scala interpreta esto como:

```scala
Calculadora.apply(5, 3)
```

Resultado:

```text
8
```

---

# 12. `apply` como método de fábrica

Una práctica muy común es utilizar `apply` dentro de un companion object para crear objetos.

```scala
class Persona(
  val nombre: String,
  val edad: Int
)

object Persona {

  def apply(
    nombre: String,
    edad: Int
  ): Persona =
    new Persona(nombre, edad)
}
```

Ahora podemos crear:

```scala
val persona =
  Persona("Ana", 20)
```

En lugar de:

```scala
val persona =
  new Persona("Ana", 20)
```

Scala ejecutará:

```scala
Persona.apply("Ana", 20)
```

---

# 13. Ventaja de utilizar `apply`

La creación de objetos queda más limpia:

```scala
val producto =
  Producto("Monitor", 1200)
```

en lugar de:

```scala
val producto =
  new Producto("Monitor", 1200)
```

Además, el companion object puede controlar la lógica de creación.

---

# 14. Constructor privado

Podemos impedir que una clase sea creada directamente utilizando `new`.

Por ejemplo:

```scala
class Cuenta private (
  val titular: String,
  val saldo: Double
)
```

Ahora esto no estará permitido desde cualquier parte del programa:

```scala
new Cuenta("Ana", 1000)
```

Podemos controlar la creación desde su companion object:

```scala
object Cuenta {

  def apply(
    titular: String,
    saldo: Double
  ): Cuenta =
    new Cuenta(
      titular,
      if saldo < 0 then 0.0 else saldo
    )
}
```

Ahora los objetos deben crearse utilizando:

```scala
val cuenta =
  Cuenta("Ana", 1000)
```

---

# 15. ¿Por qué controlar la construcción?

En aplicaciones reales puede ser importante impedir que existan objetos inválidos.

Por ejemplo, no queremos:

```text
precio negativo
edad negativa
saldo inicial inválido
cantidad negativa
```

El método de fábrica puede verificar estas condiciones.

---

# 16. Ejemplo de objeto válido

```scala
class Producto private (
  val nombre: String,
  val precio: Double
)

object Producto {

  def apply(
    nombre: String,
    precio: Double
  ): Producto =
    new Producto(
      nombre,
      if precio >= 0 then precio else 0.0
    )
}
```

Uso:

```scala
val p1 =
  Producto("Teclado", 250)

val p2 =
  Producto("Monitor", -100)
```

Entonces:

```text
p1.precio = 250
p2.precio = 0
```

---

# 17. Métodos como operadores

En Scala, muchos operadores son en realidad métodos.

Por ejemplo:

```scala
5 + 3
```

puede interpretarse conceptualmente como:

```scala
5.+(3)
```

El operador:

```text
+
```

es un método.

Esto significa que podemos definir métodos con nombres como:

```text
+
-
*
/
```

dentro de nuestras propias clases.

---

# 18. Ejemplo: clase `Punto`

```scala
class Punto(
  val x: Int,
  val y: Int
) {

  def +(otro: Punto): Punto =
    new Punto(
      x + otro.x,
      y + otro.y
    )
}
```

Ahora podemos crear:

```scala
val p1 =
  new Punto(2, 3)

val p2 =
  new Punto(4, 5)
```

Y escribir:

```scala
val p3 =
  p1 + p2
```

Scala interpreta:

```scala
p1.+(p2)
```

---

# 19. Resultado del operador `+`

Para:

```text
p1 = (2, 3)
p2 = (4, 5)
```

obtenemos:

```text
p3 = (6, 8)
```

El objeto `p1` no cambia.

El objeto `p2` tampoco cambia.

Se crea un nuevo `Punto`.

Esto combina:

* orientación a objetos;
* métodos;
* operadores;
* inmutabilidad.

---

# 20. Operador de resta

También podemos definir:

```scala
class Punto(
  val x: Int,
  val y: Int
) {

  def +(otro: Punto): Punto =
    new Punto(
      x + otro.x,
      y + otro.y
    )

  def -(otro: Punto): Punto =
    new Punto(
      x - otro.x,
      y - otro.y
    )
}
```

Ahora:

```scala
val resultado =
  p1 - p2
```

es equivalente a:

```scala
p1.-(p2)
```

---

# 21. Métodos con sintaxis infija

Scala permite escribir algunos métodos utilizando sintaxis similar a operadores.

Por ejemplo:

```scala
class Numero(
  val valor: Int
) {

  def sumar(otro: Numero): Numero =
    new Numero(valor + otro.valor)
}
```

Podemos ejecutar normalmente:

```scala
n1.sumar(n2)
```

En ciertos casos también podemos encontrar sintaxis infija como:

```scala
n1 sumar n2
```

Sin embargo, en código moderno de Scala se recomienda utilizar esta sintaxis con moderación y priorizar la legibilidad.

---

# 22. Operadores deben representar operaciones naturales

Aunque Scala permite crear nombres de métodos como:

```text
+
-
*
/
```

no significa que debamos utilizarlos para cualquier operación.

Por ejemplo:

```scala
producto + descuento
```

podría resultar confuso si `+` realmente significa "aplicar descuento".

Los operadores deberían utilizarse cuando su significado sea intuitivo.

Ejemplos adecuados:

```text
Vector + Vector
Punto + Punto
Fraccion + Fraccion
Dinero + Dinero
```

---

# 23. Ejemplo: Fracción

Podemos representar fracciones:

```scala
class Fraccion(
  val numerador: Int,
  val denominador: Int
) {

  def +(otra: Fraccion): Fraccion =
    new Fraccion(
      numerador * otra.denominador +
        otra.numerador * denominador,
      denominador * otra.denominador
    )
}
```

Ahora:

```scala
val f1 =
  new Fraccion(1, 2)

val f2 =
  new Fraccion(1, 4)
```

Podemos escribir:

```scala
val resultado =
  f1 + f2
```

---

# 24. Representación funcional de datos

Hasta ahora hemos utilizado clases para representar datos.

En programación funcional buscamos que estos datos sean preferentemente:

* inmutables;
* predecibles;
* fáciles de transformar;
* independientes de cambios externos.

Por ejemplo:

```scala
class Cuenta(
  val saldo: Double
)
```

representa un estado inmutable.

---

# 25. Representar cambios sin mutación

Suponga:

```scala
class Cuenta(
  val saldo: Double
)
```

Si queremos depositar dinero, podríamos modificar el saldo utilizando `var`.

Pero el enfoque funcional consiste en devolver una nueva cuenta.

```scala
class Cuenta(
  val saldo: Double
) {

  def depositar(
    monto: Double
  ): Cuenta =
    new Cuenta(saldo + monto)
}
```

Uso:

```scala
val cuenta1 =
  new Cuenta(1000)

val cuenta2 =
  cuenta1.depositar(500)
```

Resultado:

```text
cuenta1.saldo = 1000
cuenta2.saldo = 1500
```

---

# 26. Estado como una secuencia de valores

En programación imperativa podemos pensar:

```text
cuenta
↓ modificar
cuenta
↓ modificar
cuenta
```

El mismo objeto cambia.

En programación funcional podemos pensar:

```text
cuenta1
↓ operación
cuenta2
↓ operación
cuenta3
```

Cada estado es representado mediante un nuevo valor.

---

# 27. Ejemplo: Carrito de compra

```scala
class Carrito(
  val total: Double,
  val cantidadProductos: Int
) {

  def agregarProducto(
    precio: Double
  ): Carrito =
    new Carrito(
      total + precio,
      cantidadProductos + 1
    )
}
```

Uso:

```scala
val carrito1 =
  new Carrito(0, 0)

val carrito2 =
  carrito1.agregarProducto(100)

val carrito3 =
  carrito2.agregarProducto(250)
```

Tenemos:

```text
carrito1 → total 0, productos 0

carrito2 → total 100, productos 1

carrito3 → total 350, productos 2
```

Ningún carrito fue modificado.

---

# 28. Utilizar companion object con datos inmutables

Podemos mejorar el ejemplo anterior:

```scala
class Carrito private (
  val total: Double,
  val cantidadProductos: Int
) {

  def agregarProducto(
    precio: Double
  ): Carrito =
    Carrito(
      total + precio,
      cantidadProductos + 1
    )
}
```

Companion object:

```scala
object Carrito {

  def apply(
    total: Double,
    cantidadProductos: Int
  ): Carrito =
    new Carrito(
      total,
      cantidadProductos
    )

  def vacio: Carrito =
    new Carrito(0.0, 0)
}
```

Ahora podemos crear:

```scala
val carrito =
  Carrito.vacio
```

---

# 29. Integración de los conceptos

Podemos combinar:

* clase;
* companion object;
* `apply`;
* métodos de fábrica;
* operadores;
* inmutabilidad.

Ejemplo:

```scala
class Dinero private (
  val monto: Double
) {

  def +(otro: Dinero): Dinero =
    Dinero(monto + otro.monto)

  def -(otro: Dinero): Dinero =
    Dinero(monto - otro.monto)
}
```

Companion object:

```scala
object Dinero {

  def apply(
    monto: Double
  ): Dinero =
    new Dinero(
      if monto >= 0 then monto else 0
    )

  def cero: Dinero =
    Dinero(0)
}
```

---

# 30. Utilizando `Dinero`

```scala
val precio1 =
  Dinero(100)

val precio2 =
  Dinero(50)

val total =
  precio1 + precio2
```

Resultado:

```text
total.monto = 150
```

También:

```scala
val diferencia =
  precio1 - precio2
```

Resultado:

```text
diferencia.monto = 50
```

---

# 31. ¿Para qué sirve esto en un futuro proyecto de software?

Estos conceptos aparecen frecuentemente cuando diseñamos aplicaciones reales.

## Objetos acompañantes

Pueden utilizarse para centralizar la creación y configuración de objetos.

Por ejemplo:

```scala
Usuario(...)
Producto(...)
Pedido(...)
Factura(...)
```

En lugar de distribuir la lógica de creación por todo el programa.

---

## Métodos de fábrica

Permiten garantizar que los objetos sean construidos correctamente.

Por ejemplo, en un sistema de ventas:

```text
Producto
```

podría exigir:

```text
precio >= 0
stock >= 0
nombre no vacío
```

En lugar de repetir estas validaciones cada vez que se crea un producto, se pueden centralizar.

---

## Operadores

Son útiles cuando creamos tipos de datos donde ciertas operaciones tienen significado natural.

Por ejemplo:

```text
Dinero + Dinero
Vector + Vector
Fecha + Días
Fraccion + Fraccion
```

Esto puede hacer que el código sea más expresivo.

---

## Datos inmutables

En aplicaciones reales la inmutabilidad ayuda a:

* reducir errores producidos por modificaciones inesperadas;
* facilitar las pruebas;
* comprender mejor el flujo de datos;
* trabajar con concurrencia de forma más segura;
* mantener estados anteriores;
* implementar sistemas donde los cambios deben ser predecibles.

---

# 32. Ejemplo dentro de un sistema real

Imagine un sistema de comercio electrónico.

Podemos tener:

```text
Producto
Cliente
Carrito
Pedido
Pago
```

Un carrito podría representarse de manera inmutable.

```scala
val carrito1 =
  Carrito.vacio

val carrito2 =
  carrito1.agregarProducto(100)

val carrito3 =
  carrito2.agregarProducto(250)
```

Cada operación genera un nuevo estado.

Esto puede facilitar:

* registrar cambios;
* realizar pruebas;
* revertir operaciones;
* detectar errores;
* mantener el sistema predecible.

---

# 33. Diseño orientado a dominio

Cuando construimos software, intentamos que el código represente conceptos del problema real.

Por ejemplo:

```scala
class Producto
class Cuenta
class Pedido
class Dinero
class Cliente
```

Sus métodos representan operaciones relacionadas con esos conceptos.

Por ejemplo:

```scala
pedido.agregarProducto(...)
```

o:

```scala
saldo + deposito
```

Esto permite que el código sea más cercano al dominio del problema.

---

# 34. Buenas prácticas con companion objects

Al utilizar objetos acompañantes:

* mantener dentro de ellos operaciones relacionadas con la creación del tipo;
* utilizar métodos de fábrica para validar la construcción;
* evitar convertir el companion object en un contenedor de funciones no relacionadas;
* utilizar `apply` cuando represente una forma natural de crear objetos;
* mantener responsabilidades claras.

---

# 35. Buenas prácticas con operadores

Al definir operadores:

* utilizar operadores que tengan significado natural;
* evitar nombres confusos;
* mantener las operaciones puras cuando sea posible;
* devolver nuevos objetos en lugar de modificar los existentes.

Por ejemplo:

```scala
punto1 + punto2
```

debería crear otro punto, no modificar `punto1`.

---

# 36. Buenas prácticas para representar datos funcionalmente

* Utilizar `val` para los atributos.
* Evitar `var` cuando sea posible.
* Evitar métodos que modifiquen el objeto actual.
* Crear nuevos objetos para representar cambios.
* Mantener métodos pequeños.
* Evitar efectos secundarios dentro de las operaciones principales.
* Centralizar validaciones importantes.

---

# 37. Ejercicios de práctica

## Ejercicio 1. Cuenta bancaria

Cree una clase inmutable:

```scala
class Cuenta(
  val titular: String,
  val saldo: Double
)
```

Cree un companion object:

```scala
object Cuenta
```

La cuenta deberá construirse utilizando:

```scala
Cuenta("Ana", 1000)
```

El companion object deberá garantizar que el saldo inicial nunca sea negativo.

Si se recibe un saldo negativo, se deberá utilizar:

```text
0.0
```

La clase deberá incluir:

```scala
def depositar(monto: Double): Cuenta
```

y:

```scala
def retirar(monto: Double): Cuenta
```

Ambos métodos deberán devolver una nueva cuenta sin modificar la original.

---

## Ejercicio 2. Punto cartesiano

Cree:

```scala
class Punto(
  val x: Int,
  val y: Int
)
```

Implemente los operadores:

```scala
+
-
```

El operador `+` deberá sumar las coordenadas de dos puntos.

El operador `-` deberá restarlas.

### Ejemplo

```text
Punto(2, 3) + Punto(4, 1)

Resultado:

Punto(6, 4)
```

Utilice un companion object con `apply` para crear los puntos sin escribir `new`.

---

## Ejercicio 3. Producto

Cree una clase:

```scala
class Producto(
  val nombre: String,
  val precio: Double,
  val stock: Int
)
```

Utilice un companion object para garantizar:

```text
precio >= 0
stock >= 0
```

Cree un método:

```scala
def agregarStock(
  cantidad: Int
): Producto
```

que devuelva un nuevo producto.

También:

```scala
def aplicarDescuento(
  porcentaje: Double
): Producto
```

que devuelva un nuevo producto con el precio actualizado.

El producto original debe permanecer sin cambios.

---

## Ejercicio 4. Fracción

Cree una clase:

```scala
class Fraccion(
  val numerador: Int,
  val denominador: Int
)
```

El denominador nunca podrá ser `0`.

Utilice un companion object para controlar la creación de objetos.

Implemente:

```scala
def +(otra: Fraccion): Fraccion
```

y:

```scala
def -(otra: Fraccion): Fraccion
```

### Ejemplo

```text
1/2 + 1/4 = 6/8
```

No es obligatorio simplificar la fracción resultante.

---

## Ejercicio 5. Carrito de compras

Cree una clase inmutable:

```scala
class Carrito(
  val total: Double,
  val cantidadProductos: Int
)
```

El companion object deberá contener:

```scala
def vacio: Carrito
```

para crear:

```text
total = 0
cantidadProductos = 0
```

La clase deberá implementar:

```scala
def agregarProducto(
  precio: Double
): Carrito
```

El método deberá devolver un nuevo carrito.

### Ejemplo

```text
Carrito inicial:
total = 0
productos = 0

Agregar producto de 100:
total = 100
productos = 1

Agregar producto de 250:
total = 350
productos = 2
```

Los carritos anteriores deben mantener sus valores originales.

---

# 38. Reto adicional: Dinero

Cree una clase:

```scala
class Dinero(
  val monto: Double
)
```

Utilice un companion object con `apply`.

No permita montos negativos.

Implemente:

```scala
+
```

para sumar dos cantidades de dinero.

Implemente:

```scala
-
```

para restarlas.

El resultado nunca deberá tener un monto menor que `0`.

### Ejemplo

```text
Dinero(100) + Dinero(50)
= Dinero(150)

Dinero(100) - Dinero(30)
= Dinero(70)

Dinero(50) - Dinero(100)
= Dinero(0)
```

---

# 39. Resumen

Un `object` representa una única instancia:

```scala
object Configuracion
```

Una clase puede tener un objeto acompañante:

```scala
class Producto(...)

object Producto {
  ...
}
```

Los companion objects pueden contener métodos de fábrica.

El método:

```scala
apply
```

permite utilizar:

```scala
Producto(...)
```

en lugar de:

```scala
new Producto(...)
```

Scala también permite definir métodos como operadores:

```scala
def +(otro: Punto): Punto
```

y utilizar:

```scala
p1 + p2
```

En programación funcional preferimos representar los cambios creando nuevos objetos:

```text
estado1
↓ operación
estado2
↓ operación
estado3
```

en lugar de modificar continuamente el mismo objeto.

La idea fundamental de esta sesión es:

> **Scala permite diseñar tipos de datos expresivos, controlando su construcción mediante objetos acompañantes y métodos de fábrica, definiendo operaciones naturales mediante métodos-operadores y representando los cambios mediante nuevos valores inmutables.**
