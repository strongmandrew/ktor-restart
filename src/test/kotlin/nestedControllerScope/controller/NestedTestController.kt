package nestedControllerScope.controller

import com.strongmandrew.method.Get
import nestedControllerScope.utils.libraryName
import nestedControllerScope.utils.testName
import nestedControllerScope.utils.thirdScopeLevel

class NestedTestController {

    @Get(path = thirdScopeLevel)
    fun getLibraryName(): String = libraryName

    @Get
    fun getTestName(): String = testName
}