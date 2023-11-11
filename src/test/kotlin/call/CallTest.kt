package call

import BaseApplicationTest
import call.utils.incorrectTypeCallPath
import call.utils.simpleCallPath
import com.strongmandrew.config.rootController
import com.strongmandrew.extractor.exception.TypeMismatchException
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertFailsWith

class CallTest : BaseApplicationTest() {

    @Test
    fun getCall() = testApplicationWithCallController {
        executeGet(simpleCallPath).assertStatus(HttpStatusCode.OK)
    }

    @Test
    fun getCallFailedDueToTypeMismatch() = testApplicationWithCallController {
        assertFailsWith(TypeMismatchException::class) {
            executeGet(incorrectTypeCallPath)
        }
    }

    private fun testApplicationWithCallController(
        applicationTestBuilder: suspend ApplicationTestBuilder.() -> Unit = {},
    ) = testApplication {
        application {
            rootController {
                createController<CallController>()
            }
        }
        applicationTestBuilder.invoke(this)
    }
}