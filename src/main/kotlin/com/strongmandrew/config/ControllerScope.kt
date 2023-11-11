package com.strongmandrew.config

import com.strongmandrew.encoder.DefaultJsonProvider
import com.strongmandrew.encoder.FailedToDecodeException
import com.strongmandrew.encoder.JsonProvider
import com.strongmandrew.extractor.CallElementExtractor
import com.strongmandrew.extractor.DefaultQueryParamExtractor
import com.strongmandrew.handler.DefaultGetRouteHandler
import com.strongmandrew.handler.RouteHandler
import io.ktor.server.application.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.serializer
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberFunctions

class ControllerScope(
    val application: Application,
) {
    private val handlers: MutableSet<RouteHandler<*>> = mutableSetOf(
        DefaultGetRouteHandler(this)
    )

    private val extractors: MutableSet<CallElementExtractor> = mutableSetOf(
        DefaultQueryParamExtractor(this)
    )

    val completePath: StringBuilder = StringBuilder("/")

    val encoder: JsonProvider = DefaultJsonProvider()

    inline fun <reified T : Any> ControllerScope.createController(
        path: String = "",
        childControllersScope: ControllerScope.() -> Unit = {},
    ): ControllerScope {
        val instance = T::class.createInstance()

        val controllerWithParent = apply {
            completePath.append("/$path")
            childControllersScope.invoke(this)
        }

        T::class.declaredMemberFunctions.forEach { func ->
            val handler = controllerWithParent.findHandler(func)

            handler?.handle(controllerWithParent, instance, func)
        }

        return controllerWithParent
    }

    fun findHandler(func: KFunction<*>): RouteHandler<*>? = handlers.reversed()
        .find { mutableEntry -> mutableEntry.satisfies(func) }

    fun findExtractor(parameter: KParameter): CallElementExtractor? = extractors.reversed()
        .find { extractor -> extractor.satisfies(parameter) }

    fun addCustomHandler(
        validatorWithHandler: ControllerScope.() -> RouteHandler<*>,
    ) {
        handlers.add(validatorWithHandler.invoke(this))
    }

    fun addCustomExtractor(extractorBlock: ControllerScope.() -> CallElementExtractor) {
        extractors.add(extractorBlock.invoke(this))
    }

    fun decodeKParameter(parameter: KParameter, value: String): Any? = try {
        val serializer = serializer(parameter.type)

        encoder.provide().decodeFromString(
            deserializer = serializer,
            string = value
        )
    } catch (e: SerializationException) {
        FailedToDecodeException
            .withCauseMessage(e, "Failed to decode $value into ${parameter.name} with type ${parameter.type}")
    }
}