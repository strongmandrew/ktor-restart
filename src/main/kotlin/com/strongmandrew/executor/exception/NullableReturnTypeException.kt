package com.strongmandrew.executor.exception

class NullableReturnTypeException(override val message: String?) : RuntimeException() {

    companion object {
        fun withMessage(message: String): Nothing = throw NullableReturnTypeException(message)
    }
}