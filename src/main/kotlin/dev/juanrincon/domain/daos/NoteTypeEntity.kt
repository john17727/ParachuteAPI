package dev.juanrincon.domain.daos

import dev.juanrincon.domain.interfaces.utilities.DomainMapper
import dev.juanrincon.domain.models.GenericType
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class NoteTypeEntity(id: EntityID<Int>): IntEntity(id), DomainMapper<GenericType> {
    companion object : IntEntityClass<NoteTypeEntity>(NoteTypeTable)
    var name by NoteTypeTable.name

    override fun toModel() = GenericType(
        id.value,
        name
    )
}