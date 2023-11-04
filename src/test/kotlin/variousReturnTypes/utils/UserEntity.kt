package variousReturnTypes.utils

import kotlinx.serialization.Serializable

@Serializable
class UserEntity(
    val age: Int,
    val name: String,
    val smokes: Boolean
)