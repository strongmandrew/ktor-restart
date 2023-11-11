package headers.utils

import io.ktor.http.*
import java.util.UUID

const val simpleHeaderPath = "headerPath"
const val simpleHeaderKey = "name"
const val simpleHeaderValue = "Sam"

const val predefinedHeaderPath = "predefinedHeaderPath"
const val userAgentHeaderKey = "user-agent"
const val userAgentHeaderValue = "ktor-client"
const val acceptHeaderKey = "accept"
val acceptHeaderValue = ContentType.Application.GZip.toString()
fun joinedHeaders(vararg header: String): String = header.joinToString()

const val serializableHeaderPath = "serializableHeader"
const val serializableHeaderKey = "serializableHeaderKey"
val serializableHeaderValue = CustomHeaderType(
    expires = System.currentTimeMillis(),
    uuid = UUID.randomUUID().toString()
)

const val headerWithDefaultPath = "headerWithDefaultPath"
const val headerWithDefaultKey = "headerWithDefaultKey"
const val headerWithDefaultValue = 99

const val nullableHeaderPath = "nullableHeaderPath"
const val nullableHeaderKey = "nullableHeaderKey"
const val nullableHeaderElvisValue = "value when header was not present"