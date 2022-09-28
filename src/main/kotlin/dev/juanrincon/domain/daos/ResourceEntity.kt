package dev.juanrincon.domain.daos

import dev.juanrincon.domain.interfaces.utilities.DomainMapper
import dev.juanrincon.domain.models.Resource
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ResourceEntity(id: EntityID<Int>): IntEntity(id), DomainMapper<Resource> {
    companion object : IntEntityClass<ResourceEntity>(ResourceTable)
    var name by ResourceTable.name
    var area by AreaEntity optionalReferencedOn ResourceTable.areaId
    var user by UserEntity referencedOn ResourceTable.userId

    override fun toModel() = Resource(
        id.value,
        name,
        area.id.value
    )
}