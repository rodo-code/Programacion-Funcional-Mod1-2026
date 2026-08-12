val s: String = "Programacion Funcional"

// Longitud de un String
s.length

// head solo obtiene el primer caracter
s.head

// last solo obtiene el ultimo caracter
s.last

// tail obtiene un substring sin el primer elemento
s.tail

// init obtiene un substring sin el ultimo elemento
s.init

// Acceso por posiciones
s(3)
s(12)
s(13)
s(0)
s(s.length - 1)
// Substring
s.substring(0,12)
s.substring(4)
// Contains 
s.contains("b")

// Funcion Pura que invierte un string
def invertir(s: String): String = {
  if s.length == 1 then s
  else invertir(s.substring(1)) + s(0)
}

invertir(s)