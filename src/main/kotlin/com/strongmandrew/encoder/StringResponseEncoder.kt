package com.strongmandrew.encoder

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.executor.ExecutedFunction
import com.strongmandrew.response.ResponseEntity
import io.ktor.server.application.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.typeOf

class StringResponseEncoder : ResponseEncoder<String> {

    override fun encode(
        controllerScope: ControllerScope,
        executedFunction: ExecutedFunction,
        call: ApplicationCall,
    ): EncodedResponse<String> {
        val functionReturnType = executedFunction.func.returnType

        val encoder = controllerScope.responseEncoder.provide()

        return when {
            functionReturnType.isSubtypeOf(typeOf<ResponseEntity<*>>()) -> {
                val responseEntity = executedFunction.executedResult as ResponseEntity<Any>

                val encodedValue = encoder.encodeToString(
                    serializer = responseEntity.getEntitySerializer(),
                    value = responseEntity.getEntity()
                )

                EncodedResponse(
                    value = encodedValue,
                    statusCode = responseEntity.getStatusCode(),
                    contentType = responseEntity.getContentType()
                )
            }
            else -> {
                val encodedValue = encoder.encodeToString(
                    serializer = serializer(functionReturnType) as KSerializer<Any>,
                    value = executedFunction.executedResult as Any
                )

                return EncodedResponse(
                    value = encodedValue
                )
            }
        }
    }
}