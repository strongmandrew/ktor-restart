package get.overriddenRoute

import BaseApplicationTest
import com.strongmandrew.controller.createController
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test

class OverriddenPathTest : BaseApplicationTest() {

    @Test
    fun requestByOverriddenName() = testApplicationWithController {
        executePlainGet("/overriddenPath").assertOKAndTextBodyEquals(stringSuccessValue.quote())
    }

    private fun testApplicationWithController(
        applicationTestBuilder: suspend ApplicationTestBuilder.() -> Unit,
    ) = testApplication {
        routing {
            createController<OverriddenPathController>()
        }
        applicationTestBuilder.invoke(this)
    }
}