package get.helloWorld

import com.strongmandrew.get.Get

class HelloWorldController {

    @Get
    fun helloWorld(): String = helloWorldValue
}