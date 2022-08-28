package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class NoteTypeEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<NoteTypeEntity>(NoteTypeTable)
    var name by NoteTypeTable.name
}