package com.strongmandrew.multipart

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class MultipartFormData(
    val key: String
)
