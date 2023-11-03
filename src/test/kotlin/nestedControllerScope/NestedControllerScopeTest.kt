package nestedControllerScope

import BaseApplicationTest
import com.strongmandrew.config.rootController
import io.ktor.http.*
import io.ktor.server.testing.*
import nestedControllerScope.controller.ApiController
import nestedControllerScope.controller.NestedTestController
import nestedControllerScope.utils.firstScopeLevel
import nestedControllerScope.utils.libraryName
import nestedControllerScope.utils.secondScopeLevel
import nestedControllerScope.utils.thirdScopeLevel
import kotlin.test.Test

class NestedControllerScopeTest : BaseApplicationTest() {

    @Test
    fun getLibraryTestName() = testApplicationWithNestedControllers {
        val completeRoute = listOf(firstScopeLevel, secondScopeLevel, thirdScopeLevel).joinToString(
            separator = "/"
        )

        executePlainGet(completeRoute).assertOKAndTextBodyEquals(
            expectedBody = libraryName.quote()
        )
    }

    private fun testApplicationWithNestedControllers(
        applicationTestBuilder: suspend ApplicationTestBuilder.() -> Unit = {}
    ) = testApplication {
        application {
            rootController {
                createController<ApiController>(firstScopeLevel) {
                    createController<NestedTestController>(secondScopeLevel)
                }
            }
        }
        applicationTestBuilder.invoke(this)
    }
}