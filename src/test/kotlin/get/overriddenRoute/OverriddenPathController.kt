package get.overriddenRoute

import com.strongmandrew.get.Get

class OverriddenPathController {

    @Get(path = "/overriddenPath")
    fun getSuccess(): String = stringSuccessValue
}