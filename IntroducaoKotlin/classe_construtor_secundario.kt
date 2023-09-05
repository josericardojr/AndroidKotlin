//main function
fun main(args: Array<String>)
{
	Add(5,6)
}
class Add {
	// calling another secondary using this
	constructor(a: Int,b:Int) : this(a,b,7) {
		var sumOfTwo = a + b
		println("The sum of two numbers 5 and 6 is: $sumOfTwo")
	}
	// this executes first
	constructor(a: Int, b: Int,c: Int) {
		var sumOfThree = a + b + c
		println("The sum of three numbers 5,6 and 7 is: $sumOfThree")
	}
}
