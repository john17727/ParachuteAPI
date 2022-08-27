package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable: IntIdTable("user") {
    val firstName = varchar("first_name", 32)
    val lastName = varchar("last_name", 32)
    val email = varchar("email", 64)
}