package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable

object ResourceTable: IntIdTable("resources") {
    val name = varchar("name", 256)
    val areaId = reference("area_id", AreaTable)
    val userId = reference("user_id", UserTable)
}