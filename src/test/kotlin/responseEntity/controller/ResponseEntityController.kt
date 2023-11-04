package responseEntity.controller

import com.strongmandrew.method.Get
import com.strongmandrew.response.ResponseEntity
import com.strongmandrew.response.asResponseEntity
import io.ktor.http.*
import responseEntity.utils.*

class ResponseEntityController {

    @Get(okPath)
    fun getNameWithOk(): ResponseEntity<String> = okName.asResponseEntity {
        ok()
    }

    @Get(badRequestPath)
    fun getNameWithBadRequest(): ResponseEntity<String> = badRequestName.asResponseEntity {
        statusCode(HttpStatusCode.BadRequest)
    }

    @Get(internalServerErrorPath)
    fun getNameWithInternalServerError(): ResponseEntity<String> = internalServerErrorName.asResponseEntity {
        statusCode(HttpStatusCode.InternalServerError)
    }
}