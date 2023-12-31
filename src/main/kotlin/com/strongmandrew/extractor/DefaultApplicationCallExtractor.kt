package com.strongmandrew.extractor

import com.strongmandrew.call.Call
import com.strongmandrew.config.ControllerScope
import com.strongmandrew.extractor.entity.ExtractedCallElement
import com.strongmandrew.extractor.exception.TypeMismatchException
import io.ktor.server.application.*
import kotlin.reflect.KParameter
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.typeOf

class DefaultApplicationCallExtractor(
    controllerScope: ControllerScope,
) : CallElementExtractor(controllerScope) {

    override fun satisfies(param: KParameter): Boolean =
        param.hasAnnotation<Call>()

    override fun wrapCallElement(param: KParameter, call: ApplicationCall): ExtractedCallElement {
        val paramOfApplicationCallType = param.type == typeOf<ApplicationCall>()

        if (!paramOfApplicationCallType) {
            throw TypeMismatchException(
                "Value parameter annotated with ${Call::class.qualifiedName} must be of type ${ApplicationCall::class.qualifiedName}"
            )
        }

        val extractedCallElement = super.wrapCallElement(param, call)

        return extractedCallElement.copy(
            isSerializable = false
        )
    }

    override fun extractValue(param: KParameter, call: ApplicationCall): ApplicationCall = call
}