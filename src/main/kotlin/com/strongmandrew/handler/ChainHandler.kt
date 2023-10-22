package com.strongmandrew.handler

import io.ktor.server.routing.*
import kotlin.reflect.KFunction

abstract class ChainHandler<T : Any> {

    abstract val nextHandler: ChainHandler<T>?

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