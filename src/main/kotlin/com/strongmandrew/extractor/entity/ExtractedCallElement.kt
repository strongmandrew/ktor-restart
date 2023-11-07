package com.strongmandrew.extractor.entity

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.extractor.exception.CallElementNotFoundException
import kotlin.reflect.KParameter

class ExtractedCallElement(
    private val controllerScope: ControllerScope,
    private val param: KParameter,
    private val extractedValue: String?
) {

    init {
        if (!param.isOptional && !param.type.isMarkedNullable && extractedValue == null) {
            CallElementNotFoundException.withMessage("Call element by name ${param.name} not found")
        }
    }

    fun mustReturnAnyValue(): Boolean = !param.isOptional || extractedValue != null

    fun decodeAsKParameterPair(): Pair<KParameter, Any?> =
        param to extractedValue?.let { value -> controllerScope.decodeKParameter(param, value) }
}