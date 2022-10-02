package dev.juanrincon.domain.daos

import dev.juanrincon.domain.interfaces.utilities.DomainMapper
import dev.juanrincon.domain.models.GenericType
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PriorityLevelEntity(id: EntityID<Int>): IntEntity(id), DomainMapper<GenericType> {
    companion object : IntEntityClass<PriorityLevelEntity>(PriorityLevelTable)
    var name by PriorityLevelTable.name

    override fun toModel() = GenericType(
        id.value,
        name
    )
}