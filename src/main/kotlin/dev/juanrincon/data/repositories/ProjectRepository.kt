package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.*
import dev.juanrincon.domain.interfaces.ProjectDatabase
import dev.juanrincon.domain.models.request.ProjectRequest
import dev.juanrincon.plugins.dbQuery

class ProjectRepository: ProjectDatabase {
    override suspend fun getByArea(areaId: Int, userId: Int) = dbQuery {
        if (areaId < 1) {
            ProjectEntity.find { ProjectTable.userId eq userId }.filter { it.area == null }.map { it.toModel() }
        } else {
            ProjectEntity.find { ProjectTable.areaId eq areaId }.map { it.toModel() }
        }
    }

    override suspend fun delete(id: Int) = dbQuery {
        ProjectEntity.findById(id)?.let {
            it.delete()
            true
        } ?: false
    }

    override suspend fun add(entry: ProjectRequest) = dbQuery {
        ProjectEntity.new {
            name = entry.name
            status = getStatus(entry.statusId)
            area = getArea(entry.areaId)
            user = getUser(entry.userId)
        }.toModel()
    }

    override suspend fun update(id: Int, entry: ProjectRequest) = dbQuery {
        ProjectEntity.findById(id)?.let {
            it.name = entry.name
            it.status = getStatus(entry.statusId)
            it.area = getArea(entry.areaId)
            it.user = getUser(entry.userId)
            it.toModel()
        }
    }

    override suspend fun getByUser(userId: Int) = dbQuery {
        ProjectEntity.find { ProjectTable.userId eq userId }.map { it.toModel() }
    }

    private fun getArea(id: Int?) = id?.let {
        AreaEntity.findById(it)
    }

    private fun getStatus(id: Int) = ProjectStatusEntity.findById(id)!!

    private fun getUser(id: Int) = UserEntity.findById(id)!!
}