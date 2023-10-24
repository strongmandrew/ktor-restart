package com.strongmandrew.config

import com.strongmandrew.handler.ChainHandler
import io.ktor.server.routing.*


var Route.chainHandler: ChainHandler
    get() = RouteConfigStorage.getByRoute(this).chainHandler
    set(value) {
        RouteConfigStorage.getByRoute(this).chainHandler = value
    }
