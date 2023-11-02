package com.strongmandrew.path

import com.strongmandrew.config.ControllerScope

interface PathResolver {

    fun resolve(controllerScope: ControllerScope): String
}