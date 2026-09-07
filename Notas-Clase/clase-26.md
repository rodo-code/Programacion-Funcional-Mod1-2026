# Clase 26: Jerarquías selladas y Tipos Algebraicos de Datos

## 1. ¿Por qué necesitamos jerarquías selladas?

Supongamos que una aplicación maneja pedidos que pueden estar en distintos estados:

```text
Pendiente
Confirmado
Enviado
Entregado
```

Una primera solución podría representar el estado mediante un `String`:

```scala
val estado: String = "Pendiente"
```

Pero también sería posible escribir:

```scala
val estado: String = "Pendiiente"
```

El compilador no sabe que ese valor es incorrecto.

Podemos mejorar el diseño representando los estados como tipos:

```scala
sealed trait EstadoPedido

case object Pendiente extends EstadoPedido

case class Confirmado(
  codigo: String
) extends EstadoPedido

case class Enviado(
  guia: String
) extends EstadoPedido

case object Entregado extends EstadoPedido
```

Ahora el sistema tiene un conjunto definido de alternativas.

---

## 2. ¿Qué significa `sealed`?

La palabra:

```scala
sealed
```

significa que la jerarquía está **sellada**.

En Scala, los subtipos directos de una clase o trait sellado deben declararse en el mismo archivo fuente.

Por ejemplo:

```scala
sealed trait EstadoPedido
```

permite que el compilador conozca las alternativas directas de la jerarquía.

Esto es especialmente útil para el reconocimiento de patrones.

### Comparación

Una jerarquía abierta:

```scala
trait EstadoPedido
```

puede extenderse desde otros archivos.

Una jerarquía sellada:

```scala
sealed trait EstadoPedido
```

restringe dónde pueden declararse sus subtipos directos.

**Importante:** `sealed` no significa necesariamente que ningún subtipo pueda tener descendientes. Si queremos impedir que una clase concreta vuelva a heredarse, podemos utilizar `final`, como ocurre por defecto con las `case class`.

---

## 3. `sealed trait` y `sealed abstract class`

Podemos construir una jerarquía sellada utilizando cualquiera de estas formas:

```scala
sealed trait Figura
```

o:

```scala
sealed abstract class Figura
```

Ambas permiten representar una familia de tipos.

Por ejemplo:

```scala
sealed trait Figura

case class Circulo(
  radio: Double
) extends Figura

case class Rectangulo(
  base: Double,
  altura: Double
) extends Figura
```

También podríamos utilizar una clase abstracta cuando queremos compartir atributos o lógica de una clase base.

En esta sesión utilizaremos principalmente `sealed trait`, porque resulta sencillo para representar alternativas de datos.

---

## 4. Una jerarquía representa alternativas

La jerarquía anterior puede visualizarse así:

```text
             Figura
            /      \
       Circulo    Rectangulo
```

Esto significa que un valor de tipo:

```scala
Figura
```

puede representar cualquiera de esas alternativas.

Por ejemplo:

```scala
val figura1: Figura =
  Circulo(5)

val figura2: Figura =
  Rectangulo(4, 3)
```

El tipo general es `Figura`, pero cada valor conserva su estructura concreta.

---

## 5. Pattern Matching sobre una jerarquía sellada

Podemos analizar una figura mediante `match`:

```scala
def area(
  figura: Figura
): Double =
  figura match
    case Circulo(radio) =>
      Math.PI * radio * radio

    case Rectangulo(base, altura) =>
      base * altura
```

La función recibe un `Figura`, pero puede reconocer cuál de las alternativas representa.

Ejemplos:

```scala
area(Circulo(2))
```

```scala
area(Rectangulo(5, 4))
```

No necesitamos crear una función diferente para cada tipo.

---

## 6. Exhaustividad de patrones

Una ventaja importante de las jerarquías selladas es que Scala puede comprobar si un `match` contempla todas las alternativas conocidas.

Por ejemplo:

```scala
def area(
  figura: Figura
): Double =
  figura match
    case Circulo(radio) =>
      Math.PI * radio * radio
```

Falta el caso:

```scala
Rectangulo
```

El compilador puede advertir que el reconocimiento de patrones no es exhaustivo.

Si posteriormente agregamos:

```scala
case class Triangulo(
  base: Double,
  altura: Double
) extends Figura
```

deberemos considerar cómo afecta a las funciones que procesan `Figura`.

### Buena práctica

Cuando conocemos todas las alternativas, es preferible escribirlas explícitamente:

```scala
figura match
  case Circulo(radio) =>
    ...

  case Rectangulo(base, altura) =>
    ...

  case Triangulo(base, altura) =>
    ...
```

en lugar de utilizar siempre:

```scala
case _ =>
```

Un comodín puede ocultar que olvidamos implementar un caso nuevo.

---

## 7. `case object` frente a `case class`

Dentro de una jerarquía podemos utilizar ambos.

### `case object`

Representa una alternativa que no necesita datos adicionales:

```scala
case object Pendiente extends EstadoPedido
```

Solo existe un objeto `Pendiente`.

### `case class`

Representa una alternativa que necesita almacenar datos:

```scala
case class Enviado(
  guia: String
) extends EstadoPedido
```

Podemos crear distintos valores:

```scala
Enviado("GUIA-001")
```

```scala
Enviado("GUIA-002")
```

### Regla práctica

```text
Alternativa sin datos
→ case object

Alternativa con datos
→ case class
```

---

# Tipos Algebraicos de Datos

## 8. ¿Qué es un ADT?

Un **Tipo Algebraico de Datos** (*Algebraic Data Type*) es una forma de construir tipos combinando alternativas y datos.

Los dos conceptos principales son:

* **Tipos producto:** combinan varios datos.
* **Tipos suma:** representan una elección entre varias alternativas.

El nombre “algebraico” proviene de que estas construcciones se parecen a operaciones de producto y suma sobre la cantidad de valores posibles.

---

## 9. Tipos producto

Una `case class` que contiene varios atributos es un ejemplo de tipo producto.

```scala
case class Punto(
  x: Int,
  y: Int
)
```

Un `Punto` contiene:

```text
x Y y
```

No elegimos entre uno u otro; necesitamos ambos.

Otro ejemplo:

```scala
case class Producto(
  nombre: String,
  precio: Double,
  stock: Int
)
```

Un producto combina:

```text
nombre
+
precio
+
stock
```

Por eso se denomina **tipo producto**.

### Ejemplo con Boolean

```scala
case class Interruptores(
  primero: Boolean,
  segundo: Boolean
)
```

Cada `Boolean` tiene dos posibles valores.

Las combinaciones son:

```text
(false, false)
(false, true)
(true, false)
(true, true)
```

Es decir:

```text
2 × 2 = 4 combinaciones
```

---

## 10. Tipos suma

Un tipo suma representa una elección entre alternativas.

Por ejemplo:

```scala
sealed trait Resultado

case class Exito(
  mensaje: String
) extends Resultado

case class Error(
  codigo: Int
) extends Resultado
```

Un `Resultado` puede ser:

```text
Exito
O
Error
```

No necesita ser ambos al mismo tiempo.

La jerarquía representa una **suma de alternativas**.

---

## 11. Combinación de suma y producto

Podemos combinar ambos conceptos:

```scala
sealed trait MetodoPago

case class Tarjeta(
  numero: String,
  titular: String
) extends MetodoPago

case class Transferencia(
  banco: String,
  referencia: String
) extends MetodoPago

case object Efectivo extends MetodoPago
```

Aquí:

```text
MetodoPago
    |
    +-- Tarjeta
    |     número Y titular
    |
    +-- Transferencia
    |     banco Y referencia
    |
    +-- Efectivo
```

La jerarquía es un **tipo suma**.

Cada `case class` con varios atributos es un **tipo producto**.

---

## 12. ¿Por qué son útiles los ADTs?

Permiten representar estados válidos de manera explícita.

Por ejemplo, en lugar de:

```scala
case class Pago(
  tipo: String,
  numeroTarjeta: String,
  banco: String,
  referencia: String
)
```

donde muchos campos podrían no utilizarse según el tipo de pago, podemos representar cada alternativa con sus propios datos.

Así evitamos estructuras como:

```text
Pago en efectivo
+
número de tarjeta innecesario
+
banco innecesario
+
referencia innecesaria
```

El modelo expresa mejor qué información corresponde a cada caso.

---

## 13. Ejemplo integrado: resultado de una operación

Podemos definir:

```scala
sealed trait Resultado

case class Exito(
  mensaje: String
) extends Resultado

case class Error(
  codigo: Int,
  mensaje: String
) extends Resultado
```

Luego:

```scala
def describir(
  resultado: Resultado
): String =
  resultado match
    case Exito(mensaje) =>
      s"Operación exitosa: $mensaje"

    case Error(codigo, mensaje) =>
      s"Error $codigo: $mensaje"
```

Ejemplos:

```scala
describir(
  Exito("Pago realizado")
)
```

```scala
describir(
  Error(404, "Recurso no encontrado")
)
```

La función trabaja con un tipo general, pero reconoce todas sus alternativas.

---

## 14. ADTs y polimorfismo por subtipado

En el ejemplo anterior:

```text
Exito <: Resultado
Error <: Resultado
```

Por eso podemos escribir:

```scala
def describir(
  resultado: Resultado
): String
```

La función acepta ambos subtipos.

Esto conecta con el **polimorfismo por subtipado** estudiado anteriormente.

Sin embargo, ahora no solamente utilizamos métodos compartidos: también podemos reconocer la estructura específica de cada alternativa mediante `match`.

---

## 15. ADTs genéricos

También podemos combinar ADTs con polimorfismo paramétrico.

Por ejemplo:

```scala
sealed trait Resultado[+A]

case class Exito[A](
  valor: A
) extends Resultado[A]

case class Error(
  mensaje: String
) extends Resultado[Nothing]
```

Ahora podemos representar:

```scala
val resultado1: Resultado[Int] =
  Exito(100)
```

```scala
val resultado2: Resultado[String] =
  Exito("Correcto")
```

```scala
val resultado3: Resultado[Int] =
  Error("No se pudo calcular")
```

Esto funciona porque `Resultado` es covariante y `Nothing` es subtipo de todos los tipos.

Así podemos reutilizar la misma estructura para distintos resultados.

---

## 16. Pattern Matching con un ADT genérico

```scala
def describir[A](
  resultado: Resultado[A]
): String =
  resultado match
    case Exito(valor) =>
      s"Éxito: $valor"

    case Error(mensaje) =>
      s"Error: $mensaje"
```

La función puede trabajar con:

```text
Resultado[Int]
Resultado[String]
Resultado[Double]
```

sin duplicar la lógica.

---

# Patrones anidados

## 17. Reconocer estructuras dentro de otras estructuras

Supongamos:

```scala
case class Pedido(
  id: Int,
  estado: EstadoPedido
)
```

Podemos crear:

```scala
val pedido =
  Pedido(
    10,
    Enviado("GUIA-123")
  )
```

El objeto contiene otro objeto.

Podemos reconocer ambas estructuras al mismo tiempo:

```scala
def describirPedido(
  pedido: Pedido
): String =
  pedido match
    case Pedido(id, Enviado(guia)) =>
      s"Pedido $id enviado con guía $guia"

    case Pedido(id, Pendiente) =>
      s"Pedido $id pendiente"

    case Pedido(id, _) =>
      s"Pedido $id en otro estado"
```

---

## 18. ¿Cómo funciona un patrón anidado?

En:

```scala
case Pedido(id, Enviado(guia)) =>
```

Scala realiza conceptualmente:

```text
¿Es un Pedido?
    ↓
Sí
    ↓
Extraer id y estado
    ↓
¿El estado es Enviado?
    ↓
Sí
    ↓
Extraer guía
```

Los patrones permiten descomponer estructuras completas sin acceder manualmente a cada atributo.

---

## 19. Patrones anidados con condiciones

También podemos combinar patrones con guards:

```scala
def describirPedido(
  pedido: Pedido
): String =
  pedido match
    case Pedido(id, Enviado(guia))
        if guia.nonEmpty =>
      s"Pedido $id enviado con guía $guia"

    case Pedido(id, Enviado(_)) =>
      s"Pedido $id enviado sin guía"

    case Pedido(id, _) =>
      s"Pedido $id en otro estado"
```

Aquí combinamos:

* patrón de constructor;
* patrón anidado;
* variable;
* comodín;
* guard.

---

# Estructuras recursivas inmutables

## 20. ¿Qué es una estructura recursiva?

Una estructura recursiva es aquella que puede contener otras estructuras del mismo tipo.

Por ejemplo, un árbol puede contener otros árboles.

```text
Árbol
├── Hoja
└── Nodo
    ├── Árbol
    └── Árbol
```

Esta idea es similar a una función recursiva:

> Una estructura grande se construye a partir de estructuras más pequeñas del mismo tipo.

---

## 21. Modelado de un árbol binario

Podemos definir:

```scala
sealed trait Arbol
```

Una hoja contiene un valor:

```scala
case class Hoja(
  valor: Int
) extends Arbol
```

Un nodo contiene dos árboles:

```scala
case class Nodo(
  izquierda: Arbol,
  derecha: Arbol
) extends Arbol
```

La jerarquía completa es:

```scala
sealed trait Arbol

case class Hoja(
  valor: Int
) extends Arbol

case class Nodo(
  izquierda: Arbol,
  derecha: Arbol
) extends Arbol
```

---

## 22. ¿Por qué este árbol es recursivo?

Observe:

```scala
case class Nodo(
  izquierda: Arbol,
  derecha: Arbol
)
```

Cada atributo es nuevamente un:

```text
Arbol
```

Por tanto, un nodo puede contener:

```text
Hoja
```

o:

```text
Nodo
```

y esos nodos pueden contener otros árboles.

---

## 23. Construcción de un árbol

Podemos crear:

```scala
val arbol: Arbol =
  Nodo(
    Hoja(10),
    Nodo(
      Hoja(20),
      Hoja(30)
    )
  )
```

Representación:

```text
          Nodo
         /    \
    Hoja(10)   Nodo
              /    \
         Hoja(20) Hoja(30)
```

Los valores del árbol son:

```text
10, 20, 30
```

---

## 24. Recursión sobre árboles

Para procesar un árbol necesitamos considerar sus dos alternativas:

```text
Hoja
Nodo
```

Esto se parece a la recursión que ya conocemos.

### Caso base

```scala
case Hoja(valor) =>
```

La hoja no contiene árboles más pequeños.

### Caso recursivo

```scala
case Nodo(izquierda, derecha) =>
```

Procesamos recursivamente ambos subárboles.

---

## 25. Sumar los valores de un árbol

```scala
def suma(
  arbol: Arbol
): Int =
  arbol match
    case Hoja(valor) =>
      valor

    case Nodo(izquierda, derecha) =>
      suma(izquierda) + suma(derecha)
```

Ejemplo:

```scala
suma(arbol)
```

Resultado:

```text
60
```

Porque:

```text
10 + 20 + 30 = 60
```

---

## 26. Seguimiento de la recursión

Para:

```scala
Nodo(
  Hoja(10),
  Nodo(
    Hoja(20),
    Hoja(30)
  )
)
```

la función realiza:

```text
suma(Nodo(...))
    ↓
suma(Hoja(10)) + suma(Nodo(...))
    ↓
10 + (suma(Hoja(20)) + suma(Hoja(30)))
    ↓
10 + (20 + 30)
    ↓
60
```

La estructura del algoritmo sigue la estructura de los datos.

---

## 27. Contar hojas

Podemos implementar:

```scala
def contarHojas(
  arbol: Arbol
): Int =
  arbol match
    case Hoja(_) =>
      1

    case Nodo(izquierda, derecha) =>
      contarHojas(izquierda) +
        contarHojas(derecha)
```

Para el árbol anterior:

```text
3 hojas
```

---

## 28. Calcular la altura

Definiremos la altura como el número de niveles del árbol.

Una hoja tiene altura:

```text
1
```

Un nodo tiene:

```text
1 + máximo de las alturas de sus hijos
```

Implementación:

```scala
def altura(
  arbol: Arbol
): Int =
  arbol match
    case Hoja(_) =>
      1

    case Nodo(izquierda, derecha) =>
      1 + Math.max(
        altura(izquierda),
        altura(derecha)
      )
```

Para:

```text
          Nodo
         /    \
    Hoja(10)   Nodo
              /    \
         Hoja(20) Hoja(30)
```

la altura es:

```text
3
```

---

## 29. Buscar un valor

Podemos crear:

```scala
def contiene(
  arbol: Arbol,
  buscado: Int
): Boolean =
  arbol match
    case Hoja(valor) =>
      valor == buscado

    case Nodo(izquierda, derecha) =>
      contiene(izquierda, buscado) ||
        contiene(derecha, buscado)
```

Ejemplos:

```scala
contiene(arbol, 20)
```

Resultado:

```text
true
```

```scala
contiene(arbol, 99)
```

Resultado:

```text
false
```

---

## 30. Transformar un árbol sin modificarlo

Supongamos que queremos duplicar todos los valores.

No modificamos el árbol original.

Construimos uno nuevo:

```scala
def duplicar(
  arbol: Arbol
): Arbol =
  arbol match
    case Hoja(valor) =>
      Hoja(valor * 2)

    case Nodo(izquierda, derecha) =>
      Nodo(
        duplicar(izquierda),
        duplicar(derecha)
      )
```

Si tenemos:

```scala
val arbol1: Arbol =
  Nodo(
    Hoja(10),
    Hoja(20)
  )
```

podemos hacer:

```scala
val arbol2 =
  duplicar(arbol1)
```

Resultado:

```text
arbol1:
Nodo(Hoja(10), Hoja(20))

arbol2:
Nodo(Hoja(20), Hoja(40))
```

El árbol original permanece intacto.

---

## 31. Relación con programación funcional

Este modelo combina varios conceptos del curso:

```text
sealed trait
    ↓
Define las alternativas

case class
    ↓
Representa datos inmutables

match
    ↓
Descompone los datos

Recursión
    ↓
Procesa estructuras grandes

Funciones puras
    ↓
Devuelven resultados sin modificar el árbol
```

Por ejemplo:

```scala
def suma(arbol: Arbol): Int
```

recibe un valor y devuelve un resultado.

Mientras que:

```scala
def duplicar(arbol: Arbol): Arbol
```

recibe un árbol y devuelve otro árbol.

---

## 32. ADTs y estructuras recursivas

El árbol es también un tipo algebraico de datos.

Podemos expresarlo conceptualmente como:

```text
Arbol =
    Hoja(Int)
    O
    Nodo(Arbol, Arbol)
```

Es decir:

```text
Arbol = Int + (Arbol × Arbol)
```

La expresión representa:

* una **suma** entre `Hoja` y `Nodo`;
* un **producto** de dos árboles dentro de `Nodo`;
* una definición **recursiva** porque `Arbol` aparece dentro de sí mismo.

---

## 33. Relación con las listas

En la siguiente clase estudiaremos listas inmutables.

Una lista también puede entenderse recursivamente:

```text
Lista =
    Vacía
    O
    Elemento + RestoDeLaLista
```

Conceptualmente:

```text
Lista[A] =
    Vacía
    O
    Nodo(A, Lista[A])
```

Esto será la base para comprender:

```scala
Nil
```

y:

```scala
::
```

La misma idea de:

```text
caso base
+
caso recursivo
```

aparece tanto en árboles como en listas.

---

# Ejercicios de práctica

## Ejercicio 1. Sistema de notificaciones

Cree una jerarquía sellada:

```scala
sealed trait Notificacion
```

con las alternativas:

```text
Email
SMS
Push
```

Cada alternativa deberá almacenar únicamente los datos que necesita.

Implemente:

```scala
def describir(
  notificacion: Notificacion
): String
```

Utilice `match` para generar una descripción diferente según el tipo de notificación.

---

## Ejercicio 2. Resultado de una operación

Modele un ADT que represente:

```text
Éxito
Error
```

El éxito deberá contener un valor de tipo `Int`.

El error deberá contener un mensaje.

Implemente:

```scala
def obtenerValor(
  resultado: Resultado
): Int
```

Reglas:

```text
Éxito
→ devolver el valor

Error
→ devolver 0
```

---

## Ejercicio 3. Árbol binario

Utilizando:

```scala
sealed trait Arbol

case class Hoja(
  valor: Int
) extends Arbol

case class Nodo(
  izquierda: Arbol,
  derecha: Arbol
) extends Arbol
```

implemente:

```scala
def suma(arbol: Arbol): Int
```

```scala
def contarHojas(arbol: Arbol): Int
```

```scala
def altura(arbol: Arbol): Int
```

```scala
def contiene(
  arbol: Arbol,
  valor: Int
): Boolean
```

---

## Ejercicio 4. Transformación inmutable

Implemente:

```scala
def sumarAValores(
  arbol: Arbol,
  cantidad: Int
): Arbol
```

La función deberá devolver un nuevo árbol donde cada hoja tenga su valor incrementado en `cantidad`.

Ejemplo:

```text
Árbol original:

Nodo(
  Hoja(5),
  Hoja(10)
)
```

Si:

```text
cantidad = 3
```

el resultado deberá ser:

```text
Nodo(
  Hoja(8),
  Hoja(13)
)
```

El árbol original no deberá modificarse.

---

# Errores frecuentes

## 1. Confundir `sealed` con `final`

`sealed` restringe dónde pueden declararse los subtipos directos.

`final` impide que una clase sea heredada.

No significan lo mismo.

## 2. Utilizar `case object` cuando se necesitan datos

Esto:

```scala
case object Enviado
```

no almacena una guía de envío.

Si necesitamos datos:

```scala
case class Enviado(
  guia: String
)
```

es una mejor representación.

## 3. Olvidar alternativas en un `match`

Si una jerarquía tiene tres alternativas, debemos considerar las tres cuando sea necesario.

Las jerarquías selladas ayudan al compilador a detectar casos faltantes.

## 4. Olvidar el caso base en una estructura recursiva

Una función sobre árboles debe reconocer cuándo llega a una hoja.

Sin un caso base, no tendremos una definición recursiva completa.

## 5. Modificar el árbol original

En programación funcional preferimos:

```scala
val nuevoArbol =
  transformar(arbolOriginal)
```

en lugar de modificar los nodos existentes.

---

# Resumen

Una jerarquía sellada define una familia de alternativas:

```scala
sealed trait Figura

case class Circulo(
  radio: Double
) extends Figura

case class Rectangulo(
  base: Double,
  altura: Double
) extends Figura
```

Un ADT combina:

```text
Tipos suma
→ alternativas

Tipos producto
→ datos combinados
```

Por ejemplo:

```text
Resultado =
    Exito(valor)
    O
    Error(mensaje)
```

Las estructuras recursivas pueden modelarse utilizando estos mismos conceptos:

```scala
sealed trait Arbol

case class Hoja(
  valor: Int
) extends Arbol

case class Nodo(
  izquierda: Arbol,
  derecha: Arbol
) extends Arbol
```

Y procesarse mediante recursión y pattern matching:

```scala
def suma(
  arbol: Arbol
): Int =
  arbol match
    case Hoja(valor) =>
      valor

    case Nodo(izquierda, derecha) =>
      suma(izquierda) + suma(derecha)
```

La idea fundamental de esta sesión es:

> **Los ADTs permiten representar datos mediante alternativas bien definidas. Las jerarquías selladas ayudan a controlar esas alternativas, y el pattern matching permite procesarlas de forma clara. Cuando los datos son recursivos, podemos utilizar funciones recursivas para analizarlos y transformarlos sin modificar su estructura original.**
