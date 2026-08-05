def incrementar(num: Int): Int = {
  num + 1
}

incrementar(10)

def sumar(a: Int, b: Int): Int = {
  a+b
}

sumar(3,6)

def min(a: Int, b: Int): Int = {
  if a < b then a else b
}

def max(a: Int, b: Int): Int = {
  if a > b then a else b
}

def medio(a: Int, b: Int, c: Int): Int = {
  a + b + c - min(min(a,b),c) - max(max(a,b),c)
}

medio(7,4,9)
medio(1,4,5)
medio(7,7,3)
medio(3,3,3)

val PI: Double = 3.1416

def areaCircunferencia(radio: Double): Double = {
  val PI: Double = 3
  PI * radio * radio
}

areaCircunferencia(4)