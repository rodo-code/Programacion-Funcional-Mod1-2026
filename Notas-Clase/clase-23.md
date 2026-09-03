# Clase 23: Jerarquía de Tipos en Scala

# 1. Introducción

Scala posee una jerarquía de tipos.

Esto significa que los tipos del lenguaje están relacionados entre sí.

Una representación simplificada es:

```text
                    Any
                  /     \
            AnyVal       AnyRef
           /  |  \          |
        Int Double ...   Clases y objetos
                           |
                         Null

                    Nothing
                 subtipo de todos
```

La jerarquía permite que un valor de un tipo más específico pueda ser utilizado en ciertos lugares donde se espera un tipo más general.

---

# 2. El tipo `Any`

`Any` es el tipo más general de Scala.

Todos los tipos de Scala son subtipos de `Any`.

Por ejemplo:

```text
Int
Double
Boolean
String
Persona
Producto
```

todos pertenecen, directa o indirectamente, a `Any`.

Ejemplo:

```scala
val dato1: Any = 10

val dato2: Any = "Scala"

val dato3: Any = true
```

Todos son válidos porque:

```text
Int <: Any
String <: Any
Boolean <: Any
```

La notación:

```text
A <: B
```

se lee como:

> A es subtipo de B.

---

# 3. ¿Para qué sirve `Any`?

`Any` permite representar valores de diferentes tipos bajo un tipo común.

Ejemplo:

```scala
def mostrarDato(
  dato: Any
): String =
  s"Dato recibido: $dato"
```

Podemos utilizar:

```scala
mostrarDato(10)

mostrarDato("Scala")

mostrarDato(true)
```

La función acepta todos estos valores porque todos son subtipos de `Any`.

---

# 4. Limitaciones de utilizar `Any`

Aunque `Any` puede contener valores de cualquier tipo, pierde información específica.

Por ejemplo:

```scala
val numero: Any = 10
```

Aunque el valor real sea un `Int`, la variable está declarada como:

```text
Any
```

Por tanto, no debemos asumir directamente que podemos utilizar todas las operaciones específicas de `Int`.

Utilizar tipos demasiado generales puede reducir la seguridad y claridad del programa.

Por esta razón:

> Se recomienda utilizar el tipo más específico que represente correctamente los datos.

---

# 5. `AnyVal`

`AnyVal` representa la raíz de los tipos de valor de Scala.

Algunos tipos que pertenecen a `AnyVal` son:

```text
Byte
Short
Int
Long
Float
Double
Char
Boolean
Unit
```

Por ejemplo:

```scala
val a: AnyVal = 10

val b: AnyVal = 20.5

val c: AnyVal = true

val d: AnyVal = 'A'
```

Todos son válidos.

---

# 6. Tipos de valor

Considere:

```scala
val numero: Int = 10
```

`Int` es subtipo de:

```text
AnyVal
```

y `AnyVal` es subtipo de:

```text
Any
```

Por tanto:

```text
Int
  ↓
AnyVal
  ↓
Any
```

Podemos expresar:

```text
Int <: AnyVal <: Any
```

---

# 7. Ejemplos de `AnyVal`

```scala
val entero: AnyVal =
  25

val decimal: AnyVal =
  3.14

val caracter: AnyVal =
  'S'

val activo: AnyVal =
  true
```

Pero esto no sería adecuado:

```scala
val texto: AnyVal =
  "Scala"
```

porque `String` no pertenece a `AnyVal`.

---

# 8. `AnyRef`

`AnyRef` es la raíz de los tipos de referencia de Scala.

Aquí encontramos normalmente:

* clases creadas por el programador;
* `String`;
* colecciones;
* objetos;
* muchas clases de la biblioteca.

Por ejemplo:

```scala
class Persona(
  val nombre: String
)
```

Podemos escribir:

```scala
val persona: AnyRef =
  new Persona("Ana")
```

También:

```scala
val texto: AnyRef =
  "Scala"
```

---

# 9. Relación entre `AnyRef` y clases

Supongamos:

```scala
class Producto(
  val nombre: String
)
```

La relación es:

```text
Producto
   ↓
 AnyRef
   ↓
  Any
```

Por tanto:

```text
Producto <: AnyRef <: Any
```

---

# 10. `AnyVal` frente a `AnyRef`

La división principal bajo `Any` es:

```text
                 Any
                /   \
          AnyVal     AnyRef
```

## `AnyVal`

Agrupa principalmente valores como:

```text
Int
Double
Boolean
Char
Unit
```

## `AnyRef`

Agrupa principalmente referencias a objetos:

```text
String
Persona
Producto
Cuenta
Vehiculo
```

---

# 11. Ejemplo comparativo

```scala
val numero: AnyVal =
  10
```

```scala
val texto: AnyRef =
  "Scala"
```

```scala
val dato1: Any =
  numero
```

```scala
val dato2: Any =
  texto
```

Tanto `AnyVal` como `AnyRef` son subtipos de `Any`.

---

# 12. `Unit`

`Unit` pertenece a `AnyVal`.

Representa la ausencia de un resultado significativo.

Por ejemplo:

```scala
def mostrarMensaje(
  mensaje: String
): Unit =
  println(mensaje)
```

La función realiza un efecto secundario, pero no devuelve un valor útil para continuar un cálculo.

Conceptualmente:

```text
Unit <: AnyVal <: Any
```

---

# 13. El tipo `Null`

`Null` representa el tipo del valor:

```scala
null
```

Ejemplo:

```scala
val texto: String = null
```

Dependiendo de la configuración de Scala 3 y del uso de *explicit nulls*, el tratamiento de `null` puede ser más estricto.

Conceptualmente, en la jerarquía tradicional de tipos de referencia:

```text
Null
  ↓
AnyRef
  ↓
Any
```

`Null` se relaciona con los tipos de referencia, no con los tipos de valor.

---

# 14. `null` y tipos de valor

No podemos utilizar normalmente:

```scala
val numero: Int = null
```

porque `Int` pertenece a:

```text
AnyVal
```

y `null` se relaciona con referencias.

En cambio, históricamente puede encontrarse:

```scala
val persona: Persona = null
```

porque `Persona` es un tipo de referencia.

---

# 15. Evitar `null` cuando sea posible

Aunque `null` existe en Scala, en programación funcional normalmente se evita su uso directo.

El problema es que puede provocar errores cuando intentamos utilizar una referencia inexistente.

Ejemplo conceptual:

```scala
val persona: Persona = null
```

Luego:

```scala
persona.nombre
```

puede producir un error en tiempo de ejecución.

Más adelante pueden estudiarse alternativas como:

```text
Option
Some
None
```

para representar ausencia de manera más segura.

---

# 16. El tipo `Nothing`

`Nothing` es un tipo especial de Scala.

A diferencia de `Any`, que se encuentra arriba de la jerarquía, `Nothing` se encuentra en la parte inferior.

Conceptualmente:

```text
            Any
          /     \
      AnyVal   AnyRef
         \       /
          \     /
          Nothing
```

`Nothing` es subtipo de todos los tipos.

Podemos expresar:

```text
Nothing <: Int
Nothing <: String
Nothing <: Persona
Nothing <: Any
```

---

# 17. ¿Existen valores de tipo `Nothing`?

`Nothing` no posee valores normales.

No podemos crear algo como:

```scala
val x: Nothing = ...
```

con un valor común.

`Nothing` suele aparecer en expresiones que:

* nunca terminan normalmente;
* lanzan una excepción;
* no producen un valor.

---

# 18. Ejemplo con excepción

Considere:

```scala
def error(
  mensaje: String
): Nothing =
  throw new RuntimeException(mensaje)
```

La función nunca devuelve normalmente.

En lugar de producir un resultado:

```text
lanza una excepción
```

Por eso puede tener tipo:

```text
Nothing
```

---

# 19. ¿Por qué `Nothing` es útil?

Suponga:

```scala
def dividir(
  a: Double,
  b: Double
): Double =
  if b == 0 then
    throw new IllegalArgumentException(
      "División entre cero"
    )
  else
    a / b
```

La rama:

```scala
a / b
```

produce:

```text
Double
```

Mientras que:

```scala
throw ...
```

tiene tipo:

```text
Nothing
```

Como `Nothing` es subtipo de `Double`, Scala puede considerar que toda la expresión devuelve:

```text
Double
```

---

# 20. `Nothing` y expresiones condicionales

Considere:

```scala
val resultado: Int =
  if condicion then
    10
  else
    throw new RuntimeException("Error")
```

La primera rama produce:

```text
Int
```

La segunda:

```text
Nothing
```

Como:

```text
Nothing <: Int
```

el tipo final puede ser:

```text
Int
```

---

# 21. `Null` frente a `Nothing`

No deben confundirse.

## `Null`

Representa:

```text
null
```

y está relacionado con referencias.

## `Nothing`

Representa la ausencia total de un valor normal y es subtipo de todos los tipos.

Comparación:

| Tipo      | Idea principal                               |
| --------- | -------------------------------------------- |
| `Null`    | Tipo asociado al valor `null`                |
| `Nothing` | Tipo inferior de Scala, sin valores normales |

---

# 22. Resumen de la jerarquía

Una representación simplificada es:

```text
                         Any
                        /   \
                   AnyVal   AnyRef
                  /  |  \      |
               Int Double ...  String
                              Persona
                              Producto
                                |
                               Null

                  Nothing
             subtipo de todos
```

---

# 23. Supertipos y subtipos

Si tenemos:

```text
Perro
  ↓
Animal
```

podemos decir:

```text
Perro es subtipo de Animal
Animal es supertipo de Perro
```

En notación:

```text
Perro <: Animal
```

---

# 24. Ejemplo con clases

```scala
abstract class Animal(
  val nombre: String
)
```

```scala
class Perro(
  nombre: String
) extends Animal(nombre)
```

```scala
class Gato(
  nombre: String
) extends Animal(nombre)
```

Entonces:

```text
Perro <: Animal
Gato <: Animal
```

`Animal` es un supertipo de ambos.

---

# 25. Jerarquía creada por el programador

Podemos tener:

```text
              Animal
             /      \
          Perro     Gato
```

Pero esta jerarquía forma parte de una jerarquía mayor:

```text
                Any
                 |
               AnyRef
                 |
               Animal
              /      \
           Perro     Gato
```

Por tanto:

```text
Perro <: Animal <: AnyRef <: Any
```

---

# 26. Asignación de un subtipo a un supertipo

Si:

```text
Perro <: Animal
```

podemos hacer:

```scala
val animal: Animal =
  new Perro("Rocky")
```

Esto es válido.

El objeto real sigue siendo un `Perro`.

Pero la referencia tiene tipo:

```text
Animal
```

---

# 27. Lo contrario no es automáticamente válido

Si tenemos:

```scala
val animal: Animal =
  ...
```

no podemos asumir automáticamente:

```scala
val perro: Perro =
  animal
```

porque `animal` podría realmente contener:

```text
Gato
```

o cualquier otro subtipo de `Animal`.

Ir del subtipo al supertipo es seguro.

Ir del supertipo al subtipo requiere más información.

---

# 28. Polimorfismo por subtipado

El **polimorfismo por subtipado** permite utilizar objetos de diferentes subtipos mediante una referencia o parámetro de un supertipo común.

Ejemplo:

```scala
abstract class Animal(
  val nombre: String
) {

  def sonido: String
}
```

```scala
class Perro(
  nombre: String
) extends Animal(nombre) {

  def sonido: String =
    "Guau"
}
```

```scala
class Gato(
  nombre: String
) extends Animal(nombre) {

  def sonido: String =
    "Miau"
}
```

---

# 29. Diferentes objetos, mismo tipo general

Podemos escribir:

```scala
val animal1: Animal =
  new Perro("Rocky")

val animal2: Animal =
  new Gato("Michi")
```

Ambas variables tienen tipo:

```text
Animal
```

pero contienen objetos diferentes.

---

# 30. Comportamiento polimórfico

Si ejecutamos:

```scala
animal1.sonido
```

obtenemos:

```text
Guau
```

Mientras que:

```scala
animal2.sonido
```

produce:

```text
Miau
```

Aunque trabajemos con el supertipo `Animal`, se ejecuta la implementación correspondiente al objeto real.

---

# 31. Funciones polimórficas por subtipado

Podemos crear:

```scala
def escuchar(
  animal: Animal
): String =
  animal.sonido
```

Ahora podemos enviar:

```scala
escuchar(
  new Perro("Rocky")
)
```

o:

```scala
escuchar(
  new Gato("Michi")
)
```

La función trabaja con:

```text
Animal
```

y no necesita conocer todos los subtipos posibles.

---

# 32. Polimorfismo con traits

El mismo concepto funciona con `trait`.

```scala
trait Pagable {

  def calcularPago: Double
}
```

```scala
class Empleado(
  val salario: Double
) extends Pagable {

  def calcularPago: Double =
    salario
}
```

```scala
class Factura(
  val monto: Double
) extends Pagable {

  def calcularPago: Double =
    monto
}
```

Podemos escribir:

```scala
def procesarPago(
  pagable: Pagable
): Double =
  pagable.calcularPago
```

---

# 33. El trait como supertipo

En el ejemplo anterior:

```text
Empleado <: Pagable
Factura <: Pagable
```

Por tanto:

```text
Pagable
```

es un supertipo común.

Esto permite utilizar polimorfismo sin necesidad de que los tipos pertenezcan a una misma clase abstracta.

---

# 34. Supertipos comunes

Cuando Scala debe determinar un tipo común entre diferentes valores, puede buscar un supertipo.

Por ejemplo:

```scala
val valor =
  if condicion then
    "Scala"
  else
    "Java"
```

Ambas ramas son `String`, por lo que:

```text
valor: String
```

Pero considere conceptualmente dos clases diferentes:

```text
Perro
Gato
```

Si ambas son `Animal`, el tipo común puede ser:

```text
Animal
```

---

# 35. Tipo estático y tipo real

Considere:

```scala
val animal: Animal =
  new Perro("Rocky")
```

Aquí existen dos ideas:

### Tipo estático

El tipo declarado de la variable:

```text
Animal
```

### Tipo real o concreto

El objeto creado:

```text
Perro
```

El compilador utiliza principalmente el tipo estático para determinar qué operaciones están permitidas.

---

# 36. Métodos disponibles según el tipo estático

Suponga:

```scala
class Perro(
  nombre: String
) extends Animal(nombre) {

  def sonido: String =
    "Guau"

  def traerPelota: String =
    "Trayendo pelota"
}
```

Si hacemos:

```scala
val perro: Perro =
  new Perro("Rocky")
```

podemos utilizar:

```scala
perro.traerPelota
```

Pero si hacemos:

```scala
val animal: Animal =
  new Perro("Rocky")
```

el tipo declarado es `Animal`.

Si `Animal` no declara:

```text
traerPelota
```

ese método no está disponible directamente mediante la referencia `animal`.

---

# 37. Por qué utilizar supertipos

Trabajar con supertipos permite escribir código más general.

En lugar de:

```scala
def procesarPerro(
  perro: Perro
): String =
  perro.sonido
```

podemos escribir:

```scala
def procesarAnimal(
  animal: Animal
): String =
  animal.sonido
```

Ahora funciona con cualquier subtipo de `Animal`.

---

# 38. Ventaja en proyectos de software

Suponga un sistema de pagos:

```text
MetodoPago
├── Tarjeta
├── Transferencia
└── BilleteraDigital
```

Podemos escribir:

```scala
def realizarPago(
  metodo: MetodoPago,
  monto: Double
): Boolean =
  metodo.pagar(monto)
```

Si posteriormente aparece:

```text
PagoQR
```

solo necesitamos que sea subtipo de:

```text
MetodoPago
```

La función `realizarPago` no necesita cambiar.

---

# 39. Relación con programación funcional

El polimorfismo por subtipado proviene principalmente de la orientación a objetos.

Scala permite combinarlo con programación funcional.

Por ejemplo:

```scala
def calcular(
  figura: Figura
): Double =
  figura.area
```

La función:

* recibe una abstracción;
* no modifica el objeto;
* devuelve un valor;
* puede mantenerse pura.

Así podemos combinar:

```text
Jerarquía de tipos
+
Polimorfismo
+
Funciones puras
+
Inmutabilidad
```

---

# 40. No utilizar `Any` cuando existe un mejor supertipo

Suponga:

```scala
def procesar(
  valor: Any
): String =
  ...
```

Si realmente la función solamente trabaja con animales, sería mejor:

```scala
def procesar(
  animal: Animal
): String =
  ...
```

`Animal` expresa mejor el contrato de la función.

Regla práctica:

> Utilizar el supertipo más específico que permita resolver el problema.

---

# 41. Ejemplo completo

```scala
trait Figura {

  def area: Double
}
```

```scala
class Rectangulo(
  val base: Double,
  val altura: Double
) extends Figura {

  def area: Double =
    base * altura
}
```

```scala
class Circulo(
  val radio: Double
) extends Figura {

  def area: Double =
    Math.PI * radio * radio
}
```

Función:

```scala
def obtenerArea(
  figura: Figura
): Double =
  figura.area
```

Podemos utilizar:

```scala
obtenerArea(
  new Rectangulo(5, 4)
)
```

o:

```scala
obtenerArea(
  new Circulo(3)
)
```

Esto es polimorfismo por subtipado.

---

# 42. Ejercicios de práctica

## Ejercicio 1. Clasificar tipos

Para cada tipo indique si pertenece principalmente a `AnyVal` o `AnyRef`:

```text
Int
Double
Boolean
String
Char
Persona
Unit
Producto
```

---

## Ejercicio 2. Identificar relaciones

Considere:

```scala
abstract class Vehiculo

class Auto extends Vehiculo

class Moto extends Vehiculo
```

Indique las relaciones de subtipo y supertipo existentes.

---

## Ejercicio 3. Polimorfismo

Considere:

```scala
trait Transporte {

  def costo(distancia: Double): Double
}
```

Cree dos clases:

```text
Taxi
Bus
```

que implementen `Transporte` con diferentes formas de calcular el costo.

Luego cree:

```scala
def calcularViaje(
  transporte: Transporte,
  distancia: Double
): Double
```

La función deberá funcionar con ambos tipos.

---

## Ejercicio 4. `Nothing`

Cree:

```scala
def validarPositivo(
  numero: Int
): Int =
  if numero >= 0 then
    numero
  else
    throw new IllegalArgumentException(
      "El número debe ser positivo"
    )
```

Identifique el tipo de cada rama del `if` y explique cuál es el tipo final de la expresión.

---

## Ejercicio 5. Diseñar un supertipo

Se tienen los tipos:

```text
Email
SMS
NotificacionPush
```

Diseñe un `trait` que pueda actuar como supertipo común.

Después cree una función que pueda trabajar con cualquiera de las tres implementaciones.

---

# 43. Errores frecuentes

## Error 1. Pensar que `Any` significa únicamente objetos

No.

`Any` incluye:

```text
AnyVal
AnyRef
```

Por tanto, también incluye tipos como:

```text
Int
Double
Boolean
```

---

## Error 2. Confundir `AnyRef` con `Any`

`AnyRef` representa tipos de referencia.

`Any` es todavía más general.

La relación es:

```text
AnyRef <: Any
```

---

## Error 3. Confundir `Null` y `Nothing`

`Null` está relacionado con el valor:

```text
null
```

`Nothing` no representa un valor normal y es subtipo de todos los tipos.

---

## Error 4. Pensar que una variable cambia de tipo por contener un subtipo

```scala
val animal: Animal =
  new Perro("Rocky")
```

La variable sigue teniendo tipo estático:

```text
Animal
```

aunque el objeto concreto sea:

```text
Perro
```

---

# 44. Buenas prácticas

Al trabajar con jerarquías de tipos:

* Utilizar tipos específicos siempre que sea posible.
* Utilizar supertipos cuando una función deba trabajar con varias implementaciones.
* Evitar `Any` como solución genérica cuando existe una abstracción mejor.
* Evitar `null` cuando sea posible.
* Preferir diseños donde la ausencia pueda representarse explícitamente.
* Utilizar traits y clases base para definir contratos claros.
* Mantener objetos inmutables cuando sea razonable.
* Aprovechar el polimorfismo para evitar lógica repetida según el tipo concreto.

---

# 45. Resumen

La raíz de la jerarquía de tipos de Scala es:

```text
Any
```

Debajo se encuentran:

```text
AnyVal
AnyRef
```

`AnyVal` contiene tipos de valor como:

```text
Int
Double
Boolean
Char
Unit
```

`AnyRef` representa tipos de referencia como:

```text
String
Persona
Producto
```

`Null` está relacionado con referencias y representa el tipo del valor:

```text
null
```

`Nothing` es el tipo inferior y es subtipo de todos los tipos:

```text
Nothing <: cualquier tipo
```

Una relación:

```text
Perro <: Animal
```

significa:

```text
Perro es subtipo de Animal
Animal es supertipo de Perro
```

El **polimorfismo por subtipado** permite utilizar diferentes subtipos mediante un supertipo común:

```scala
def procesar(
  animal: Animal
): String =
  animal.sonido
```

La idea fundamental de esta sesión es:

> **La jerarquía de tipos permite organizar valores desde tipos muy específicos hasta tipos generales. El subtipado y el polimorfismo permiten escribir código flexible que trabaja con abstracciones comunes sin depender de cada implementación concreta.**
