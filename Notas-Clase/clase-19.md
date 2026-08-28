# Clase 19: Traits, Clases Abstractas, Jerarquías de Clases e Integración de Programación Funcional y Orientación a Objetos

# 1. Introducción

Hasta ahora hemos creado clases concretas como:

```scala
class Persona(
  val nombre: String,
  val edad: Int
)
```

Una clase concreta puede utilizarse directamente para crear objetos:

```scala
val persona =
  new Persona("Ana", 25)
```

Sin embargo, en aplicaciones más grandes encontramos diferentes tipos de objetos que comparten características o comportamientos.

Por ejemplo:

```text
Empleado
├── Desarrollador
├── Diseñador
└── Administrador
```

Todos son empleados, pero cada uno puede comportarse de manera diferente.

Scala permite modelar estas relaciones mediante:

* `trait`
* clases abstractas;
* herencia;
* jerarquías de tipos;
* polimorfismo.

---

# 2. ¿Qué es un `trait`?

Un `trait` define características o comportamientos que pueden ser compartidos por diferentes clases.

Su sintaxis básica es:

```scala
trait NombreTrait {

}
```

Por ejemplo:

```scala
trait Saludable {

  def salud(): String
}
```

El método:

```scala
def salud(): String
```

no posee implementación.

Por esta razón se denomina un **método abstracto**.

---

# 3. Implementar un `trait`

Una clase puede implementar un `trait` utilizando `extends`.

```scala
trait Saludable {

  def salud(): String
}
```

Podemos crear:

```scala
class Persona(
  val nombre: String
) extends Saludable {

  def salud(): String =
    s"Hola, soy $nombre"
}
```

Uso:

```scala
val persona =
  new Persona("Carlos")

persona.salud()
```

Resultado:

```text
Hola, soy Carlos
```

La clase debe proporcionar una implementación para el método definido en el `trait`.

---

# 4. Utilizar `override`

Cuando una clase proporciona una nueva implementación de un miembro heredado, normalmente utilizamos:

```scala
override
```

Por ejemplo:

```scala
trait Saludable {

  def salud(): String =
    "Hola"
}
```

Ahora el método ya posee una implementación.

Una clase puede reemplazarla:

```scala
class Persona(
  val nombre: String
) extends Saludable {

  override def salud(): String =
    s"Hola, soy $nombre"
}
```

---

# 5. Métodos abstractos dentro de un `trait`

Un método abstracto solamente declara:

* su nombre;
* sus parámetros;
* su tipo de retorno.

Pero no especifica cómo se realiza la operación.

Ejemplo:

```scala
trait Figura {

  def area: Double
}
```

Cada figura debe decidir cómo calcular su área.

---

# 6. Diferentes implementaciones del mismo `trait`

Podemos crear:

```scala
trait Figura {

  def area: Double
}
```

Una clase `Rectangulo`:

```scala
class Rectangulo(
  val base: Double,
  val altura: Double
) extends Figura {

  def area: Double =
    base * altura
}
```

Y una clase `Circulo`:

```scala
class Circulo(
  val radio: Double
) extends Figura {

  def area: Double =
    Math.PI * radio * radio
}
```

Ambas son `Figura`, pero calculan el área de manera diferente.

---

# 7. Un `trait` también puede tener implementación

Un `trait` no está limitado a métodos abstractos.

Puede proporcionar comportamiento directamente.

```scala
trait Figura {

  def area: Double

  def describir(): String =
    s"El área de la figura es $area"
}
```

Las clases solamente deben implementar:

```scala
def area: Double
```

El método:

```scala
describir()
```

ya está disponible.

---

# 8. Ejemplo completo

```scala
trait Figura {

  def area: Double

  def describir(): String =
    s"Área: $area"
}
```

```scala
class Cuadrado(
  val lado: Double
) extends Figura {

  def area: Double =
    lado * lado
}
```

Uso:

```scala
val cuadrado =
  new Cuadrado(5)

cuadrado.area
cuadrado.describir()
```

Resultados:

```text
25.0
Área: 25.0
```

---

# 9. Atributos abstractos en un `trait`

Un `trait` también puede exigir que una clase proporcione determinados atributos.

```scala
trait Identificable {

  val id: Int

  val nombre: String
}
```

Una clase puede implementarlo:

```scala
class Producto(
  val id: Int,
  val nombre: String,
  val precio: Double
) extends Identificable
```

La clase ya proporciona:

```text
id
nombre
```

mediante los parámetros de su constructor.

---

# 10. Múltiples `trait`

Una clase puede utilizar varios `trait`.

Por ejemplo:

```scala
trait Identificable {

  val id: Int
}
```

```scala
trait Describible {

  def descripcion: String
}
```

Podemos escribir:

```scala
class Producto(
  val id: Int,
  val nombre: String,
  val precio: Double
) extends Identificable with Describible {

  def descripcion: String =
    s"$nombre - Bs. $precio"
}
```

La clase posee características provenientes de ambos `trait`.

---

# 11. ¿Por qué utilizar varios `trait`?

Suponga que tenemos:

```text
Volador
Nadador
Caminante
```

No todos los animales poseen las mismas capacidades.

Podemos representar cada comportamiento de manera independiente.

```scala
trait Volador {

  def volar(): String =
    "Volando"
}
```

```scala
trait Nadador {

  def nadar(): String =
    "Nadando"
}
```

Una clase puede combinar ambos:

```scala
class Pato(
  val nombre: String
) extends Volador with Nadador
```

Ahora:

```scala
val pato =
  new Pato("Donald")

pato.volar()
pato.nadar()
```

---

# 12. Composición de comportamientos

Los `trait` permiten modelar capacidades independientes.

Por ejemplo:

```text
Vehiculo
├── Electrico
├── Conectable
└── Autonomo
```

Una clase podría combinar únicamente las características que necesita.

Esto evita crear jerarquías innecesariamente grandes.

---

# 13. ¿Qué es una clase abstracta?

Una **clase abstracta** es una clase que no puede utilizarse directamente para crear objetos.

Se declara utilizando:

```scala
abstract class
```

Ejemplo:

```scala
abstract class Animal(
  val nombre: String
)
```

No podemos hacer directamente:

```scala
val animal =
  new Animal("Animal")
```

porque `Animal` es abstracta.

---

# 14. Métodos abstractos dentro de una clase abstracta

Una clase abstracta puede contener métodos sin implementación.

```scala
abstract class Animal(
  val nombre: String
) {

  def sonido(): String
}
```

Luego:

```scala
class Perro(
  nombre: String
) extends Animal(nombre) {

  def sonido(): String =
    "Guau"
}
```

Y:

```scala
class Gato(
  nombre: String
) extends Animal(nombre) {

  def sonido(): String =
    "Miau"
}
```

---

# 15. Utilizando una clase abstracta

```scala
val perro =
  new Perro("Firulais")

val gato =
  new Gato("Michi")
```

Podemos ejecutar:

```scala
perro.sonido()
```

Resultado:

```text
Guau
```

Y:

```scala
gato.sonido()
```

Resultado:

```text
Miau
```

---

# 16. Una clase abstracta también puede tener implementación

No todos sus métodos tienen que ser abstractos.

```scala
abstract class Animal(
  val nombre: String
) {

  def sonido(): String

  def presentarse(): String =
    s"Mi nombre es $nombre"
}
```

Las clases hijas heredan:

```scala
presentarse()
```

y solamente necesitan implementar:

```scala
sonido()
```

---

# 17. Constructor de una clase abstracta

Una diferencia importante respecto a muchos `trait` es que una clase abstracta puede representar claramente un **estado base común** mediante su constructor.

Ejemplo:

```scala
abstract class Empleado(
  val nombre: String,
  val salarioBase: Double
) {

  def calcularSalario(): Double
}
```

Luego:

```scala
class Desarrollador(
  nombre: String,
  salarioBase: Double,
  val bono: Double
) extends Empleado(
  nombre,
  salarioBase
) {

  def calcularSalario(): Double =
    salarioBase + bono
}
```

---

# 18. `trait` frente a clase abstracta

Ambos pueden contener:

* métodos abstractos;
* métodos implementados;
* valores;
* comportamiento compartido.

Sin embargo, tienen usos diferentes.

| `trait`                                                  | `abstract class`                                    |
| -------------------------------------------------------- | --------------------------------------------------- |
| Representa frecuentemente una capacidad o comportamiento | Representa frecuentemente un concepto base          |
| Una clase puede utilizar varios traits                   | Una clase solo puede extender una clase             |
| Ideal para combinar comportamientos                      | Ideal para compartir estado y estructura base       |
| Puede ser implementado por clases no relacionadas        | Normalmente participa directamente en una jerarquía |

---

# 19. Ejemplo conceptual

Suponga:

```text
Animal
```

Es una buena candidata para:

```scala
abstract class Animal
```

porque representa una categoría general.

Mientras que:

```text
Volador
Nadador
Corredor
```

pueden representarse con:

```scala
trait
```

porque representan capacidades.

---

# 20. Combinando clase abstracta y `trait`

Podemos escribir:

```scala
abstract class Animal(
  val nombre: String
) {

  def sonido(): String
}
```

```scala
trait Nadador {

  def nadar(): String =
    "Estoy nadando"
}
```

```scala
trait Volador {

  def volar(): String =
    "Estoy volando"
}
```

Ahora:

```scala
class Pato(
  nombre: String
) extends Animal(nombre)
    with Nadador
    with Volador {

  def sonido(): String =
    "Cuac"
}
```

La clase `Pato`:

* es un `Animal`;
* puede nadar;
* puede volar.

---

# 21. Jerarquías de clases

Una **jerarquía de clases** representa relaciones entre tipos generales y tipos más específicos.

Por ejemplo:

```text
            Animal
          /        \
       Perro       Gato
```

Podemos modelarlo:

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

---

# 22. Relación "es un"

Cuando utilizamos herencia, normalmente estamos representando una relación:

> **es un**

Por ejemplo:

```text
Perro es un Animal
Gato es un Animal
Desarrollador es un Empleado
Rectangulo es una Figura
```

Esto significa que un objeto del tipo específico también puede ser tratado como su tipo general.

---

# 23. Polimorfismo

Considere:

```scala
abstract class Animal(
  val nombre: String
) {

  def sonido(): String
}
```

Tenemos:

```scala
class Perro(
  nombre: String
) extends Animal(nombre) {

  def sonido(): String =
    "Guau"
}
```

Y:

```scala
class Gato(
  nombre: String
) extends Animal(nombre) {

  def sonido(): String =
    "Miau"
}
```

Podemos escribir:

```scala
val animal1: Animal =
  new Perro("Firulais")

val animal2: Animal =
  new Gato("Michi")
```

Aunque ambos valores tienen tipo:

```text
Animal
```

su comportamiento concreto es diferente.

---

# 24. El mismo mensaje, diferente comportamiento

```scala
animal1.sonido()
```

devuelve:

```text
Guau
```

Mientras que:

```scala
animal2.sonido()
```

devuelve:

```text
Miau
```

Esto es un ejemplo de:

> **Polimorfismo**

Podemos tratar diferentes objetos mediante un tipo común y permitir que cada uno determine su propio comportamiento.

---

# 25. Funciones que reciben tipos generales

Podemos aprovechar el polimorfismo creando funciones que trabajen con el tipo general.

```scala
def escuchar(
  animal: Animal
): String =
  animal.sonido()
```

Podemos utilizar:

```scala
escuchar(
  new Perro("Rocky")
)
```

o:

```scala
escuchar(
  new Gato("Garfield")
)
```

La función no necesita saber qué tipo concreto recibió.

Solamente necesita saber que:

```text
Animal
```

posee:

```scala
sonido()
```

---

# 26. Jerarquías y abstracción

Una jerarquía permite colocar características comunes en un nivel superior.

Por ejemplo:

```scala
abstract class Vehiculo(
  val marca: String,
  val velocidadMaxima: Double
) {

  def descripcion(): String =
    s"$marca - $velocidadMaxima km/h"
}
```

Luego:

```scala
class Automovil(
  marca: String,
  velocidadMaxima: Double,
  val puertas: Int
) extends Vehiculo(
  marca,
  velocidadMaxima
)
```

Y:

```scala
class Motocicleta(
  marca: String,
  velocidadMaxima: Double,
  val cilindrada: Int
) extends Vehiculo(
  marca,
  velocidadMaxima
)
```

La información común se encuentra en `Vehiculo`.

---

# 27. Evitar duplicación

Sin una clase base podríamos terminar escribiendo varias veces:

```text
marca
velocidadMaxima
descripcion()
```

Una jerarquía permite centralizar aquello que es común.

Esto reduce:

* duplicación;
* inconsistencias;
* mantenimiento innecesario.

---

# 28. Sobrescritura de métodos

Una clase hija puede reemplazar una implementación existente.

```scala
abstract class Empleado(
  val nombre: String
) {

  def descripcion(): String =
    s"Empleado: $nombre"
}
```

Una clase puede escribir:

```scala
class Gerente(
  nombre: String
) extends Empleado(nombre) {

  override def descripcion(): String =
    s"Gerente: $nombre"
}
```

Uso:

```scala
val gerente =
  new Gerente("Laura")

gerente.descripcion()
```

Resultado:

```text
Gerente: Laura
```

---

# 29. Diseño de jerarquías

Al crear una jerarquía debemos preguntarnos:

### ¿Qué tienen todos los objetos en común?

Esto puede pertenecer a una clase abstracta.

### ¿Qué comportamientos pueden compartir algunos objetos?

Esto puede convertirse en un `trait`.

### ¿Qué comportamiento cambia entre las clases?

Puede definirse como un método abstracto.

---

# 30. Ejemplo de diseño

Suponga que queremos representar empleados:

```text
Empleado
├── Desarrollador
├── Vendedor
└── Gerente
```

Todos tienen:

```text
nombre
salarioBase
```

Por tanto:

```scala
abstract class Empleado(
  val nombre: String,
  val salarioBase: Double
) {

  def salarioTotal: Double
}
```

---

# 31. Implementaciones diferentes

Un desarrollador:

```scala
class Desarrollador(
  nombre: String,
  salarioBase: Double,
  val bono: Double
) extends Empleado(
  nombre,
  salarioBase
) {

  def salarioTotal: Double =
    salarioBase + bono
}
```

Un vendedor:

```scala
class Vendedor(
  nombre: String,
  salarioBase: Double,
  val ventas: Double
) extends Empleado(
  nombre,
  salarioBase
) {

  def salarioTotal: Double =
    salarioBase + ventas * 0.05
}
```

Ambos son empleados, pero calculan su salario de manera diferente.

---

# 32. Integración de Programación Funcional y Orientación a Objetos

Scala no obliga a elegir únicamente entre:

```text
Programación Funcional
```

y:

```text
Orientación a Objetos
```

Permite combinar ambos paradigmas.

Podemos utilizar:

* clases para modelar datos;
* `trait` para modelar comportamientos;
* jerarquías para organizar tipos;
* funciones puras para realizar cálculos;
* objetos inmutables para representar estados;
* métodos que retornan nuevos objetos en lugar de modificar los existentes.

---

# 33. Orientación a objetos tradicional

Un diseño mutable podría verse así:

```scala
class Cuenta(
  var saldo: Double
) {

  def depositar(
    monto: Double
  ): Unit =
    saldo = saldo + monto
}
```

Uso:

```scala
val cuenta =
  new Cuenta(1000)

cuenta.depositar(500)
```

El mismo objeto fue modificado.

---

# 34. Integración con programación funcional

Podemos mantener la clase, pero representar los cambios inmutablemente:

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

Seguimos utilizando orientación a objetos, pero aplicamos inmutabilidad.

---

# 35. Traits con métodos puros

Un `trait` también puede representar operaciones funcionales.

Por ejemplo:

```scala
trait Descontable {

  def precio: Double

  def calcularDescuento(
    porcentaje: Double
  ): Double =
    precio - precio * porcentaje
}
```

La operación:

```scala
calcularDescuento
```

no modifica el objeto.

Solamente calcula y devuelve un nuevo valor.

---

# 36. Métodos que devuelven nuevos objetos

Considere:

```scala
abstract class Figura {

  def mover(
    dx: Double,
    dy: Double
  ): Figura
}
```

Una implementación podría devolver una nueva figura en lugar de modificar la existente.

Este patrón aparece frecuentemente en diseños funcionales:

```text
objeto actual
      ↓
   operación
      ↓
nuevo objeto
```

---

# 37. Polimorfismo con funciones puras

Podemos combinar jerarquías con funciones.

```scala
def mostrarArea(
  figura: Figura
): Double =
  figura.area
```

La función recibe un tipo general.

Cada implementación determina cómo obtener el resultado.

Esto combina:

```text
Orientación a objetos
        +
Programación funcional
```

La jerarquía modela los diferentes tipos y la función realiza una transformación sin modificar los objetos.

---

# 38. Ejemplo completo: Sistema de pagos

Podemos comenzar con:

```scala
trait Pagable {

  def calcularPago: Double
}
```

Una clase:

```scala
class EmpleadoFijo(
  val salario: Double
) extends Pagable {

  def calcularPago: Double =
    salario
}
```

Otra:

```scala
class EmpleadoPorHora(
  val horas: Double,
  val pagoPorHora: Double
) extends Pagable {

  def calcularPago: Double =
    horas * pagoPorHora
}
```

Podemos crear:

```scala
def obtenerPago(
  pagable: Pagable
): Double =
  pagable.calcularPago
```

La función funciona con cualquier objeto que implemente `Pagable`.

---

# 39. ¿Para qué sirve esto en un proyecto de software?

En un sistema real aparecen muchas entidades relacionadas.

Por ejemplo, en un sistema de comercio electrónico:

```text
MetodoPago
├── Tarjeta
├── Transferencia
└── BilleteraDigital
```

Todos representan formas de pago.

Podríamos definir:

```scala
trait MetodoPago {

  def pagar(
    monto: Double
  ): Boolean
}
```

Cada implementación decidiría cómo realizar el pago.

El resto del sistema puede trabajar simplemente con:

```text
MetodoPago
```

sin depender del tipo concreto.

---

# 40. Otro ejemplo: sistema de notificaciones

Podemos definir:

```scala
trait Notificacion {

  def enviar(
    mensaje: String
  ): String
}
```

Implementaciones:

```text
NotificacionEmail
NotificacionSMS
NotificacionPush
```

Una función puede trabajar con cualquiera:

```scala
def notificar(
  servicio: Notificacion,
  mensaje: String
): String =
  servicio.enviar(mensaje)
```

Si posteriormente aparece:

```text
NotificacionWhatsApp
```

podemos crear otra implementación sin modificar la función `notificar`.

---

# 41. Ventajas en proyectos reales

El uso adecuado de traits, clases abstractas y jerarquías permite:

* evitar código duplicado;
* separar responsabilidades;
* representar conceptos del dominio;
* reemplazar implementaciones;
* agregar nuevos comportamientos;
* crear código reutilizable;
* facilitar las pruebas;
* reducir dependencias entre componentes.

La combinación con programación funcional permite además:

* favorecer inmutabilidad;
* evitar efectos secundarios innecesarios;
* crear funciones predecibles;
* separar datos y transformaciones;
* facilitar el razonamiento sobre el programa.

---

# 42. `trait` como contrato

Podemos interpretar un `trait` como un contrato.

Si tenemos:

```scala
trait Exportable {

  def exportar(): String
}
```

cualquier clase que implemente `Exportable` debe poder:

```text
exportar
```

No importa cómo lo haga internamente.

Por ejemplo:

```scala
class ReportePDF
  extends Exportable {

  def exportar(): String =
    "Exportando PDF"
}
```

Y:

```scala
class ReporteCSV
  extends Exportable {

  def exportar(): String =
    "Exportando CSV"
}
```

---

# 43. Programar utilizando abstracciones

En lugar de escribir:

```scala
def exportarPDF(
  reporte: ReportePDF
): String = ...
```

y después:

```scala
def exportarCSV(
  reporte: ReporteCSV
): String = ...
```

podemos escribir:

```scala
def exportarReporte(
  reporte: Exportable
): String =
  reporte.exportar()
```

Ahora la función trabaja con la abstracción:

```text
Exportable
```

y no con una implementación específica.

---

# 44. ¿Cuándo utilizar una clase abstracta?

Una clase abstracta es una buena opción cuando:

* existe una relación clara de tipo **es un**;
* las clases comparten datos;
* existe un estado base común;
* queremos proporcionar una implementación compartida;
* queremos construir una jerarquía principal.

Ejemplo:

```text
Empleado
├── Gerente
├── Desarrollador
└── Vendedor
```

---

# 45. ¿Cuándo utilizar un `trait`?

Un `trait` es una buena opción cuando queremos representar:

* una capacidad;
* un comportamiento;
* un contrato;
* una característica que diferentes clases pueden compartir.

Ejemplos:

```text
Comparable
Imprimible
Exportable
Autenticable
Volador
Nadador
```

Una clase puede combinar varios de ellos.

---

# 46. Regla práctica

Una forma inicial de pensar la diferencia es:

```text
¿QUÉ ES?
    ↓
abstract class
```

Por ejemplo:

```text
Perro ES un Animal
```

Mientras que:

```text
¿QUÉ PUEDE HACER?
       ↓
     trait
```

Por ejemplo:

```text
Pato PUEDE volar
Pato PUEDE nadar
```

Esta regla no cubre todos los diseños posibles, pero es una buena referencia inicial.

---

# 47. Buenas prácticas

Al diseñar jerarquías en Scala:

* Preferir jerarquías sencillas.
* Evitar niveles de herencia innecesarios.
* Utilizar `trait` para comportamientos reutilizables.
* Utilizar clases abstractas cuando existe un estado base claro.
* Utilizar `override` cuando se reemplaza una implementación existente.
* Favorecer atributos con `val`.
* Evitar `var` cuando sea posible.
* Mantener métodos sin efectos secundarios cuando sea razonable.
* Hacer que los cambios produzcan nuevos objetos.
* Programar utilizando tipos generales cuando sea posible.

---

# 48. Ejercicio 1: Figuras geométricas

Cree:

```scala
trait Figura {

  def area: Double
}
```

Implemente:

```text
Rectangulo
Circulo
Triangulo
```

Cada clase deberá implementar:

```scala
area
```

Luego cree una función:

```scala
def mostrarArea(
  figura: Figura
): Double
```

y pruebe la función con objetos de las tres clases.

---

# 49. Ejercicio 2: Animales y capacidades

Cree una clase abstracta:

```scala
abstract class Animal(
  val nombre: String
) {

  def sonido(): String
}
```

Cree los traits:

```scala
trait Volador
```

```scala
trait Nadador
```

Cada trait deberá contener un método relacionado con su capacidad.

Luego cree:

```text
Perro
Pato
Pez
```

Características:

```text
Perro → Animal

Pato → Animal + Volador + Nadador

Pez → Animal + Nadador
```

Cada animal deberá implementar su propio sonido.

---

# 50. Ejercicio 3: Empleados

Cree:

```scala
abstract class Empleado(
  val nombre: String,
  val salarioBase: Double
) {

  def salarioTotal: Double
}
```

Implemente:

```text
Desarrollador
Vendedor
Gerente
```

Reglas:

* El desarrollador recibe un bono fijo.
* El vendedor recibe una comisión sobre sus ventas.
* El gerente recibe un bono porcentual sobre su salario base.

Todos los cálculos deberán realizarse sin modificar los objetos.

---

# 51. Ejercicio 4: Sistema de notificaciones

Cree:

```scala
trait Notificacion {

  def enviar(
    mensaje: String
  ): String
}
```

Implemente:

```text
Email
SMS
Push
```

Cada clase deberá producir un mensaje diferente.

Ejemplo:

```text
Email: "Enviando correo: Hola"

SMS: "Enviando SMS: Hola"

Push: "Enviando notificación: Hola"
```

Después cree:

```scala
def procesarNotificacion(
  notificacion: Notificacion,
  mensaje: String
): String
```

La función deberá trabajar con cualquiera de las implementaciones.

---

# 52. Ejercicio 5: Vehículos

Cree:

```scala
abstract class Vehiculo(
  val marca: String,
  val velocidadMaxima: Double
)
```

Agregue:

```scala
def descripcion(): String
```

Luego cree los traits:

```scala
trait Electrico
```

```scala
trait Recargable
```

Cree:

```text
Automovil
Motocicleta
AutoElectrico
```

`AutoElectrico` deberá implementar las capacidades correspondientes mediante traits.

---

# 53. Reto: Sistema de cuentas inmutables

Cree:

```scala
abstract class Cuenta(
  val titular: String,
  val saldo: Double
) {

  def depositar(
    monto: Double
  ): Cuenta

  def retirar(
    monto: Double
  ): Cuenta
}
```

Implemente:

```text
CuentaAhorro
CuentaCorriente
```

Las operaciones:

```text
depositar
retirar
```

no deberán modificar el objeto actual.

Cada operación deberá devolver una nueva cuenta.

Ejemplo:

```text
cuenta1
saldo = 1000

cuenta2 = cuenta1.depositar(500)

cuenta1.saldo = 1000
cuenta2.saldo = 1500
```

Además, `CuentaAhorro` y `CuentaCorriente` pueden aplicar reglas diferentes para realizar retiros.

---

# 54. Resumen

Un `trait` permite definir comportamientos compartidos:

```scala
trait Figura {

  def area: Double
}
```

Una clase puede implementar uno o varios traits:

```scala
class Pato
  extends Animal
    with Volador
    with Nadador
```

Una clase abstracta representa un concepto base que no puede instanciarse directamente:

```scala
abstract class Animal(
  val nombre: String
)
```

Las jerarquías permiten representar relaciones:

```text
          Animal
         /      \
      Perro     Gato
```

El **polimorfismo** permite tratar distintos objetos mediante un tipo común:

```scala
val animal: Animal =
  new Perro("Rocky")
```

Scala permite combinar orientación a objetos con programación funcional utilizando:

```text
Clases y Traits
      +
Inmutabilidad
      +
Funciones puras
      +
Nuevos objetos en lugar de mutación
```

La idea fundamental de esta sesión es:

> **Las clases abstractas permiten representar conceptos generales, los traits permiten compartir y combinar comportamientos, y el polimorfismo permite trabajar con diferentes implementaciones mediante una misma abstracción. Scala combina estas herramientas orientadas a objetos con principios funcionales como la inmutabilidad y las transformaciones sin efectos secundarios.**
