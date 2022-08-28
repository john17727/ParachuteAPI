package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PriorityLevelEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<PriorityLevelEntity>(PriorityLevelTable)
    var name by PriorityLevelTable.name
}