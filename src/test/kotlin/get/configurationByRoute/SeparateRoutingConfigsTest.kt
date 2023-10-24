package get.configurationByRoute

import com.strongmandrew.config.RouteConfigStorage
import com.strongmandrew.config.chainHandler
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class SeparateRoutingConfigsTest {

    @Test
    fun separateRouteConfigs() {
        var firstRoute: Route? = null
        var secondRoute: Route? = null

        val firstHandler = MockChainHandler()
        val secondHandler = MockChainHandler()

        testApplication {
            routing {
                route("first") {
                    firstRoute = this
                    chainHandler = firstHandler
                }

                route("second") {
                    secondRoute = this
                    chainHandler = secondHandler
                }
            }
        }

        assertNotEquals(
            illegal = firstRoute,
            actual = secondRoute
        )

        assertNotEquals(
            illegal = firstRoute?.let { RouteConfigStorage.getByRoute(it).chainHandler },
            actual = secondRoute?.let { RouteConfigStorage.getByRoute(it).chainHandler }
        )
    }

    @Test
    fun sameRoutingConfigs() {
        var firstRoute: Route? = null
        var secondRoute: Route? = null

        val firstHandler = MockChainHandler()
        val secondHandler = MockChainHandler()

        testApplication {
            routing {
                firstRoute = this
                chainHandler = firstHandler
            }

            routing {
                secondRoute = this
                chainHandler = secondHandler
            }
        }

        assertEquals(
            expected = firstRoute,
            actual = secondRoute
        )

        assertEquals(
            expected = firstRoute?.let { RouteConfigStorage.getByRoute(it).chainHandler },
            actual = secondRoute?.let { RouteConfigStorage.getByRoute(it).chainHandler }
        )
    }
}