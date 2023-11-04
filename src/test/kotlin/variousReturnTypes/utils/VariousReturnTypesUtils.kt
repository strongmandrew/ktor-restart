package variousReturnTypes.utils

/* Primitives */
const val stringValue = "string return value"
const val stringPath = "stringPath"

const val longValue = 52894624698L
const val longPath = "longPath"

const val intValue = 327468
const val intPath = "intPath"

const val booleanValue = true
const val booleanPath = "booleanPath"

const val charValue = 'm'
const val charPath = "charPath"

/* Collections */
val listOfStrings = listOf("Amy", "Samy", "Johnie")
const val listPath = "listPath"

val mapOfStringAndInt = mapOf(
    "Paul" to 2,
    "Jeff" to 6,
    "Mike" to 14
)
const val mapPath = "mapPath"

/* User types */
val userEntity = UserEntity(
    age = 25,
    name = "Steve",
    smokes = true
)
const val userEntityPath = "userEntityPath"
