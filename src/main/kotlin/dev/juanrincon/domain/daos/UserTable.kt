package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable: IntIdTable("users") {
    val firstName = varchar("first_name", 32).nullable()
    val lastName = varchar("last_name", 32).nullable()
    val imageUrl = varchar("imageUrl", 250).nullable()
    val email = varchar("email", 64)
    val hash = varchar("hash",  256)
    val salt = varchar("salt", 64)
}