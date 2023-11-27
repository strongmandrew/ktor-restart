package multipart.utils

import kotlinx.serialization.Serializable

@Serializable
data class MultipartCustomType(
    val age: Long,
    val names: MultipartInnerCustomType,
    val occupation: String
) {
    companion object {
        val test = MultipartCustomType(
            age = 90,
            names = MultipartInnerCustomType.test,
            occupation = "smith"
        )
    }
}