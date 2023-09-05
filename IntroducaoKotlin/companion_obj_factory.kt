class DatabaseConnection(var connectionName: String) {
    var userName : String = ""
    fun getFullConnection() : String {
        return "$connectionName:$userName"
    }
    companion object {
        val HOST = "hostA"
        val PORT = "1234"
        // create method - used as a factory to create parent class obj
        fun createConnection() : DatabaseConnection =             DatabaseConnection("$HOST:$PORT")
    }
}
 
// main function to call the factory method from companion object
fun main() {
    val dbCon = DatabaseConnection.createConnection()
    dbCon.userName = "adam"
    println("Connection Name: ${dbCon.connectionName}")
    println("Connection Full Url: ${dbCon.getFullConnection()}")
 
}