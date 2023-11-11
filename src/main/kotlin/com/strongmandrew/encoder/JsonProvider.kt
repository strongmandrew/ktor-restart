package com.strongmandrew.encoder

import kotlinx.serialization.json.Json

fun interface JsonProvider {

    fun provide(): Json
}