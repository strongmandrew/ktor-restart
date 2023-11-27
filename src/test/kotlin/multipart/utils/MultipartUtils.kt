package multipart.utils

const val simpleMultipartPath = "simpleMultipartPath"
const val simpleMultipartKey = "simpleMultipartKey"
const val simpleMultipartValue = "simpleMultipartValue"

const val multipartWithDefaultSetPath = "multipartWithDefaultSetPart"
const val multipartWithDefaultSetKey = "multipartWithDefaultSetKey"
val multipartWithDefaultSetValue = MultipartCustomType.test
val multipartWithDefaultSetProvidedValue = MultipartCustomType(
    age = 22,
    names = MultipartInnerCustomType(
        name = "Mike",
        surname = "Davis"
    ),
    occupation = "Musician"
)

const val nullableMultipartPath = "nullableMultipartPath"
const val nullableMultipartKey = "nullableMultipartKey"
val nullableMultipartElvisValue = MultipartInnerCustomType.test
val nullableMultipartProvidedValue = MultipartInnerCustomType(
    name = "Mark",
    surname = "Simpson"
)