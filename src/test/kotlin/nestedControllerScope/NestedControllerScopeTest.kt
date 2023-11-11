package nestedControllerScope

import BaseApplicationTest
import com.strongmandrew.config.rootController
import io.ktor.server.testing.*
import nestedControllerScope.controller.ApiController
import nestedControllerScope.controller.NestedTestController
import nestedControllerScope.utils.*
import kotlin.test.Test

class NestedControllerScopeTest : BaseApplicationTest() {

    @Test
    fun getLibraryTestName() = testApplicationWithNestedControllers {
        val completeRoute = getCompleteRouteByPath(firstScopeLevel, secondScopeLevel, thirdScopeLevel)

        executePlainGet(completeRoute).assertOkAndBodyEquals(
            expectedBody = libraryName
        )
    }

    @Test
    fun getByNotOverriddenPath() = testApplicationWithNestedControllers {
        val completeRoute = getCompleteRouteByPath(firstScopeLevel, secondScopeLevel)

        executePlainGet(completeRoute).assertOkAndBodyEquals(
            expectedBody = testName
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