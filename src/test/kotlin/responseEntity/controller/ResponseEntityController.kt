package responseEntity.controller

import com.strongmandrew.method.Get
import com.strongmandrew.response.ResponseEntity
import com.strongmandrew.response.badRequest
import com.strongmandrew.response.internalError
import com.strongmandrew.response.ok
import responseEntity.utils.*

class ResponseEntityController {

    @Get(okPath)
    fun getNameWithOk(): ResponseEntity<String> = okName.ok()

    @Get(badRequestPath)
    fun getNameWithBadRequest(): ResponseEntity<String> = badRequestName.badRequest()

    @Get(internalServerErrorPath)
    fun getNameWithInternalServerError(): ResponseEntity<String> = internalServerErrorName.internalError()
}