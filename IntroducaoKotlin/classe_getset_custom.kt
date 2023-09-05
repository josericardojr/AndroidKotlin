class Registration( email: String, pwd: String, age: Int , gender: Char) {

	var email_id: String = email
		// Custom Getter
		get() {
		return field.toLowerCase()
		}
	var password: String = pwd
		// Custom Setter
		set(value){
			field = if(value.length > 6) value else throw IllegalArgumentException("Passwords is too small")
		}

	var age: Int = age
		// Custom Setter
		set(value) {
			field = if(value > 18 ) value else throw IllegalArgumentException("Age must be 18+")
		}
	var gender : Char = gender
		// Custom Setter
		set (value){
			field = if(value == 'M') value else throw IllegalArgumentException("User should be male")
		}
}

fun main(args: Array<String>) {

	val geek = Registration("PRAVEENRUHIL1993@GMAIL.COM","Geeks@123",25,'M')

	println("${geek.email_id}")
	geek.email_id = "GEEKSFORGEEKS@CAREERS.ORG"
	println("${geek.email_id}")
	println("${geek.password}")
	println("${geek.age}")
	println("${geek.gender}")
	
	// throw IllegalArgumentException("Passwords is too small")
	geek.password = "abc"
	
	// throw IllegalArgumentException("Age should be 18+")
	geek.age= 5
			
	// throw IllegalArgumentException("User should be male")
	geek.gender = 'F'		
}
