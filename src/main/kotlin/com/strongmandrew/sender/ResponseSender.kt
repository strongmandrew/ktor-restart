package com.strongmandrew.sender

import io.ktor.server.application.*

interface ResponseSender<T> {

    suspend fun send(call: ApplicationCall, value: T)
}