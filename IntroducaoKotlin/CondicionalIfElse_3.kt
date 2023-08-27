// Uso do If - Else - Operador Ternário

fun main(args: Array<String>) {
	var a = 50
	var b = 40

	// Retorna um valor
	// que será armazenado na variável max
	var max = if(a > b){				
		print("Greater number is: ")
		a
	}
	else{
		print("Greater number is:")
		b
	}
	print(max)
}

