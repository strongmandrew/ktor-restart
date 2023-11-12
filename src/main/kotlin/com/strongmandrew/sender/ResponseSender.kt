package com.strongmandrew.sender

import com.strongmandrew.encoder.entity.EncodedResponse
import io.ktor.server.application.*

interface ResponseSender<T> {

    suspend fun send(call: ApplicationCall, encodedResponse: EncodedResponse<T>)
}