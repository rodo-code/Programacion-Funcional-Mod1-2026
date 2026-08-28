# Clase 17: Clases, Objetos, Atributos, Métodos y Constructores en Scala

# 1. Introducción a clases y objetos

Hasta ahora hemos trabajado principalmente con:

* valores;
* expresiones;
* funciones;
* recursión;
* funciones de orden superior.

Sin embargo, muchos programas necesitan representar entidades que contienen varios datos relacionados.

Por ejemplo, una persona puede tener:

```text
nombre
edad
correo
```

Un producto puede tener:

```text
nombre
precio
cantidad
```

Un punto puede tener:

```text
coordenada x
coordenada y
```

Una **clase** permite agrupar datos y comportamientos relacionados.

---

# 2. ¿Qué es una clase?

Una clase es una estructura que define las características y comportamientos que tendrán determinados objetos.

Por ejemplo:

```scala
class Persona(
  val nombre: String,
  val edad: Int
)
```

La clase `Persona` indica que cada persona tendrá:

```text
nombre: String
edad: Int
```

La clase puede entenderse como una plantilla a partir de la cual se crean objetos.

---

# 3. ¿Qué es un objeto?

Un objeto es una instancia concreta de una clase.

Si tenemos:

```scala
class Persona(
  val nombre: String,
  val edad: Int
)
```

podemos crear:

```scala
val persona1 =
  new Persona("Ana", 20)
```

También:

```scala
val persona2 =
  new Persona("Carlos", 25)
```

`persona1` y `persona2` pertenecen a la misma clase, pero contienen valores diferentes.

---

# 4. Crear objetos con `new`

La sintaxis básica es:

```scala
new NombreClase(argumentos)
```

Ejemplo:

```scala
val persona =
  new Persona("Laura", 23)
```

El tipo de `persona` es:

```text
Persona
```

También puede escribirse explícitamente:

```scala
val persona: Persona =
  new Persona("Laura", 23)
```

---

# 5. Acceder a los atributos

Considere:

```scala
class Persona(
  val nombre: String,
  val edad: Int
)
```

Luego:

```scala
val persona =
  new Persona("Ana", 20)
```

Podemos acceder a sus atributos utilizando:

```scala
persona.nombre
```

Resultado:

```text
Ana
```

Y:

```scala
persona.edad
```

Resultado:

```text
20
```

La sintaxis general es:

```text
objeto.atributo
```

---

# 6. Constructor primario

En Scala, los parámetros declarados directamente junto al nombre de la clase forman parte del **constructor primario**.

Por ejemplo:

```scala
class Rectangulo(
  val base: Double,
  val altura: Double
)
```

Cuando escribimos:

```scala
val rectangulo =
  new Rectangulo(5.0, 3.0)
```

estamos enviando los valores necesarios al constructor de la clase.

En este caso:

```text
base = 5.0
altura = 3.0
```

---

# 7. Clases en Scala y Java

En lenguajes como Java, normalmente se escribe un constructor explícitamente.

Por ejemplo:

```java
class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
}
```

En Scala podemos expresar una estructura similar de manera más compacta:

```scala
class Persona(
  val nombre: String,
  val edad: Int
)
```

El constructor primario forma parte directamente de la declaración de la clase.

---

# 8. Atributos con `val`

Cuando un atributo se declara utilizando `val`, su referencia no puede reasignarse después de crear el objeto.

```scala
class Producto(
  val nombre: String,
  val precio: Double
)
```

Creamos:

```scala
val producto =
  new Producto("Teclado", 250.0)
```

Podemos consultar:

```scala
producto.precio
```

Pero no podemos hacer:

```scala
producto.precio = 300.0
```

Esto produce un error.

---

# 9. Atributos con `var`

Scala también permite declarar atributos mutables:

```scala
class Producto(
  val nombre: String,
  var precio: Double
)
```

Ahora sería posible:

```scala
val producto =
  new Producto("Teclado", 250.0)

producto.precio = 300.0
```

El objeto ha cambiado internamente.

---

# 10. `val` frente a `var`

| Declaración | ¿Puede reasignarse? |
| ----------- | ------------------- |
| `val`       | No                  |
| `var`       | Sí                  |

En programación funcional se prefiere:

```scala
val
```

porque favorece la **inmutabilidad**.

Esto hace que el comportamiento de los programas sea más predecible y fácil de razonar.

---

# 11. Métodos dentro de una clase

Una clase puede contener funciones que trabajan con los datos del objeto.

Dentro de una clase, estas funciones se denominan normalmente **métodos**.

Ejemplo:

```scala
class Rectangulo(
  val base: Double,
  val altura: Double
) {

  def area: Double =
    base * altura
}
```

Creamos:

```scala
val rectangulo =
  new Rectangulo(5.0, 3.0)
```

Podemos ejecutar:

```scala
rectangulo.area
```

Resultado:

```text
15.0
```

---

# 12. Métodos sin parámetros

Un método que no necesita parámetros puede escribirse:

```scala
def area: Double =
  base * altura
```

Otro ejemplo:

```scala
class Rectangulo(
  val base: Double,
  val altura: Double
) {

  def area: Double =
    base * altura

  def perimetro: Double =
    2 * (base + altura)
}
```

Uso:

```scala
val r =
  new Rectangulo(4.0, 3.0)

r.area
r.perimetro
```

Resultados:

```text
12.0
14.0
```

---

# 13. Métodos con parámetros

Los métodos también pueden recibir parámetros.

```scala
class Producto(
  val nombre: String,
  val precio: Double
) {

  def precioConDescuento(
    porcentaje: Double
  ): Double =
    precio - precio * porcentaje
}
```

Creamos:

```scala
val producto =
  new Producto("Monitor", 1500.0)
```

Luego:

```scala
producto.precioConDescuento(0.10)
```

Resultado:

```text
1350.0
```

---

# 14. Atributos y parámetros de métodos

Es importante distinguir entre ambos.

Considere:

```scala
class Producto(
  val nombre: String,
  val precio: Double
) {

  def precioConDescuento(
    porcentaje: Double
  ): Double =
    precio - precio * porcentaje
}
```

Los atributos del objeto son:

```text
nombre
precio
```

El parámetro del método es:

```text
porcentaje
```

Cada objeto mantiene sus propios atributos.

El parámetro solamente existe durante la ejecución del método.

---

# 15. Ejemplo completo

```scala
class Circulo(
  val radio: Double
) {

  def area: Double =
    Math.PI * radio * radio

  def diametro: Double =
    radio * 2

  def longitudCircunferencia: Double =
    2 * Math.PI * radio
}
```

Uso:

```scala
val circulo =
  new Circulo(5.0)

circulo.area
circulo.diametro
circulo.longitudCircunferencia
```

---

# 16. El uso de `this`

Dentro de una clase, `this` hace referencia al objeto actual.

Por ejemplo:

```scala
class Persona(
  val nombre: String,
  val edad: Int
) {

  def esMayorQue(
    otra: Persona
  ): Boolean =
    this.edad > otra.edad
}
```

Creamos:

```scala
val ana =
  new Persona("Ana", 25)

val carlos =
  new Persona("Carlos", 20)
```

Entonces:

```scala
ana.esMayorQue(carlos)
```

Resultado:

```text
true
```

---

# 17. `this` puede ser opcional

En muchos casos Scala permite omitir `this`.

Por ejemplo:

```scala
def esMayorQue(
  otra: Persona
): Boolean =
  edad > otra.edad
```

es equivalente a:

```scala
def esMayorQue(
  otra: Persona
): Boolean =
  this.edad > otra.edad
```

El uso de `this` puede ser útil cuando queremos dejar explícito que nos referimos al objeto actual.

---

# 18. Objetos como parámetros

Un método puede recibir otro objeto como parámetro.

Ejemplo:

```scala
class Punto(
  val x: Double,
  val y: Double
) {

  def distanciaHorizontal(
    otro: Punto
  ): Double =
    Math.abs(x - otro.x)
}
```

Uso:

```scala
val p1 =
  new Punto(3.0, 5.0)

val p2 =
  new Punto(8.0, 7.0)

p1.distanciaHorizontal(p2)
```

Resultado:

```text
5.0
```

---

# 19. Mutabilidad dentro de objetos

Considere:

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
  new Cuenta(1000.0)

cuenta.depositar(500.0)
```

Ahora:

```scala
cuenta.saldo
```

produce:

```text
1500.0
```

El objeto original fue modificado.

---

# 20. Una alternativa funcional

En programación funcional podemos evitar modificar el objeto original.

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
  new Cuenta(1000.0)

val cuenta2 =
  cuenta1.depositar(500.0)
```

Ahora:

```text
cuenta1.saldo = 1000.0
cuenta2.saldo = 1500.0
```

`cuenta1` continúa exactamente igual.

Se creó un nuevo objeto.

---

# 21. Métodos que devuelven objetos

Un método puede devolver un objeto de la misma clase.

Por ejemplo:

```scala
class Punto(
  val x: Double,
  val y: Double
) {

  def mover(
    dx: Double,
    dy: Double
  ): Punto =
    new Punto(
      x + dx,
      y + dy
    )
}
```

Uso:

```scala
val punto1 =
  new Punto(2.0, 3.0)

val punto2 =
  punto1.mover(5.0, 1.0)
```

Ahora:

```text
punto1 = (2.0, 3.0)

punto2 = (7.0, 4.0)
```

El objeto original no cambió.

---

# 22. Inmutabilidad aplicada a objetos

La inmutabilidad no significa que no podamos representar cambios.

Significa que, en lugar de modificar un objeto existente:

```text
objeto viejo
↓ modificar
objeto viejo modificado
```

podemos crear:

```text
objeto original
↓ operación
nuevo objeto
```

Así:

```text
objeto original → permanece igual
nuevo objeto    → contiene el nuevo estado
```

---

# 23. Ejemplo: Producto inmutable

```scala
class Producto(
  val nombre: String,
  val precio: Double,
  val cantidad: Int
) {

  def subtotal: Double =
    precio * cantidad

  def agregarUnidades(
    unidades: Int
  ): Producto =
    new Producto(
      nombre,
      precio,
      cantidad + unidades
    )

  def aplicarDescuento(
    porcentaje: Double
  ): Producto =
    new Producto(
      nombre,
      precio - precio * porcentaje,
      cantidad
    )
}
```

---

# 24. Utilizando `Producto`

```scala
val producto1 =
  new Producto(
    "Monitor",
    1500.0,
    2
  )
```

Su subtotal es:

```scala
producto1.subtotal
```

Resultado:

```text
3000.0
```

Podemos crear:

```scala
val producto2 =
  producto1.agregarUnidades(3)
```

Ahora:

```text
producto1.cantidad = 2
producto2.cantidad = 5
```

El primer producto no fue modificado.

---

# 25. Aplicar un descuento sin mutar

Podemos hacer:

```scala
val producto3 =
  producto1.aplicarDescuento(0.10)
```

Entonces:

```text
producto1.precio = 1500.0

producto3.precio = 1350.0
```

Nuevamente, se mantiene la inmutabilidad.

---

# 26. Métodos que devuelven valores frente a métodos que devuelven objetos

Un método puede devolver un valor simple:

```scala
def subtotal: Double =
  precio * cantidad
```

O puede devolver un objeto:

```scala
def agregarUnidades(
  unidades: Int
): Producto =
  new Producto(
    nombre,
    precio,
    cantidad + unidades
  )
```

El tipo de retorno indica qué produce cada método.

---

# 27. Encapsular comportamiento

Una ventaja de las clases es que los datos y las operaciones relacionadas pueden encontrarse en el mismo lugar.

Por ejemplo:

```scala
class Rectangulo(
  val base: Double,
  val altura: Double
) {

  def area: Double =
    base * altura

  def perimetro: Double =
    2 * (base + altura)
}
```

Los datos:

```text
base
altura
```

y los comportamientos:

```text
area
perimetro
```

pertenecen al mismo concepto: `Rectangulo`.

---

# 28. Clases y funciones puras

Los métodos también pueden comportarse como funciones puras.

Ejemplo:

```scala
class Rectangulo(
  val base: Double,
  val altura: Double
) {

  def area: Double =
    base * altura
}
```

Para el mismo objeto, `area` siempre devuelve el mismo resultado.

No:

* modifica atributos;
* modifica variables externas;
* produce efectos secundarios.

Esto permite combinar orientación a objetos con principios funcionales.

---

# 29. Estado mutable frente a estado inmutable

## Objeto mutable

```scala
class Contador(
  var valor: Int
) {

  def incrementar(): Unit =
    valor = valor + 1
}
```

Aquí el mismo objeto cambia.

---

## Objeto inmutable

```scala
class Contador(
  val valor: Int
) {

  def incrementar(): Contador =
    new Contador(valor + 1)
}
```

Aquí se devuelve un nuevo objeto.

---

# 30. Ejemplo de ejecución

```scala
val contador1 =
  new Contador(0)

val contador2 =
  contador1.incrementar()

val contador3 =
  contador2.incrementar()
```

Resultados:

```text
contador1.valor = 0
contador2.valor = 1
contador3.valor = 2
```

Cada objeto conserva su propio estado.

---

# 31. ¿Qué enfoque preferimos?

Scala permite ambos enfoques:

```text
Orientación a objetos mutable
```

y:

```text
Orientación a objetos inmutable
```

En programación funcional normalmente se prefiere:

* atributos con `val`;
* métodos sin efectos secundarios;
* creación de nuevos objetos cuando se necesita representar un cambio.

---

# 32. Ejercicio 1: Rectángulo

Cree una clase:

```scala
class Rectangulo(
  val base: Double,
  val altura: Double
)
```

Debe incluir los métodos:

```scala
def area: Double
```

```scala
def perimetro: Double
```

### Ejemplo

Para:

```text
base = 5
altura = 4
```

debe producir:

```text
area = 20
perimetro = 18
```

---

# 33. Ejercicio 2: Cuenta

Cree una clase inmutable:

```scala
class Cuenta(
  val titular: String,
  val saldo: Double
)
```

Incluya:

```scala
def depositar(
  monto: Double
): Cuenta
```

y:

```scala
def retirar(
  monto: Double
): Cuenta
```

Ambos métodos deberán devolver una nueva cuenta.

El objeto original no deberá modificarse.

---

# 34. Ejercicio 3: Punto

Cree:

```scala
class Punto(
  val x: Double,
  val y: Double
)
```

Incluya:

```scala
def mover(
  dx: Double,
  dy: Double
): Punto
```

El método deberá devolver un nuevo punto.

### Ejemplo

```text
Punto inicial: (2, 3)

mover(4, 2)

Nuevo punto: (6, 5)
```

---

# 35. Ejercicio 4: Producto

Cree:

```scala
class Producto(
  val nombre: String,
  val precio: Double,
  val cantidad: Int
)
```

Incluya:

```scala
def subtotal: Double
```

```scala
def agregarUnidades(
  unidades: Int
): Producto
```

```scala
def aplicarDescuento(
  porcentaje: Double
): Producto
```

Ningún método deberá modificar el objeto original.

---

# 36. Ejercicio 5: Temperatura

Cree una clase:

```scala
class Temperatura(
  val celsius: Double
)
```

Incluya métodos para:

```scala
def fahrenheit: Double
```

```scala
def kelvin: Double
```

y:

```scala
def aumentar(
  grados: Double
): Temperatura
```

El método `aumentar` deberá devolver una nueva temperatura.

---

# 37. Errores frecuentes

## Error 1. Intentar modificar un `val`

Si tenemos:

```scala
class Persona(
  val edad: Int
)
```

no podemos hacer:

```scala
persona.edad = 25
```

porque `edad` fue declarado con `val`.

---

# 38. Error: olvidar crear un nuevo objeto

Si queremos mantener inmutabilidad, esto no es apropiado:

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

Una alternativa funcional sería:

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

---

# 39. Error: confundir clase y objeto

Esto:

```scala
class Persona(
  val nombre: String
)
```

es una **clase**.

Esto:

```scala
val ana =
  new Persona("Ana")
```

es un **objeto**.

---

# 40. Error: confundir atributo y variable externa

Considere:

```scala
class Persona(
  val nombre: String
)
```

`nombre` pertenece a cada objeto `Persona`.

Por ejemplo:

```scala
val p1 =
  new Persona("Ana")

val p2 =
  new Persona("Luis")
```

Entonces:

```text
p1.nombre = "Ana"
p2.nombre = "Luis"
```

Cada objeto posee su propio valor.

---

# 41. Buenas prácticas

Al crear clases en Scala:

* Utilizar nombres de clases descriptivos.
* Utilizar nombres de clases comenzando con mayúscula.
* Preferir `val` sobre `var`.
* Mantener objetos inmutables cuando sea posible.
* Evitar efectos secundarios innecesarios.
* Crear métodos pequeños y con un propósito claro.
* Declarar explícitamente los tipos de retorno.
* Devolver nuevos objetos cuando una operación representa un cambio.
* Evitar modificar estado externo desde los métodos.

---

# 42. Resumen

Una clase define la estructura de un tipo de objeto:

```scala
class Persona(
  val nombre: String,
  val edad: Int
)
```

Un objeto es una instancia:

```scala
val persona =
  new Persona("Ana", 20)
```

Podemos acceder a sus atributos:

```scala
persona.nombre
persona.edad
```

Las clases pueden contener métodos:

```scala
class Rectangulo(
  val base: Double,
  val altura: Double
) {

  def area: Double =
    base * altura
}
```

En programación funcional preferimos atributos inmutables:

```scala
val
```

En lugar de modificar un objeto:

```text
objeto → modificar
```

podemos crear un nuevo objeto:

```text
objeto original
      ↓
    método
      ↓
nuevo objeto
```

La idea fundamental de esta sesión es:

> **Scala permite combinar orientación a objetos y programación funcional, modelando datos mediante clases y utilizando preferentemente objetos inmutables y métodos que producen nuevos valores.**
