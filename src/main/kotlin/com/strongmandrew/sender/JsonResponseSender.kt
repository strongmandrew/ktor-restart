package com.strongmandrew.sender

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class JsonResponseSender : ResponseSender<String> {

    override suspend fun send(call: ApplicationCall, value: String) {
        call.respondText(value, ContentType.Text.Plain)
    }
}