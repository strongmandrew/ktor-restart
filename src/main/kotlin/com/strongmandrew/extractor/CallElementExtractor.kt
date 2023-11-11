package com.strongmandrew.extractor

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.extractor.entity.ExtractedCallElement
import io.ktor.server.application.*
import kotlin.reflect.KParameter

abstract class CallElementExtractor(
    private val controllerScope: ControllerScope
) {

    abstract fun satisfies(param: KParameter): Boolean

    fun wrapCallElement(param: KParameter, call: ApplicationCall): ExtractedCallElement {
        check(param.kind == KParameter.Kind.VALUE) {
            "Only value parameters of function can be interpreted by ${this::class.qualifiedName}"
        }

        val extractedValue = extractValue(param, call)

        return ExtractedCallElement(
            controllerScope = controllerScope,
            param = param,
            extractedValue = extractedValue
        )
    }

    abstract fun extractValue(param: KParameter, call: ApplicationCall): String?
}