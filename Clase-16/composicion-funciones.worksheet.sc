val f: Double => Double = (x) => (x+1)
val g: Double => Double = (x) => (2*x)

f(g(3))
g(f(3))

val comp = (x: Double) => g(f(x))
comp(3)

val h = g.compose(f)
val h2 = f.compose(g)
h(3)
h2(3)

val sumarUno: Double => Double = (x) => (x+1)
val duplicar: Double => Double = (x) => (2*x)

val transformar2 = sumarUno.compose(duplicar)
transformar2(5)
val transformar = sumarUno.andThen(duplicar).andThen(sumarUno)
transformar(5)

// Generando una funcion que multiplique por 2, x veces
def generarPorDos(veces: Int): (Int => Int) = {
  if veces == 0 then (x: Int) => x
  else {
    val duplicar = (x: Int) => 2*x
    duplicar.andThen(generarPorDos(veces-1))
  }  
}

val f2 = generarPorDos(5)
f2(3)

