package com.strongmandrew.extractor

import com.strongmandrew.extractor.entity.ExtractedCallElement
import io.ktor.server.application.*
import kotlin.reflect.KParameter

interface CallElementExtractor {

    fun satisfies(param: KParameter): Boolean

    fun extract(param: KParameter, call: ApplicationCall): ExtractedCallElement
}