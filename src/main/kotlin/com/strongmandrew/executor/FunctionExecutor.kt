package com.strongmandrew.executor

import com.strongmandrew.executor.entity.ExecutedFunction
import io.ktor.server.application.*
import kotlin.reflect.KFunction

interface FunctionExecutor {

    /**
     * Executed [func] of certain controller [instance] and then wraps result into
     * kind of [ExecutedFunction]
     */
    suspend fun execute(call: ApplicationCall, instance: Any, func: KFunction<*>): ExecutedFunction
}
