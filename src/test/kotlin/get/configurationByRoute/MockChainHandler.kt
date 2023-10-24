package get.configurationByRoute

import com.strongmandrew.executor.FunctionExecutor
import com.strongmandrew.handler.ChainHandler
import com.strongmandrew.validator.Validator
import io.ktor.server.routing.*
import kotlin.reflect.KFunction

class MockChainHandler : ChainHandler() {

    override val nextHandler: ChainHandler? = null
    override val validator: Validator = Validator { true }
    override val functionExecutor: FunctionExecutor = FunctionExecutor { _, _, _ ->  }

    override fun satisfies(route: Route, func: KFunction<*>): Boolean = true

    override fun handle(route: Route, instance: Any, func: KFunction<*>) {}
}