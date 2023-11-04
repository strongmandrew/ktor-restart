package com.strongmandrew.handler

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.encoder.ResponseEncoder
import com.strongmandrew.encoder.StringResponseEncoder
import com.strongmandrew.executor.FunctionExecutor
import com.strongmandrew.executor.FunctionExecutorImpl
import com.strongmandrew.method.Get
import com.strongmandrew.sender.ResponseSender
import com.strongmandrew.sender.JsonResponseSender
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation

class DefaultGetRouteHandler: RouteHandler<String> {

    override val functionExecutor: FunctionExecutor = FunctionExecutorImpl()
    override val responseEncoder: ResponseEncoder<String> = StringResponseEncoder()
    override val responseSender: ResponseSender<String> = JsonResponseSender()

    override fun handle(controllerScope: ControllerScope, instance: Any, func: KFunction<*>) {
        controllerScope.application.routing {

            val controllerPath = controllerScope.completePath.toString()

            route(controllerPath) {
                val annotation = func.findAnnotation<Get>() ?: error("")

                get(annotation.path) {
                    val executedFunction = functionExecutor.execute(call, instance, func)

                    val encodedResponse = responseEncoder.encode(
                        controllerScope = controllerScope,
                        executedFunction = executedFunction,
                        call = call
                    )

                    responseSender.send(call, encodedResponse)
                }
            }
        }
    }
}