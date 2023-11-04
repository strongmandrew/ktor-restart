import com.strongmandrew.encoder.DefaultJsonProvider
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.assertEquals

open class BaseApplicationTest {

    protected val jsonProvider = DefaultJsonProvider()

    protected suspend fun ApplicationTestBuilder.executePlainGet(route: String): Pair<HttpStatusCode, String> {
        val response = client.get(route)
        return Pair(response.status, response.bodyAsText())
    }

    protected inline fun <reified T> Pair<HttpStatusCode, String>.assertStatusAndBodyEquals(
        statusCode: HttpStatusCode,
        expectedBody: T
    ) {
        val decodedBody = jsonProvider.provide().decodeFromString<T>(
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


    protected fun getCompleteRouteByPath(vararg path: String): String = path.joinToString("/")
}