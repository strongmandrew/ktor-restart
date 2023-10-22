package get.variousReturnTypes

import com.strongmandrew.get.Get

class VariousReturnTypesController {

    @Get("/isSuccess")
    fun getSuccess(): Boolean = booleanTypeValue

    @Get
    fun getLong(): Long = longTypeValue

    @Get
    fun getSpecialValue(): TestReturnValue = specialTypeValue
}