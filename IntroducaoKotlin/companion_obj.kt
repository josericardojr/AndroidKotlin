class EmployeeData(var empId: Int, var empName: String) {
 
	// fields which are common across class objects
	companion object {
		val companyName = "CompanyA"
		val companyLocation = "New York"
	}
}
 
fun main() {
	// companion object fields accessed with {className}.{companion object parameter}
	println("Companion Object Data - ${EmployeeData.companyLocation} & ${EmployeeData.companyName}")
}