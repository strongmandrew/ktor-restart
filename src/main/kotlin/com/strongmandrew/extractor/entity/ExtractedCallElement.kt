package com.strongmandrew.extractor.entity

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.extractor.exception.CallElementNotFoundException
import kotlin.reflect.KParameter

data class ExtractedCallElement(
    private val controllerScope: ControllerScope,
    private val param: KParameter,
    private val extractedValue: Any?,
    private val isSerializable: Boolean = true
) {

    init {
        if (!param.isOptional && !param.type.isMarkedNullable && extractedValue == null) {
            throw CallElementNotFoundException("Call element by name ${param.name} not found")
        }
    }

    fun mustReturnAnyValue(): Boolean = !param.isOptional || extractedValue != null

    fun decodeAsKParameterPair(): Pair<KParameter, Any?> =
        param to extractedValue?.let { value ->
            if (isSerializable) {
                controllerScope.decodeKParameter(param, value)
            } else {
                value
            }
        }
}