package cookie

import BaseApplicationTest
import com.strongmandrew.config.rootController
import com.strongmandrew.encoder.exception.FailedToDecodeException
import cookie.controller.CookieController
import cookie.utils.*
import io.ktor.server.testing.*
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlin.test.Test
import kotlin.test.assertFailsWith

class CookieTest : BaseApplicationTest() {

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
        ).also(::println)

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