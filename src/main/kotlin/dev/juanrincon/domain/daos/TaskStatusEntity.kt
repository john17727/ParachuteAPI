package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TaskStatusEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<TaskStatusEntity>(TaskStatusTable)
    var name by TaskStatusTable.name
}