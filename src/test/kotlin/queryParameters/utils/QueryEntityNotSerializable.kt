package queryParameters.utils

class QueryEntityNotSerializable(
    val age: Int,
    val name: String,
    val occupation: String
) {

    companion object {
        val TEST = QueryEntityNotSerializable(
            age = 44,
            name = "Kylie",
            occupation = "painter"
        )
    }
}