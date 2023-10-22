package com.strongmandrew.executor

import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializerOrNull
import kotlin.reflect.KFunction
import kotlin.reflect.full.callSuspend

class FunctionExecutorImpl<T : Any> : FunctionExecutor<T> {

    override suspend fun execute(call: ApplicationCall, instance: T, func: KFunction<*>) {
        val executeResult = func.callSuspend(instance)

        val serializer = serializerOrNull(func.returnType)

        serializer?.let {
            val body = Json.encodeToString(
                serializer = it,
                value = executeResult
            )

            call.respond(body)
        }
    }
}