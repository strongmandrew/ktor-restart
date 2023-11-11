package pathParameters

import BaseApplicationTest
import com.strongmandrew.config.rootController
import io.ktor.server.testing.*
import kotlin.test.Test

class PathParamsTest : BaseApplicationTest() {

    @Test
    fun getIdFromPathParams() = testApplicationWithPathParamsController {
        val pathParam = 24
        executePlainGet("$pathParamsPath/$pathParam").assertOkAndBodyEquals(pathParam)
    }

    private fun testApplicationWithPathParamsController(
        applicationTestBuilder: suspend ApplicationTestBuilder.() -> Unit = {}
    ) = testApplication {
        application {
            rootController {
                createController<PathParamsController>()
            }
        }
        applicationTestBuilder.invoke(this)
    }
}