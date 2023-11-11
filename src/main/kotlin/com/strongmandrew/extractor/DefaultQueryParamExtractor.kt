package com.strongmandrew.extractor

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.query.QueryParam
import io.ktor.server.application.*
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

class DefaultQueryParamExtractor(
    controllerScope: ControllerScope
) : CallElementExtractor(controllerScope) {

    override fun satisfies(param: KParameter): Boolean = param.hasAnnotation<QueryParam>()

    override fun extractValue(param: KParameter, call: ApplicationCall): String? {
        val parameterName = param.findAnnotation<QueryParam>()!!

        return call.request.queryParameters[parameterName.key]
    }
}