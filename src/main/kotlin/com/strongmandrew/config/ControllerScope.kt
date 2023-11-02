package com.strongmandrew.config

import com.strongmandrew.get.GetChainHandler
import com.strongmandrew.handler.ChainHandler
import io.ktor.server.application.*
import java.lang.StringBuilder

class ControllerScope(
    val application: Application,
    initialPath: String
) {
    var parent: ControllerScope? = null
    var completePath: StringBuilder = StringBuilder(initialPath)
    var chainHandler: ChainHandler = GetChainHandler()
}