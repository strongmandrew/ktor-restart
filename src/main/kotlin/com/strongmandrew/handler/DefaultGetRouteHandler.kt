package com.strongmandrew.handler

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.encoder.ResponseEncoder
import com.strongmandrew.encoder.StringResponseEncoder
import com.strongmandrew.executor.DefaultFunctionExecutor
import com.strongmandrew.executor.FunctionExecutor
import com.strongmandrew.method.Get
import com.strongmandrew.sender.JsonResponseSender
import com.strongmandrew.sender.ResponseSender
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

class DefaultGetRouteHandler(
    override val controllerScope: ControllerScope
): RouteHandler<String> {

    private val functionExecutor: FunctionExecutor = DefaultFunctionExecutor(controllerScope)
    private val responseEncoder: ResponseEncoder<String> = StringResponseEncoder()
    private val responseSender: ResponseSender<String> = JsonResponseSender()

    override fun satisfies(func: KFunction<*>): Boolean {
        return func.hasAnnotation<Get>()
    }

    override fun handle(controllerScope: ControllerScope, instance: Any, func: KFunction<*>) {
        controllerScope.application.routing {

            val controllerPath = controllerScope.completePath.toString()

            route(controllerPath) {
                /* guaranteed that invoked after satisfies returns true */
                val annotation = func.findAnnotation<Get>()!!

                get(annotation.path) {
                    val executedFunction = functionExecutor.execute(call, instance, func)

                    val encodedResponse = responseEncoder.encode(
                        controllerScope = controllerScope,
                        executedFunction = executedFunction,
                    )

                    responseSender.send(call, encodedResponse)
                }
            }
        }
    }
}