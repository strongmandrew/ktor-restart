package com.strongmandrew.get

import com.strongmandrew.logger.getLogger
import com.strongmandrew.validator.Validator
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.full.hasAnnotation

class GetValidator : Validator {

    private val logger = getLogger()

    companion object {
        val GET_FORBIDDEN_PARAMETER_ANNOTATIONS = listOf<KClass<*>>(

        )
    }

    override fun isValid(func: KFunction<*>): Boolean {
        val isGet = func.hasAnnotation<Get>()

        func.parameters
            .flatMap(KParameter::annotations)
            .forEach { annotation ->
                if (annotation.annotationClass in GET_FORBIDDEN_PARAMETER_ANNOTATIONS) {
                    logger.error("Method with annotation \"GET\" cannot have parameter annotated ${annotation.annotationClass}")
                    return false
                }
            }

        return isGet
    }
}