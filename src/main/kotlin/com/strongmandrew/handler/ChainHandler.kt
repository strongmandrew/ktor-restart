package com.strongmandrew.handler

import com.strongmandrew.executor.FunctionExecutor
import com.strongmandrew.validator.Validator
import io.ktor.server.routing.*
import kotlin.reflect.KFunction

abstract class ChainHandler {

    abstract val nextHandler: ChainHandler?
    abstract val validator: Validator
    abstract val functionExecutor: FunctionExecutor

    fun onReceive(route: Route, instance: Any, func: KFunction<*>) {
        when (satisfies(route, func)) {
            true -> handle(route, instance, func)
            false -> proceed(route, instance, func)
        }
    }

    abstract fun satisfies(route: Route, func: KFunction<*>): Boolean

    abstract fun handle(route: Route, instance: Any, func: KFunction<*>)

    private fun proceed(route: Route, instance: Any, func: KFunction<*>) = nextHandler?.onReceive(route, instance, func)
}