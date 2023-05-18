package multilib.server.commands

import allForCommands.commands.AbstractCommand
import multilib.server.dataBase.DataBaseWorker
import multilib.utilities.result.Result

class Registration: AbstractCommand() {
    private val dataBaseWorker = DataBaseWorker()
    private val description: String = "allows you to register"
    private var fields: Map<String, Map<String, String>> = mapOf(
        "login" to mapOf(
            "title" to "Enter login",
            "type" to "String"
        ),
        "password" to mapOf(
            "title" to "Enter password",
            "type" to "String",
            "minLength" to "8"
        )
    )
    override fun action(data: Map<String, String?>): Result {
        dataBaseWorker.getConnectionToDataBase()
        val result = Result()
        val login = data["login"]!!
        val password = data["password"]!!

        if (dataBaseWorker.getUserInfoForRegistration(login)) {
            result.setMessage("This name already exist")
            return result
        }
        dataBaseWorker.registerUser(login, password)
        result.setMessage("Done")
        dataBaseWorker.closeConnectionToDataBase()
        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields

}