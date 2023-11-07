package com.strongmandrew.extractor

import io.ktor.server.application.*
import kotlin.reflect.KParameter

interface CallElementExtractor {

    fun satisfies(param: KParameter): Boolean

    fun extract(param: KParameter, call: ApplicationCall): Any?
}