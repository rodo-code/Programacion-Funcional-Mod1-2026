# Guía práctica: primeras herramientas de Scala

En esta guía aprenderás a probar instrucciones en el **REPL**, crear y ejecutar tu primer archivo `.scala` y utilizar un **worksheet** para experimentar con varias expresiones.

Antes de comenzar, comprueba que Scala funciona en tu computadora:

```bash
scala --version
```

---

# 1. Uso del REPL de Scala

El **REPL** es una consola interactiva que permite escribir y ejecutar instrucciones de Scala inmediatamente, sin necesidad de crear un archivo.

Su nombre proviene de:

* **Read:** leer la instrucción.
* **Evaluate:** evaluarla.
* **Print:** mostrar el resultado.
* **Loop:** repetir el proceso.

El REPL se utiliza principalmente para probar expresiones, experimentar con la sintaxis y verificar pequeños fragmentos de código.

## 1.1 Iniciar el REPL

Abre una terminal y ejecuta:

```bash
scala
```

Después de unos segundos aparecerá el símbolo del REPL:

```text
scala>
```

Ahora puedes escribir instrucciones de Scala.

---

## 1.2 Ejecutar operaciones básicas

Escribe las siguientes expresiones una por una:

```scala
2 + 3
```

```scala
10 - 4
```

```scala
6 * 5
```

```scala
20 / 4
```

Después de escribir cada expresión, presiona **Enter**.

Scala evaluará la expresión y mostrará su resultado:

```text
val res0: Int = 5
```

El resultado indica que:

* Scala creó un valor llamado `res0`.
* Su tipo es `Int`.
* Su valor es `5`.

---

## 1.3 Trabajar con textos

Los textos se escriben entre comillas dobles:

```scala
"Hola mundo"
```

Puedes unir textos utilizando el operador `+`:

```scala
"Hola" + " mundo"
```

También puedes imprimir un mensaje con:

```scala
println("Hola desde Scala")
```

---

## 1.4 Declarar valores

En Scala se utiliza `val` para declarar un valor que no puede reasignarse.

```scala
val nombre = "Ana"
```

Después puedes consultar su contenido:

```scala
nombre
```

También puedes utilizarlo en otras expresiones:

```scala
"Hola, " + nombre
```

Declara ahora un valor numérico:

```scala
val edad = 20
```

Realiza algunas operaciones:

```scala
edad + 1
```

```scala
edad * 2
```

Scala puede identificar automáticamente que `nombre` es un texto y que `edad` es un número. Esta característica se denomina **inferencia de tipos**.

También puedes indicar el tipo explícitamente:

```scala
val cantidad: Int = 10
```

```scala
val precio: Double = 15.5
```

```scala
val disponible: Boolean = true
```

---

## 1.5 Definir un método sencillo

Puedes definir un método dentro del REPL utilizando `def`:

```scala
def duplicar(numero: Int): Int = {
  numero * 2
}
```

Ahora llama al método:

```scala
duplicar(5)
```

El resultado será:

```text
10
```

También puedes probarlo con otros valores:

```scala
duplicar(12)
```

```scala
duplicar(-3)
```

---

## 1.6 Comandos útiles

Para ver los comandos disponibles:

```text
:help
```

Para salir del REPL:

```text
:quit
```

También puedes presionar:

```text
Ctrl + D
```

---

## Ejercicio 1: presentación personal en el REPL

Dentro del REPL:

1. Declara un valor llamado `nombre`.
2. Declara un valor llamado `edad`.
3. Declara un valor llamado `semestre`.
4. Calcula la edad que tendrás el próximo año.
5. Muestra un mensaje con tus datos.

Ejemplo:

```scala
val nombre = "Carlos"
val edad = 20
val semestre = 4

val edadSiguiente = edad + 1

println("Mi nombre es " + nombre)
println("Actualmente tengo " + edad + " años")
println("Estoy en el semestre " + semestre)
println("El próximo año tendré " + edadSiguiente + " años")
```

Modifica los valores utilizando tus propios datos.

---

# 2. Creación y ejecución de un archivo Scala

El REPL es útil para realizar pruebas rápidas, pero los programas normalmente se guardan en archivos.

Los archivos de código Scala utilizan la extensión:

```text
.scala
```

Por ejemplo:

```text
HolaMundo.scala
```

---

## 2.1 Crear una carpeta de trabajo

Abre una terminal y crea una carpeta para tus prácticas:

```bash
mkdir programacion-funcional
```

Ingresa en la carpeta:

```bash
cd programacion-funcional
```

Puedes comprobar la carpeta actual con:

```bash
pwd
```

En Windows puedes utilizar:

```powershell
cd programacion-funcional
```

---

## 2.2 Crear el archivo

Abre la carpeta con tu editor y crea un archivo llamado:

```text
HolaMundo.scala
```

Escribe el siguiente código:

```scala
object HolaMundo {
  def main(args: Array[String]): Unit = {
    println("Hola mundo")
  }
}
```

Guarda el archivo.

---

## 2.3 Ejecutar el programa

Desde la terminal, asegúrate de encontrarte en la misma carpeta que contiene el archivo.

Ejecuta:

```bash
scala run HolaMundo.scala
```

El comando `scala run` compila y ejecuta el archivo Scala.

El resultado esperado es:

```text
Hola mundo
```

La primera ejecución puede requerir la descarga de componentes del compilador. Las siguientes ejecuciones normalmente utilizarán los archivos almacenados en la computadora.

---

## 2.4 Comprender el programa

### Declaración del objeto

```scala
object HolaMundo
```

Declara un objeto único llamado `HolaMundo`.

### Método principal

```scala
def main(args: Array[String]): Unit
```

El método `main` es el punto desde el cual comienza la ejecución del programa.

### Argumentos

```scala
args: Array[String]
```

`args` contiene los argumentos de texto que pueden enviarse desde la terminal.

### Tipo `Unit`

```scala
: Unit
```

Indica que el método ejecuta instrucciones, pero no devuelve un resultado que deba utilizarse posteriormente.

### Impresión en consola

```scala
println("Hola mundo")
```

Muestra el texto `Hola mundo` en la terminal.

---

## 2.5 Modificar el programa

Modifica el contenido del método `main`:

```scala
object HolaMundo {
  def main(args: Array[String]): Unit = {
    println("Hola, mi nombre es Andrea")
    println("Estudio Ingeniería en Sistemas Computacionales")
    println("Estoy aprendiendo Scala")
  }
}
```

Guarda nuevamente el archivo y ejecútalo:

```bash
scala run HolaMundo.scala
```

Cada vez que modifiques el código, debes guardar el archivo antes de volver a ejecutarlo.

---

## Ejercicio 2: perfil del estudiante

Crea un archivo llamado:

```text
PerfilEstudiante.scala
```

Dentro del archivo, crea un objeto llamado `PerfilEstudiante` con un método `main`.

Declara los siguientes valores:

```scala
val nombre = "Tu nombre"
val carrera = "Ingeniería en Sistemas Computacionales"
val semestre = 4
```

Después, muestra los datos en la consola.

Puedes utilizar esta estructura:

```scala
object PerfilEstudiante {
  def main(args: Array[String]): Unit = {
    val nombre = "Tu nombre"
    val carrera = "Ingeniería en Sistemas Computacionales"
    val semestre = 4

    println("Nombre: " + nombre)
    println("Carrera: " + carrera)
    println("Semestre: " + semestre)
  }
}
```

Ejecuta el archivo con:

```bash
scala run PerfilEstudiante.scala
```

El resultado debe contener tu nombre, tu carrera y tu semestre.

---

# 3. Uso de worksheets

Un **worksheet** es un archivo que permite escribir varias expresiones de Scala y observar sus resultados directamente en el editor.

A diferencia de un programa normal, un worksheet no necesita un objeto ni un método `main`. Las instrucciones se evalúan de arriba hacia abajo.

Los worksheets son útiles para:

* Resolver ejercicios.
* Probar expresiones.
* Experimentar con valores y tipos.
* Ver resultados sin ejecutar manualmente un programa completo.
* Guardar las pruebas realizadas.

---

## 3.1 Preparar un proyecto Scala

Para utilizar worksheets necesitas abrir un proyecto Scala en un editor compatible.

### En Visual Studio Code

Debes tener instalada la extensión **Metals**.

Abre la paleta de comandos:

```text
Ctrl + Shift + P
```

En macOS:

```text
Command + Shift + P
```

Busca:

```text
Metals: New Scala Project
```

Selecciona una plantilla de Scala 3, elige una ubicación y abre el proyecto generado.

Cuando Metals solicite importar el proyecto, acepta la opción:

```text
Import build
```

---

## 3.2 Crear el worksheet

Dentro del proyecto, busca la carpeta:

```text
src/main/scala
```

Crea un archivo llamado:

```text
introduccion.worksheet.sc
```

La terminación característica es:

```text
.worksheet.sc
```

---

## 3.3 Escribir expresiones

Agrega el siguiente contenido:

```scala
println("Mi primer worksheet")

val numero1 = 10
val numero2 = 5

numero1 + numero2
numero1 - numero2
numero1 * numero2
numero1.toDouble / numero2
```

Guarda el archivo.

En Visual Studio Code con Metals, los resultados pueden aparecer como comentarios junto a las expresiones. En IntelliJ IDEA pueden mostrarse en un panel lateral después de ejecutar el worksheet.

Un resultado podría verse de esta forma:

```scala
val numero1 = 10       // numero1: Int = 10
val numero2 = 5        // numero2: Int = 5

numero1 + numero2      // 15
numero1 - numero2      // 5
numero1 * numero2      // 50
```

Los comentarios con resultados son generados por el editor y no forman parte del código original.

---

## 3.4 Trabajar con textos

Agrega al worksheet:

```scala
val nombre = "María"
val materia = "Programación Funcional"

val mensaje = "Hola " + nombre

mensaje
materia.toUpperCase
nombre.length
```

El editor evaluará cada expresión y mostrará sus resultados.

---

## 3.5 Diferencia entre un archivo y un worksheet

### Archivo Scala normal

```scala
object Programa {
  def main(args: Array[String]): Unit = {
    println("Hola")
  }
}
```

Para ejecutarlo se utiliza:

```bash
scala run Programa.scala
```

### Worksheet

```scala
println("Hola")

val numero = 10

numero * 2
```

El worksheet:

* No requiere un método `main`.
* Evalúa las instrucciones de arriba hacia abajo.
* Muestra el resultado de cada expresión.
* Está pensado principalmente para prácticas y experimentos.

---

## Ejercicio 3: cálculo del costo de una compra

Crea un worksheet llamado:

```text
compra.worksheet.sc
```

Declara los siguientes valores:

```scala
val producto = "Cuaderno"
val precio = 15.5
val cantidad = 3
```

Calcula el costo total:

```scala
val total = precio * cantidad
```

Después muestra los datos:

```scala
println("Producto: " + producto)
println("Precio unitario: " + precio)
println("Cantidad: " + cantidad)
println("Total: " + total)
```

Agrega también las siguientes expresiones para observar sus resultados:

```scala
cantidad + 2
precio * 2
total / cantidad
```

El resultado principal debe ser:

```text
Producto: Cuaderno
Precio unitario: 15.5
Cantidad: 3
Total: 46.5
```

---

# Resumen de las herramientas

| HERRAMIENTA          | USO PRINCIPAL                             | ¿EL CÓDIGO SE GUARDA? |                  ¿NECESITA `main`? |
| -------------------- | ----------------------------------------- | --------------------: | ---------------------------------: |
| **REPL**             | Probar instrucciones rápidamente          |     No necesariamente |                                 No |
| **Archivo `.scala`** | Crear y ejecutar programas                |                    Sí | Sí, para la estructura tradicional |
| **Worksheet**        | Resolver ejercicios y observar resultados |                    Sí |                                 No |

Utiliza el **REPL** para pruebas rápidas, un **worksheet** para ejercicios y experimentos guardados, y un archivo `.scala` para construir programas ejecutables.
