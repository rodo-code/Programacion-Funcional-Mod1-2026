import scala.annotation.tailrec

def factorial(num: Int): Int = {
  @tailrec
  def factorialAux (num: Int, res: Int): Int = {
    if num == 0 then res
    else factorialAux(num-1,res*num)
  }
  factorialAux(num,1)
}

factorial(6)

// FUNCION PARA SUMAR LOS NUMEROS DE 1 A N version RECURSION COLA

def suma(num: Int): Int = {
  @tailrec
  def sumaAux(num: Int, acum: Int): Int = {
    if num == 0 then acum
    else sumaAux(num-1,num+acum)
  }
  sumaAux(num,0)
}

suma(10)

// FUNCION PARA ELEVAR UN NUMERO A UNA POTENCIA version RECURSION COLA

def potencia(base: Int, exponente: Int): Int = {
  @tailrec
  def potenciaAux(base: Int, exponente: Int, res: Int): Int = {
    if exponente == 0 then res
    else potenciaAux(base,exponente-1,base*res)
  }
  potenciaAux(base,exponente,1)
}

potencia(3,5)

// FIBONACCI version RECURSION COLA
def fibonacci(pos: Int): Long = {
  @tailrec
  def fibonacciAux(pos: Int, anterior: Long, acum: Long): Long = {
    if pos == 0 then anterior
    else if pos == 1 then acum
    else fibonacciAux(pos-1,acum,anterior+acum)
  }
  fibonacciAux(pos,0,1)
}

fibonacci(100)

// FUNCION CONTAR LETRAS EN UN TEXTO version RECURSION de COLA

def contar(letra: Char, texto: String): Int = {
  def contarAux(letra: Char, texto: String, cont: Int): Int = {
    if texto.length == 0 then cont
    else if texto(0) == letra then contarAux(letra,texto.tail,cont+1)
    else contarAux(letra,texto.tail,cont)
  }
  contarAux(letra,texto,0)
}

contar('a',"naranja")