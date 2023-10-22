package get.helloWorld

import BaseApplicationTest
import com.strongmandrew.controller.createController
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test

class HelloWorldControllerTest : BaseApplicationTest() {

    @Test
    fun getHelloWorld() = testApplicationWithController {
        executePlainGet("/helloWorld").assertOKAndTextBodyEquals(helloWorldValue.quote())
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