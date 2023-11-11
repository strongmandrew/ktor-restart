package com.strongmandrew.extractor

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.path.PathParam
import io.ktor.server.application.*
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

class DefaultPathParamExtractor(
    controllerScope: ControllerScope,
) : CallElementExtractor(controllerScope) {

    override fun satisfies(param: KParameter): Boolean =
        param.hasAnnotation<PathParam>()

    override fun extractValue(param: KParameter, call: ApplicationCall): String? {
        val parameterName = param.findAnnotation<PathParam>()!!

        return call.parameters[parameterName.key]
    }
}