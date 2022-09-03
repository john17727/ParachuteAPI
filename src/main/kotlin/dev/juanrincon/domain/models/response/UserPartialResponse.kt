package dev.juanrincon.domain.models.response

data class UserPartialResponse(
    val email: String,
    val firstName: String?,
    val lastName: String?
)
