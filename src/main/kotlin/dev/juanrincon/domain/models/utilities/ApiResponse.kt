package dev.juanrincon.domain.models.utilities

data class ApiResponse<T>(
    val status: String,
    val message: String? = null,
    val count: Long? = null,
    val limit: Int? = null,
    val previousOffset: Long? = null,
    val nextOffset: Long? = null,
    val data: T? = null
) {
    companion object {
        fun <T> success(
            data: T,
            count: Long? = null,
            limit: Int? = null,
            previousOffset: Long? = null,
            nextOffset: Long? = null
        ) = ApiResponse(
            status = "success",
            data = data,
            count = count,
            limit = limit,
            previousOffset = previousOffset,
            nextOffset = nextOffset
        )

        fun fail(message: String) = ApiResponse<Nothing>(
            status = "fail",
            message = message
        )

        fun error(
            message: String
        ) = ApiResponse<Nothing>(
            status = "error",
            message = message
        )
    }
}
