package cookie.controller

import com.strongmandrew.cookie.Cookie
import com.strongmandrew.method.Get
import cookie.utils.*

class CookieController {

    @Get(simpleCookiePath)
    fun getSimpleCookie(
        @Cookie(simpleCookieKey) name: String
    ): String = name

    @Get(serializedCookiePath)
    fun getSerializedCookie(
        @Cookie(serializedCookieKey) type: SerializedCustomCookieType
    ): SerializedCustomCookieType = type

    @Get(defaultProvidedCookiePath)
    fun getDefaultProvidedCookie(
        @Cookie(defaultProvidedCookieKey) cookie: Map<String, Int> = defaultProvidedCookieValue
    ): Map<String, Int> = cookie

    @Get(nullableCookiePath)
    fun getNullableCookie(
        @Cookie(nullableCookieKey) cookie: Int?
    ): Int = cookie ?: alternativeCookieValue
}