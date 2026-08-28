class Estudiante(
  val codigo: Int,
  val nombre: String,
  val apellido: String,
  val edad: Int = 17,
  val semestre: Int = 1
){
  def saludar(): String = {
    s"Hola $nombre $apellido"
  }
  def mayorEdad(): Boolean = {
    this.edad>=18
  }
  def semestreSuperior(otroEstudiante: Estudiante): Estudiante = {
    if this.semestre >= otroEstudiante.semestre then this
    else otroEstudiante
  }
  def setSemestre(semestre: Int): Estudiante = {
    new Estudiante(this.codigo,this.nombre,this.apellido,this.edad,semestre)
  }
}

val estudiante1: Estudiante = new Estudiante(12345,"Juanito","Fernandez")
val estudiante2: Estudiante = new Estudiante(54321,"Pedro","Jimenez",20,3)
estudiante1.apellido
estudiante2.apellido
estudiante1.saludar()
estudiante2.saludar()
estudiante1.mayorEdad()
estudiante1.semestreSuperior(estudiante2).nombre

val estudianteActualizado = estudiante1.setSemestre(2)

/* EJERCICIO 1
Crear una clase producto, que tenga los siguientes atributos:
- Nombre, String
- Precio, Double
- Cantidad, Int

Con las siguientes funciones:
- Calcula el valor en inventario de ese producto (precio*cantidad)
- Actualizar la cantidad disponible de un producto 
- Actualizar el precio de un producto en base a un porcentaje de descuento
*/

class Producto(
  val nombre: String,
  val precio: Double,
  val cantidad: Int
) {
  def calcularValor(): Double = {
    precio*cantidad
  }
  def actualizarCantidad(nuevaCantidad: Int): Producto = {
    new Producto(nombre,precio,nuevaCantidad)
  }
  def aplicarDescuento(porcentajeDescuento: Double): Producto = {
    new Producto(nombre,precio-(precio*porcentajeDescuento),cantidad)
  }
}

val producto = new Producto("Pringles",30.50,10)
producto.calcularValor()
val producto1 = producto.actualizarCantidad(15)
producto1.calcularValor()
val producto2 = producto1.aplicarDescuento(0.1)
producto2.calcularValor()