package queryParameters.controller

import com.strongmandrew.method.Get
import com.strongmandrew.parameter.QueryParam
import queryParameters.utils.*

class QueryParamController {

    @Get(simpleQueryParamPath)
    fun getPassedName(
        @QueryParam(simpleQueryParamKey) name: String
    ): String = name

    @Get(simpleDefaultVsQueryParamPath)
    fun getDefaultName(
        @QueryParam(simpleDefaultVsQueryParamKey) age: Int = simpleDefaultVsQueryParamDefault
    ): Int = age
}