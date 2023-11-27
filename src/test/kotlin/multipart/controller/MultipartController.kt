package multipart.controller

import com.strongmandrew.method.Get
import com.strongmandrew.multipart.MultipartFormData
import multipart.utils.*

class MultipartController {

    @Get(simpleMultipartPath)
    fun getMultipartFormData(
        @MultipartFormData(simpleMultipartKey) key: String,
    ) = key

    @Get(multipartWithDefaultSetPath)
    fun getMultipartFormDataWithDefault(
        @MultipartFormData(multipartWithDefaultSetKey) key: MultipartCustomType = multipartWithDefaultSetValue
    ) = key

    @Get(nullableMultipartPath)
    fun getNullableMultipartFormData(
        @MultipartFormData(nullableMultipartKey) key: MultipartInnerCustomType?
    ): MultipartInnerCustomType = key ?: nullableMultipartElvisValue
}