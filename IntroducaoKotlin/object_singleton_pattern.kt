object Singleton {
	init {
		println("Singleton object instantiated!")
	}
 
	var name = "Name data"
 
	fun printDetails() {
		println(name)
	}
}
 
fun main() {
	Singleton.printDetails()
}