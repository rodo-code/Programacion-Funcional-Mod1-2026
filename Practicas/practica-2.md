# PRÁCTICA 2: RECURSIVIDAD

# Sección 1: Recursividad Tradicional

Resolver los siguientes ejercicios bajo los siguientes lineamientos:

- Utilizar `val` y evitar `var`.
- No utilizar ciclos como `for`, `while` o `do-while`.
- No utilizar colecciones para resolver los ejercicios.
- Se pueden utilizar funciones auxiliares internas cuando sea necesario.
- Todos los parámetros deberán indicar explícitamente su tipo.
- Todas las funciones deberán indicar explícitamente su tipo de retorno.
- Las funciones de cálculo deberán ser puras y no utilizar `println`.

## Ejercicio 1. Conversión de decimal a binario

Desarrolle una función recursiva que reciba un número entero positivo expresado en sistema decimal y devuelva su representación en sistema binario.

El resultado deberá ser retornado como un `String`.

### Ejemplos

```text
decimalABinario(1) = "1"

decimalABinario(2) = "10"

decimalABinario(5) = "101"

decimalABinario(10) = "1010"

decimalABinario(25) = "11001"

decimalABinario(42) = "101010"
```

---

## Ejercicio 2. Dígito mayor de un número

Desarrolle una función recursiva que reciba un número entero positivo y determine cuál es el dígito de mayor valor que contiene.

La función deberá devolver un valor de tipo `Int`.

### Ejemplos

```text
digitoMayor(58329) = 9

digitoMayor(4441) = 4

digitoMayor(123456) = 6

digitoMayor(80723) = 8

digitoMayor(5) = 5
```

---

## Ejercicio 3. Contar dígitos pares

Desarrolle una función recursiva que reciba un número entero no negativo y determine cuántos de sus dígitos son pares.

Se debe considerar al `0` como un dígito par cuando aparezca dentro del número.

### Ejemplos

```text
contarDigitosPares(583246) = 4

contarDigitosPares(13579) = 0

contarDigitosPares(2468) = 4

contarDigitosPares(102030) = 4

contarDigitosPares(7) = 0
```

## Ejercicio 4. Contar apariciones de un dígito

Desarrolle una función recursiva que reciba:

- Un número entero no negativo.
- Un dígito entre `0` y `9`.

La función deberá devolver cuántas veces aparece ese dígito dentro del número.

### Ejemplos

```text
contarDigito(525235, 5) = 3

contarDigito(11111, 1) = 5

contarDigito(987654, 3) = 0

contarDigito(707070, 0) = 3
```

## Ejercicio 5. Formar un número con los dígitos pares

Desarrolle una función recursiva que reciba un número entero positivo y devuelva un `String` formado únicamente por los dígitos pares del número original, conservando el orden en el que aparecen.

Si el número no contiene ningún dígito par, la función deberá devolver una cadena vacía.

### Ejemplos

```text
extraerPares(583246) = "8246"

extraerPares(13579) = ""

extraerPares(2468) = "2468"

extraerPares(102034) = "0204"

extraerPares(908172) = "9082"
```

# Sección 2: Recursividad de Cola

Resolver los siguientes ejercicios bajo los siguientes lineamientos:

- Utilizar `val` y evitar `var`.
- No utilizar ciclos como `for`, `while` o `do-while`.
- No utilizar colecciones para resolver los ejercicios.
- La llamada recursiva deberá ser la última operación realizada.
- Utilizar la anotación `@tailrec` en la función recursiva.
- Se pueden utilizar funciones auxiliares internas cuando sea necesario.
- Todos los parámetros deberán indicar explícitamente su tipo.
- Todas las funciones deberán indicar explícitamente su tipo de retorno.
- Las funciones de cálculo deberán ser puras y no utilizar `println`.
- Los acumuladores necesarios deberán enviarse como parámetros de las funciones auxiliares.

## Ejercicio 6. Sumar únicamente los dígitos pares

Desarrolle una función que reciba un número entero no negativo y calcule la suma de todos sus dígitos pares.

El `0` deberá considerarse un dígito par.

### Ejemplos

```text
sumarDigitosPares(583246) = 20
sumarDigitosPares(13579) = 0
sumarDigitosPares(2468) = 20
sumarDigitosPares(102030) = 6
sumarDigitosPares(8) = 8
```

---

## Ejercicio 7. Contar letras mayúsculas

Desarrolle una función que reciba un `String` y determine cuántos caracteres corresponden a letras mayúsculas.

La función no deberá modificar el texto recibido.

Los números, espacios y otros símbolos no deberán contarse como letras mayúsculas.

### Ejemplos

```text
contarMayusculas("HolaMundoScala") = 3
contarMayusculas("SCALA") = 5
contarMayusculas("programacion") = 0
contarMayusculas("Scala3EsGenial") = 3
contarMayusculas("") = 0
```

---

## Ejercicio 8. Contar cambios de paridad entre dígitos

Desarrolle una función que reciba un número entero positivo y determine cuántas veces cambia la paridad entre dos dígitos consecutivos.

Se considera un cambio de paridad cuando:

- un dígito par está seguido por uno impar; o
- un dígito impar está seguido por uno par.

Un número de un solo dígito deberá producir `0`.

### Ejemplos

```text
contarCambiosParidad(123456) = 5
contarCambiosParidad(248135) = 1
contarCambiosParidad(2468) = 0
contarCambiosParidad(13579) = 0
contarCambiosParidad(52841) = 3
```

En el primer ejemplo ocurren los siguientes cambios:

```text
1→2, 2→3, 3→4, 4→5, 5→6
```

---

## Ejercicio 9. Segundo dígito mayor

Desarrolle una función que reciba un número entero positivo y determine cuál es el **segundo dígito distinto de mayor valor** que aparece en el número.

Los dígitos repetidos deberán considerarse una sola vez para determinar el segundo mayor.

Si el número contiene únicamente un valor de dígito diferente, la función deberá devolver `-1`.

### Ejemplos

```text
segundoMayorDigito(58329) = 8
segundoMayorDigito(7416) = 6
segundoMayorDigito(9995) = 5
segundoMayorDigito(777) = -1
segundoMayorDigito(908090) = 8
```

---

## Ejercicio 10. Longitud de la mayor secuencia de caracteres iguales

Desarrolle una función que reciba un `String` y determine la longitud de la secuencia consecutiva más larga formada por el mismo carácter.

La comparación deberá distinguir entre letras mayúsculas y minúsculas.

### Ejemplos

```text
rachaMaxima("aaabbccccdaa") = 4
rachaMaxima("aabbbbbcc") = 5
rachaMaxima("abcde") = 1
rachaMaxima("aaaaaa") = 6
rachaMaxima("aabbbaaaa") = 4
rachaMaxima("") = 0
rachaMaxima("aaAAaa") = 2
```

En el primer ejemplo, la secuencia más larga es:

```text
"cccc"
```

y tiene una longitud de `4`.
