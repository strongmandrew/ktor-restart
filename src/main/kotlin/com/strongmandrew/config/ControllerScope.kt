package com.strongmandrew.config

import com.strongmandrew.get.GetRouteHandler
import com.strongmandrew.get.GetValidator
import com.strongmandrew.handler.RouteHandler
import com.strongmandrew.validator.Validator
import io.ktor.server.application.*
import kotlin.reflect.KFunction
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberFunctions

class ControllerScope(
    val application: Application,
) {
    private val handlers = mutableMapOf<Validator, RouteHandler>(
        GetValidator() to GetRouteHandler()
    )

    val completePath: StringBuilder = StringBuilder("/")

    inline fun <reified T : Any> ControllerScope.createController(
        path: String = "",
        childControllersScope: ControllerScope.() -> Unit = {}
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

    fun findHandler(func: KFunction<*>): RouteHandler? = handlers.entries.reversed()
        .find { mutableEntry -> mutableEntry.key.isValid(func) }
        ?.value

    fun addCustomHandler(
        validatorWithHandler: () -> Pair<Validator, RouteHandler>
    ) {
        handlers.run {
            val invokedValidatorWithHandler = validatorWithHandler.invoke()
            put(invokedValidatorWithHandler.first, invokedValidatorWithHandler.second)
        }
    }
}