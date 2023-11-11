package com.strongmandrew.method

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Get(
    val path: String = ""
)
