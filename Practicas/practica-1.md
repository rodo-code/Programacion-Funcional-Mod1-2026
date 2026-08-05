# PRÁCTICA 1 - PROGRAMACIÓN FUNCIONAL

## Lineamientos generales

Todos los problemas de esta práctica deberán cumplir las siguientes reglas.

1. Utilizar únicamente **`val`** para declarar valores. No debe utilizarse `var`.

2. Todas las funciones deberán ser **funciones puras**, es decir:

   * deberán recibir toda la información necesaria mediante parámetros;
   * no deberán depender de variables globales;
   * no deberán modificar información externa;
   * no deberán utilizar `println` dentro de las funciones.

3. Todas las funciones deberán declarar explícitamente:

   * el tipo de cada parámetro;
   * el tipo de retorno.

4. Utilizar nombres descriptivos para:

   * funciones;
   * parámetros;
   * valores.

5. Los datos de entrada **no deben solicitarse por teclado** ni mediante consola.

   Todos los valores necesarios para cada problema deberán declararse directamente dentro del programa utilizando `val`.

6. La impresión de resultados deberá realizarse **únicamente al final del programa**, una vez que todas las funciones hayan sido ejecutadas.

7. Utilizar **String Interpolation** (`s"..."`) para construir los mensajes mostrados al usuario.

8. El programa debe estar correctamente indentado y organizado para facilitar su lectura.

---

# Problema 1. Planificación del costo de un viaje

## Enunciado

Una empresa de turismo está desarrollando una aplicación sencilla para ayudar a sus clientes a estimar el costo de un viaje por carretera antes de iniciar el recorrido.

Actualmente el cálculo se realiza manualmente utilizando una calculadora, lo que ocasiona errores frecuentes cuando cambian la distancia del recorrido, el consumo del vehículo o el precio del combustible.

Como primer prototipo, la empresa desea un programa que permita calcular automáticamente el combustible necesario para realizar el viaje, el costo total del combustible y cuánto deberá pagar cada pasajero al dividir el gasto entre todos los ocupantes del vehículo.

Además, con fines informativos, la empresa desea clasificar el viaje como **ECONÓMICO** o **COSTOSO**, dependiendo del costo que deberá asumir cada pasajero, si el costo es mayor a 200, el viaje es **COSTOSO**, si no es **ECONOMICO**.

Para desarrollar esta solución será necesario dividir el problema en varias funciones puras, donde cada función sea responsable de realizar un único cálculo.

---

## Funciones puras requeridas

El programa deberá implementar, como mínimo, las siguientes funciones:

| Función                        | Descripción                                                                       |
| ------------------------------ | --------------------------------------------------------------------------------- |
| `calcularCombustibleNecesario` | Calcula la cantidad de litros de combustible necesarios para completar el viaje.  |
| `calcularCostoCombustible`     | Calcula el costo total del combustible necesario para el viaje.                   |
| `calcularCostoPorPasajero`     | Calcula cuánto deberá pagar cada pasajero.                                        |
| `clasificarViaje`              | Determina si el viaje es **ECONÓMICO** o **COSTOSO** según el límite establecido. |

Es posible implementar funciones adicionales si se considera necesario.

---

## Datos principales de entrada

Los siguientes valores deberán declararse directamente en el código utilizando `val`.

**No deben solicitarse desde consola ni mediante ningún otro mecanismo de entrada.**

| Dato                                                          | Tipo     |
| ------------------------------------------------------------- | -------- |
| Distancia del viaje (kilómetros)                              | `Double` |
| Rendimiento del vehículo (kilómetros por litro)               | `Double` |
| Precio del combustible por litro                              | `Double` |
| Cantidad de pasajeros                                         | `Int`    |
| Límite máximo para considerar económico el costo por pasajero | `Double` |

---

## Información que debe imprimirse

Al finalizar la ejecución del programa deberán mostrarse únicamente los siguientes resultados:

* Litros de combustible necesarios.
* Costo total del combustible.
* Costo por pasajero.
* Clasificación del viaje (`ECONÓMICO` o `COSTOSO`).

---

## Caso de prueba

### Datos de entrada

| Dato                                      |       Valor |
| ----------------------------------------- | ----------: |
| Distancia del viaje                       |  `360.0` km |
| Rendimiento del vehículo                  | `12.0` km/L |
| Precio del combustible                    | `3.74` Bs/L |
| Cantidad de pasajeros                     |         `4` |
| Límite para considerar económico el viaje |   `30.0` Bs |

### Salida esperada

```text
Litros de combustible necesarios : 30.0 L
Costo total del combustible      : 112.20 Bs
Costo por pasajero               : 28.05 Bs
Clasificación del viaje          : ECONÓMICO
```

---

# Problema 2. Sistema de cálculo de compras con descuentos y envío

## Enunciado

Una tienda dedicada a la venta de productos tecnológicos está desarrollando un sistema para automatizar el cálculo del importe final de las compras realizadas por sus clientes.

Actualmente, los vendedores realizan todos los cálculos manualmente utilizando una calculadora. Esto ha provocado errores al aplicar descuentos, calcular el costo de envío y determinar el monto final que debe pagar el cliente.

La empresa ha decidido desarrollar un primer módulo del sistema que permita realizar estos cálculos de forma automática. Para ello, el programa deberá calcular el subtotal de la compra, determinar el descuento correspondiente de acuerdo con el monto de la compra, calcular el importe del descuento, establecer si corresponde pagar envío y obtener finalmente el total a cancelar.

Adicionalmente, la empresa desea clasificar cada compra según el monto total pagado, con el objetivo de analizar posteriormente el comportamiento de sus ventas.

La solución deberá dividir el problema en varias funciones puras, donde cada función sea responsable de realizar una única tarea.

---

## Funciones puras requeridas

El programa deberá implementar, como mínimo, las siguientes funciones:

| Función                      | Descripción                                                                               |
| ---------------------------- | ----------------------------------------------------------------------------------------- |
| `calcularSubtotal`           | Calcula el subtotal de la compra considerando el precio unitario y la cantidad adquirida. |
| `obtenerPorcentajeDescuento` | Determina el porcentaje de descuento que corresponde aplicar según el subtotal.           |
| `calcularMontoDescuento`     | Calcula el monto del descuento utilizando el subtotal y el porcentaje obtenido.           |
| `calcularCostoEnvio`         | Determina el costo del envío según el total después del descuento.                        |
| `calcularTotalFinal`         | Calcula el importe final que deberá pagar el cliente.                                     |
| `clasificarCompra`           | Determina la categoría de la compra según el total final pagado.                          |

Es posible implementar funciones adicionales si se considera necesario.

---

## Reglas del problema

### Descuento

El porcentaje de descuento dependerá del **subtotal de la compra**.

|                                    Subtotal | Descuento |
| ------------------------------------------: | --------: |
|                        Menor que **200 Bs** |   **0 %** |
|  Desde **200 Bs** hasta menos de **500 Bs** |   **5 %** |
| Desde **500 Bs** hasta menos de **1000 Bs** |  **10 %** |
|               Igual o mayor que **1000 Bs** |  **15 %** |

---

### Costo de envío

El costo de envío dependerá del **total después de aplicar el descuento**.

|  Total después del descuento |            Costo de envío |
| ---------------------------: | ------------------------: |
|         Menor que **300 Bs** |                 **25 Bs** |
| Igual o mayor que **300 Bs** | **0 Bs (Envío gratuito)** |

---

### Categoría de la compra

La categoría deberá determinarse utilizando el **total final**, es decir, después de aplicar el descuento y sumar el costo de envío.

|                                Total final | Categoría          |
| -----------------------------------------: | ------------------ |
|                       Menor que **300 Bs** | **COMPRA PEQUEÑA** |
| Desde **300 Bs** hasta menos de **800 Bs** | **COMPRA MEDIANA** |
|               Igual o mayor que **800 Bs** | **COMPRA GRANDE**  |

---

## Datos principales de entrada

Los siguientes valores deberán declararse directamente en el código utilizando `val`.

**No deben solicitarse desde consola ni mediante ningún otro mecanismo de entrada.**

| Dato                            | Tipo     |
| ------------------------------- | -------- |
| Nombre del producto             | `String` |
| Precio unitario del producto    | `Double` |
| Cantidad de productos comprados | `Int`    |

---

## Información que debe imprimirse

Al finalizar la ejecución del programa deberán mostrarse únicamente los siguientes resultados:

* Subtotal de la compra.
* Porcentaje de descuento aplicado.
* Monto del descuento.
* Total después de aplicar el descuento.
* Costo de envío.
* Total final de la compra.
* Categoría de la compra.

---

## Caso de prueba

### Datos de entrada

| Dato                |           Valor |
| ------------------- | --------------: |
| Nombre del producto | `"Monitor LED"` |
| Precio unitario     |     `350.00 Bs` |
| Cantidad            |             `2` |

### Salida esperada

```text
Subtotal de la compra          : 700.00 Bs
Porcentaje de descuento        : 10 %
Monto del descuento            : 70.00 Bs
Total después del descuento    : 630.00 Bs
Costo de envío                 : 0.00 Bs
Total final                    : 630.00 Bs
Categoría de la compra         : COMPRA MEDIANA
```

---

# Problema 3. Sistema de cálculo de pago mensual de un trabajador

## Enunciado

Una empresa de servicios necesita automatizar el cálculo del pago mensual de sus trabajadores. Actualmente, el área administrativa realiza estos cálculos manualmente considerando las horas trabajadas, las horas extra, el nivel de rendimiento, los aportes obligatorios y los retrasos registrados durante el mes.

El procedimiento manual ha generado inconsistencias, especialmente cuando un trabajador supera la cantidad de horas normales, recibe una bonificación por rendimiento o acumula varios retrasos. Por este motivo, la empresa desea implementar un programa que divida el cálculo en varias funciones puras y produzca un resultado claro y verificable.

El programa deberá determinar cuántas horas corresponden a trabajo normal y cuántas deben considerarse horas extra. También deberá calcular el pago por ambos conceptos, establecer la bonificación de acuerdo con la puntuación de rendimiento, calcular los descuentos correspondientes y obtener finalmente el salario neto del trabajador.

Además, la empresa desea clasificar el rendimiento de cada trabajador para utilizar esa información en futuras evaluaciones internas.

La solución deberá organizarse mediante funciones puras, donde cada función sea responsable de realizar una única tarea.

---

## Funciones puras requeridas

El programa deberá implementar, como mínimo, las siguientes funciones:

| Función                         | Descripción                                                                                     |
| ------------------------------- | ----------------------------------------------------------------------------------------------- |
| `calcularHorasNormales`         | Determina la cantidad de horas normales trabajadas, considerando el límite mensual establecido. |
| `calcularHorasExtra`            | Determina la cantidad de horas trabajadas por encima del límite mensual.                        |
| `calcularPagoHorasNormales`     | Calcula el pago correspondiente a las horas normales.                                           |
| `calcularPagoHorasExtra`        | Calcula el pago correspondiente a las horas extra, aplicando el recargo establecido.            |
| `calcularSalarioBruto`          | Calcula el salario bruto sumando el pago por horas normales y horas extra.                      |
| `obtenerPorcentajeBonificacion` | Determina el porcentaje de bonificación según la puntuación de rendimiento.                     |
| `calcularMontoBonificacion`     | Calcula el monto de la bonificación sobre el salario bruto.                                     |
| `calcularDescuentoAportes`      | Calcula el descuento correspondiente a los aportes obligatorios.                                |
| `calcularDescuentoRetrasos`     | Calcula el descuento adicional según la cantidad de retrasos registrados.                       |
| `calcularTotalDescuentos`       | Suma los descuentos por aportes y retrasos.                                                     |
| `calcularSalarioNeto`           | Calcula el salario neto después de sumar la bonificación y restar los descuentos.               |
| `clasificarRendimiento`         | Determina la categoría de rendimiento según la puntuación obtenida.                             |

Es posible implementar funciones adicionales si se considera necesario.

---

## Reglas del problema

### Horas normales y horas extra

El límite mensual de trabajo normal será de **160 horas**.

| Horas trabajadas en el mes | Tratamiento                                                |
| -------------------------: | ---------------------------------------------------------- |
|          Hasta `160` horas | Todas se consideran horas normales                         |
|         Más de `160` horas | Las primeras `160` son normales y el resto son horas extra |

---

### Pago de horas extra

Cada hora extra se pagará con un recargo del **50 %** sobre el valor de una hora normal.

Por tanto:

* Una hora normal se paga al valor establecido.
* Una hora extra se paga al `150 %` del valor normal.

---

### Bonificación por rendimiento

El porcentaje de bonificación dependerá de la puntuación de rendimiento.

|      Puntuación de rendimiento | Bonificación |
| -----------------------------: | -----------: |
|                 Menor que `60` |        `0 %` |
| Desde `60` hasta menos de `75` |        `5 %` |
| Desde `75` hasta menos de `90` |       `10 %` |
|         Desde `90` hasta `100` |       `15 %` |

La bonificación deberá calcularse sobre el **salario bruto**.

---

### Descuento por aportes

El descuento por aportes será equivalente al **12 %** de la suma del salario bruto y la bonificación.

---

### Descuento por retrasos

El descuento dependerá de la cantidad de retrasos registrados durante el mes.

|  Cantidad de retrasos | Descuento adicional |
| --------------------: | ------------------: |
|          `0` retrasos |              `0 Bs` |
| De `1` a `2` retrasos |             `50 Bs` |
| De `3` a `5` retrasos |            `120 Bs` |
|   Más de `5` retrasos |            `250 Bs` |

---

### Clasificación del rendimiento

La clasificación deberá determinarse utilizando la puntuación de rendimiento.

|                     Puntuación | Categoría               |
| -----------------------------: | ----------------------- |
|                 Menor que `60` | `RENDIMIENTO BAJO`      |
| Desde `60` hasta menos de `75` | `RENDIMIENTO REGULAR`   |
| Desde `75` hasta menos de `90` | `BUEN RENDIMIENTO`      |
|         Desde `90` hasta `100` | `RENDIMIENTO EXCELENTE` |

---

## Datos principales de entrada

Los siguientes valores deberán declararse directamente en el código utilizando `val`.

**No deben solicitarse desde consola ni mediante ningún otro mecanismo de entrada.**

| Dato                            | Tipo     |
| ------------------------------- | -------- |
| Nombre del trabajador           | `String` |
| Horas trabajadas durante el mes | `Double` |
| Pago por hora normal            | `Double` |
| Puntuación de rendimiento       | `Double` |
| Cantidad de retrasos            | `Int`    |

---

## Información que debe imprimirse

Al finalizar la ejecución del programa deberán mostrarse únicamente los siguientes resultados:

* Cantidad de horas normales.
* Cantidad de horas extra.
* Pago correspondiente a las horas normales.
* Pago correspondiente a las horas extra.
* Salario bruto.
* Porcentaje de bonificación aplicado.
* Monto de la bonificación.
* Descuento por aportes.
* Descuento por retrasos.
* Total de descuentos.
* Salario neto.
* Clasificación de rendimiento.

---

## Caso de prueba

### Datos de entrada

| Dato                      |              Valor |
| ------------------------- | -----------------: |
| Nombre del trabajador     | `"Carlos Mendoza"` |
| Horas trabajadas          |            `176.0` |
| Pago por hora normal      |          `25.0 Bs` |
| Puntuación de rendimiento |             `88.0` |
| Cantidad de retrasos      |                `3` |

### Salida esperada

```text
Horas normales                    : 160.0
Horas extra                       : 16.0
Pago por horas normales           : 4000.00 Bs
Pago por horas extra              : 600.00 Bs
Salario bruto                     : 4600.00 Bs
Porcentaje de bonificación        : 10 %
Monto de la bonificación          : 460.00 Bs
Descuento por aportes             : 607.20 Bs
Descuento por retrasos            : 120.00 Bs
Total de descuentos               : 727.20 Bs
Salario neto                      : 4332.80 Bs
Clasificación de rendimiento      : BUEN RENDIMIENTO
```
