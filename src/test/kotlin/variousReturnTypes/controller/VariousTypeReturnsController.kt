package variousReturnTypes.controller

import com.strongmandrew.method.Get
import variousReturnTypes.utils.*

class VariousTypeReturnsController {

    @Get(stringPath)
    fun returnString(): String = stringValue

    @Get(longPath)
    fun returnLong(): Long = longValue

    @Get(intPath)
    fun returnInt(): Int = intValue

    @Get(booleanPath)
    fun returnBoolean(): Boolean = booleanValue

    @Get(charPath)
    fun returnChar(): Char = charValue

    @Get(listPath)
    fun returnList(): List<String> = listOfStrings

    @Get(mapPath)
    fun returnMap(): Map<String, Int> = mapOfStringAndInt

    @Get(userEntityPath)
    fun returnUserEntity(): UserEntity = userEntity

    @Get(nullablePath)
    fun returnNull(): UserEntity? = nullable
}