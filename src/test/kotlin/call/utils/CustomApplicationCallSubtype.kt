package call.utils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.*

class CustomApplicationCallSubtype : ApplicationCall {

    override val application: Application
        get() = TODO("Not yet implemented")
    override val attributes: Attributes
        get() = TODO("Not yet implemented")
    override val parameters: Parameters
        get() = TODO("Not yet implemented")
    override val request: ApplicationRequest
        get() = TODO("Not yet implemented")
    override val response: ApplicationResponse
        get() = TODO("Not yet implemented")
}