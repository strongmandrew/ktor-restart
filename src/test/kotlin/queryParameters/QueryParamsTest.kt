package queryParameters

import BaseApplicationTest
import com.strongmandrew.config.rootController
import io.ktor.server.testing.*
import queryParameters.controller.QueryParamController
import queryParameters.utils.*
import kotlin.test.Ignore
import kotlin.test.Test

class QueryParamsTest : BaseApplicationTest() {

    @Test
    fun getByQueryParam() = testApplicationWithQueryParamController {
        executeGetWithQueryParams(
            route = simpleQueryParamPath,
            params = mapOf(simpleQueryParamKey to simpleQueryParamValue)
        ).assertOkAndBodyEquals(simpleQueryParamValue)
    }

    @Test
    fun getQueryParamWhenDefaultSet() = testApplicationWithQueryParamController {
        executePlainGet(simpleDefaultVsQueryParamPath).assertOkAndBodyEquals(simpleDefaultVsQueryParamDefault)
    }

    @Test
    fun getQueryParamWhenSetDespiteDefaultSet() = testApplicationWithQueryParamController {
        executeGetWithQueryParams(
            route = simpleDefaultVsQueryParamPath,
            params = mapOf(simpleDefaultVsQueryParamKey to simpleDefaultVsQueryParamValue)
        ).assertOkAndBodyEquals(simpleDefaultVsQueryParamValue)
    }

    private fun testApplicationWithQueryParamController(
        applicationTestBuilder: suspend ApplicationTestBuilder.() -> Unit = {},
    ) = testApplication {
        application {
            rootController {
                createController<QueryParamController>()
            }
        }
        applicationTestBuilder.invoke(this)
    }
}