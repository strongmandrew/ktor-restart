package queryParameters

import BaseJsonApplicationTest
import com.strongmandrew.config.rootController
import com.strongmandrew.encoder.exception.FailedToDecodeException
import com.strongmandrew.extractor.exception.CallElementNotFoundException
import io.ktor.server.testing.*
import queryParameters.controller.QueryParamController
import queryParameters.utils.*
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertFailsWith

class QueryParamsTestJson : BaseJsonApplicationTest() {

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
        val encodedEntity = encodeToString(
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
    fun getIfNullableQueryNotProvided() = testApplicationWithQueryParamController {
        executePlainGet(nullableQueryPath).assertOkAndBodyEquals(nullableReturnString)
    }

    @Test
    fun getIfNullableQueryProvided() = testApplicationWithQueryParamController {
        val testQuery = "providedString"

        executeGetWithQueryParams(
            route = nullableQueryPath,
            params = mapOf(nullableQueryKey to testQuery)
        ).assertOkAndBodyEquals(testQuery)
    }

    @Test
    fun getIfNullableQueryNotProvidedWithDefaultSet() = testApplicationWithQueryParamController {
        executePlainGet(nullableDefaultQueryPath).assertOkAndBodyEquals(nullableDefaultReturnValue)
    }

    @Test
    fun getIfNullableQueryProvidedWithDefaultSet() = testApplicationWithQueryParamController {
        val testQuery = 11

        executeGetWithQueryParams(
            route = nullableDefaultQueryPath,
            params = mapOf(nullableDefaultQueryKey to testQuery)
        ).assertOkAndBodyEquals(testQuery)
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