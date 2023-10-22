import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.assertEquals

open class BaseApplicationTest {

    protected suspend fun ApplicationTestBuilder.executePlainGet(route: String): Pair<HttpStatusCode, String> {
        val response = client.get(route)
        return Pair(response.status, response.bodyAsText())
    }

    protected fun Pair<HttpStatusCode, String>.assertOKAndTextBodyEquals(expectedBody: String) {
        assertEquals(
            expected = HttpStatusCode.OK,
            actual = first
        )

        assertEquals(
            expected = expectedBody,
            actual = second
        )
    }
}