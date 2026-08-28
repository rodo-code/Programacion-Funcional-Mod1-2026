# Ejercicio Integrador: Sistema de Gestión de Vehículos

Una empresa de transporte desea desarrollar un sistema para representar diferentes tipos de vehículos de su flota.

La empresa posee vehículos con características diferentes. Algunos utilizan combustible, otros son eléctricos, algunos pueden transportar carga y otros están destinados al transporte de pasajeros.

El objetivo es diseñar una jerarquía de clases utilizando:

* clases abstractas;
* `trait`;
* herencia;
* sobrescritura de métodos;
* polimorfismo;
* objetos inmutables.

---

## 1. Clase abstracta `Vehiculo`

Cree una clase abstracta:

```scala
abstract class Vehiculo(
  val marca: String,
  val modelo: String,
  val velocidadMaxima: Double
)
```

Todo vehículo deberá implementar los siguientes métodos:

```scala
def tipoVehiculo: String
```

```scala
def costoViaje(
  distanciaKm: Double
): Double
```

La clase deberá incluir también el método:

```scala
def descripcion: String
```

Este método deberá devolver un texto con:

* marca;
* modelo;
* tipo de vehículo;
* velocidad máxima.

### Ejemplo

```text
Toyota Hilux
Tipo: Camioneta
Velocidad máxima: 180 km/h
```

---

# 2. Trait `Combustion`

Cree:

```scala
trait Combustion
```

Los vehículos que utilicen combustible deberán proporcionar:

```scala
val rendimientoKmLitro: Double
```

```scala
val precioLitro: Double
```

El trait deberá incluir:

```scala
def combustibleNecesario(
  distanciaKm: Double
): Double
```

El combustible necesario se obtiene mediante:

```text
distancia / rendimiento
```

También deberá incluir:

```scala
def costoCombustible(
  distanciaKm: Double
): Double
```

El costo se obtiene multiplicando los litros necesarios por el precio del combustible.

---

# 3. Trait `Electrico`

Cree:

```scala
trait Electrico
```

Todo vehículo eléctrico deberá proporcionar:

```scala
val consumoKWh100Km: Double
```

```scala
val precioKWh: Double
```

El trait deberá implementar:

```scala
def energiaNecesaria(
  distanciaKm: Double
): Double
```

La energía requerida deberá calcularse de acuerdo con el consumo por cada `100 km`.

También:

```scala
def costoEnergia(
  distanciaKm: Double
): Double
```

---

# 4. Trait `TransportaPasajeros`

Cree:

```scala
trait TransportaPasajeros
```

Deberá exigir:

```scala
val capacidadPasajeros: Int
```

También deberá incluir:

```scala
def puedeTransportar(
  pasajeros: Int
): Boolean
```

El método deberá devolver `true` cuando la cantidad solicitada no supere la capacidad del vehículo.

---

# 5. Trait `TransportaCarga`

Cree:

```scala
trait TransportaCarga
```

Deberá exigir:

```scala
val capacidadCargaKg: Double
```

También deberá implementar:

```scala
def puedeTransportarCarga(
  pesoKg: Double
): Boolean
```

---

# 6. Trait `Recargable`

Cree:

```scala
trait Recargable
```

Deberá exigir:

```scala
val bateriaActual: Double
```

donde la batería será representada mediante un porcentaje entre:

```text
0 y 100
```

También deberá declarar:

```scala
def recargar(
  porcentaje: Double
): Vehiculo
```

La operación **no deberá modificar el objeto actual**.

Deberá devolver un nuevo vehículo con un nuevo nivel de batería.

La batería nunca podrá superar:

```text
100%
```

---

# 7. Clase `Automovil`

Cree una clase:

```scala
class Automovil(...)
```

Un automóvil deberá:

* extender `Vehiculo`;
* implementar `Combustion`;
* implementar `TransportaPasajeros`.

Deberá tener, además de los datos generales:

```text
rendimientoKmLitro
precioLitro
capacidadPasajeros
```

El método:

```scala
tipoVehiculo
```

deberá devolver:

```text
"Automóvil"
```

El costo del viaje deberá corresponder al costo del combustible.

---

# 8. Clase `Motocicleta`

Cree:

```scala
class Motocicleta(...)
```

Deberá:

* extender `Vehiculo`;
* implementar `Combustion`;
* implementar `TransportaPasajeros`.

Su capacidad normalmente será de:

```text
2 pasajeros
```

`tipoVehiculo` deberá devolver:

```text
"Motocicleta"
```

El costo del viaje deberá calcularse utilizando el consumo de combustible.

---

# 9. Clase `Camion`

Cree:

```scala
class Camion(...)
```

Deberá:

* extender `Vehiculo`;
* implementar `Combustion`;
* implementar `TransportaCarga`.

Además deberá tener:

```scala
val capacidadCargaKg: Double
```

`tipoVehiculo` deberá devolver:

```text
"Camión"
```

El costo del viaje deberá calcularse utilizando combustible.

---

# 10. Clase `Bus`

Cree:

```scala
class Bus(...)
```

Deberá:

* extender `Vehiculo`;
* implementar `Combustion`;
* implementar `TransportaPasajeros`.

Debe poder representar una capacidad considerable de pasajeros.

Por ejemplo:

```text
40
50
60
```

`tipoVehiculo` deberá devolver:

```text
"Bus"
```

---

# 11. Clase `AutoElectrico`

Cree:

```scala
class AutoElectrico(...)
```

Deberá:

* extender `Vehiculo`;
* implementar `Electrico`;
* implementar `Recargable`;
* implementar `TransportaPasajeros`.

Además deberá poseer:

```scala
val consumoKWh100Km: Double
val precioKWh: Double
val bateriaActual: Double
val capacidadPasajeros: Int
```

`tipoVehiculo` deberá devolver:

```text
"Automóvil Eléctrico"
```

El costo de viaje deberá calcularse según la energía consumida.

---

# 12. Recarga inmutable

El método:

```scala
recargar
```

de `AutoElectrico` deberá devolver un **nuevo objeto**.

Por ejemplo:

```scala
val auto1 =
  new AutoElectrico(
    ...
    bateriaActual = 40
  )

val auto2 =
  auto1.recargar(30)
```

Después de la operación:

```text
auto1.bateriaActual = 40

auto2.bateriaActual = 70
```

El objeto `auto1` no deberá cambiar.

Si se intenta recargar:

```text
50%
```

cuando la batería ya está en:

```text
80%
```

el nuevo valor deberá ser:

```text
100%
```

y no:

```text
130%
```

---

# 13. Clase `CamionElectrico`

Cree también:

```scala
class CamionElectrico(...)
```

Deberá:

* extender `Vehiculo`;
* implementar `Electrico`;
* implementar `Recargable`;
* implementar `TransportaCarga`.

Deberá permitir calcular:

* energía necesaria;
* costo energético;
* capacidad de carga;
* posibilidad de transportar determinado peso;
* recarga de batería.

`tipoVehiculo` deberá devolver:

```text
"Camión Eléctrico"
```

---

# 14. Polimorfismo

Cree la siguiente función:

```scala
def mostrarInformacion(
  vehiculo: Vehiculo
): String
```

La función deberá devolver:

* descripción del vehículo;
* costo estimado para un viaje de `100 km`.

Debe funcionar correctamente con cualquiera de las clases creadas:

```text
Automovil
Motocicleta
Camion
Bus
AutoElectrico
CamionElectrico
```

La función no deberá utilizar un parámetro específico para cada clase.

Debe trabajar únicamente con:

```scala
Vehiculo
```

---

# 15. Calcular costo de viaje

Cree:

```scala
def calcularCostoViaje(
  vehiculo: Vehiculo,
  distancia: Double
): Double =
  vehiculo.costoViaje(distancia)
```

Pruebe la función con diferentes tipos de vehículos.

Por ejemplo:

```scala
calcularCostoViaje(
  automovil,
  250
)
```

y:

```scala
calcularCostoViaje(
  autoElectrico,
  250
)
```

La función deberá funcionar sin conocer el tipo concreto del vehículo.

---

# 16. Datos de prueba

Cree al menos un objeto de cada clase.

Por ejemplo:

```text
Automóvil:
Marca: Toyota
Modelo: Corolla
Velocidad máxima: 190 km/h
Rendimiento: 14 km/L
Precio combustible: 3.74 Bs/L
Capacidad: 5 pasajeros
```

```text
Motocicleta:
Marca: Yamaha
Modelo: MT-03
Velocidad máxima: 170 km/h
Rendimiento: 28 km/L
Precio combustible: 3.74 Bs/L
Capacidad: 2 pasajeros
```

```text
Camión:
Marca: Volvo
Modelo: FH
Velocidad máxima: 120 km/h
Rendimiento: 5 km/L
Precio combustible: 3.74 Bs/L
Capacidad de carga: 18000 kg
```

```text
Bus:
Marca: Mercedes-Benz
Modelo: Tourismo
Velocidad máxima: 130 km/h
Rendimiento: 6 km/L
Precio combustible: 3.74 Bs/L
Capacidad: 50 pasajeros
```

```text
Auto eléctrico:
Marca: Tesla
Modelo: Model 3
Velocidad máxima: 225 km/h
Consumo: 15 kWh/100 km
Precio energía: 0.80 Bs/kWh
Batería: 60%
Capacidad: 5 pasajeros
```

```text
Camión eléctrico:
Marca: Volvo
Modelo: FH Electric
Velocidad máxima: 90 km/h
Consumo: 120 kWh/100 km
Precio energía: 0.80 Bs/kWh
Batería: 75%
Capacidad de carga: 16000 kg
```

---

# 17. Pruebas requeridas

El programa deberá demostrar al menos las siguientes operaciones.

### Transporte de pasajeros

```scala
automovil.puedeTransportar(4)
```

Resultado esperado:

```text
true
```

---

### Capacidad de carga

```scala
camion.puedeTransportarCarga(15000)
```

Resultado esperado:

```text
true
```

---

### Costo de combustible

Calcular el costo de un viaje de:

```text
300 km
```

para un automóvil.

---

### Costo eléctrico

Calcular el costo de un viaje de:

```text
300 km
```

para un automóvil eléctrico.

---

### Inmutabilidad

Crear:

```scala
val electrico2 =
  electrico1.recargar(20)
```

y comprobar que:

```text
electrico1
```

mantenga su batería original.

---

### Polimorfismo

Utilizar:

```scala
mostrarInformacion(...)
```

con al menos tres vehículos diferentes.

---

# 18. Restricciones

* Utilizar atributos con `val`.
* No utilizar `var`.
* No modificar los objetos existentes.
* Las operaciones que representen cambios deberán crear nuevos objetos.
* Utilizar al menos una clase abstracta.
* Utilizar al menos cuatro `trait`.
* Utilizar herencia mediante `extends`.
* Utilizar composición de traits mediante `with`.
* Utilizar `override` cuando corresponda.
* Evitar `println` dentro de los métodos de cálculo.
* Los métodos de cálculo deben devolver valores.
* Utilizar tipos de retorno explícitos.
* Utilizar polimorfismo mediante el tipo `Vehiculo`.

---

# 19. Preguntas de análisis

Después de completar el ejercicio responda:

1. ¿Por qué `Vehiculo` fue modelado como una clase abstracta y no como un `trait`?

2. ¿Por qué `Electrico`, `Combustion`, `TransportaCarga` y `TransportaPasajeros` pueden modelarse adecuadamente utilizando `trait`?

3. ¿Qué clases forman parte de la jerarquía de `Vehiculo`?

4. ¿Dónde se utiliza polimorfismo dentro de la solución?

5. ¿Por qué `calcularCostoViaje` puede trabajar con cualquier vehículo sin conocer su clase concreta?

6. ¿Qué ventajas proporciona utilizar `val` en los atributos?

7. ¿Por qué `recargar` devuelve un nuevo objeto en lugar de modificar la batería actual?

8. ¿Qué principio de programación funcional se está aplicando al mantener los vehículos inmutables?

9. ¿Qué comportamiento se comparte mediante traits?

10. ¿Qué elementos de esta solución corresponden a orientación a objetos y cuáles corresponden a programación funcional?
