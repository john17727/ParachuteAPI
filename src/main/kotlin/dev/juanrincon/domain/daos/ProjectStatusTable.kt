package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable

object ProjectStatusTable: IntIdTable("project_statuses") {
    val name = varchar("name", 16)
}