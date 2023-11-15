package cookie.utils

const val simpleCookiePath = "simpleCookiePath"
const val simpleCookieKey = "simpleCookieKey"
const val simpleCookieValue = "simpleCookieValue"

const val serializedCookiePath = "serializedCookiePath"
const val serializedCookieKey = "serializedCookieKey"
val serializedCookieValue = SerializedCustomCookieType(
    params = mapOf("A" to 2, "B" to 3),
    age = 6L
)