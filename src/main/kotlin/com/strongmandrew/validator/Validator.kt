package com.strongmandrew.validator

import kotlin.reflect.KFunction

fun interface Validator {

    fun isValid(func: KFunction<*>): Boolean
}