package com.strongmandrew.executor

import com.strongmandrew.query.QueryParamExtractor
import io.ktor.server.application.*
import kotlin.reflect.KFunction

interface FunctionExecutor {

    val queryParamExtractor: QueryParamExtractor

    suspend fun execute(call: ApplicationCall, instance: Any, func: KFunction<*>): ExecutedFunction
}
