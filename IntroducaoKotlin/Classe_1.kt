class Car {
	var make: String
	var model: String
	var year: Int

	fun getInfo(): String {
		return "$make $model, year $year"
	}
}

fun main() {
	val myCar = Car()
	myCar.make = "Toyota"
	myCar.model = "Camry"
	myCar.year = 2020

	println(myCar.getInfo())
}
