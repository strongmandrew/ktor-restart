package com.strongmandrew.executor

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.executor.entity.ExecutedFunction
import com.strongmandrew.executor.exception.NullableReturnTypeException
import com.strongmandrew.executor.extension.toCallableArgs
import io.ktor.server.application.*
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.full.callSuspendBy
import kotlin.reflect.full.instanceParameter

class DefaultFunctionExecutor(
    private val controllerScope: ControllerScope,
) : FunctionExecutor {

    override suspend fun execute(
        call: ApplicationCall,
        instance: Any,
        func: KFunction<*>,
    ): ExecutedFunction {
        if (func.returnType.isMarkedNullable) {
            NullableReturnTypeException.withMessage("Function ${func.name} return type is marked nullable though it's not supported")
        }

        val parametersWithoutInstance = func.parameters.filterValueParameters()

        val args = when {
            parametersWithoutInstance.isNotEmpty() -> {
                parametersWithoutInstance.mapNotNull { param ->
                    controllerScope.findExtractor(param)?.wrapCallElement(param, call)
                }.toCallableArgs()
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

    private fun List<KParameter>.filterValueParameters(): List<KParameter> =
        filterNot { param -> param.kind in listOf(KParameter.Kind.INSTANCE, KParameter.Kind.EXTENSION_RECEIVER) }
}