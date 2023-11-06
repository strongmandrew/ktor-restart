package com.strongmandrew.extractor

import com.strongmandrew.config.ControllerScope
import io.ktor.server.application.*
import kotlin.reflect.KParameter

interface QueryParamExtractor {

    fun extract(
        parameter: KParameter,
        call: ApplicationCall
    ): Any?
}
