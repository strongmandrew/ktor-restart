package com.strongmandrew.path

import com.strongmandrew.config.ControllerScope

class ControllerPathResolver : PathResolver {

    override fun resolve(controllerScope: ControllerScope): String {
        return controllerScope.completePath.toString()
    }
}