class Company {
	var name: String = ""
		get() = field	 // getter
		set(value) {		 // setter
			field = value
		}
}
fun main(args: Array<String>) {
	val c = Company()
	c.name = "GeeksforGeeks" // access setter
	println(c.name)		 // access getter
}
