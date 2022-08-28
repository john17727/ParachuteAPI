package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable

object AreaTable: IntIdTable("areas") {
    val name = varchar("name", 256)
    val userId = reference("user_id", UserTable)
}