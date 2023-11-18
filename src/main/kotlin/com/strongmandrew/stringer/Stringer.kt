package com.strongmandrew.stringer

import kotlin.reflect.KType

interface Stringer {

    fun <T> decodeFromString(
        targetType: KType,
        value: String,
    ): T

    fun <T> encodeToString(
        sourceType: KType,
        value: T,
    ): String
}