package com.strongmandrew.encoder

class FailedToDecodeException(override val message: String?, override val cause: Throwable?) : Exception(message, cause) {

    companion object {
        fun withCauseMessage(
            cause: Throwable? = null,
            message: String
        ): Nothing = throw FailedToDecodeException(message, cause)
    }
}