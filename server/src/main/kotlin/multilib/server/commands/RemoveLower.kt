package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import multilib.utilities.input.*
import multilib.utilities.result.Result


class RemoveLower: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "remove from the collection all elements smaller than the given one"
    private var fields: Map<String, Map<String, String>> = mapOf(
        "value" to mapOf<String, String>(
            "type" to "Int",
            "length" to "27"
        )
    )

    override fun action(data: Map<String, String?>, result: Result): Result {

        val count = data["value"]!!.toInt()
        val newOrgs = MyCollection<Organization>()
        val userLogin = data["userLogin"]

        for ( org in orgs ) {
            if ( org.getPostalAddressStreet().length < count && org.getUserLogin() == userLogin ) {
                newOrgs.add(org)
            }
        }
        for ( org in newOrgs ) {
            orgs.remove(org)
        }

        result.setMessage("Done\n")

        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
    override fun commandBuilding(mapData: MutableMap<String, String>, data: String): MutableMap<String, String> {
        val input = InputFile(data)

        for (key in fields.keys) {
            mapData[key] = input.getNextWord(null)
        }

        return mapData
    }
}