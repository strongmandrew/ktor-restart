package com.strongmandrew.executor

import io.ktor.server.application.*
import kotlin.reflect.KFunction

fun interface FunctionExecutor {

    suspend fun execute(call: ApplicationCall, instance: Any, func: KFunction<*>)
}
