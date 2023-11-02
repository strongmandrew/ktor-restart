package com.strongmandrew.config

import io.ktor.server.application.*

fun Application.rootController(
    path: String = "/",
    childControllersScope: ControllerScope.() -> Unit
) {
    val controllerScope = ControllerScope(
        application = this,
        initialPath = path
    )
    childControllersScope.invoke(controllerScope)
}