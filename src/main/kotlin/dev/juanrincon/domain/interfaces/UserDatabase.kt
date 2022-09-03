package dev.juanrincon.domain.interfaces

import dev.juanrincon.domain.interfaces.utilities.Repository
import dev.juanrincon.domain.models.User

interface UserDatabase: Repository<User> {
    suspend fun checkUserExists(email: String): Boolean

    suspend fun checkPasswordMatches(email: String, password: String): Boolean

    suspend fun get(email: String): User
}