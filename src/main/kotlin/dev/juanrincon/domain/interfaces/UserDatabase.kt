package dev.juanrincon.domain.interfaces

import dev.juanrincon.domain.daos.UserEntity
import dev.juanrincon.domain.interfaces.utilities.Repository

interface UserDatabase: Repository<UserEntity> {
    suspend fun checkIfUserExists(email: String): Boolean

    suspend fun checkPasswordMatches(password: String): Boolean
}