package com.strongmandrew.executor.entity

import kotlin.reflect.KFunction

class ExecutedFunction(
    val func: KFunction<*>,
    val executedResult: Any?,
)