package com.strongmandrew.cookie

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Cookie(
    val key: String
)
