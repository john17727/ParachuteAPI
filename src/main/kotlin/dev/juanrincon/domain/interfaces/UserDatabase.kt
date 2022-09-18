package dev.juanrincon.domain.interfaces

import dev.juanrincon.domain.interfaces.utilities.Repository
import dev.juanrincon.domain.models.User
import dev.juanrincon.domain.models.request.HashedUser
import dev.juanrincon.domain.models.request.RegisterRequest
import dev.juanrincon.security.hashing.SaltedHash

interface UserDatabase: Repository<HashedUser, User> {
    suspend fun checkUserExists(email: String): Boolean

    suspend fun getSaltedHash(email: String): SaltedHash?

    suspend fun get(email: String): User
}