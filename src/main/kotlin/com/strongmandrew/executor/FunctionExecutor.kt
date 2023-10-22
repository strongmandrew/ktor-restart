package com.strongmandrew.executor

import io.ktor.server.application.*
import kotlin.reflect.KFunction

interface FunctionExecutor<T : Any> {

    suspend fun execute(call: ApplicationCall, instance: T, func: KFunction<*>)
}
