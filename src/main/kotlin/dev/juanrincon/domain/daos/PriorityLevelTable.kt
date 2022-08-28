package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable

object PriorityLevelTable: IntIdTable("priority_levels") {
    val name = varchar("name", 16)
}