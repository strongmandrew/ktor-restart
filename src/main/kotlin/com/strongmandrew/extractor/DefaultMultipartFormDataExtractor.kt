package com.strongmandrew.extractor

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.multipart.MultipartFormData
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

class DefaultMultipartFormDataExtractor(
    private val controllerScope: ControllerScope
) : CallElementExtractor(controllerScope) {

    override fun satisfies(param: KParameter): Boolean = param.hasAnnotation<MultipartFormData>()

    override fun extractValue(param: KParameter, call: ApplicationCall): Any? = runBlocking {
        val annotation = param.findAnnotation<MultipartFormData>()!!

        if (!call.request.isMultipart()) return@runBlocking null

        val multipart = call.receiveMultipart()

        while (true) {
            multipart.readPart()?.let {
                if (it is PartData.FormItem && it.name == annotation.key) {
                    return@runBlocking it.value
                }
                it.dispose()
            } ?: break
        }
    }
}