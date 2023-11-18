package com.strongmandrew.encoder

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.encoder.entity.EncodedResponse
import com.strongmandrew.executor.entity.ExecutedFunction

interface ResponseEncoder<T : Any> {

    /**
     * Encodes the result of function execution [executedFunction] into
     * kind of [EncodedResponse] to be later sent as response
     */
    fun encode(
        controllerScope: ControllerScope,
        executedFunction: ExecutedFunction,
    ): EncodedResponse<T>
}
