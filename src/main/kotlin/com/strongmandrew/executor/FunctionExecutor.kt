package com.strongmandrew.executor

import com.strongmandrew.extractor.QueryParamExtractor
import io.ktor.server.application.*
import kotlin.reflect.KFunction

interface FunctionExecutor {

    suspend fun execute(call: ApplicationCall, instance: Any, func: KFunction<*>): ExecutedFunction
}
