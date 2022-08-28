package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProjectEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<ProjectEntity>(ProjectTable)
    var name by ProjectTable.name
    var status by ProjectStatusEntity referencedOn ProjectTable.statusId
    var area by AreaEntity referencedOn ResourceTable.areaId
    var user by UserEntity referencedOn ResourceTable.userId
}