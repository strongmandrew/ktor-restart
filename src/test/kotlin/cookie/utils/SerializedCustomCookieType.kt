package cookie.utils

import kotlinx.serialization.Serializable

@Serializable
data class SerializedCustomCookieType(
    val params: Map<String, Int>,
    val age: Long
)