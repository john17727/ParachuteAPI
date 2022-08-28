package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ResourceEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<ResourceEntity>(ResourceTable)
    var name by ResourceTable.name
    var area by AreaEntity referencedOn ResourceTable.areaId
    var user by UserEntity referencedOn ResourceTable.userId
}