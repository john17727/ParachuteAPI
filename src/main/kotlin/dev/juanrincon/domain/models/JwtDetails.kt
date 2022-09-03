package dev.juanrincon.domain.models

data class JwtDetails(
    val issuer: String,
    val audience: String,
    val secret: String
)
