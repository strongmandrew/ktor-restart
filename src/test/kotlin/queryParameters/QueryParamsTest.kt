package queryParameters

import BaseApplicationTest
import com.strongmandrew.config.rootController
import com.strongmandrew.encoder.FailedToDecodeException
import com.strongmandrew.extractor.CallElementNotFoundException
import io.ktor.server.testing.*
import queryParameters.controller.QueryParamController
import queryParameters.utils.*
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertFailsWith

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

    @Test
    fun getQueryParamNotFound() = testApplicationWithQueryParamController {
        assertFailsWith(CallElementNotFoundException::class) {
            executeGetWithQueryParams(
                route = mediumQueryParamPath,
                params = mapOf(mediumQueryParamFirstKey to mediumQueryParamFirstValue)
            )
        }
    }

    @Test
    fun getQueryParamSerializable() = testApplicationWithQueryParamController {
        val encodedEntity = jsonProvider.provide().encodeToString(
            serializer = QueryEntitySerializable.serializer(),
            value = serializableQueryValue
        )

        executeGetWithQueryParams(
            route = serializableQueryPath,
            params = mapOf(serializableQueryKey to encodedEntity)
        ).assertOkAndBodyEquals(serializableQueryValue)
    }

    @Test
    fun getQueryParamNotSerializable() = testApplicationWithQueryParamController {
        assertFailsWith(FailedToDecodeException::class) {
            executeGetWithQueryParams(
                route = notSerializableQueryPath,
                params = mapOf(notSerializableQueryKey to notSerializableQueryValue)
            )
        }
    }

    @Test
    @Ignore("Need to handle both nullable & default arguments")
    fun getIfNullableQueryNotProvided() = testApplicationWithQueryParamController {
        executePlainGet(nullableQueryPath).assertOkAndBodyEquals(nullableReturnString)
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