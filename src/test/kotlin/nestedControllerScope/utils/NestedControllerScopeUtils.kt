package nestedControllerScope.utils

import io.ktor.http.*

const val secondScopeLevel: String = "/test"
const val thirdScopeLevel: String = "/library"
const val firstScopeLevel: String = "/api"

val testName = "test-name".quote()
val libraryName = "ktor-restart".quote()
