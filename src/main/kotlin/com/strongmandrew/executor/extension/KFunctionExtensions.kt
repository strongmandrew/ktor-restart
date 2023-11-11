package com.strongmandrew.executor.extension

import com.strongmandrew.extractor.entity.ExtractedCallElement
import kotlin.reflect.KParameter

fun List<ExtractedCallElement>.toCallableArgs(): Map<KParameter, Any?> = filter { callElement ->
    callElement.mustReturnAnyValue()
}.associate { callElement -> callElement.decodeAsKParameterPair() }