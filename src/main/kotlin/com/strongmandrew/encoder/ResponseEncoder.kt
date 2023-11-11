package com.strongmandrew.encoder

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.executor.entity.ExecutedFunction

interface ResponseEncoder<T : Any> {

    fun encode(
        controllerScope: ControllerScope,
        executedFunction: ExecutedFunction,
    ): EncodedResponse<T>
}
