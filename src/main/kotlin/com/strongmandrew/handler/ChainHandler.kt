package com.strongmandrew.handler

import com.strongmandrew.executor.FunctionExecutor
import com.strongmandrew.validator.Validator
import io.ktor.server.routing.*
import kotlin.reflect.KFunction

abstract class ChainHandler<T : Any> {

    abstract val nextHandler: ChainHandler<T>?
    abstract val validator: Validator
    abstract val functionExecutor: FunctionExecutor<T>

    fun onReceive(route: Route, instance: T, func: KFunction<*>) {
        when (satisfies(route, func)) {
            true -> handle(route, instance, func)
            false -> proceed(route, instance, func)
        }
    }

    abstract fun satisfies(route: Route, func: KFunction<*>): Boolean

    abstract fun handle(route: Route, instance: T, func: KFunction<*>)

    private fun proceed(route: Route, instance: T, func: KFunction<*>) = nextHandler?.onReceive(route, instance, func)
}