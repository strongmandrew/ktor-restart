package com.strongmandrew.extractor

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.cookie.Cookie
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.util.*
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

class DefaultCookieExtractor(
    controllerScope: ControllerScope
) : CallElementExtractor(controllerScope) {

    override fun satisfies(param: KParameter): Boolean = param.hasAnnotation<Cookie>()

    override fun extractValue(param: KParameter, call: ApplicationCall): String? {
        val annotation = param.findAnnotation<Cookie>()!!
        return call.request.cookies[annotation.key]
    }
}