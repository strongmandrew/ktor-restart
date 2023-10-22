package com.strongmandrew.get

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Get(
    val path: String = ""
)
