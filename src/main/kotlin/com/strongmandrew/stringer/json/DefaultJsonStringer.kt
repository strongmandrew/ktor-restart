package com.strongmandrew.stringer.json

import com.strongmandrew.encoder.exception.FailedToDecodeException
import com.strongmandrew.stringer.Stringer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KType

class DefaultJsonStringer : Stringer {

    private val json = Json {
        isLenient = true
    }

    override fun <T> decodeFromString(
        targetType: KType,
        value: String,
    ): T = json.decodeFromString(
        deserializer = getSerializerOfKType(targetType),
        string = value
    ) as T

    override fun <T> encodeToString(
        sourceType: KType,
        value: T,
    ): String = json.encodeToString(
        serializer = getSerializerOfKType(sourceType),
        value = value
    )

    private fun getSerializerOfKType(kType: KType): KSerializer<Any?> = try {
        serializer(kType)
    } catch (e: SerializationException) {
        throw FailedToDecodeException("Failed to create serializer of type $kType", e)
    }
}