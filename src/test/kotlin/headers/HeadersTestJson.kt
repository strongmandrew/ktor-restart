package headers

import BaseJsonApplicationTest
import com.strongmandrew.config.rootController
import headers.controller.HeadersController
import headers.utils.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.reflect.typeOf
import kotlin.test.Test

class HeadersTestJson : BaseJsonApplicationTest() {

    @Test
    fun getStringFromHeaders() = testApplicationWithHeaderController {
        executeGetWithHeaders(
            route = simpleHeaderPath,
            headers = mapOf(simpleHeaderKey to simpleHeaderValue)
        ).assertOkAndBodyEquals(simpleHeaderValue)
    }

    @Test
    fun getPredefinedHeaders() = testApplicationWithHeaderController {
        executeGet(predefinedHeaderPath) {
            accept(ContentType.parse(acceptHeaderValue))
            userAgent(userAgentHeaderValue)
        }.assertOkAndBodyEquals(
            joinedHeaders(userAgentHeaderValue, acceptHeaderValue)
        )
    }

    @Test
    fun getHeaderOfCustomType() = testApplicationWithHeaderController {
        val encodedType = encodeToString(
            serializer = CustomHeaderType.serializer(),
            value = serializableHeaderValue
        )

        executeGetWithHeaders(
            route = serializableHeaderPath,
            headers = mapOf(serializableHeaderKey to encodedType)
        ).assertOkAndBodyEquals(serializableHeaderValue)
    }

    @Test
    fun getHeaderWithDefaultWhenHeaderNotSet() = testApplicationWithHeaderController {
        executeGet(headerWithDefaultPath).assertOkAndBodyEquals(headerWithDefaultValue)
    }

    @Test
    fun getHeaderWithDefaultWhenHeaderSet() = testApplicationWithHeaderController {
        val headerValue = 81

        executeGetWithHeaders(
            route = headerWithDefaultPath,
            headers = mapOf(headerWithDefaultKey to headerValue)
        ).assertOkAndBodyEquals(headerValue)
    }

    @Test
    fun getNullableHeaderWhenNotSet() = testApplicationWithHeaderController {
        executeGet(nullableHeaderPath).assertOkAndBodyEquals(nullableHeaderElvisValue)
    }

    private fun testApplicationWithHeaderController(
        applicationTestBuilder: suspend ApplicationTestBuilder.() -> Unit = {}
    ) = testApplication {
        application {
            rootController {
                createController<HeadersController>()
            }
        }
        applicationTestBuilder.invoke(this)
    }
}