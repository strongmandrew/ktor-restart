package get.configurationByRoute

import com.strongmandrew.config.RouteConfigStorage
import com.strongmandrew.config.chainHandler
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class RoutingOverriddenConfigTest {

    @Test
    fun getRoutingConfig() {
        var initialRouting: Route? = null
        val overriddenChainHandler = MockChainHandler()


        testApplication {
            routing {
                initialRouting = this@routing
                chainHandler = overriddenChainHandler
            }
        }

        assertEquals(
            expected = overriddenChainHandler,
            actual = initialRouting?.let { RouteConfigStorage.getByRoute(it).chainHandler }
        )
    }
}