package nestedControllerScope.controller

import com.strongmandrew.get.Get
import nestedControllerScope.utils.libraryName
import nestedControllerScope.utils.thirdScopeLevel

class NestedTestController {

    @Get(path = thirdScopeLevel)
    fun getLibraryName(): String = libraryName
}