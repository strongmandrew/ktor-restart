package get

import com.strongmandrew.get.Get

class HelloWorldController {

    @Get
    fun helloWorld(): String {
        return "Hello, world!"
    }

    @Get(path = "overriddenHelloWorld")
    fun helloWorldOverridden(): String {
        return "Hello, world (overridden)!"
    }
}