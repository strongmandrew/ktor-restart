package com.strongmandrew.encoder.entity

import io.ktor.http.*

class EncodedResponse<T>(
    val value: T,
    val statusCode: HttpStatusCode = HttpStatusCode.OK,
    val contentType: ContentType? = null
)