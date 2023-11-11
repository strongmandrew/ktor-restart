package queryParameters.utils

import kotlinx.serialization.Serializable

@Serializable
data class QueryEntitySerializable(
    val age: Int,
    val hobbies: List<String>,
    val country: String
) {

    companion object {
        val TEST = QueryEntitySerializable(
            age = 20,
            hobbies = listOf("Smoking"),
            country = "USA"
        )
    }
}