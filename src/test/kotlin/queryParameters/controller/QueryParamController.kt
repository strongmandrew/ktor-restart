package queryParameters.controller

import com.strongmandrew.method.Get
import com.strongmandrew.query.QueryParam
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

    @Get(mediumQueryParamPath)
    fun getNamesByOrder(
        @QueryParam(mediumQueryParamFirstKey) firstName: String,
        @QueryParam(mediumQueryParamSecondKey) secondName: String = mediumQueryParamSecondDefault,
        @QueryParam(mediumQueryParamThirdKey) thirdName: String,
    ): String = joinNames(firstName, secondName, thirdName)

    @Get(serializableQueryPath)
    fun getSerializedEntityFromQuery(
        @QueryParam(serializableQueryKey) query: QueryEntitySerializable
    ): QueryEntitySerializable = query

    @Get(notSerializableQueryPath)
    fun getNotSerializableEntity(
        @QueryParam(notSerializableQueryKey) query: QueryEntityNotSerializable
    ): QueryEntityNotSerializable = query
}