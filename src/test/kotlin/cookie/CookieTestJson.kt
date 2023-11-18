package cookie

import BaseJsonApplicationTest
import com.strongmandrew.config.rootController
import cookie.controller.CookieController
import cookie.utils.*
import io.ktor.server.testing.*
import kotlin.reflect.typeOf
import kotlin.test.Test

class CookieTestJson : BaseJsonApplicationTest() {

    @Test
    fun getSimpleCookie() = testApplicationWithCookieController {
        executeGetWithCookies(
            route = simpleCookiePath,
            cookies = mapOf(
                simpleCookieKey to simpleCookieValue
            )
        ).assertOkAndBodyEquals(simpleCookieValue)
    }

    @Test
    fun getSerializedCookie() = testApplicationWithCookieController {
        val serializedValue = encodeToString(
            serializer = SerializedCustomCookieType.serializer(),
            value = serializedCookieValue
        )

        executeGetWithCookies(
            route = serializedCookiePath,
            cookies = mapOf(
                serializedCookieKey to serializedValue
            )
        ).assertOkAndBodyEquals(serializedCookieValue)
    }

    @Test
    fun getDefaultProvidedCookie() = testApplicationWithCookieController {
        executeGet(defaultProvidedCookiePath).assertOkAndBodyEquals(defaultProvidedCookieValue)
    }

    @Test
    fun getNullableCookie() = testApplicationWithCookieController {
        executeGet(nullableCookiePath).assertOkAndBodyEquals(alternativeCookieValue)
    }

    private fun testApplicationWithCookieController(
        applicationTestBuilder: suspend ApplicationTestBuilder.() -> Unit
    ) = testApplication {
        application {
            rootController {
                createController<CookieController>()
            }
        }
        applicationTestBuilder.invoke(this)
    }
}