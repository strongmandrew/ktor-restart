package com.strongmandrew.sender

import com.strongmandrew.encoder.entity.EncodedResponse
import io.ktor.server.application.*
import io.ktor.server.response.*

class JsonResponseSender : ResponseSender<String> {

    override suspend fun send(call: ApplicationCall, encodedResponse: EncodedResponse<String>) {
        call.respondText(
            status = encodedResponse.statusCode,
            text = encodedResponse.value,
            contentType = encodedResponse.contentType
        )
    }
}