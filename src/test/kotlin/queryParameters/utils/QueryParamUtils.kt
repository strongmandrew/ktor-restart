package queryParameters.utils

const val simpleQueryParamPath = "simpleQuery"
const val simpleQueryParamKey = "name"
const val simpleQueryParamValue = "Johnie"

const val simpleDefaultVsQueryParamPath = "simpleDefaultVsQuery"
const val simpleDefaultVsQueryParamKey = "age"
const val simpleDefaultVsQueryParamValue = 66
const val simpleDefaultVsQueryParamDefault = 100

const val mediumQueryParamPath = "mediumQueryParam"
const val mediumQueryParamFirstKey = "mediumQueryParamFirstKey"
const val mediumQueryParamSecondKey = "mediumQueryParamSecondKey"
const val mediumQueryParamThirdKey = "mediumQueryParamThirdKey"
const val mediumQueryParamFirstValue = "Johnie"
const val mediumQueryParamSecondDefault = "Amy"
const val mediumQueryParamThirdValue = "Dough"

fun joinNames(vararg name: String): String = name.joinToString("/")
