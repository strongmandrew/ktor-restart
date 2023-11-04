package variousReturnTypes

import BaseApplicationTest
import com.strongmandrew.config.rootController
import io.ktor.server.testing.*
import variousReturnTypes.controller.VariousTypeReturnsController
import variousReturnTypes.utils.*
import kotlin.test.Test

class VariousReturnTypesTest : BaseApplicationTest() {

    @Test
    fun returnString() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(stringPath).assertOKAndBodyEquals(
            expectedBody = stringValue
        )
    }

    @Test
    fun returnLong() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(longPath).assertOKAndBodyEquals(
            expectedBody = longValue
        )
    }

    @Test
    fun returnBoolean() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(booleanPath).assertOKAndBodyEquals(
            expectedBody = booleanValue
        )
    }

    @Test
    fun returnChar() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(charPath).assertOKAndBodyEquals(
            expectedBody = charValue
        )
    }

    @Test
    fun returnInt() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(intPath).assertOKAndBodyEquals(
            expectedBody = intValue
        )
    }

    @Test
    fun returnList() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(listPath).assertOKAndBodyEquals(
            expectedBody = listOfStrings
        )
    }

    @Test
    fun returnMap() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(mapPath).assertOKAndBodyEquals(
            expectedBody = mapOfStringAndInt
        )
    }

    @Test
    fun returnUserEntity() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(userEntityPath).assertOKAndBodyEquals(
            expectedBody = userEntity
        )
    }

    private fun testApplicationWithVariousReturnTypesControllers(
        applicationTestBuilder: suspend ApplicationTestBuilder.() -> Unit = {},
    ) = testApplication {
        application {
            rootController {
                createController<VariousTypeReturnsController>()
            }
        }
        applicationTestBuilder.invoke(this)
    }
}