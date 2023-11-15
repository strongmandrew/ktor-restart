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
}