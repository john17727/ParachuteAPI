package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.AreaEntity
import dev.juanrincon.domain.daos.ResourceEntity
import dev.juanrincon.domain.daos.ResourceTable
import dev.juanrincon.domain.daos.UserEntity
import dev.juanrincon.domain.interfaces.ResourceDatabase
import dev.juanrincon.domain.models.request.ResourceRequest
import dev.juanrincon.plugins.dbQuery
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select

class ResourceRepository: ResourceDatabase {
    override suspend fun getByArea(areaId: Int, userId: Int) = dbQuery {
        if (areaId < 1) {
            ResourceEntity.find { ResourceTable.userId eq userId }.filter { it.area == null }.map { it.toModel() }
        } else {
            ResourceEntity.find { ResourceTable.areaId eq areaId }.map { it.toModel() }
        }
    }

    override suspend fun getByUser(userId: Int) = dbQuery {
        ResourceEntity.find { ResourceTable.userId eq userId }.map { it.toModel() }
    }

    override suspend fun delete(id: Int) = dbQuery {
        ResourceEntity.findById(id)?.let {
            it.delete()
            true
        } ?: false
    }

    override suspend fun update(id: Int, entry: ResourceRequest) = dbQuery {
        ResourceEntity.findById(id)?.let {
            it.name = entry.name
            it.area = getResourceArea(entry.areaId)
            it.toModel()
        }
    }

    override suspend fun add(entry: ResourceRequest) = dbQuery {
        ResourceEntity.new {
            name = entry.name
            area = getResourceArea(entry.areaId)
            user = UserEntity.findById(entry.userId)!!
        }.toModel()
    }

    private fun getResourceArea(id: Int?) = id?.let {
        AreaEntity.findById(it)
    }
}