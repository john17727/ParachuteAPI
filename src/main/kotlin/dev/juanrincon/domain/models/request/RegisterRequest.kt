package dev.juanrincon.domain.models.request

import dev.juanrincon.domain.interfaces.utilities.DomainMapper
import dev.juanrincon.domain.models.User

data class RegisterRequest(
    val firstName: String?,
    val lastName: String?,
    val email: String,
    var password: String,
) : DomainMapper<User> {
    override fun toModel() = User(
        -1,
        firstName,
        lastName,
        "",
        email,
        password
    )
}
