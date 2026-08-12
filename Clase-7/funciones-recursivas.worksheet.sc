def fibo(pos: Int): Int = {
  if pos<=1 then pos
  else fibo(pos-1)+fibo(pos-2)
}

fibo(5)

def mcd(a: Int, b:Int): Int = {
  if b == 0 then a
  else mcd(b,a%b)
}

mcd(12000,740)

def contar(letra: Char, texto: String): Int = {
  if texto.length == 0 then 0
  else if texto(0) == letra then 1 + contar(letra,texto.tail)
  else contar(letra,texto.tail)
}

contar('a',"naranja")