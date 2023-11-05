package com.strongmandrew.config

import com.strongmandrew.encoder.DefaultJsonProvider
import com.strongmandrew.encoder.JsonProvider
import com.strongmandrew.handler.DefaultGetRouteHandler
import com.strongmandrew.handler.RouteHandler
import com.strongmandrew.validator.GetValidator
import com.strongmandrew.validator.Validator
import io.ktor.server.application.*
import kotlin.reflect.KFunction
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberFunctions

class ControllerScope(
    val application: Application,
) {
    private val handlers: MutableMap<Validator, RouteHandler<*>> = mutableMapOf(
        GetValidator() to DefaultGetRouteHandler(this)
    )

    val completePath: StringBuilder = StringBuilder("/")

    val responseEncoder: JsonProvider = DefaultJsonProvider()

    inline fun <reified T : Any> ControllerScope.createController(
        path: String = "",
        childControllersScope: ControllerScope.() -> Unit = {},
    ): ControllerScope {
        val instance = T::class.createInstance()

        val controllerWithParent = apply {
            completePath.append("/$path")
            childControllersScope.invoke(this)
        }

        T::class.declaredMemberFunctions.forEach { func ->
            val handler = controllerWithParent.findHandler(func)

            handler?.handle(controllerWithParent, instance, func)
        }

        return controllerWithParent
    }

    fun findHandler(func: KFunction<*>): RouteHandler<*>? = handlers.entries.reversed()
        .find { mutableEntry -> mutableEntry.key.isValid(func) }
        ?.value

    fun addCustomHandler(
        validatorWithHandler: ControllerScope.() -> Map<Validator, RouteHandler<*>>,
    ) {
        handlers.putAll(validatorWithHandler.invoke(this))
    }
}