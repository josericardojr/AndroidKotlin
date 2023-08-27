// NullSafety

fun main(args: Array<String>) {
	var str : String? = "GeeksforGeeks"
	println(str?.length)
	str = null
	println(str?.length ?: "-1")
	
}

















