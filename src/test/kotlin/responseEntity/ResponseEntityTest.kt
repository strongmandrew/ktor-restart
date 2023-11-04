package responseEntity

import BaseApplicationTest
import com.strongmandrew.config.rootController
import io.ktor.http.*
import io.ktor.server.testing.*
import responseEntity.controller.ResponseEntityController
import responseEntity.utils.*
import kotlin.test.Test

class ResponseEntityTest : BaseApplicationTest() {

    @Test
    fun getOkStatusCode() = testApplicationWithResponseEntityController {
        executePlainGet(okPath).assertStatusAndBodyEquals(
            statusCode = HttpStatusCode.OK,
            expectedBody = okName
        )
    }

    @Test
    fun getBadRequestStatusCode() = testApplicationWithResponseEntityController {
        executePlainGet(badRequestPath).assertStatusAndBodyEquals(
            statusCode = HttpStatusCode.BadRequest,
            expectedBody = badRequestName
        )
    }

    @Test
    fun getInternalServerErrorStatusCode() = testApplicationWithResponseEntityController {
        executePlainGet(internalServerErrorPath).assertStatusAndBodyEquals(
            statusCode = HttpStatusCode.InternalServerError,
            expectedBody = internalServerErrorName
        )
    }

    private fun testApplicationWithResponseEntityController(
        applicationTestBuilder: suspend ApplicationTestBuilder.() -> Unit = {},
    ) = testApplication {
        application {
            rootController {
                createController<ResponseEntityController>()
            }
        }
        applicationTestBuilder.invoke(this)
    }
}