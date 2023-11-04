package com.strongmandrew.response

fun <T : Any> T.asResponseEntity(
    builder: ResponseEntity<T>.() -> Unit = {}
): ResponseEntity<T> = ResponseEntity.builder {
    entity(this@asResponseEntity)
    builder.invoke(this)
}