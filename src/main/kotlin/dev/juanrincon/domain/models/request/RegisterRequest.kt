package dev.juanrincon.domain.models.request

data class RegisterRequest(
    val firstName: String?,
    val lastName: String?,
    val imageUrl: String?,
    val email: String,
    val password: String
)
