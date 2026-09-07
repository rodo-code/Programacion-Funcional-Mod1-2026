case class Persona(
  nombre: String,
  edad: Int
){
  def aumentarEdad(): Persona = this.copy(edad=this.edad+1)
}

val persona = Persona("Pablo",19)
val persona2 = persona.aumentarEdad()
val persona3 = Persona("Pablo",20)
//println(persona)
//println(persona2)

if persona3 == persona2 then "Los objetos son iguales"
else "Los objetos no son iguales"

/* EJERCICIO 1

Crear una case class que represente una cuenta Bancaria
- Numero como Long
- Nombre del Titular como String
- Saldo como Double
- Activo como Boolean

Crear ademas 2 métodos
- uno para actualizar saldo
- y el otro para desactivar una cuenta

Usar copy para ambos metodos y no usar new para crear el objeto
*/



// PATTERN MATCHING
def literal(numero: Int): String = {
  numero match
    case 0 => "Cero"
    case 1 => "Uno"
    case 2 => "Dos"
    case 3 => "Tres"
    case 4 => "Cuatro"
    case 5 => "Cinco"
    case n => s"Aun no me se el numero $n"
    case _ => "Aun no me se ese valor"
}

literal(0)

def lenguaje(codigo: String): String = {
  codigo match
    case "es" => "Español"
    case "fr" => "Frances"
    case "en" => "Ingles"
    case "dt" => "Aleman"
    case _ => "Codigo invalido"
}
lenguaje("po")

// CASE OBJECT
case object Pendiente
case object Procesando
case object Completado

def mostrarEstado(estado: AnyRef): String = {
  estado match 
    case Pendiente => "Transaccion Pendiente"
    case Procesando => "Transaccion en Proceso"
    case Completado => "Transaccion Completada"
}

mostrarEstado(Completado)

def mostrarPersona(persona: Persona): String = {
  persona match 
    case Persona(nombre,edad) => s"$nombre tiene $edad años"
}

mostrarPersona(persona3)

def saludar(persona: Persona): String = {
  persona match
    case Persona("Oscar",_) => "Buenos dias señor Rector"
    case Persona("Tommy",_) => "Buenos dias señor Decano"
    case Persona("Alexis",_) => "Hola Alexis"
    case Persona(nombre,edad) => {
      if edad >=30 then s"Buenos dias $nombre" else s"Hola $nombre"
    }
}

saludar(new Persona("Oscar",50))
saludar(new Persona("Alexis",30))
saludar(new Persona("Rodolfo",27))

// PATTERN GUARDS 
def clasificar(persona: Persona): String = {
  persona match 
    case Persona(nombre,edad) if edad >= 18 => s"$nombre es mayor de edad"
    case Persona(nombre,edad) => s"$nombre es menor de edad"
}

clasificar(persona3)
clasificar(new Persona("Huguito",7))

/* EJERCICIO 2

En base a la clase Cuenta 

case class Cuenta(
  numero: Long,
  nombreTitular: String,
  saldo: Double,
  activo: Boolean
)

Hacer una función llamada estadoCuenta usando pattern matching que:
  si la cuenta esta desactivada, devuelva "CUENTA DESACTIVADA"
  si la cuenta esta activa:
    - si el saldo es 0 devuelva "CUENTA VACIA"
    - si el saldo es mayor a 0 pero menor o igual a 100 devuelva "CUENTA PEQUEÑA"
    - si el saldo es mayor a 100 devuelva "CUENTA NORMAL"
*/

case class Cuenta(
  numero: Long,
  nombreTitular: String,
  saldo: Double,
  activo: Boolean
)

def estadoCuenta(cuenta: Cuenta) = {
  cuenta match 
    case Cuenta(_,_,_,false) => "CUENTA DESACTIVADA"
    case Cuenta(_,_,0.0,true) => "CUENTA VACIA"
    case Cuenta(_,_,saldo,true) if saldo>0 && saldo<=100 => "CUENTA PEQUEÑA"
    case Cuenta(_,_,saldo,true) if saldo>100 => "CUENTA NORMAL"
    case _ => "CUENTA INVALIDA"
}

estadoCuenta(new Cuenta(1,"Rodolfo",-100,true))