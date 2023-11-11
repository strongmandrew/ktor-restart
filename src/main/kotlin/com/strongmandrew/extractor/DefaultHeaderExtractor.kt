package com.strongmandrew.extractor

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.header.Header
import io.ktor.server.application.*
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

class DefaultHeaderExtractor(
    controllerScope: ControllerScope
): CallElementExtractor(controllerScope) {

    override fun satisfies(param: KParameter): Boolean = param.hasAnnotation<Header>()

    override fun extractValue(param: KParameter, call: ApplicationCall): String? {
        val annotation = param.findAnnotation<Header>()!!

        return call.request.headers[annotation.key]
    }
}