package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.UserEntity
import dev.juanrincon.domain.daos.UserTable
import dev.juanrincon.domain.interfaces.UserDatabase
import dev.juanrincon.domain.models.User
import dev.juanrincon.plugins.dbQuery

class UserRepository: UserDatabase {
    override suspend fun checkIfUserExists(email: String) = dbQuery {
        !UserEntity.find { UserTable.email eq email }.empty()
    }

    override suspend fun checkPasswordMatches(email: String, password: String) = dbQuery {
        try {
            val user = UserEntity.find { UserTable.email eq email}.first()
            user.password == password
        } catch (e: NoSuchElementException) {
            false
        }
    }

    override suspend fun getUserId(email: String) = dbQuery {
        UserEntity.find { UserTable.email eq email }.first().id.value
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

    override suspend fun add(entry: User) = dbQuery {
        val newUser = UserEntity.new {
            firstName = entry.firstName
            lastName = entry.lastName
            imageUrl = entry.imageUrl
            email = entry.email
            password = entry.password
        }
        newUser.toModel()
    }

    override suspend fun update(entry: User) = dbQuery {
        val user = UserEntity.findById(entry.id)
        user?.let {
            it.firstName = entry.firstName
            it.lastName = entry.lastName
            it.imageUrl = entry.imageUrl
            it.email = entry.email
            entry
        }
    }
}