package dev.juanrincon.domain.models.request

data class HashedUser(
    val firstName: String?,
    val lastName: String?,
    val imageUrl: String?,
    val email: String,
    val hash: String,
    val salt: String
)
