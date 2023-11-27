package multipart

import BaseJsonApplicationTest
import com.strongmandrew.config.rootController
import io.ktor.server.testing.*
import multipart.controller.MultipartController
import multipart.utils.*
import kotlin.test.Test

class MultipartTest : BaseJsonApplicationTest() {

    @Test
    fun getMultipartFormData() = testApplicationWithMultipartController {
        executeGetWithMultipartFormData(
            route = simpleMultipartPath,
            multipart = mapOf(simpleMultipartKey to simpleMultipartValue)
        ).assertOkAndBodyEquals(simpleMultipartValue)
    }

    @Test
    fun getMultipartFormDataWithDefaultSet() = testApplicationWithMultipartController {
        /* multipart not provided */
        executeGet(multipartWithDefaultSetPath).assertOkAndBodyEquals(
            multipartWithDefaultSetValue
        )

        val encodedValue = encodeToString(
            serializer = MultipartCustomType.serializer(),
            value = multipartWithDefaultSetProvidedValue
        )
        /* multipart provided */
        executeGetWithMultipartFormData(
            route = multipartWithDefaultSetPath,
            multipart = mapOf(multipartWithDefaultSetKey to encodedValue)
        ).assertOkAndBodyEquals(multipartWithDefaultSetProvidedValue)
    }

    @Test
    fun getMultipartWithNullableMultipartFormData() = testApplicationWithMultipartController {
        /* multipart not provided */
        executeGet(nullableMultipartPath).assertOkAndBodyEquals(
            nullableMultipartElvisValue
        )

        val encodedValue = encodeToString(
            serializer = MultipartInnerCustomType.serializer(),
            value = nullableMultipartProvidedValue
        )
        /* multipart provided */
        executeGetWithMultipartFormData(
            route = nullableMultipartPath,
            multipart = mapOf(nullableMultipartKey to encodedValue)
        ).assertOkAndBodyEquals(nullableMultipartProvidedValue)
    }

    private fun testApplicationWithMultipartController(
        applicationTestBuilder: suspend ApplicationTestBuilder.() -> Unit = {}
    ) = testApplication {
        application {
            rootController {
                createController<MultipartController>()
            }
        }
        applicationTestBuilder.invoke(this)
    }
}