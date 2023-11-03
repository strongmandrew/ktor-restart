package com.strongmandrew.handler

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.executor.FunctionExecutor
import kotlin.reflect.KFunction

interface RouteHandler {

    val functionExecutor: FunctionExecutor

    fun handle(controllerScope: ControllerScope, instance: Any, func: KFunction<*>)
}