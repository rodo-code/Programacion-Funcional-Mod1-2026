import scala.annotation.tailrec

// SOLUCION AL PROBLEMA 1
def sumaCuadrados(num: Int): Int = {
  @tailrec
  def auxSumaCuadrados(i: Int, acum: Int): Int = {
    if i > num then acum
    else auxSumaCuadrados(i+1,acum+(i*i))
  }
  auxSumaCuadrados(1,0)
}

sumaCuadrados(6)

// SOLUCION AL PROBLEMA 2
def productoDigitos(num: Int): Int = {
  def productoDigitosAux(num: Int, acum: Int): Int = {
    if num < 10 then acum * num
    else productoDigitosAux(num/10,acum*(num%10))
  }
  productoDigitosAux(num,1)
}

productoDigitos(0)
productoDigitos(234)

// SOLUCION AL PROBLEMA 3
def invertirNumero(num: Int): Int = {
  def invertirNumeroAux(num: Int, numReves: Int): Int = {
    if num == 0 then numReves
    else invertirNumeroAux(num/10,numReves*10 + (num%10))
  }
  invertirNumeroAux(num,0)
}

invertirNumero(1200)