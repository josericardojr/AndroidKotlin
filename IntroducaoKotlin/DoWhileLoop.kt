// Uso do Do-While

fun main(args: Array<String>) {
	var number = 6
	var factorial = 1
	do {
		factorial *= number
		number--
	}while(number > 0)
	println("O Fatorial de 6 é $factorial")
}



