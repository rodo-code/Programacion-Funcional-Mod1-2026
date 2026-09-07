sealed trait EstadoPedido

case object Pendiente extends EstadoPedido

case class Confirmado(
  codigo: String
) extends EstadoPedido

case class Enviado(
  encargado: String
) extends EstadoPedido

case object Entregado extends EstadoPedido

def describirEstado(estado: EstadoPedido): String = {
  estado match 
    case Pendiente => "Pedido Pendiente"
    case Confirmado(codigo) => s"Pedido confirmado con el codigo $codigo"
    case Enviado(encargado) => s"Pedido enviado, debe ser entregado por $encargado"
    case Entregado => "Pedido Entregado"
}

describirEstado(Pendiente)
describirEstado(Confirmado("500AB"))
describirEstado(Enviado("Pablito Marmol"))
describirEstado(Entregado)

sealed abstract class Figura(
  nombre: String
)

case class Rectangulo(
  nombre: String,
  base: Double,
  altura: Double
) extends Figura(nombre)

case class Circulo(
  nombre: String,
  radio: Double
) extends Figura(nombre)

def area(figura: Figura): Double = {
  figura match
    case Rectangulo(_,base,altura) => (base*altura)
    case Circulo(_,radio) => Math.PI * radio * radio
}

area(Rectangulo("rectangulo",4,5))
area(Circulo("circulo",7))

/* 
  EJERCICIO 1
  Crear dos case clases nuevos que hereden de Figura
  Uno debe ser Cuadrado que solamente debe adicionar el atributo de "lado"
  el otro debe ser Triangulo que debe adicionar dos atributos "base" y "altura"
  modificar la funcion area para tambien obtener el area de esas figuras
*/

// ADT - TIPOS DE DATO ALGEBRAICO
// 1. TIPO PRODUCTO
case class Moto(
  marca: String,
  numeroLlantas: Int
)

// 2. TIPO SUMA 

// EstadoPedido es una ADT de tipo suma, porque define alternativo

// 3. TIPO COMBINADO
case class Pedido(
  restaurante: String,
  costoEnvio: Double,
  estado: EstadoPedido
)

// ADT Generico

sealed trait Resultado[+A]

case class Exito[A](
  valor: A
) extends Resultado[A]

case class Error(
  mensaje: String
) extends Resultado[Nothing]

val resultado: Resultado[Int] = Exito[Int](23)
val resultado2: Resultado[Nothing] = Error("Ocurrio un error")
val resultado3: Resultado[Double] = Error("No pude dividir entre 0")

def mostrarResultado[A](resultado: Resultado[A]): String = {
  resultado match 
    case Exito(valor) => s"El resultado fue existoso con valor = $valor"
    case Error(mensaje) => s"El resultado fue erroneo con mensaje = $mensaje"
}

mostrarResultado(resultado)
mostrarResultado(resultado3)
mostrarResultado[Int](resultado2)

def describirPedido(pedido: Pedido) = {
  pedido match
    case Pedido(restaurante,costoEnvio,estado) if costoEnvio>=15 => s"Restaurante: $restaurante, Envio Costoso, ${describirEstado(estado)}"
    case Pedido(restaurante,costoEnvio,estado) => s"Restaurante: $restaurante, Envio Normal, ${describirEstado(estado)}"
}

describirPedido(Pedido("Elis",50,Pendiente))

// EJERCICIO 2 
// Completar describir pedido con los otros 3 estados, se puede hacer refactor
// si lo ven necesario

// ARBOLES

sealed trait Arbol

case class Hoja(
  valor: Int
) extends Arbol

case class Nodo(
  valor: Int,
  izquierda: Arbol,
  derecha: Arbol
) extends Arbol

val arbolito:Arbol = Nodo(
  7,
  Hoja(10),
  Nodo(11,Hoja(9),Hoja(5))
)
