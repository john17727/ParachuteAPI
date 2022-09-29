package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class NoteEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<NoteEntity>(NoteTable)
    var title by NoteTable.title
    var markdown by NoteTable.markdown
    var html by NoteTable.html
    var type by NoteTypeEntity referencedOn NoteTable.typeId
    var favorite by NoteTable.favorite
    var fleeting by NoteTable.fleeting
    var project by ProjectEntity referencedOn NoteTable.projectId
    var resource by ResourceEntity referencedOn NoteTable.resourceId
    var area by AreaEntity referencedOn NoteTable.areaId
    var user by UserEntity referencedOn NoteTable.userId
}