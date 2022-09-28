package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.AreaEntity
import dev.juanrincon.domain.daos.AreaTable
import dev.juanrincon.domain.daos.UserEntity
import dev.juanrincon.domain.interfaces.utilities.MutableRepository
import dev.juanrincon.domain.models.Area
import dev.juanrincon.domain.models.request.AreaRequest
import dev.juanrincon.plugins.dbQuery

class AreaRepository: MutableRepository<AreaRequest, Area> {
    override suspend fun getByUser(userId: Int) = dbQuery {
        AreaEntity.find { AreaTable.userId eq userId }.map { it.toModel() }
    }

    override suspend fun delete(id: Int) = dbQuery {
        AreaEntity.findById(id)?.let {
            it.delete()
            true
        } ?: false
    }

    override suspend fun update(id: Int, entry: AreaRequest) = dbQuery {
        val area = AreaEntity.findById(id)

        area?.let {
            it.name = entry.name
            it.toModel()
        }
    }

    override suspend fun add(entry: AreaRequest) = dbQuery {
        val currentUser = UserEntity.findById(entry.userId)
        val newArea = AreaEntity.new {
            name = entry.name
            user = currentUser!!
        }
        newArea.toModel()
    }
}