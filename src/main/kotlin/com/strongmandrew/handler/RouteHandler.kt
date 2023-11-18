package com.strongmandrew.handler

import com.strongmandrew.config.ControllerScope
import kotlin.reflect.KFunction

interface RouteHandler<T : Any> {

    val controllerScope: ControllerScope

    /**
     * Invoked at first to define whether handler is suitable for [func]
     */
    fun satisfies(func: KFunction<*>): Boolean

    /**
     * Invoked after [satisfies] to handle suitable [func] of controller [instance]
     */
    fun handle(controllerScope: ControllerScope, instance: Any, func: KFunction<*>)
}