package com.strongmandrew.response

import io.ktor.http.*
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import kotlin.reflect.KType
import kotlin.reflect.full.createType

class ResponseEntity<T : Any> {

    private var _statusCode: HttpStatusCode = HttpStatusCode.OK

    private lateinit var _entity: T

    private var _contentType: ContentType = ContentType.Application.Json

    companion object {
        fun <T : Any> builder(builder: ResponseEntity<T>.() -> Unit): ResponseEntity<T> =
            ResponseEntity<T>().apply(builder)
    }

    fun statusCode(httpStatusCode: HttpStatusCode) {
        _statusCode = httpStatusCode
    }

    fun getStatusCode(): HttpStatusCode = _statusCode

    fun ok() = statusCode(HttpStatusCode.OK)

    fun entity(entity: T) {
        _entity = entity
    }

    fun getEntity(): T = _entity

    fun contentType(contentType: ContentType) {
        _contentType = contentType
    }

    fun getContentType(): ContentType = _contentType

    fun getEntityKType(): KType = _entity::class.createType()
}