val nombreCompleto = "Pedro Picapiedra"
val nota1: Double = 90.0
val nota2: Double = 90.0
val nota3: Double = 100.0

val promedio: Double = (nota1+nota2+nota3)/3

val validacion: Boolean = promedio >= 0 && promedio <= 100
val mensajeValidacion: String =
if validacion then "NOTAS VALIDAS" 
else "NOTAS INVALIDAS"

val mensajeFinal: String = if validacion then {
  val categoria: String = if promedio < 60 then "REPROBADO"
  else if promedio<70 then "NOTA REGULAR"
  else if promedio<85 then "NOTA BUENA"
  else "EXCELENTE"
  categoria
}
else mensajeValidacion

println(mensajeFinal)