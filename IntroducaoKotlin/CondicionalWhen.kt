// Uso do condicional When

fun main (args : Array<String>) {
	print("Insira o nome de um corpo celestial: ")
	var name= readLine()!!.toString()
	when(name) {
		"Sol" -> print("Sol é uma estrela!")
		"Lua" -> print("Lua é um satélite!")
		"Terra" -> print("Terra é um planeta!")
		else -> print("Eu não sei nada sobre isso!")
	}
}







