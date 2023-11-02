package com.strongmandrew.get

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.executor.FunctionExecutor
import com.strongmandrew.executor.FunctionExecutorImpl
import com.strongmandrew.handler.ChainHandler
import com.strongmandrew.path.ControllerPathResolver
import com.strongmandrew.path.PathResolver
import com.strongmandrew.validator.Validator
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation

class GetChainHandler(
    override val nextHandler: ChainHandler? = null,
    override val validator: Validator = GetValidator(),
    override val functionExecutor: FunctionExecutor = FunctionExecutorImpl(),
    override val pathResolver: PathResolver = ControllerPathResolver()
) : ChainHandler() {

    override fun satisfies(func: KFunction<*>): Boolean {
        return validator.isValid(func)
    }

    override fun handle(controllerScope: ControllerScope, instance: Any, func: KFunction<*>) {
        controllerScope.application.routing {

            val controllerPath = pathResolver.resolve(controllerScope)

            route(controllerPath) {
                val annotation = func.findAnnotation<Get>() ?: error("")

                val path = annotation.path.ifBlank { func.name }
                get(path) {
                    functionExecutor.execute(call, instance, func)
                }
            }
        }
    }
}