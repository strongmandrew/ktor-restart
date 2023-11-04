import com.strongmandrew.encoder.DefaultJsonProvider
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import kotlin.reflect.full.createType
import kotlin.test.assertEquals

open class BaseApplicationTest {

    protected val jsonProvider = DefaultJsonProvider()

    protected suspend fun ApplicationTestBuilder.executePlainGet(route: String): Pair<HttpStatusCode, String> {
        val response = client.get(route)
        return Pair(response.status, response.bodyAsText())
    }

    protected inline fun <reified T : Any> Pair<HttpStatusCode, String>.assertOKAndBodyEquals(expectedBody: T) {
        val encodedExpectedBody = jsonProvider.provide().encodeToString(
            serializer = expectedBody.createSerializer(), value = expectedBody
        )

        assertEquals(
            expected = HttpStatusCode.OK, actual = first
        )

        assertEquals(
            expected = encodedExpectedBody, actual = second
        )
    }

    protected fun getCompleteRouteByPath(vararg path: String): String = path.joinToString("/")

    protected inline fun <reified T : Any> T.createSerializer(): KSerializer<T> {
        return serializer(this::class.createType()) as KSerializer<T>
    }
}