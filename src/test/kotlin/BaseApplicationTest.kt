import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*

open class BaseApplicationTest {

    protected suspend fun ApplicationTestBuilder.executePlainGet(route: String): Pair<HttpStatusCode, String> {
        val response = client.get(route)
        return Pair(response.status, response.bodyAsText())
    }
}