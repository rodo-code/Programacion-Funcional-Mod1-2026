# Clase 16: Composición de Funciones y Abstracciones Funcionales

# 1. Recordatorio: funciones como valores

Ya sabemos que una función puede almacenarse en un `val`.

Por ejemplo:

```scala
val duplicar: Int => Int =
  numero => numero * 2
```

También:

```scala
val sumarUno: Int => Int =
  numero => numero + 1
```

Podemos ejecutarlas de manera independiente:

```scala
duplicar(5)
```

Resultado:

```text
10
```

Y:

```scala
sumarUno(5)
```

Resultado:

```text
6
```

En esta sesión veremos cómo podemos **combinar funciones**.

---

# 2. ¿Qué significa componer funciones?

La composición consiste en utilizar el resultado de una función como entrada de otra función.

Por ejemplo:

```scala
val duplicar: Int => Int =
  numero => numero * 2

val sumarUno: Int => Int =
  numero => numero + 1
```

Podemos hacer:

```scala
sumarUno(duplicar(5))
```

Primero se ejecuta:

```text
duplicar(5)
```

Resultado:

```text
10
```

Luego:

```text
sumarUno(10)
```

Resultado final:

```text
11
```

Conceptualmente:

```text
5
↓
duplicar
↓
10
↓
sumarUno
↓
11
```

---

# 3. Composición manual

Podemos crear una nueva función combinando las anteriores:

```scala
val transformar: Int => Int =
  numero => sumarUno(duplicar(numero))
```

Ahora:

```scala
transformar(5)
```

Resultado:

```text
11
```

La nueva función utiliza dos funciones más pequeñas:

```text
duplicar
sumarUno
```

---

# 4. La idea matemática de composición

Si tenemos dos funciones:

```text
f
g
```

podemos crear:

```text
f(g(x))
```

Esto significa:

1. aplicar `g` a `x`;
2. tomar el resultado;
3. aplicar `f`.

Por ejemplo:

```text
f = sumarUno
g = duplicar
```

Entonces:

```text
f(g(5))
```

equivale a:

```text
sumarUno(duplicar(5))
```

Resultado:

```text
11
```

---

# 5. El método `compose`

Scala permite componer funciones directamente utilizando:

```scala
compose
```

Considere:

```scala
val duplicar: Int => Int =
  numero => numero * 2

val sumarUno: Int => Int =
  numero => numero + 1
```

Podemos escribir:

```scala
val transformar: Int => Int =
  sumarUno.compose(duplicar)
```

Ahora:

```scala
transformar(5)
```

Resultado:

```text
11
```

---

# 6. Orden de ejecución con `compose`

Esta expresión:

```scala
sumarUno.compose(duplicar)
```

significa:

```text
primero duplicar
después sumarUno
```

Es equivalente a:

```scala
numero => sumarUno(duplicar(numero))
```

Por tanto:

```text
5
↓ duplicar
10
↓ sumarUno
11
```

Una forma útil de recordarlo es:

> `f.compose(g)` ejecuta primero `g` y después `f`.

---

# 7. Otro ejemplo con `compose`

```scala
val cuadrado: Int => Int =
  numero => numero * numero

val restarTres: Int => Int =
  numero => numero - 3
```

Podemos escribir:

```scala
val operacion: Int => Int =
  restarTres.compose(cuadrado)
```

Entonces:

```scala
operacion(5)
```

Proceso:

```text
5
↓ cuadrado
25
↓ restarTres
22
```

Resultado:

```text
22
```

---

# 8. El método `andThen`

Scala también proporciona:

```scala
andThen
```

Considere nuevamente:

```scala
val duplicar: Int => Int =
  numero => numero * 2

val sumarUno: Int => Int =
  numero => numero + 1
```

Podemos escribir:

```scala
val transformar: Int => Int =
  duplicar.andThen(sumarUno)
```

Ahora:

```scala
transformar(5)
```

Resultado:

```text
11
```

---

# 9. Orden de ejecución con `andThen`

La expresión:

```scala
duplicar.andThen(sumarUno)
```

significa:

```text
primero duplicar
después sumarUno
```

Conceptualmente:

```text
5
↓ duplicar
10
↓ sumarUno
11
```

Una forma sencilla de leerlo es:

> `f.andThen(g)` ejecuta primero `f` y después `g`.

---

# 10. `compose` frente a `andThen`

Las siguientes expresiones son equivalentes:

```scala
sumarUno.compose(duplicar)
```

y:

```scala
duplicar.andThen(sumarUno)
```

Ambas realizan:

```text
duplicar
↓
sumarUno
```

---

## Comparación

| Expresión      | Orden                    |
| -------------- | ------------------------ |
| `f.compose(g)` | primero `g`, después `f` |
| `f.andThen(g)` | primero `f`, después `g` |

---

# 11. Ejemplo para comparar el orden

```scala
val multiplicarDos: Int => Int =
  numero => numero * 2

val sumarDiez: Int => Int =
  numero => numero + 10
```

### Caso 1

```scala
val operacion1 =
  sumarDiez.compose(multiplicarDos)
```

Para:

```scala
operacion1(5)
```

tenemos:

```text
5 * 2 = 10
10 + 10 = 20
```

Resultado:

```text
20
```

---

### Caso 2

```scala
val operacion2 =
  sumarDiez.andThen(multiplicarDos)
```

Para:

```scala
operacion2(5)
```

tenemos:

```text
5 + 10 = 15
15 * 2 = 30
```

Resultado:

```text
30
```

El orden de composición puede cambiar completamente el resultado.

---

# 12. Composición con diferentes tipos

Las funciones que se componen no necesitan trabajar siempre con el mismo tipo.

Por ejemplo:

```scala
val longitud: String => Int =
  texto => texto.length
```

Y:

```scala
val esPar: Int => Boolean =
  numero => numero % 2 == 0
```

Podemos combinar ambas:

```scala
val longitudPar: String => Boolean =
  longitud.andThen(esPar)
```

Ahora:

```scala
longitudPar("Scala")
```

Proceso:

```text
"Scala"
↓ longitud
5
↓ esPar
false
```

Resultado:

```text
false
```

---

# 13. Compatibilidad de tipos

Para poder componer dos funciones, los tipos deben ser compatibles.

Suponga:

```text
A => B
```

y:

```text
B => C
```

Podemos combinarlas para obtener:

```text
A => C
```

Porque la salida de la primera función:

```text
B
```

es compatible con la entrada de la segunda:

```text
B
```

---

# 14. Ejemplo de compatibilidad

Tenemos:

```scala
val longitud: String => Int =
  texto => texto.length
```

Tipo:

```text
String => Int
```

Y:

```scala
val esMayorQueCinco: Int => Boolean =
  numero => numero > 5
```

Tipo:

```text
Int => Boolean
```

Podemos crear:

```scala
val textoLargo: String => Boolean =
  longitud.andThen(esMayorQueCinco)
```

El tipo final es:

```text
String => Boolean
```

Conceptualmente:

```text
String
↓
Int
↓
Boolean
```

---

# 15. Ejemplo completo con diferentes tipos

```scala
val convertirNumero: String => Int =
  texto => texto.toInt

val duplicar: Int => Int =
  numero => numero * 2

val crearMensaje: Int => String =
  numero => s"Resultado: $numero"
```

Podemos crear:

```scala
val procesar =
  convertirNumero
    .andThen(duplicar)
    .andThen(crearMensaje)
```

Entonces:

```scala
procesar("15")
```

Proceso:

```text
"15"
↓
15
↓
30
↓
"Resultado: 30"
```

Resultado:

```text
Resultado: 30
```

El tipo de `procesar` es:

```text
String => String
```

---

# 16. Encadenar varias funciones

La composición no está limitada a dos funciones.

Por ejemplo:

```scala
val sumarUno: Int => Int =
  numero => numero + 1

val duplicar: Int => Int =
  numero => numero * 2

val restarTres: Int => Int =
  numero => numero - 3
```

Podemos escribir:

```scala
val procesar =
  sumarUno
    .andThen(duplicar)
    .andThen(restarTres)
```

Para:

```scala
procesar(5)
```

el proceso es:

```text
5
↓ +1
6
↓ *2
12
↓ -3
9
```

Resultado:

```text
9
```

---

# 17. Funciones pequeñas y reutilizables

En programación funcional es común crear funciones pequeñas.

Por ejemplo:

```scala
val eliminarEspacios: String => String =
  texto => texto.trim

val convertirMinusculas: String => String =
  texto => texto.toLowerCase

val agregarPrefijo: String => String =
  texto => s"Usuario: $texto"
```

Podemos combinarlas:

```scala
val normalizarUsuario =
  eliminarEspacios
    .andThen(convertirMinusculas)
    .andThen(agregarPrefijo)
```

Uso:

```scala
normalizarUsuario("  RODOLFO  ")
```

Resultado:

```text
Usuario: rodolfo
```

---

# 18. Crear nuestra propia función de composición

Como ya conocemos funciones de orden superior, podemos implementar nuestra propia función para componer.

```scala
def componer(
  f: Int => Int,
  g: Int => Int
): Int => Int =
  numero => f(g(numero))
```

Observe que:

* recibe dos funciones;
* devuelve una nueva función.

Por tanto, también es una **función de orden superior**.

---

# 19. Utilizando `componer`

Podemos definir:

```scala
val doble: Int => Int =
  numero => numero * 2

val sumarUno: Int => Int =
  numero => numero + 1
```

Luego:

```scala
val resultado =
  componer(sumarUno, doble)
```

La función creada equivale a:

```scala
numero => sumarUno(doble(numero))
```

Entonces:

```scala
resultado(5)
```

Resultado:

```text
11
```

---

# 20. Una versión más general de composición

Una función de composición no necesita limitarse a `Int`.

Conceptualmente queremos:

```text
A => B
B => C
```

y obtener:

```text
A => C
```

En Scala podemos expresar esto utilizando tipos genéricos:

```scala
def componer[A, B, C](
  f: B => C,
  g: A => B
): A => C =
  x => f(g(x))
```

Por ahora, lo importante es comprender la idea:

> La salida de `g` se convierte en la entrada de `f`.

Los tipos genéricos serán estudiados con mayor profundidad posteriormente.

---

# 21. ¿Qué es una abstracción?

Una **abstracción** consiste en identificar una estructura común y separar aquello que cambia.

Por ejemplo:

```scala
def sumaCuadrados(a: Int, b: Int): Int =
  a * a + b * b
```

Y:

```scala
def sumaCubos(a: Int, b: Int): Int =
  a * a * a + b * b * b
```

Ambas funciones poseen una estructura similar.

En ambos casos hacemos:

```text
transformar(a) + transformar(b)
```

Lo único que cambia es la transformación.

---

# 22. Crear una abstracción funcional

Podemos escribir:

```scala
def sumarTransformados(
  a: Int,
  b: Int,
  transformar: Int => Int
): Int =
  transformar(a) + transformar(b)
```

Ahora podemos calcular suma de cuadrados:

```scala
sumarTransformados(
  2,
  3,
  numero => numero * numero
)
```

Resultado:

```text
13
```

Porque:

```text
2² + 3²
4 + 9
13
```

---

# 23. Reutilizar la misma abstracción

Podemos utilizar la misma función para calcular cubos:

```scala
sumarTransformados(
  2,
  3,
  numero => numero * numero * numero
)
```

Resultado:

```text
35
```

Porque:

```text
2³ + 3³
8 + 27
35
```

La estructura:

```text
transformar(a) + transformar(b)
```

permanece igual.

Solo cambia:

```text
transformar
```

---

# 24. Separar lo que permanece de lo que cambia

Considere:

```scala
def sumarDobles(a: Int, b: Int): Int =
  a * 2 + b * 2

def sumarTriples(a: Int, b: Int): Int =
  a * 3 + b * 3

def sumarCuadrados(a: Int, b: Int): Int =
  a * a + b * b
```

Existe una estructura común:

```text
transformacion(a) + transformacion(b)
```

Podemos reemplazar las tres funciones con:

```scala
def sumarTransformados(
  a: Int,
  b: Int,
  transformacion: Int => Int
): Int =
  transformacion(a) + transformacion(b)
```

---

# 25. Generalizar también la operación

Podemos avanzar un paso más.

En:

```scala
def sumarTransformados(
  a: Int,
  b: Int,
  transformar: Int => Int
): Int =
  transformar(a) + transformar(b)
```

la transformación puede cambiar, pero siempre realizamos una suma.

¿Qué sucede si también queremos cambiar la operación final?

Podemos escribir:

```scala
def operarTransformados(
  a: Int,
  b: Int,
  transformacion: Int => Int,
  operacion: (Int, Int) => Int
): Int =
  operacion(
    transformacion(a),
    transformacion(b)
  )
```

Ahora podemos modificar:

* la transformación;
* la operación final.

---

# 26. Ejemplo: suma de cuadrados

```scala
operarTransformados(
  2,
  3,
  numero => numero * numero,
  (a, b) => a + b
)
```

Primero:

```text
2 → 4
3 → 9
```

Después:

```text
4 + 9
```

Resultado:

```text
13
```

---

# 27. Ejemplo: producto de cuadrados

```scala
operarTransformados(
  2,
  3,
  numero => numero * numero,
  (a, b) => a * b
)
```

Primero:

```text
2 → 4
3 → 9
```

Después:

```text
4 * 9
```

Resultado:

```text
36
```

---

# 28. Ejemplo: suma de cubos

```scala
operarTransformados(
  2,
  3,
  numero => numero * numero * numero,
  (a, b) => a + b
)
```

Resultado:

```text
35
```

---

# 29. ¿Por qué utilizar abstracciones funcionales?

Las abstracciones funcionales permiten:

* reducir código repetido;
* reutilizar estructuras comunes;
* separar comportamiento de datos;
* crear funciones generales;
* cambiar fácilmente una parte del algoritmo;
* mantener funciones pequeñas;
* facilitar la lectura y mantenimiento del código.

---

# 30. Composición frente a abstracción

Aunque están relacionadas, no son exactamente lo mismo.

## Composición

Consiste en combinar funciones:

```text
funcion1
↓
funcion2
↓
funcion3
```

Por ejemplo:

```scala
duplicar.andThen(sumarUno)
```

---

## Abstracción

Consiste en identificar una estructura común y convertir lo que cambia en parámetros.

Por ejemplo:

```scala
def operarTransformados(
  a: Int,
  b: Int,
  transformacion: Int => Int,
  operacion: (Int, Int) => Int
): Int
```

---

# 31. Ejercicios de práctica

## Ejercicio 1. Triple y resta

Cree las funciones:

```scala
val triple: Int => Int =
  numero => numero * 3

val restarDos: Int => Int =
  numero => numero - 2
```

Compóngalas para crear una función que:

1. multiplique un número por `3`;
2. reste `2` al resultado.

### Ejemplo

```text
procesar(5) = 13
```

---

## Ejercicio 2. Longitud par

Cree:

```scala
val longitud: String => Int
```

que obtenga la longitud de un texto.

Luego cree:

```scala
val esPar: Int => Boolean
```

que determine si un entero es par.

Componga ambas funciones para obtener:

```scala
val tieneLongitudPar: String => Boolean
```

### Ejemplos

```text
tieneLongitudPar("Scala") = false

tieneLongitudPar("Lambda") = true
```

---

## Ejercicio 3. Conversión y validación

Cree:

```scala
val convertirEntero: String => Int
```

y:

```scala
val esPositivo: Int => Boolean
```

Después cree una función:

```scala
val textoRepresentaPositivo: String => Boolean
```

utilizando composición.

### Ejemplos

```text
textoRepresentaPositivo("25") = true

textoRepresentaPositivo("-10") = false
```

---

## Ejercicio 4. Crear una función `componer`

Implemente:

```scala
def componer(
  f: Int => Int,
  g: Int => Int
): Int => Int
```

sin utilizar:

```text
compose
andThen
```

Utilice la función para combinar diferentes lambdas.

---

## Ejercicio 5. Operar valores transformados

Implemente:

```scala
def operarTransformados(
  a: Int,
  b: Int,
  transformacion: Int => Int,
  operacion: (Int, Int) => Int
): Int
```

Utilícela para calcular:

* suma de cuadrados;
* producto de cuadrados;
* suma de cubos;
* producto de dobles.

---

# 32. Errores frecuentes

## Error 1. Confundir el orden de `compose`

```scala
f.compose(g)
```

No significa:

```text
primero f
después g
```

Significa:

```text
primero g
después f
```

---

## Error 2. Confundir el orden de `andThen`

```scala
f.andThen(g)
```

significa:

```text
primero f
después g
```

---

## Error 3. Componer tipos incompatibles

Suponga:

```scala
val funcion1: String => Boolean = ...
```

y:

```scala
val funcion2: Int => Double = ...
```

No podemos ejecutar directamente:

```scala
funcion1.andThen(funcion2)
```

porque `funcion1` devuelve:

```text
Boolean
```

pero `funcion2` necesita:

```text
Int
```

---

# 33. Regla de compatibilidad

Si tenemos:

```text
A => B
```

y después queremos ejecutar:

```text
C => D
```

para poder componerlas debe cumplirse:

```text
B = C
```

Es decir:

> El tipo de salida de una función debe ser compatible con el tipo de entrada de la siguiente.

---

# 34. Buenas prácticas

Al utilizar composición y abstracciones funcionales:

* Crear funciones pequeñas y con un propósito claro.
* Mantener las funciones puras cuando sea posible.
* Utilizar nombres descriptivos.
* Verificar los tipos de entrada y salida.
* Preferir `andThen` cuando ayude a leer naturalmente el flujo de izquierda a derecha.
* Evitar composiciones demasiado largas o difíciles de leer.
* Buscar código repetido que pueda convertirse en una abstracción.
* Utilizar funciones como parámetros para representar aquello que cambia.
* Utilizar `val` en lugar de `var` siempre que sea posible.

---

# 35. Resumen

La **composición de funciones** permite utilizar el resultado de una función como entrada de otra.

Podemos escribir:

```scala
val transformar =
  duplicar.andThen(sumarUno)
```

O:

```scala
val transformar =
  sumarUno.compose(duplicar)
```

Ambas representan:

```text
duplicar
↓
sumarUno
```

La regla general de tipos es:

```text
A => B
B => C
---------
A => C
```

También podemos crear nuestra propia función de composición:

```scala
def componer(
  f: Int => Int,
  g: Int => Int
): Int => Int =
  x => f(g(x))
```

Las **abstracciones funcionales** permiten identificar patrones repetidos y convertir el comportamiento variable en funciones recibidas como parámetros.

Por ejemplo:

```scala
def operarTransformados(
  a: Int,
  b: Int,
  transformacion: Int => Int,
  operacion: (Int, Int) => Int
): Int =
  operacion(
    transformacion(a),
    transformacion(b)
  )
```

La idea fundamental de esta sesión es:

> **En programación funcional podemos construir soluciones complejas combinando funciones pequeñas y creando abstracciones que separen aquello que permanece de aquello que cambia.**
