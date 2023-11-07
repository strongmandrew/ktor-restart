package com.strongmandrew.extractor

import com.strongmandrew.config.ControllerScope
import com.strongmandrew.query.QueryParam
import io.ktor.server.application.*
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

class DefaultQueryParamCallElementExtractor(
    private val controllerScope: ControllerScope
) : CallElementExtractor {

    override fun satisfies(param: KParameter): Boolean = param.hasAnnotation<QueryParam>()

    override fun extract(param: KParameter, call: ApplicationCall): Any? {
        check(param.kind == KParameter.Kind.VALUE) {
            "Only value parameters of function can be provided with query params"
        }

        /* guaranteed that invoked after satisfies returns true */
        val queryParam = param.findAnnotation<QueryParam>()!!

        val parameterName = queryParam.key
        val obtainedQuery = call.request.queryParameters[parameterName]

        if (obtainedQuery == null && !param.isOptional) {
            CallElementNotFoundException.withMessage("Query param not found by name ${param.name}")
        }

        return obtainedQuery?.let {
            controllerScope.decodeKParameter(
                parameter = param,
                value = it
            )
        }
    }
}