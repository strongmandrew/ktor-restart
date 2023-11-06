package queryParameters

import BaseApplicationTest
import com.strongmandrew.config.rootController
import io.ktor.server.testing.*
import queryParameters.controller.QueryParamController
import queryParameters.utils.*
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

    @Test
    fun getQueryParamsMixedWithDefault() = testApplicationWithQueryParamController {
        executeGetWithQueryParams(
            route = mediumQueryParamPath,
            params = mapOf(
                mediumQueryParamFirstKey to mediumQueryParamFirstValue,
                mediumQueryParamThirdKey to mediumQueryParamThirdValue
            )
        ).assertOkAndBodyEquals(
            joinNames(mediumQueryParamFirstValue, mediumQueryParamSecondDefault, mediumQueryParamThirdValue)
        )
    }

    @Test
    fun getQueryParamsMixedWithDefaultOverriddenByQuery() = testApplicationWithQueryParamController {
        val overriddenDefaultInQuery = "Sam"

        executeGetWithQueryParams(
            route = mediumQueryParamPath,
            params = mapOf(
                mediumQueryParamFirstKey to mediumQueryParamFirstValue,
                mediumQueryParamSecondKey to overriddenDefaultInQuery,
                mediumQueryParamThirdKey to mediumQueryParamThirdValue
            )
        ).assertOkAndBodyEquals(
            joinNames(mediumQueryParamFirstValue, overriddenDefaultInQuery, mediumQueryParamThirdValue)
        )
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