package com.strongmandrew.handler

import com.strongmandrew.config.ControllerScope
import kotlin.reflect.KFunction

interface RouteHandler<T : Any> {

    val controllerScope: ControllerScope

    fun satisfies(func: KFunction<*>): Boolean

    fun handle(controllerScope: ControllerScope, instance: Any, func: KFunction<*>)
}