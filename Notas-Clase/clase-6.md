# Clase 6: Fundamentos de Recursión en Scala

# 1. Introducción a la recursión

La **recursión** es una técnica de programación en la que una función se llama a sí misma para resolver un problema.

La idea principal consiste en transformar un problema en una versión más pequeña del mismo problema hasta llegar a una situación suficientemente sencilla que pueda resolverse directamente.

De manera general, una solución recursiva sigue la siguiente estructura:

```text
Problema
   ↓
Problema más pequeño
   ↓
Problema más pequeño
   ↓
Problema más pequeño
   ↓
Caso base
```

La recursión es especialmente importante en programación funcional porque permite expresar procesos repetitivos utilizando funciones y expresiones sin depender necesariamente de variables mutables o ciclos.

---

# 2. Relación con las funciones

Una función normal puede recibir parámetros, realizar un cálculo y devolver un resultado.

Por ejemplo:

```scala
def cuadrado(numero: Int): Int =
  numero * numero
```

Una función recursiva posee las mismas características, pero además puede realizar una llamada a sí misma.

Por ejemplo:

```scala
def cuentaRegresiva(numero: Int): Unit =
  if numero <= 0 then
    println("Fin")
  else
    println(numero)
    cuentaRegresiva(numero - 1)
```

Al ejecutar:

```scala
cuentaRegresiva(3)
```

Se obtiene:

```text
3
2
1
Fin
```

Durante la ejecución, la función realiza nuevas llamadas a sí misma:

```text
cuentaRegresiva(3)
        ↓
cuentaRegresiva(2)
        ↓
cuentaRegresiva(1)
        ↓
cuentaRegresiva(0)
        ↓
       Fin
```

---

# 3. Componentes de una función recursiva

Una función recursiva correctamente diseñada necesita considerar tres elementos fundamentales:

1. Caso base.
2. Caso recursivo.
3. Progreso hacia el caso base.

---

# 4. Caso base

El **caso base** representa una situación suficientemente sencilla para devolver directamente un resultado sin realizar otra llamada recursiva.

Es el punto en el que la recursión termina.

Por ejemplo, matemáticamente se define:

[
0! = 1
]

Por tanto, al calcular el factorial de `0`, no es necesario realizar ningún cálculo adicional.

En Scala:

```scala
if numero == 0 then
  1
```

El caso base evita que la función continúe llamándose indefinidamente.

---

# 5. Caso recursivo

El **caso recursivo** es la parte de la función que utiliza una nueva llamada a la misma función.

Esta llamada debe representar una versión más pequeña del problema original.

Para el factorial:

[
n! = n \times (n-1)!
]

Por ejemplo:

[
5! = 5 \times 4!
]

A su vez:

[
4! = 4 \times 3!
]

Y:

[
3! = 3 \times 2!
]

El problema se reduce progresivamente hasta alcanzar el caso base.

En Scala, esta relación puede expresarse como:

```scala
numero * factorial(numero - 1)
```

---

# 6. Progreso hacia el caso base

No es suficiente con tener un caso base y una llamada recursiva.

Cada llamada debe acercarse al caso base.

Considere:

```text
5 → 4 → 3 → 2 → 1 → 0
```

Si el caso base es `0`, esta secuencia eventualmente terminará.

En cambio:

```text
5 → 6 → 7 → 8 → 9 → ...
```

se aleja del caso base y la recursión no terminará.

Una función recursiva debe garantizar que las sucesivas llamadas eventualmente alcancen el caso base.

---

# 7. Ejemplo: factorial

El factorial de un número entero no negativo se define como el producto de todos los números enteros positivos desde ese número hasta `1`.

Por ejemplo:

[
5! = 5 \times 4 \times 3 \times 2 \times 1
]

Resultado:

[
5! = 120
]

También puede definirse recursivamente:

[
n! = n \times (n-1)!
]

con:

[
0! = 1
]

En Scala:

```scala
def factorial(numero: Int): Int =
  if numero == 0 then
    1
  else
    numero * factorial(numero - 1)
```

---

# 8. Seguimiento de una función recursiva

Considere:

```scala
factorial(4)
```

Inicialmente:

```text
factorial(4)
```

Como `4` no corresponde al caso base:

```text
4 * factorial(3)
```

La nueva llamada tampoco corresponde al caso base:

```text
4 * (3 * factorial(2))
```

Continuando:

```text
4 * (3 * (2 * factorial(1)))
```

Y:

```text
4 * (3 * (2 * (1 * factorial(0))))
```

Finalmente se alcanza:

```text
factorial(0)
```

El caso base devuelve `1`.

Por tanto:

```text
4 * (3 * (2 * (1 * 1)))
```

Ahora pueden resolverse las operaciones pendientes:

```text
4 * (3 * (2 * 1))
```

```text
4 * (3 * 2)
```

```text
4 * 6
```

```text
24
```

Por tanto:

[
4! = 24
]

---

# 9. Expansión y reducción

La ejecución de una función recursiva puede comprenderse mediante dos etapas conceptuales.

## 9.1 Expansión

Durante la expansión aparecen nuevas llamadas recursivas.

Para:

```scala
factorial(4)
```

se produce:

```text
factorial(4)
↓
4 * factorial(3)
↓
4 * 3 * factorial(2)
↓
4 * 3 * 2 * factorial(1)
↓
4 * 3 * 2 * 1 * factorial(0)
```

El proceso continúa hasta alcanzar el caso base.

---

## 9.2 Reducción

Una vez alcanzado el caso base, comienzan a resolverse las operaciones pendientes.

```text
factorial(0) = 1
↑
factorial(1) = 1
↑
factorial(2) = 2
↑
factorial(3) = 6
↑
factorial(4) = 24
```

El resultado final se obtiene después de resolver todas las operaciones pendientes.

---

# 10. Relación con el modelo de sustitución

Las funciones recursivas pueden analizarse utilizando el modelo de sustitución.

Considere:

```scala
def duplicar(numero: Int): Int =
  numero * 2
```

La expresión:

```scala
duplicar(5)
```

puede evaluarse sustituyendo el parámetro:

```text
duplicar(5)
→ 5 * 2
→ 10
```

La misma idea puede aplicarse a una función recursiva.

Considere:

```scala
def factorial(numero: Int): Int =
  if numero == 0 then
    1
  else
    numero * factorial(numero - 1)
```

Entonces:

```text
factorial(3)

→ 3 * factorial(2)

→ 3 * (2 * factorial(1))

→ 3 * (2 * (1 * factorial(0)))

→ 3 * (2 * (1 * 1))

→ 6
```

El modelo de sustitución permite observar claramente cómo una función recursiva construye su resultado.

---

# 11. Ejemplo: suma de los primeros números naturales

Considere el problema de sumar todos los números naturales desde `1` hasta un determinado número.

Por ejemplo:

[
suma(4)=4+3+2+1
]

Resultado:

[
suma(4)=10
]

La definición recursiva puede expresarse como:

[
suma(n)=n+suma(n-1)
]

y:

[
suma(0)=0
]

En Scala:

```scala
def sumarHasta(numero: Int): Int =
  if numero == 0 then
    0
  else
    numero + sumarHasta(numero - 1)
```

Para:

```scala
sumarHasta(3)
```

la evaluación será:

```text
sumarHasta(3)

→ 3 + sumarHasta(2)

→ 3 + 2 + sumarHasta(1)

→ 3 + 2 + 1 + sumarHasta(0)

→ 3 + 2 + 1 + 0

→ 6
```

---

# 12. Ejemplo: potencia

Una potencia puede expresarse como una multiplicación repetida.

Por ejemplo:

[
2^4 = 2 \times 2 \times 2 \times 2
]

También puede definirse recursivamente:

[
base^{exponente}
================

base \times base^{exponente-1}
]

Para exponentes enteros no negativos se tiene:

[
base^0 = 1
]

Una implementación posible es:

```scala
def potencia(base: Int, exponente: Int): Int =
  if exponente == 0 then
    1
  else
    base * potencia(base, exponente - 1)
```

Por ejemplo:

```scala
potencia(2, 3)
```

se evalúa como:

```text
potencia(2, 3)

→ 2 * potencia(2, 2)

→ 2 * 2 * potencia(2, 1)

→ 2 * 2 * 2 * potencia(2, 0)

→ 2 * 2 * 2 * 1

→ 8
```

---

# 13. Recursión infinita

Una función recursiva incorrectamente diseñada puede continuar realizando llamadas sin alcanzar nunca el caso base.

## Ausencia de caso base

Por ejemplo:

```scala
def contar(numero: Int): Int =
  contar(numero - 1)
```

No existe ninguna condición que detenga la función.

La ejecución continuará:

```text
contar(5)
→ contar(4)
→ contar(3)
→ contar(2)
→ contar(1)
→ contar(0)
→ contar(-1)
→ contar(-2)
→ ...
```

---

# 14. Alejarse del caso base

Una función puede tener un caso base y aun así no terminar.

Por ejemplo:

```scala
def contar(numero: Int): Int =
  if numero == 0 then
    0
  else
    contar(numero + 1)
```

Si se ejecuta:

```scala
contar(5)
```

se obtiene conceptualmente:

```text
5
→ 6
→ 7
→ 8
→ 9
→ ...
```

La función posee un caso base en `0`, pero cada llamada se aleja de ese valor.

---

# 15. Dominio de entrada

También es importante establecer qué valores acepta una función.

Considere:

```scala
def sumarHasta(numero: Int): Int =
  if numero == 0 then
    0
  else
    numero + sumarHasta(numero - 1)
```

Para valores positivos:

```text
3 → 2 → 1 → 0
```

la función termina correctamente.

Sin embargo, si recibe:

```scala
sumarHasta(-3)
```

las llamadas serían:

```text
-3 → -4 → -5 → -6 → ...
```

Nunca alcanzaría `0`.

Por esta razón, cuando se diseña una función también debe definirse correctamente su **dominio de entrada**.

Por ejemplo, una función puede estar diseñada exclusivamente para:

```text
numero >= 0
```

---

# 16. Recursión y funciones puras

La recursión se relaciona naturalmente con la programación funcional.

Considere:

```scala
def factorial(numero: Int): Int =
  if numero == 0 then
    1
  else
    numero * factorial(numero - 1)
```

Esta función:

* utiliza únicamente sus parámetros;
* no modifica variables externas;
* no utiliza `var`;
* no necesita un ciclo;
* no produce efectos secundarios;
* siempre devuelve el mismo resultado para la misma entrada.

Por tanto, es una **función pura**.

---

# 17. Recursión frente a iteración

En programación imperativa es común expresar procesos repetitivos mediante ciclos.

Por ejemplo:

```text
Repetir una operación
↓
Modificar una variable
↓
Comprobar una condición
↓
Continuar o terminar
```

La recursión plantea el problema de otra manera:

```text
Resolver el problema
↓
Resolver una versión más pequeña
↓
Resolver una versión más pequeña
↓
Llegar al caso base
```

En programación funcional, la recursión constituye una herramienta importante para expresar procesos repetitivos sin depender de variables mutables.

---

# 18. Diseño de una función recursiva

Antes de implementar una solución recursiva es necesario identificar tres elementos.

## 1. Caso base

¿Qué situación puede resolverse directamente?

## 2. Caso recursivo

¿Cómo puede expresarse el problema utilizando una versión más pequeña del mismo problema?

## 3. Progreso

¿Cada nueva llamada se acerca realmente al caso base?

Una función recursiva correctamente diseñada debe responder claramente estas tres preguntas.

---

# Ejercicios para desarrollar en clase

## Ejercicio 1 — Multiplicación mediante sumas

Desarrolle una función recursiva que reciba dos números enteros no negativos y calcule su multiplicación utilizando sumas sucesivas.

No utilice el operador de multiplicación (`*`) para realizar el cálculo.

Ejemplos de resultados:

```text
multiplicar(5, 3) = 15
multiplicar(7, 4) = 28
multiplicar(8, 0) = 0
```

---

## Ejercicio 2 — Contar dígitos

Desarrolle una función recursiva que reciba un número entero positivo y determine la cantidad de dígitos que contiene.

Ejemplos de resultados:

```text
contarDigitos(8) = 1
contarDigitos(42) = 2
contarDigitos(5832) = 4
contarDigitos(123456) = 6
```

---

# Ejercicios para casa

## Ejercicio 1 — Suma de dígitos

Desarrolle una función recursiva que reciba un número entero no negativo y devuelva la suma de sus dígitos.

Ejemplos:

```text
sumarDigitos(352) = 10
sumarDigitos(8194) = 22
sumarDigitos(7) = 7
```

---

## Ejercicio 2 — Invertir un texto

Desarrolle una función recursiva que reciba un `String` y devuelva el texto con sus caracteres en orden inverso.

Ejemplos:

```text
invertir("Scala") = "alacS"
invertir("Hola") = "aloH"
invertir("recursion") = "noisrucer"
```


# Resumen

Una **función recursiva** es una función que se llama a sí misma para resolver una versión más pequeña del mismo problema.

Toda solución recursiva debe considerar:

```text
CASO BASE
    +
CASO RECURSIVO
    +
PROGRESO HACIA EL CASO BASE
```

El **caso base** permite terminar la recursión.

El **caso recursivo** transforma el problema en una versión más pequeña.

El **progreso** garantiza que las llamadas eventualmente alcancen el caso base.

La ejecución puede comprenderse mediante dos etapas:

```text
EXPANSIÓN
    ↓
CASO BASE
    ↓
REDUCCIÓN
```

El modelo de sustitución permite seguir paso a paso la evaluación de una función recursiva.

La recursión es especialmente importante dentro de la programación funcional porque permite expresar procesos repetitivos mediante funciones, evitando en muchos casos la necesidad de estado mutable.
