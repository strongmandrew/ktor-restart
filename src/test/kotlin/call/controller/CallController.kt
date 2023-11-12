package call.controller

import call.utils.CustomApplicationCallSubtype
import call.utils.customCallPath
import call.utils.incorrectTypeCallPath
import call.utils.simpleCallPath
import com.strongmandrew.call.Call
import com.strongmandrew.method.Get
import io.ktor.server.application.*

class CallController {

    @Get(simpleCallPath)
    fun getCall(
        @Call call: ApplicationCall,
    ): String = call.toString()

    @Get(incorrectTypeCallPath)
    fun getCallOfIncorrectType(
        @Call call: String,
    ): String = call

    @Get(customCallPath)
    fun getCallOfCustomSubtype(
        @Call call: CustomApplicationCallSubtype
    ) {}
}