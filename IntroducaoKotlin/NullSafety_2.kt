// NullSafety

fun main(args: Array<String>) {
	// variable declared as nullable
	var s: String? = "GeeksforGeeks"
	println(s)
	if (s != null) {
		println("String of length ${s.length}")
	} else {
		println("Null string")
	}
	// assign null
	s = null
	println(s)
	if (s != null) {
		println("String of length ${s.length}")
	} else {
		println("Null String")
	}
}















