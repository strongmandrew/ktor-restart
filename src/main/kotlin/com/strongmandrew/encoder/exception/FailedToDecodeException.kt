package com.strongmandrew.encoder.exception

class FailedToDecodeException(override val message: String?, override val cause: Throwable?) : Exception(message, cause)