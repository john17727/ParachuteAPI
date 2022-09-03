package dev.juanrincon.domain.models.response

data class TokenResponse(
    val token: String,
    val user: UserPartialResponse? = null
)
