package com.strongmandrew.encoder.json

import kotlinx.serialization.json.Json

fun interface JsonProvider {

    fun provide(): Json
}