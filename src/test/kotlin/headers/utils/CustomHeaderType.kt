package headers.utils

import kotlinx.serialization.Serializable

@Serializable
data class CustomHeaderType(
    val expires: Long,
    val uuid: String,
)