package com.strongmandrew.response

import io.ktor.http.*

fun <T : Any> T.asResponseEntity(
    builder: ResponseEntity<T>.() -> Unit = {}
): ResponseEntity<T> = ResponseEntity.builder {
    entity(this@asResponseEntity)
    builder.invoke(this)
}

fun <T : Any> T.withStatusCode(statusCode: HttpStatusCode): ResponseEntity<T> =
    ResponseEntity.builder {
        entity(this@withStatusCode)
        statusCode(statusCode)
    }

fun <T : Any> T.ok(): ResponseEntity<T> = withStatusCode(HttpStatusCode.OK)

fun <T : Any> T.badRequest(): ResponseEntity<T> = withStatusCode(HttpStatusCode.BadRequest)

fun <T : Any> T.internalError(): ResponseEntity<T> = withStatusCode(HttpStatusCode.InternalServerError)