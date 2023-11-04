package com.strongmandrew.executor

import io.ktor.server.application.*
import kotlin.reflect.KFunction
import kotlin.reflect.full.callSuspend

class FunctionExecutorImpl : FunctionExecutor {

    override suspend fun execute(
        call: ApplicationCall,
        instance: Any,
        func: KFunction<*>,
    ): ExecutedFunction {
        val executeResult = func.callSuspend(instance)

        return ExecutedFunction(
            func = func,
            executedResult = executeResult
        )
    }
}