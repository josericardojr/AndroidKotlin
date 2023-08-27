// Expressão Lambda

// Expressao Lambda com anotação de tipo
val sum1 = { a: Int, b: Int -> a + b }

// Expressao Lambda sem anotação de tipo
val sum2:(Int,Int)-> Int = { a , b -> a + b}

fun main(args: Array<String>) {
	val result1 = sum1(2,3)
	val result2 = sum2(3,4)
	println("The sum of two numbers is: $result1")
	println("The sum of two numbers is: $result2")

	// directly print the return value of lambda
	// without storing in a variable.
	println(sum1(5,7))
}












