# Clase 25: Case Classes y Pattern Matching en Scala

# 1. Introducción

Hasta ahora hemos creado clases de esta forma:

```scala
class Persona(
  val nombre: String,
  val edad: Int
)
```

Y objetos:

```scala
val persona =
  new Persona(
    "Ana",
    25
  )
```

Esto funciona correctamente.

Sin embargo, cuando una clase se utiliza principalmente para **representar datos**, Scala proporciona una alternativa especialmente útil:

```scala
case class
```

Por ejemplo:

```scala
case class Persona(
  nombre: String,
  edad: Int
)
```

Las `case class` son muy utilizadas en programación funcional porque facilitan:

- representar datos;
- trabajar con objetos inmutables;
- comparar valores;
- crear copias modificadas;
- descomponer objetos;
- utilizar `pattern matching`.

---

# 2. Primera `case class`

Definimos:

```scala
case class Persona(
  nombre: String,
  edad: Int
)
```

Podemos crear directamente:

```scala
val persona =
  Persona(
    "Ana",
    25
  )
```

Observe que no necesitamos escribir:

```scala
new Persona(...)
```

Podemos utilizar directamente:

```scala
Persona(...)
```

---

# 3. ¿Por qué no necesitamos `new`?

Cuando declaramos:

```scala
case class Persona(
  nombre: String,
  edad: Int
)
```

Scala genera automáticamente un **objeto acompañante** con funcionalidades útiles, incluyendo un método `apply`.

Por eso:

```scala
Persona("Ana", 25)
```

es posible.

Conceptualmente es similar a:

```scala
Persona.apply(
  "Ana",
  25
)
```

---

# 4. Los parámetros son accesibles automáticamente

En una clase normal:

```scala
class Persona(
  nombre: String,
  edad: Int
)
```

los parámetros no son necesariamente atributos públicos.

Con una `case class`:

```scala
case class Persona(
  nombre: String,
  edad: Int
)
```

podemos acceder directamente:

```scala
val persona =
  Persona("Ana", 25)

persona.nombre
```

Resultado:

```text
Ana
```

También:

```scala
persona.edad
```

Resultado:

```text
25
```

---

# 5. Las `case class` son inmutables por defecto

Normalmente declaramos:

```scala
case class Persona(
  nombre: String,
  edad: Int
)
```

Sus parámetros actúan como valores inmutables.

Por ejemplo:

```scala
val persona =
  Persona("Ana", 25)
```

No podemos hacer:

```scala
persona.edad = 26
```

En programación funcional esto es conveniente porque evitamos modificar el estado de objetos existentes.

---

# 6. Modificar datos de manera inmutable con `copy`

Suponga:

```scala
val persona1 =
  Persona(
    "Ana",
    25
  )
```

Queremos representar que ahora tiene `26` años.

No modificamos `persona1`.

Creamos una copia:

```scala
val persona2 =
  persona1.copy(
    edad = 26
  )
```

Ahora:

```scala
println(persona1)
println(persona2)
```

Resultado:

```text
Persona(Ana,25)
Persona(Ana,26)
```

El objeto original permanece sin cambios.

---

# 7. `copy`

El método `copy` es generado automáticamente por las `case class`.

Podemos cambiar únicamente los atributos necesarios.

```scala
case class Producto(
  nombre: String,
  precio: Double,
  stock: Int
)
```

```scala
val producto1 =
  Producto(
    "Teclado",
    250.0,
    10
  )

val producto2 =
  producto1.copy(
    stock = 9
  )
```

Resultado:

```text
producto1.stock = 10
producto2.stock = 9
```

---

# 8. `toString` automático

Con una `case class`:

```scala
case class Producto(
  nombre: String,
  precio: Double
)
```

podemos hacer:

```scala
val producto =
  Producto(
    "Mouse",
    120.0
  )

println(producto)
```

Resultado aproximado:

```text
Producto(Mouse,120.0)
```

Scala genera automáticamente una representación legible del objeto.

---

# 9. Comparación automática

```scala
val persona1 = Persona("Ana", 25)
val persona2 = Persona("Ana", 25)
```

Podemos comparar:

```scala
persona1 == persona2
```

Resultado:

```text
true
```

Las `case class` comparan sus **datos**, no solamente si ambas variables apuntan exactamente al mismo objeto.

---

# 10. Clase tradicional vs `case class`

### Clase tradicional

```scala
class Producto(
  val nombre: String,
  val precio: Double
)
```

Creación:

```scala
new Producto(
  "Mouse",
  100
)
```

### Case class

```scala
case class Producto(
  nombre: String,
  precio: Double
)
```

Creación:

```scala
Producto(
  "Mouse",
  100
)
```

---

# 11. Principales ventajas de `case class`

Scala proporciona automáticamente funcionalidades como:

```text
apply
copy
equals
hashCode
toString
unapply
```

`unapply` será especialmente importante para el **pattern matching**.

---

# 12. ¿Cuándo utilizar una `case class`?

Una `case class` es especialmente adecuada cuando queremos representar:

- personas;
- productos;
- pedidos;
- coordenadas;
- figuras;
- resultados;
- eventos;
- estados;
- datos de una aplicación.

Ejemplos:

```scala
case class Punto(
  x: Double,
  y: Double
)
```

```scala
case class Pedido(
  id: Int,
  total: Double
)
```

---

# 13. Case Objects

Scala también permite:

```scala
case object
```

Un `case object` representa un único objeto.

```scala
case object Activo
case object Inactivo
```

No necesitamos crear:

```scala
new Activo
```

El propio `Activo` es el objeto.

---

# 14. Ejemplo con estados

```scala
case object Pendiente
case object Procesando
case object Completado
```

```scala
val estado = Pendiente
```

Los `case object` son especialmente útiles para representar valores únicos o estados concretos.

---

# 15. ¿Qué es Pattern Matching?

El **reconocimiento de patrones** o **pattern matching** permite analizar un valor y ejecutar diferentes expresiones dependiendo de su estructura o contenido.

En Scala utilizamos `match`.

```scala
valor match
  case patron1 =>
    resultado1

  case patron2 =>
    resultado2

  case patron3 =>
    resultado3
```

---

# 16. Primer ejemplo de `match`

```scala
def describir(
  numero: Int
): String =
  numero match
    case 0 =>
      "Cero"

    case 1 =>
      "Uno"

    case 2 =>
      "Dos"

    case _ =>
      "Otro número"
```

Uso:

```scala
describir(1)
```

Resultado:

```text
Uno
```

---

# 17. `match` es una expresión

En Scala, `match` produce un resultado.

```scala
val resultado =
  numero match
    case 0 => "Cero"
    case 1 => "Uno"
    case _ => "Otro"
```

El resultado del patrón seleccionado se convierte en el valor de `resultado`.

---

# 18. Patrones constantes

```scala
def dia(
  numero: Int
): String =
  numero match
    case 1 => "Lunes"
    case 2 => "Martes"
    case 3 => "Miércoles"
    case _ => "Otro"
```

Aquí `case 1`, `case 2` y `case 3` son patrones constantes.

---

# 19. Patrón comodín `_`

El símbolo `_` significa:

> cualquier otro valor.

```scala
numero match
  case 0 =>
    "Cero"

  case _ =>
    "No es cero"
```

---

# 20. Patrones con variables

```scala
def describir(
  numero: Int
): String =
  numero match
    case 0 =>
      "Cero"

    case n =>
      s"El número es $n"
```

Si ejecutamos:

```scala
describir(25)
```

Scala entra en `case n` y captura:

```text
n = 25
```

---

# 21. Orden de los patrones

Scala evalúa los patrones de arriba hacia abajo.

Incorrecto:

```scala
numero match
  case n =>
    s"Número: $n"

  case 0 =>
    "Cero"
```

El segundo caso nunca será alcanzado porque `case n` acepta cualquier valor.

Los patrones más generales deben colocarse normalmente al final.

---

# 22. Pattern Matching con Strings

```scala
def idioma(
  codigo: String
): String =
  codigo match
    case "es" => "Español"
    case "en" => "Inglés"
    case "fr" => "Francés"
    case _ => "Idioma desconocido"
```

---

# 23. Pattern Matching con Boolean

```scala
def estado(
  activo: Boolean
): String =
  activo match
    case true => "Activo"
    case false => "Inactivo"
```

Aunque en este caso un `if` podría ser más simple, sirve para comprender que `match` puede trabajar con diferentes tipos.

---

# 24. Pattern Matching con `case object`

```scala
case object Pendiente
case object Procesando
case object Completado
```

```scala
def descripcionEstado(
  estado: Any
): String =
  estado match
    case Pendiente =>
      "El proceso está pendiente"

    case Procesando =>
      "El proceso está ejecutándose"

    case Completado =>
      "El proceso terminó"

    case _ =>
      "Estado desconocido"
```

Más adelante veremos cómo modelar estos estados sin recurrir a `Any`.

---

# 25. Pattern Matching con Case Classes

```scala
case class Persona(
  nombre: String,
  edad: Int
)
```

```scala
def mostrar(
  persona: Persona
): String =
  persona match
    case Persona(nombre, edad) =>
      s"$nombre tiene $edad años"
```

---

# 26. Descomposición de objetos

Cuando escribimos:

```scala
case Persona(nombre, edad) =>
```

Scala descompone el objeto.

Si tenemos:

```scala
val persona =
  Persona(
    "Carlos",
    30
  )
```

el patrón produce conceptualmente:

```text
nombre = "Carlos"
edad = 30
```

Esto se conoce como **patrón de constructor**.

---

# 27. Patrones de constructor

```scala
case class Producto(
  nombre: String,
  precio: Double
)
```

```scala
def descripcion(
  producto: Producto
): String =
  producto match
    case Producto(nombre, precio) =>
      s"$nombre cuesta $precio Bs"
```

Uso:

```scala
descripcion(
  Producto("Mouse", 150)
)
```

Resultado:

```text
Mouse cuesta 150.0 Bs
```

---

# 28. Ignorar partes de un objeto

Si solamente necesitamos el nombre:

```scala
case Producto(nombre, _) =>
  nombre
```

El `_` indica que existe un valor en esa posición, pero no necesitamos utilizarlo.

---

# 29. Coincidir con valores específicos dentro de una Case Class

```scala
def analizar(
  persona: Persona
): String =
  persona match
    case Persona("Ana", 25) =>
      "Es Ana de 25 años"

    case Persona(nombre, edad) =>
      s"$nombre tiene $edad años"
```

El primer patrón solamente coincide exactamente con `Persona("Ana", 25)`.

---

# 30. Pattern Guards

Podemos agregar una condición con `if` después del patrón.

```scala
def clasificar(
  persona: Persona
): String =
  persona match
    case Persona(nombre, edad)
        if edad >= 18 =>
      s"$nombre es mayor de edad"

    case Persona(nombre, _) =>
      s"$nombre es menor de edad"
```

La condición `if edad >= 18` se denomina **Pattern Guard**.

---

# 31. Otro ejemplo de guard

```scala
case class Producto(
  nombre: String,
  precio: Double
)
```

```scala
def clasificar(
  producto: Producto
): String =
  producto match
    case Producto(nombre, precio)
        if precio >= 1000 =>
      s"$nombre es un producto costoso"

    case Producto(nombre, _) =>
      s"$nombre es un producto estándar"
```

---

# 32. Varios Guards

```scala
def clasificarNumero(
  numero: Int
): String =
  numero match
    case n if n < 0 =>
      "Negativo"

    case n if n == 0 =>
      "Cero"

    case n if n % 2 == 0 =>
      "Positivo par"

    case n =>
      "Positivo impar"
```

---

# 33. Pattern Matching con Tuplas

```scala
val coordenada =
  (10, 20)
```

```scala
coordenada match
  case (x, y) =>
    s"x = $x, y = $y"
```

---

# 34. Ejemplo con coordenadas

```scala
def ubicar(
  punto: (Int, Int)
): String =
  punto match
    case (0, 0) =>
      "Origen"

    case (0, y) =>
      s"Eje Y: $y"

    case (x, 0) =>
      s"Eje X: $x"

    case (x, y) =>
      s"Punto ($x, $y)"
```

---

# 35. Patrones anidados

Los objetos pueden contener otros objetos.

```scala
case class Direccion(
  ciudad: String,
  pais: String
)
```

```scala
case class Persona(
  nombre: String,
  edad: Int,
  direccion: Direccion
)
```

```scala
val persona =
  Persona(
    "Ana",
    25,
    Direccion(
      "Cochabamba",
      "Bolivia"
    )
  )
```

---

# 36. Descomposición anidada

```scala
def obtenerCiudad(
  persona: Persona
): String =
  persona match
    case Persona(
      _,
      _,
      Direccion(ciudad, _)
    ) =>
      ciudad
```

Resultado:

```text
Cochabamba
```

---

# 37. Ejemplo de patrón anidado

```scala
def describir(
  persona: Persona
): String =
  persona match
    case Persona(
      nombre,
      _,
      Direccion(
        "Cochabamba",
        "Bolivia"
      )
    ) =>
      s"$nombre vive en Cochabamba"

    case Persona(
      nombre,
      _,
      Direccion(ciudad, pais)
    ) =>
      s"$nombre vive en $ciudad, $pais"
```

---

# 38. Pattern Matching con diferentes tipos

```scala
def describir(
  valor: Any
): String =
  valor match
    case numero: Int =>
      s"Entero: $numero"

    case texto: String =>
      s"Texto: $texto"

    case decimal: Double =>
      s"Double: $decimal"

    case _ =>
      "Otro tipo"
```

---

# 39. Pattern Matching y `Any`

Aunque el ejemplo anterior utiliza `Any`, no significa que debamos utilizarlo frecuentemente.

Si sabemos exactamente qué tipos pueden aparecer, es mejor definir un modelo más preciso.

Más adelante utilizaremos:

```scala
sealed trait
```

junto con:

```scala
case class
case object
```

para representar conjuntos cerrados de posibilidades.

---

# 40. Pattern Matching frente a muchos `if`

```scala
def descripcion(
  codigo: Int
): String =
  codigo match
    case 1 => "Pendiente"
    case 2 => "Procesando"
    case 3 => "Completado"
    case _ => "Desconocido"
```

`match` resulta especialmente poderoso cuando no solamente comprobamos valores, sino también la **estructura de los datos**.

---

# 41. Ejemplo integrado: sistema de pagos

```scala
case class Pago(
  cliente: String,
  monto: Double,
  metodo: String
)
```

```scala
def procesar(
  pago: Pago
): String =
  pago match
    case Pago(cliente, monto, "Efectivo") =>
      s"$cliente pagó $monto en efectivo"

    case Pago(cliente, monto, "Tarjeta")
        if monto > 1000 =>
      s"Pago grande con tarjeta de $cliente"

    case Pago(cliente, monto, metodo) =>
      s"$cliente pagó $monto mediante $metodo"
```

Aquí combinamos:

- `case class`;
- patrones de constructor;
- valores constantes;
- variables;
- guards.

---

# 42. Ejemplo integrado: actualización inmutable

```scala
case class Cuenta(
  titular: String,
  saldo: Double
)
```

```scala
def depositar(
  cuenta: Cuenta,
  monto: Double
): Cuenta =
  if monto > 0 then
    cuenta.copy(
      saldo =
        cuenta.saldo + monto
    )
  else
    cuenta
```

```scala
val cuenta1 =
  Cuenta(
    "Ana",
    1000
  )

val cuenta2 =
  depositar(
    cuenta1,
    500
  )
```

Resultado:

```text
cuenta1.saldo = 1000
cuenta2.saldo = 1500
```

Esto muestra la relación entre:

```text
case class
+
copy
+
inmutabilidad
+
funciones puras
```

---

# 43. Case Classes y Programación Funcional

Las `case class` son especialmente útiles en programación funcional porque permiten tratar los datos como valores.

En lugar de:

```text
crear objeto
↓
modificar objeto
↓
volver a modificar
```

preferimos:

```text
valor original
↓
transformación
↓
nuevo valor
```

Ejemplo:

```scala
val producto2 =
  producto1.copy(
    precio = 250
  )
```

El objeto original permanece intacto.

---

# 44. Pattern Matching y Programación Funcional

```scala
def precioFinal(
  producto: Producto
): Double =
  producto match
    case Producto(_, precio)
        if precio >= 1000 =>
      precio * 0.90

    case Producto(_, precio) =>
      precio
```

La función:

- recibe un valor;
- reconoce su estructura;
- devuelve un nuevo valor;
- no necesita modificar estado.

---

# 45. Ejercicio 1: Producto

Cree:

```scala
case class Producto(
  nombre: String,
  precio: Double,
  stock: Int
)
```

Implemente:

```scala
def clasificar(
  producto: Producto
): String
```

Reglas:

```text
stock = 0
→ "Sin stock"

precio >= 1000
→ "Producto premium"

cualquier otro caso
→ "Producto estándar"
```

Utilice `match`.

---

# 46. Ejercicio 2: Coordenadas

Implemente:

```scala
def posicion(
  punto: (Int, Int)
): String
```

Debe reconocer:

```text
(0, 0)
→ Origen

(0, y)
→ Eje Y

(x, 0)
→ Eje X

(x, y)
→ Otro punto
```

Utilice pattern matching sobre tuplas.

---

# 47. Ejercicio 3: Estudiante

Cree:

```scala
case class Estudiante(
  nombre: String,
  nota: Double
)
```

Implemente:

```scala
def resultado(
  estudiante: Estudiante
): String
```

Reglas:

```text
nota >= 90
→ "Excelente"

nota >= 70
→ "Aprobado"

nota >= 51
→ "Regular"

nota < 51
→ "Reprobado"
```

Utilice pattern guards.

---

# 48. Ejercicio 4: Copias inmutables

Cree:

```scala
case class Inventario(
  producto: String,
  cantidad: Int
)
```

Implemente:

```scala
def agregar(
  inventario: Inventario,
  cantidad: Int
): Inventario
```

La función deberá devolver un nuevo inventario.

Ejemplo:

```text
Inventario("Teclado", 10)

agregar 5

→ Inventario("Teclado", 15)
```

El objeto original no deberá modificarse.

---

# 49. Ejercicio 5: Patrones anidados

Cree:

```scala
case class Direccion(
  ciudad: String,
  pais: String
)
```

y:

```scala
case class Cliente(
  nombre: String,
  direccion: Direccion
)
```

Implemente:

```scala
def ubicacion(
  cliente: Cliente
): String
```

La función deberá utilizar un patrón anidado para devolver:

```text
"Cliente boliviano"
```

si el país es `Bolivia`, y:

```text
"Cliente extranjero"
```

en cualquier otro caso.

---

# 50. Errores frecuentes

## Error 1: olvidar `case`

Una clase tradicional no posee automáticamente todas las funcionalidades de una `case class`.

## Error 2: modificar una `case class`

Evitar:

```scala
persona.edad = 30
```

Preferir:

```scala
val personaNueva =
  persona.copy(
    edad = 30
  )
```

## Error 3: colocar primero un patrón demasiado general

Incorrecto:

```scala
numero match
  case n =>
    s"$n"

  case 0 =>
    "Cero"
```

Mejor:

```scala
numero match
  case 0 =>
    "Cero"

  case n =>
    s"$n"
```

## Error 4: olvidar un caso general

Si no cubrimos todas las posibilidades, podemos producir un error en tiempo de ejecución.

Cuando sea necesario, utilizar:

```scala
case _ =>
```

---

# 51. Buenas prácticas

Al trabajar con `case class`:

- Preferir datos inmutables.
- Utilizar `copy` para representar cambios.
- Utilizar nombres de clases que representen conceptos del dominio.
- Evitar agregar mutabilidad sin necesidad.
- Aprovechar el pattern matching para descomponer los datos.

Al trabajar con `match`:

- Colocar primero los patrones más específicos.
- Colocar los patrones generales al final.
- Utilizar `_` cuando un valor no es importante.
- Utilizar guards cuando la estructura del patrón no es suficiente.
- Evitar `Any` cuando existe un modelo de tipos más específico.

---

# 52. Resumen

Una `case class` permite representar datos de manera compacta:

```scala
case class Persona(
  nombre: String,
  edad: Int
)
```

Podemos crearla sin `new`:

```scala
val persona =
  Persona(
    "Ana",
    25
  )
```

Podemos crear versiones modificadas sin alterar el original:

```scala
val persona2 =
  persona.copy(
    edad = 26
  )
```

El pattern matching permite reconocer valores:

```scala
numero match
  case 0 =>
    "Cero"

  case _ =>
    "Otro"
```

También puede descomponer una `case class`:

```scala
persona match
  case Persona(nombre, edad) =>
    s"$nombre tiene $edad años"
```

Puede utilizar condiciones:

```scala
case Persona(nombre, edad)
    if edad >= 18 =>
```

y estructuras anidadas:

```scala
case Persona(
  nombre,
  _,
  Direccion(ciudad, pais)
) =>
```

La relación principal es:

```text
Case Class
    ↓
Representa datos

Pattern Matching
    ↓
Descompone y analiza datos

copy
    ↓
Permite transformarlos sin mutación
```

La idea fundamental de esta sesión es:

> **Las `case class` permiten modelar datos inmutables de forma sencilla, mientras que el pattern matching permite analizar y descomponer esos datos declarativamente. Juntas constituyen una de las herramientas más importantes de la programación funcional en Scala.**
