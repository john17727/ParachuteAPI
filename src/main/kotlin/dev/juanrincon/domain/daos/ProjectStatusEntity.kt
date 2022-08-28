package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProjectStatusEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<ProjectStatusEntity>(ProjectStatusTable)
    var name by ProjectStatusTable.name
}