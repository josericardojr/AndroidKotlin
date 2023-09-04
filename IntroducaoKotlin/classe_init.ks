class Person (val _name: String) {
	
// Member Variables
var name: String

// Initializer Blocks
	
init{
		println("This is first init block")
	}
	
	init{
		println("This is second init block")
	}
	
	init{
		println("This is third init block")
	}
	init {
	this.name = _name
	println("Name = $name")
}
}

fun main(args: Array<String>) {
val person = Person("Geeks")
}



	
