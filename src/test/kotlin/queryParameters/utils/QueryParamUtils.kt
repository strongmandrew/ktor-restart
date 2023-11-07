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

const val serializableQueryPath = "serializableQuery"
const val serializableQueryKey = "serialized"
val serializableQueryValue = QueryEntitySerializable.TEST

const val notSerializableQueryPath = "notSerializablePath"
const val notSerializableQueryKey = "notSerialized"
val notSerializableQueryValue = QueryEntityNotSerializable.TEST

const val nullableQueryPath = "nullablePath"
const val nullableQueryKey = "nullableKey"
const val nullableReturnString = "value is null"

const val nullableDefaultQueryPath = "nullableDefaultPath"
const val nullableDefaultQueryKey = "nullableDefaultKey"
const val nullableDefaultReturnValue = 88