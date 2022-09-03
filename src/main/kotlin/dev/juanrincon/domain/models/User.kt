package dev.juanrincon.domain.models

data class User(
    var id: Int,
    val firstName: String?,
    val lastName: String?,
    val imageUrl: String?,
    val email: String,
    val password: String,
)
