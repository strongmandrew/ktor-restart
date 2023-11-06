package com.strongmandrew.executor

class NullableReturnTypeException(override val message: String?) : RuntimeException() {

    companion object {
        fun withMessage(message: String): Nothing = throw NullableReturnTypeException(message)
    }
}