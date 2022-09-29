package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable

object ProjectTable: IntIdTable("projects") {
    val name = varchar("name", 256)
    val statusId = reference("status_id", ProjectStatusTable)
    val areaId = reference("area_id", AreaTable).nullable()
    val userId = reference("user_id", UserTable)
}