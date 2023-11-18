package com.strongmandrew.config

import com.strongmandrew.stringer.Stringer
import com.strongmandrew.stringer.json.DefaultJsonStringer
import com.strongmandrew.extractor.*
import com.strongmandrew.handler.DefaultGetRouteHandler
import com.strongmandrew.handler.RouteHandler
import io.ktor.server.application.*
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
        DefaultApplicationCallExtractor(this),
        DefaultQueryParamExtractor(this),
        DefaultHeaderExtractor(this),
        DefaultCookieExtractor(this),
    )

    val completePath: StringBuilder = StringBuilder("/")

    private val stringers: MutableList<Stringer> = mutableListOf(
        DefaultJsonStringer()
    )

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

    fun provideStringer(): Stringer = stringers.reversed().first()

    fun addCustomHandler(
        validatorWithHandler: ControllerScope.() -> RouteHandler<*>,
    ) {
        handlers.add(validatorWithHandler.invoke(this))
    }

    fun addCustomExtractor(extractorBlock: ControllerScope.() -> CallElementExtractor) {
        extractors.add(extractorBlock.invoke(this))
    }

    fun addCustomStringer(stringerBlock: ControllerScope.() -> Stringer) {
        stringers.add(stringerBlock.invoke(this))
    }
}