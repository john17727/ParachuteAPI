package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable

object ArchivedNoteTable: IntIdTable("archived_notes") {
    val title = varchar("title", 256)
    val markdown = varchar("markdown", Int.MAX_VALUE)
    val html = varchar("html", Int.MAX_VALUE)
    val typeId = reference("type_id", NoteTypeTable)
    val favorite = bool("favorite")
    val fleeting = bool("fleeting")
    val projectId = reference("projectId", ProjectTable)
    val resourceId = reference("resource_id", ResourceTable)
    val areaId = reference("area_id", AreaTable)
    val userId = reference("user_id", UserTable)
}