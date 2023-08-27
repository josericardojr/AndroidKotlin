// Funcao de alta ordem

// expressao lambda
var lambda = {a: Int , b: Int -> a + b }

// funcao de alta ordem
fun higherfunc( lmbd: (Int, Int) -> Int) {	 // accepting lambda as parameter
		
	var result = lmbd(2,4) // invokes the lambda expression by passing parameters				
	println("The sum of two numbers is: $result")
}

fun main(args: Array<String>) {
	higherfunc(lambda)		 //passing lambda as parameter
}














