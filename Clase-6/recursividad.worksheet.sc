def cuentaRegresiva(n: Int): Unit = {
  println(n)
  if n>0 then cuentaRegresiva(n-1)
}

//cuentaRegresiva(10)

def ascendente(n: Int, limite: Int, paso: Int): String = {
  if n+paso <= limite then s"$n ${ascendente(n+paso,limite,paso)}"
  else s"$n"
}

ascendente(0,9,2)

def factorial(n: Int): Int = {
  if n == 0 then 1
  else n*factorial(n-1)
}

factorial(5)

// SUMAR LOS NUMEROS DEL 1 a N
def suma(n: Int): Int = {
  if n == 1 then 1
  else n + suma(n-1)
}

suma(10)

// POTENCIA DE a elevado a b
def potencia(a: Int,b: Int): Int = {
  if b == 0 then 1
  else a * potencia(a,b-1)
}

potencia(4,6)

// SUMAR DIGITOS
def sumarDigitos(num: Int): Int = {
  if num == 0 then 0
  else (num%10)+sumarDigitos(num/10)
}

sumarDigitos(12345)