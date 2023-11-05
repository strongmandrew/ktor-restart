package com.strongmandrew.query

import com.strongmandrew.config.ControllerScope
import io.ktor.http.*
import io.ktor.server.application.*
import kotlinx.coroutines.delay
import kotlinx.serialization.serializer
import kotlinx.serialization.serializerOrNull
import kotlin.reflect.KParameter

class DefaultQueryParamExtractor(
    private val controllerScope: ControllerScope
) : QueryParamExtractor {

    override fun extract(parameter: KParameter, call: ApplicationCall): Any? {
        val json = controllerScope.responseEncoder.provide()

        val parameterName = parameter.name.orEmpty()

        val obtainedQuery = call.request.queryParameters[parameterName]

        if (obtainedQuery == null && !parameter.isOptional) {
            error("Query parameter not found by key $parameterName")
        }

        val parameterSerializer = serializerOrNull(parameter.type)
            ?: error("Serializer for property $parameter with type ${parameter.type} not found")

        return obtainedQuery?.let {
            json.decodeFromString(
                deserializer = parameterSerializer,
                string = it
            )
        }
    }
}