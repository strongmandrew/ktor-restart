package com.strongmandrew.get

import com.strongmandrew.handler.ChainHandler
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import kotlin.reflect.KFunction
import kotlin.reflect.full.callSuspend
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

class GetChainHandler<T : Any>(
    override val nextHandler: ChainHandler<T>? = null,
) : ChainHandler<T>() {

    override fun satisfies(route: Route, func: KFunction<*>): Boolean {
        return func.hasAnnotation<Get>()
    }

    override fun handle(route: Route, instance: T, func: KFunction<*>) {
        with(route) {
            val annotation = func.findAnnotation<Get>() ?: error("")

            val path = annotation.path.ifBlank { func.name }
            get(path) {
                val invokeResult = func.callSuspend(instance)

                call.respond(
                    invokeResult,
                    TypeInfo(type = func.returnType::class, reifiedType = func.returnType.platformType)
                )
            }
        }
    }
}