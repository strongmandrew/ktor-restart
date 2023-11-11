package variousReturnTypes.utils

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val age: Int,
    val name: String,
    val smokes: Boolean
)