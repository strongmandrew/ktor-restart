package com.strongmandrew.handler

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.executor.FunctionExecutor
import com.strongmandrew.path.PathResolver
import com.strongmandrew.validator.Validator
import kotlin.reflect.KFunction

abstract class ChainHandler {

    abstract val nextHandler: ChainHandler?
    abstract val validator: Validator
    abstract val functionExecutor: FunctionExecutor
    abstract val pathResolver: PathResolver

    fun onReceive(controllerScope: ControllerScope, instance: Any, func: KFunction<*>) {
        when (satisfies(func)) {
            true -> handle(controllerScope, instance, func)
            false -> proceed(controllerScope, instance, func)
        }
    }

    abstract fun satisfies(func: KFunction<*>): Boolean

    abstract fun handle(controllerScope: ControllerScope, instance: Any, func: KFunction<*>)

    private fun proceed(controllerScope: ControllerScope, instance: Any, func: KFunction<*>) = nextHandler?.onReceive(controllerScope, instance, func)
}