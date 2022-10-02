package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable

object NoteTable: IntIdTable("notes") {
    val title = varchar("title", 256)
    val markdown = text("markdown")
    val html = text("html")
    val typeId = reference("type_id", NoteTypeTable)
    val favorite = bool("favorite")
    val fleeting = bool("fleeting")
    val projectId = reference("projectId", ProjectTable).nullable()
    val resourceId = reference("resource_id", ResourceTable).nullable()
    val areaId = reference("area_id", AreaTable).nullable()
    val userId = reference("user_id", UserTable)
}