package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable

object TaskStatusTable: IntIdTable("task_statuses") {
    val name =varchar("name", 16)
}