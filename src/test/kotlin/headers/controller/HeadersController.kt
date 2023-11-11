package headers.controller

import com.strongmandrew.header.Header
import com.strongmandrew.method.Get
import headers.utils.*

class HeadersController {

    @Get(simpleHeaderPath)
    fun getSimpleHeader(
        @Header(simpleHeaderKey) name: String
    ): String = name

    @Get(predefinedHeaderPath)
    fun getPredefinedHeaders(
        @Header(userAgentHeaderKey) userAgent: String,
        @Header(acceptHeaderKey) accept: String
    ): String = joinedHeaders(userAgent, accept)

    @Get(serializableHeaderPath)
    fun getHeaderOfCustomType(
        @Header(serializableHeaderKey) value: CustomHeaderType
    ): CustomHeaderType = value

    @Get(headerWithDefaultPath)
    fun getHeaderWithDefaultValue(
        @Header(headerWithDefaultKey) value: Int = headerWithDefaultValue
    ): Int = value

    @Get(nullableHeaderPath)
    fun getNullableHeader(
        @Header(nullableHeaderKey) value: String?
    ): String = value ?: nullableHeaderElvisValue
}