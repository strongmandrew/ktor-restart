package com.strongmandrew.controller

import com.strongmandrew.config.ControllerScope
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberFunctions

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
        chainHandler.onReceive(controllerWithParent, instance, func)
    }

    return controllerWithParent
}