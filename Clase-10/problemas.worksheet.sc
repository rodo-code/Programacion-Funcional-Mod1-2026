import scala.annotation.tailrec

// SOLUCION AL PROBLEMA 4

def contarGrupos(texto: String): Int = {
  @tailrec
  def contar(pos: Int, acum: Int): Int = {
    if pos == texto.length then acum
    else if pos == 0 then contar(pos+1,acum+1)
    else if texto(pos) == texto(pos-1) then contar(pos+1,acum)
    else contar(pos+1,acum+1)
  }
  contar(0,0)
}

contarGrupos("aaabbcaa")
contarGrupos("aaaa")
contarGrupos("abcde")
contarGrupos("aabbcc")
contarGrupos("")

// SOLUCION PROBLEMA 5
def multiplicacionDigitos(num: Int): Int = {
  @tailrec
  def auxMultDig(num: Int, res: Int): Int = {
    if num < 10 then num*res
    else auxMultDig(num/10,res*(num%10))
  }
  auxMultDig(num,1)
}

def persistenciaMultiplicativa(num: Int): Int = {
  @tailrec
  def auxPersistencia(num: Int, acum: Int): Int = {
    if num < 10 then acum
    else auxPersistencia(multiplicacionDigitos(num),acum+1)
  }
  auxPersistencia(num,0)
}

persistenciaMultiplicativa(999)