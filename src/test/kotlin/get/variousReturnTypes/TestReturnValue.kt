package get.variousReturnTypes

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestReturnValue(
    val name: String,
    val age: Int,
    val hobbies: List<String>,
) {

    fun encodeToString(): String = Json.encodeToString(
        serializer = serializer(),
        value = this
    )
}