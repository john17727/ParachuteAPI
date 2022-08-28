package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AreaEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<AreaEntity>(AreaTable)
    var name by AreaTable.name
    var user by UserEntity referencedOn AreaTable.userId
}
