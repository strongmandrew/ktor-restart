package com.strongmandrew.get

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.executor.FunctionExecutor
import com.strongmandrew.executor.FunctionExecutorImpl
import com.strongmandrew.handler.RouteHandler
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation

class GetRouteHandler(
    override val functionExecutor: FunctionExecutor = FunctionExecutorImpl(),
) : RouteHandler {

    override fun handle(controllerScope: ControllerScope, instance: Any, func: KFunction<*>) {
        controllerScope.application.routing {

            val controllerPath = controllerScope.completePath.toString()

            route(controllerPath) {
                val annotation = func.findAnnotation<Get>() ?: error("")

                val path = annotation.path
                get(path) {
                    functionExecutor.execute(call, instance, func)
                }
            }
        }
    }
}