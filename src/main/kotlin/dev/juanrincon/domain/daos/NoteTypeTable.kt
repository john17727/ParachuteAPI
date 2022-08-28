package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable

object NoteTypeTable: IntIdTable("note_types") {
    val name = varchar("name", 16)
}