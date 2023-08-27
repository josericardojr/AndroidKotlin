// Uso do condicional When dentro do intervalo

fun main (args:Array<String>) {
	print("Enter the month number of year: ")
	var num= readLine()!!.toInt()
	when(num) {
		in 1..3 -> print("Spring season")
		in 4..6 -> print("Summer season")
		in 7..8 -> print("Rainy season")
		in 9..10 -> print("Autumn season")
		in 11..12 -> print("Winter season")
		!in 1..12 -> print("Enter valid month of year")
	}
}









