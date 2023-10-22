package com.strongmandrew.validator

import kotlin.reflect.KFunction

interface Validator {

    fun isValid(func: KFunction<*>): Boolean
}