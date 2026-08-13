# Sesión 9: Ejercicios de Recursión de Cola

Todos los ejercicios de esta guía deberán resolverse utilizando **recursión de cola**.

## Lineamientos generales

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

## Ejercicio 1. Suma de cuadrados

Desarrolle una función que reciba un número entero no negativo `n` y calcule la suma de los cuadrados de todos los números desde `1` hasta `n`.

### Ejemplos

```text
sumaCuadrados(0) = 0
sumaCuadrados(1) = 1
sumaCuadrados(3) = 14
sumaCuadrados(4) = 30
sumaCuadrados(5) = 55
```

---

## Ejercicio 2. Producto de los dígitos de un número

Desarrolle una función que reciba un número entero positivo y calcule el producto de todos sus dígitos.

### Ejemplos

```text
productoDigitos(234) = 24
productoDigitos(521) = 10
productoDigitos(105) = 0
productoDigitos(7) = 7
productoDigitos(333) = 27
```

---

## Ejercicio 3. Invertir un número entero

Desarrolle una función que reciba un número entero positivo y devuelva otro número con sus dígitos en orden inverso.

El resultado deberá ser de tipo `Int`.

Los ceros que queden al inicio del número invertido no serán conservados debido a la representación numérica.

### Ejemplos

```text
invertirNumero(5832) = 2385
invertirNumero(123456) = 654321
invertirNumero(7) = 7
invertirNumero(1200) = 21
invertirNumero(9081) = 1809
```

---

## Ejercicio 4. Contar grupos de caracteres consecutivos

Desarrolle una función que reciba un `String` y determine cuántos grupos de caracteres consecutivos iguales contiene.

Un grupo está formado por uno o más caracteres iguales ubicados consecutivamente.

La comparación deberá distinguir entre letras mayúsculas y minúsculas.

### Ejemplos

```text
contarGrupos("aaabbcaa") = 4
contarGrupos("aaaa") = 1
contarGrupos("abcde") = 5
contarGrupos("aabbcc") = 3
contarGrupos("") = 0
```

En el primer ejemplo, los grupos son:

```text
"aaa", "bb", "c", "aa"
```

---

## Ejercicio 5. Persistencia multiplicativa

La **persistencia multiplicativa** de un número indica cuántas veces deben multiplicarse sus dígitos hasta obtener un número de un solo dígito.

Por ejemplo, para `39`:

```text
39
↓
3 * 9 = 27
↓
2 * 7 = 14
↓
1 * 4 = 4
```

Por tanto:

```text
persistenciaMultiplicativa(39) = 3
```

Desarrolle una solución utilizando recursión de cola.

### Ejemplos

```text
persistenciaMultiplicativa(7) = 0
persistenciaMultiplicativa(25) = 2
persistenciaMultiplicativa(39) = 3
persistenciaMultiplicativa(77) = 4
persistenciaMultiplicativa(999) = 4
```

Algunos procesos completos son:

```text
25 → 10 → 0
39 → 27 → 14 → 4
77 → 49 → 36 → 18 → 8
999 → 729 → 126 → 12 → 2
```
