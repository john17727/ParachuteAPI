package dev.juanrincon.domain.daos

import dev.juanrincon.domain.interfaces.utilities.DomainMapper
import dev.juanrincon.domain.models.Project
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProjectEntity(id: EntityID<Int>): IntEntity(id), DomainMapper<Project> {
    companion object : IntEntityClass<ProjectEntity>(ProjectTable)
    var name by ProjectTable.name
    var status by ProjectStatusEntity referencedOn ProjectTable.statusId
    var area by AreaEntity optionalReferencedOn ProjectTable.areaId
    var user by UserEntity referencedOn ProjectTable.userId

    override fun toModel() = Project(
        id.value,
        name,
        status.id.value,
        area?.id?.value,
    )
}