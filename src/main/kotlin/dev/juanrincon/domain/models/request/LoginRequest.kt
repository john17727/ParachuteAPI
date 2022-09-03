package dev.juanrincon.domain.models.request

data class LoginRequest(
    val email: String,
    var password: String
)