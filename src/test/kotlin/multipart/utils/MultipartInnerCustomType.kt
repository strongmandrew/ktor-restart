package multipart.utils

import kotlinx.serialization.Serializable

@Serializable
data class MultipartInnerCustomType(
    private val name: String,
    private val surname: String
) {
    companion object {
        val test = MultipartInnerCustomType(
            name = "Sam",
            surname = "Smith"
        )
    }
}