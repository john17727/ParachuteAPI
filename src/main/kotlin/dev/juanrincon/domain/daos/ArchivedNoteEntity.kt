package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ArchivedNoteEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<ArchivedNoteEntity>(ArchivedNoteTable)
    var noteId by ArchivedNoteTable.noteId
    var title by ArchivedNoteTable.title
    var markdown by ArchivedNoteTable.markdown
    var html by ArchivedNoteTable.html
    var type by NoteTypeEntity referencedOn ArchivedNoteTable.typeId
    var favorite by ArchivedNoteTable.favorite
    var fleeting by ArchivedNoteTable.fleeting
    var project by ProjectEntity optionalReferencedOn  ArchivedNoteTable.projectId
    var resource by ResourceEntity optionalReferencedOn ArchivedNoteTable.resourceId
    var area by AreaEntity optionalReferencedOn ArchivedNoteTable.areaId
    var user by UserEntity referencedOn ArchivedNoteTable.userId
}