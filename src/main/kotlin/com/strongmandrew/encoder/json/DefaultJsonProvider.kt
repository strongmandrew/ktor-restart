package com.strongmandrew.encoder.json

import kotlinx.serialization.json.Json

class DefaultJsonProvider : JsonProvider {

    override fun provide(): Json = Json {
        isLenient = true
    }
}