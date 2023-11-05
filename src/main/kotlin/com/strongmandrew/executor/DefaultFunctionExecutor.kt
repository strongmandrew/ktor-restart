package com.strongmandrew.executor

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.query.DefaultQueryParamExtractor
import com.strongmandrew.query.QueryParamExtractor
import io.ktor.server.application.*
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.full.callSuspend
import kotlin.reflect.full.callSuspendBy
import kotlin.reflect.full.instanceParameter

class DefaultFunctionExecutor(
    private val controllerScope: ControllerScope
) : FunctionExecutor {

    override val queryParamExtractor: QueryParamExtractor = DefaultQueryParamExtractor(controllerScope)

    override suspend fun execute(
        call: ApplicationCall,
        instance: Any,
        func: KFunction<*>,
    ): ExecutedFunction {
        val parametersWithoutInstance = func.parameters.filterNot { it.kind in listOf(KParameter.Kind.INSTANCE, KParameter.Kind.EXTENSION_RECEIVER) }

        val args = when {
            parametersWithoutInstance.isNotEmpty() -> {

                /* TODO("Extend for cookie, path, etc extraction") */
                parametersWithoutInstance.mapNotNull { param ->
                    queryParamExtractor.extract(param, call)?.let {
                        param to it
                    }
                }.toMap()
            }

            else -> emptyMap()
        }

        val funcArgsWithInstance = buildMap {
            put(func.instanceParameter!!, instance)
            putAll(args)
        }
        val executedResult = func.callSuspendBy(funcArgsWithInstance)

        return ExecutedFunction(
            func = func,
            executedResult = executedResult
        )
    }
}