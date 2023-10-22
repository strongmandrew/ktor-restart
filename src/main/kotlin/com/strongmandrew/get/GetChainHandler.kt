package com.strongmandrew.get

import com.strongmandrew.executor.FunctionExecutor
import com.strongmandrew.executor.FunctionExecutorImpl
import com.strongmandrew.handler.ChainHandler
import com.strongmandrew.validator.Validator
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation

class GetChainHandler<T : Any>(
    override val nextHandler: ChainHandler<T>? = null,
    override val validator: Validator = GetValidator(),
    override val functionExecutor: FunctionExecutor<T> = FunctionExecutorImpl(),
) : ChainHandler<T>() {

    override fun satisfies(route: Route, func: KFunction<*>): Boolean {
        return validator.isValid(func)
    }

    override fun handle(route: Route, instance: T, func: KFunction<*>) {
        with(route) {
            val annotation = func.findAnnotation<Get>() ?: error("")

            val path = annotation.path.ifBlank { func.name }
            get(path) {
                functionExecutor.execute(call, instance, func)
            }
        }
    }
}