package dev.juanrincon.domain.daos

import dev.juanrincon.domain.interfaces.utilities.DomainMapper
import dev.juanrincon.domain.models.Area
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AreaEntity(id: EntityID<Int>): IntEntity(id), DomainMapper<Area> {
    companion object : IntEntityClass<AreaEntity>(AreaTable)
    var name by AreaTable.name
    var user by UserEntity referencedOn AreaTable.userId
    override fun toModel() = Area(
        id.value,
        name
    )
}
