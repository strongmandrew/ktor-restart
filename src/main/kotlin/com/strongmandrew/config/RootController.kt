package com.strongmandrew.config

import io.ktor.server.application.*

fun Application.rootController(
    childControllersScope: ControllerScope.() -> Unit
) {
    val controllerScope = ControllerScope(
        application = this,
    )
    childControllersScope.invoke(controllerScope)
}