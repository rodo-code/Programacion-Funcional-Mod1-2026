def procesarRango(
 desde: Int,
 hasta: Int,
 transformar: Int => Int,
 combinar: (Int, Int) => Int,
 valorInicial: Int
): Int = {
  if desde > hasta then valorInicial
  else {
    val resParcial = combinar(transformar(desde),valorInicial)
    procesarRango(desde+1,hasta,transformar,combinar,resParcial)
  }
}

def buscar(
 desde: Int,
 hasta: Int,
 condicion: Int => Boolean
): Int = {
  if desde > hasta then -1
  else {
    if condicion(desde) then desde
    else buscar(desde+1,hasta,condicion)
  }
}

def contarDivisoresSimple(n: Int): Int = {
  contarDivisores(1,n,0)
}

def contarDivisores(i: Int,n: Int,acum: Int): Int = {
  if i>n then acum
  else if n%i==0 then contarDivisores(i+1,n,acum+1)
  else contarDivisores(i+1,n,acum)
}

def tieneNDivisores(cantidadDivisores: Int)(n: Int): Boolean = {
  contarDivisoresSimple(n) == cantidadDivisores
}

def esPrimo(n: Int): Boolean = {
  contarDivisores(1,n,0) == 2
}

// Obtener el número primo más bajo de un rango 

buscar(55,150,esPrimo)

// Obtener el número con más divisores de un rango (15 puntos)
val cantidadDivisoresMenor = procesarRango(10,15,contarDivisoresSimple,(x,y) => {
  if x>y then x
  else y
},0)

buscar(10,15,tieneNDivisores(cantidadDivisoresMenor))