# Parte 3 Sistema de Biblioteca Digital

Desarrolle un sistema para representar diferentes tipos de recursos dentro de una biblioteca digital.

El sistema deberá utilizar:

- clases abstractas;
- `trait`;
- herencia;
- polimorfismo;
- objetos inmutables;
- métodos que devuelvan nuevos objetos cuando representen cambios.

---

## 1. Clase abstracta `RecursoBiblioteca`

Cree:

```scala
abstract class RecursoBiblioteca(
  val titulo: String,
  val autor: String,
  val anio: Int
) {

  def tipoRecurso: String

  def descripcion: String =
    s"$titulo - $autor ($anio) - $tipoRecurso"

  def costoPrestamo(dias: Int): Double
}
```

---

## 2. Trait `Prestable`

Cree:

```scala
trait Prestable {

  val disponible: Boolean

  def prestar(): RecursoBiblioteca
}
```

El método `prestar` deberá devolver un nuevo objeto con el estado actualizado.

---

## 3. Trait `Renovable`

Cree:

```scala
trait Renovable {

  val diasPrestamo: Int

  def renovar(diasExtra: Int): RecursoBiblioteca
}
```

La renovación deberá devolver un nuevo objeto.

---

## 4. Trait `Descargable`

Cree:

```scala
trait Descargable {

  val tamanioMB: Double

  def descargar(): String =
    s"Descargando archivo de $tamanioMB MB"
}
```

---

## 5. Trait `Reproducible`

Cree:

```scala
trait Reproducible {

  val duracionMinutos: Int

  def reproducir(): String =
    s"Reproduciendo contenido de $duracionMinutos minutos"
}
```

---

## 6. Clase `LibroFisico`

Cree:

```scala
class LibroFisico(
  titulo: String,
  autor: String,
  anio: Int,
  val disponible: Boolean,
  val diasPrestamo: Int
) extends RecursoBiblioteca(
      titulo,
      autor,
      anio
    )
    with Prestable
    with Renovable {

  override def tipoRecurso: String =
    "Libro Físico"

  override def costoPrestamo(
    dias: Int
  ): Double =
    dias * 1.5

  override def prestar(): LibroFisico =
    new LibroFisico(
      titulo,
      autor,
      anio,
      false,
      diasPrestamo
    )

  override def renovar(
    diasExtra: Int
  ): LibroFisico =
    new LibroFisico(
      titulo,
      autor,
      anio,
      disponible,
      diasPrestamo + diasExtra
    )
}
```

---

## 7. Clase `LibroDigital`

Cree:

```scala
class LibroDigital(
  titulo: String,
  autor: String,
  anio: Int,
  val tamanioMB: Double
) extends RecursoBiblioteca(
      titulo,
      autor,
      anio
    )
    with Descargable {

  override def tipoRecurso: String =
    "Libro Digital"

  override def costoPrestamo(
    dias: Int
  ): Double =
    dias * 0.5
}
```

---

## 8. Clase `Audiolibro`

Cree:

```scala
class Audiolibro(
  titulo: String,
  autor: String,
  anio: Int,
  val duracionMinutos: Int,
  val tamanioMB: Double
) extends RecursoBiblioteca(
      titulo,
      autor,
      anio
    )
    with Reproducible
    with Descargable {

  override def tipoRecurso: String =
    "Audiolibro"

  override def costoPrestamo(
    dias: Int
  ): Double =
    dias * 0.75
}
```

---

## 9. Clase `RevistaFisica`

Cree:

```scala
class RevistaFisica(
  titulo: String,
  autor: String,
  anio: Int,
  val numeroEdicion: Int,
  val disponible: Boolean,
  val diasPrestamo: Int
) extends RecursoBiblioteca(
      titulo,
      autor,
      anio
    )
    with Prestable
    with Renovable {

  override def tipoRecurso: String =
    "Revista Física"

  override def costoPrestamo(
    dias: Int
  ): Double =
    dias * 1.0

  override def prestar(): RevistaFisica =
    new RevistaFisica(
      titulo,
      autor,
      anio,
      numeroEdicion,
      false,
      diasPrestamo
    )

  override def renovar(
    diasExtra: Int
  ): RevistaFisica =
    new RevistaFisica(
      titulo,
      autor,
      anio,
      numeroEdicion,
      disponible,
      diasPrestamo + diasExtra
    )
}
```

---

## 10. Clase `VideoCurso`

Cree:

```scala
class VideoCurso(
  titulo: String,
  autor: String,
  anio: Int,
  val duracionMinutos: Int,
  val tamanioMB: Double
) extends RecursoBiblioteca(
      titulo,
      autor,
      anio
    )
    with Reproducible
    with Descargable {

  override def tipoRecurso: String =
    "Video Curso"

  override def costoPrestamo(
    dias: Int
  ): Double =
    dias * 1.2
}
```

---

## 11. Función polimórfica para mostrar información

Implemente:

```scala
def mostrarInformacion(
  recurso: RecursoBiblioteca
): String =
  recurso.descripcion
```

---

## 12. Función polimórfica para calcular costo

Implemente:

```scala
def calcularCosto(
  recurso: RecursoBiblioteca,
  dias: Int
): Double =
  recurso.costoPrestamo(dias)
```

---

## 13. Objetos de prueba

Cree:

```scala
val libro1 =
  new LibroFisico(
    "Clean Code",
    "Robert C. Martin",
    2008,
    true,
    7
  )
```

```scala
val libro2 =
  new LibroDigital(
    "Functional Programming in Scala",
    "Paul Chiusano",
    2023,
    18.5
  )
```

```scala
val audio1 =
  new Audiolibro(
    "The Pragmatic Programmer",
    "Andrew Hunt",
    1999,
    720,
    850.0
  )
```

```scala
val revista1 =
  new RevistaFisica(
    "IEEE Software",
    "IEEE",
    2026,
    8,
    true,
    5
  )
```

```scala
val curso1 =
  new VideoCurso(
    "Scala Avanzado",
    "Academia FP",
    2026,
    240,
    1500.0
  )
```

---

## 14. Pruebas de polimorfismo

Ejecute:

```scala
println(
  mostrarInformacion(libro1)
)
```

```scala
println(
  mostrarInformacion(libro2)
)
```

```scala
println(
  mostrarInformacion(audio1)
)
```

```scala
println(
  calcularCosto(
    libro1,
    10
  )
)
```

```scala
println(
  calcularCosto(
    curso1,
    5
  )
)
```

---

## 15. Pruebas de traits

Para recursos descargables:

```scala
println(
  libro2.descargar()
)
```

```scala
println(
  audio1.descargar()
)
```

Para recursos reproducibles:

```scala
println(
  audio1.reproducir()
)
```

```scala
println(
  curso1.reproducir()
)
```

---

## 16. Préstamo inmutable

Ejecute:

```scala
val libroPrestado =
  libro1.prestar()
```

Compruebe:

```scala
println(
  libro1.disponible
)
```

```scala
println(
  libroPrestado.disponible
)
```

Resultados esperados:

```text
true
false
```

El objeto original no deberá modificarse.

---

## 17. Renovación inmutable

Ejecute:

```scala
val libroRenovado =
  libro1.renovar(5)
```

Compruebe:

```scala
println(
  libro1.diasPrestamo
)
```

```scala
println(
  libroRenovado.diasPrestamo
)
```

Resultados esperados:

```text
7
12
```

---

## 18. Restricciones

- No utilizar `var`.
- Utilizar `val`.
- No modificar objetos existentes.
- Los cambios deberán producir nuevos objetos.
- Utilizar una clase abstracta.
- Utilizar al menos cuatro `trait`.
- Utilizar herencia con `extends`.
- Utilizar combinación de traits con `with`.
- Utilizar `override` donde corresponda.
- Utilizar polimorfismo mediante `RecursoBiblioteca`.
- Los métodos de cálculo no deberán imprimir resultados.
- Los tipos de retorno deberán declararse explícitamente.
