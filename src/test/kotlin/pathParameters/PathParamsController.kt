package pathParameters

import com.strongmandrew.method.Get
import com.strongmandrew.path.PathParam

class PathParamsController {

    @Get("$pathParamsPath/{id}")
    fun getId(
        @PathParam("id") id: Int
    ): Int = id
}