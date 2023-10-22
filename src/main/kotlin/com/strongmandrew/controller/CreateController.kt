package com.strongmandrew.controller

import com.strongmandrew.get.GetChainHandler
import com.strongmandrew.handler.ChainHandler
import io.ktor.server.routing.*
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberFunctions

inline fun <reified T : Any> Route.createController() {
    val instance = T::class.createInstance()

    val chain: ChainHandler<T> = GetChainHandler()

    T::class.declaredMemberFunctions.forEach { func ->
        chain.onReceive(this, instance, func)
    }
}