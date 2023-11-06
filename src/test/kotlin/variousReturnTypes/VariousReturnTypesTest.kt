package variousReturnTypes

import BaseApplicationTest
import com.strongmandrew.config.rootController
import com.strongmandrew.executor.NullableReturnTypeException
import io.ktor.server.testing.*
import variousReturnTypes.controller.VariousTypeReturnsController
import variousReturnTypes.utils.*
import kotlin.test.Test
import kotlin.test.assertFailsWith

class VariousReturnTypesTest : BaseApplicationTest() {

    @Test
    fun returnString() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(stringPath).assertOkAndBodyEquals(
            expectedBody = stringValue
        )
    }

    @Test
    fun returnLong() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(longPath).assertOkAndBodyEquals(
            expectedBody = longValue
        )
    }

    @Test
    fun returnBoolean() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(booleanPath).assertOkAndBodyEquals(
            expectedBody = booleanValue
        )
    }

    @Test
    fun returnChar() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(charPath).assertOkAndBodyEquals(
            expectedBody = charValue
        )
    }

    @Test
    fun returnInt() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(intPath).assertOkAndBodyEquals(
            expectedBody = intValue
        )
    }

    @Test
    fun returnList() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(listPath).assertOkAndBodyEquals(
            expectedBody = listOfStrings
        )
    }

    @Test
    fun returnMap() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(mapPath).assertOkAndBodyEquals(
            expectedBody = mapOfStringAndInt
        )
    }

    @Test
    fun returnUserEntity() = testApplicationWithVariousReturnTypesControllers {
        executePlainGet(userEntityPath).assertOkAndBodyEquals(
            expectedBody = userEntity
        )
    }

    @Test
    fun returnNull() = testApplicationWithVariousReturnTypesControllers {
        assertFailsWith(NullableReturnTypeException::class) {
            executePlainGet(nullablePath).assertOkAndBodyEquals(nullable)
        }
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