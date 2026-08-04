val nombre: String = "Pedro Picapiedra"
val nota1: Double = 80.0
val nota2: Double = 67.0
val nota3: Double = 90.0

val promedio: Double = (nota1+nota2+nota3)/3
val resultado: Boolean = promedio >= 60

val mensaje: String = if resultado then "APROBADO" else "REPROBADO"

println(s"Nota 1: $nota1, Nota 2: $nota2, Nota 3: $nota3")
println(s"Resultado: $mensaje")