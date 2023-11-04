package com.strongmandrew.encoder

import kotlinx.serialization.json.Json

class DefaultJsonProvider : JsonProvider {

    override fun provide(): Json = Json
}