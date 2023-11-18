package com.strongmandrew.encoder

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.encoder.entity.EncodedResponse
import com.strongmandrew.executor.entity.ExecutedFunction
import com.strongmandrew.response.ResponseEntity
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.typeOf

class StringResponseEncoder : ResponseEncoder<String> {

    override fun encode(
        controllerScope: ControllerScope,
        executedFunction: ExecutedFunction,
    ): EncodedResponse<String> {
        val functionReturnType = executedFunction.func.returnType

        val stringer = controllerScope.provideStringer()

        return when {
            functionReturnType.isSubtypeOf(typeOf<ResponseEntity<*>>()) -> {
                val responseEntity = executedFunction.executedResult as ResponseEntity<Any>

                val encodedValue = stringer.encodeToString(
                    sourceType = responseEntity.getEntityKType(),
                    value = responseEntity.getEntity()
                )

                EncodedResponse(
                    value = encodedValue,
                    statusCode = responseEntity.getStatusCode(),
                    contentType = responseEntity.getContentType()
                )
            }
            else -> {
                val encodedValue = stringer.encodeToString(
                    sourceType = functionReturnType,
                    value = executedFunction.executedResult as Any
                )

                return EncodedResponse(
                    value = encodedValue
                )
            }
        }
    }
}