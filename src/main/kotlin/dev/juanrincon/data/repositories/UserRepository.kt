package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.UserEntity
import dev.juanrincon.domain.daos.UserTable
import dev.juanrincon.domain.interfaces.UserDatabase
import dev.juanrincon.domain.models.request.HashedUser
import dev.juanrincon.domain.models.request.RegisterRequest
import dev.juanrincon.plugins.dbQuery
import dev.juanrincon.security.hashing.SaltedHash

class UserRepository: UserDatabase {
    override suspend fun checkUserExists(email: String) = dbQuery {
        !UserEntity.find { UserTable.email eq email }.empty()
    }

    override suspend fun getSaltedHash(email: String) = dbQuery {
        try {
            val user = UserEntity.find { UserTable.email eq email}.first()
            SaltedHash(user.hash, user.salt)
        } catch (e: NoSuchElementException) {
            null
        }
    }

    override suspend fun get(email: String) = dbQuery {
        UserEntity.find { UserTable.email eq email }.first().toModel()
    }

    override suspend fun get(id: Int) = dbQuery {
        UserEntity.findById(id)?.toModel()
    }

    override suspend fun delete(id: Int) = dbQuery {
        UserEntity.findById(id)?.let {
            it.delete()
            true
        } ?: false
    }

    override suspend fun add(entry: HashedUser) = dbQuery {
        val newUser = UserEntity.new {
            firstName = entry.firstName
            lastName = entry.lastName
            imageUrl = entry.imageUrl
            email = entry.email
            hash = entry.hash
            salt = entry.salt
        }
        newUser.toModel()
    }

    override suspend fun update(id: Int, entry: HashedUser) = dbQuery {
        val user = UserEntity.findById(id)
        user?.let {
            it.firstName = entry.firstName
            it.lastName = entry.lastName
            it.imageUrl = entry.imageUrl
            it.email = entry.email
            it.toModel()
        }
    }
}