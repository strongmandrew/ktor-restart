package com.strongmandrew.encoder

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.executor.ExecutedFunction
import io.ktor.server.application.*
import kotlinx.serialization.serializerOrNull

class StringResponseEncoder : ResponseEncoder<String> {

    override fun encode(
        controllerScope: ControllerScope,
        executedFunction: ExecutedFunction,
        call: ApplicationCall,
    ): String {
        val functionReturnType = executedFunction.func.returnType

        val serializer = serializerOrNull(functionReturnType)

        return serializer?.let {
            val encoder = controllerScope.responseEncoder.provide()

            encoder.encodeToString(
                serializer = it,
                value = executedFunction.executedResult
            )
        } ?: "Serializer not found for ${functionReturnType::class.qualifiedName}"
    }
}