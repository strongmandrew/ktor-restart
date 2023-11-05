package com.strongmandrew.handler

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.encoder.ResponseEncoder
import com.strongmandrew.executor.FunctionExecutor
import com.strongmandrew.sender.ResponseSender
import kotlin.reflect.KFunction

interface RouteHandler<T : Any> {

    val controllerScope: ControllerScope

    fun handle(controllerScope: ControllerScope, instance: Any, func: KFunction<*>)
}