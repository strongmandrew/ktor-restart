package get

import BaseApplicationTest
import com.strongmandrew.controller.createController
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class HelloWorldControllerTest : BaseApplicationTest() {

    @Test
    fun getHelloWorld() = testApplicationWithController {
        val response = executePlainGet("/helloWorld")

        assertEquals(
            expected = HttpStatusCode.OK,
            actual = response.first
        )

        assertEquals(
            expected = "Hello, world!",
            actual = response.second
        )
    }

    @Test
    fun helloWorldWithOverriddenName() = testApplicationWithController {
        val response = executePlainGet("/overriddenHelloWorld")

        assertEquals(
            expected = HttpStatusCode.OK,
            actual = response.first
        )

        assertEquals(
            expected = "Hello, world (overridden)!",
            actual = response.second
        )
    }

    private fun testApplicationWithController(
        applicationTestBuilder: suspend ApplicationTestBuilder.() -> Unit,
    ) = testApplication {
        routing {
            createController<HelloWorldController>()
        }
        applicationTestBuilder.invoke(this)
    }
}