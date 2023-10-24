package com.strongmandrew.config

import com.strongmandrew.get.GetChainHandler
import com.strongmandrew.handler.ChainHandler
import io.ktor.server.routing.*

class RouteConfigStorage private constructor() {

    companion object {
        private val configStorageByRoute = mutableMapOf<Route, RouteConfigStorage>()

        fun getByRoute(route: Route): RouteConfigStorage =
            configStorageByRoute[route] ?: RouteConfigStorage().also {
                configStorageByRoute[route] = it
            }
    }

    var chainHandler: ChainHandler = GetChainHandler()
}