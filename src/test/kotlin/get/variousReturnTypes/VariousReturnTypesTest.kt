package get.variousReturnTypes

import BaseApplicationTest
import com.strongmandrew.controller.createController
import io.ktor.server.testing.*
import kotlin.test.Test

class VariousReturnTypesTest : BaseApplicationTest() {

    @Test
    fun returnBooleanType() = testApplicationWithControllers {
        executePlainGet("isSuccess").assertOKAndTextBodyEquals(booleanTypeValue.toString())
    }

    @Test
    fun returnLongType() = testApplicationWithControllers {
        executePlainGet("getLong").assertOKAndTextBodyEquals(longTypeValue.toString())
    }

    @Test
    fun returnSpecialValue() = testApplicationWithControllers {
        val expectedSpecialTypeBody = specialTypeValue.encodeToString()
        executePlainGet("getSpecialValue").assertOKAndTextBodyEquals(expectedSpecialTypeBody)
    }

    private fun testApplicationWithControllers(
        applicationTestBuilder: suspend ApplicationTestBuilder.() -> Unit,
    ) = testApplication {
        routing {
            createController<VariousReturnTypesController>()
        }
        applicationTestBuilder.invoke(this)
    }
}