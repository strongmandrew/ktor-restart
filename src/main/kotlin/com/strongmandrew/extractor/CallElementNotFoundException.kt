package com.strongmandrew.extractor

class CallElementNotFoundException(override val message: String?) : RuntimeException(message) {

    companion object {
        fun withMessage(message: String): Nothing = throw CallElementNotFoundException(message)
    }
}