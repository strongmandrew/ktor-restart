package cookie

import BaseApplicationTest
import com.strongmandrew.config.rootController
import cookie.controller.CookieController
import cookie.utils.*
import io.ktor.server.testing.*
import kotlin.test.Test

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
        ).assertOkAndBodyEquals(serializedValue)
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