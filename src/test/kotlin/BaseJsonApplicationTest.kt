import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.test.assertEquals

open class BaseJsonApplicationTest {

    protected val json = Json

    protected suspend fun ApplicationTestBuilder.executeGet(
        route: String,
        requestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): Pair<HttpStatusCode, String> {
        val response = client.get(route) {
            requestBuilder.invoke(this)
        }

        return response.status to response.bodyAsText()
    }

    protected suspend fun ApplicationTestBuilder.executeGetWithQueryParams(
        route: String,
        params: Map<String, Any>,
    ): Pair<HttpStatusCode, String> = executeGet(route) {
        params.forEach { (key, value) ->
            parameter(key, value)
        }
    }

    protected suspend fun ApplicationTestBuilder.executePlainGet(route: String): Pair<HttpStatusCode, String> = executeGet(route)

    protected suspend fun ApplicationTestBuilder.executeGetWithHeaders(
        route: String,
        headers: Map<String, Any>
    ): Pair<HttpStatusCode, String> = executeGet(route) {
        headers.forEach { (key, value) ->
            header(key, value)
        }
    }

    protected suspend fun ApplicationTestBuilder.executeGetWithCookies(
        route: String,
        cookies: Map<String, String>
    ): Pair<HttpStatusCode, String> = executeGet(route) {
        cookies.forEach { (key, value) ->
            cookie(key, value)
        }
    }

    protected inline fun <reified T> Pair<HttpStatusCode, String>.assertStatusAndBodyEquals(
        statusCode: HttpStatusCode,
        expectedBody: T
    ) {
        val decodedBody = json.decodeFromString<T>(
            string = second
        )

        assertEquals(
            expected = statusCode, actual = first
        )

        assertEquals(
            expected = expectedBody, actual = decodedBody
        )
    }

    protected inline fun <reified T> Pair<HttpStatusCode, String>.assertOkAndBodyEquals(expectedBody: T) =
        assertStatusAndBodyEquals(HttpStatusCode.OK, expectedBody)

    protected fun Pair<HttpStatusCode, String>.assertStatus(
        statusCode: HttpStatusCode
    ) = assertEquals(expected = statusCode, first)

    protected fun getCompleteRouteByPath(vararg path: String): String = path.joinToString("/")

    protected fun <T> encodeToString(
        serializer: KSerializer<T>,
        value: T
    ): String = json.encodeToString(serializer, value)
}