package com.strongmandrew.executor

import kotlin.reflect.KFunction

class ExecutedFunction(
    val func: KFunction<*>,
    val executedResult: Any?,
)