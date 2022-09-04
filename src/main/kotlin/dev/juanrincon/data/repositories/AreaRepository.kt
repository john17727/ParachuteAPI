package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.AreaEntity
import dev.juanrincon.domain.daos.AreaTable
import dev.juanrincon.domain.daos.UserEntity
import dev.juanrincon.domain.interfaces.AreaDatabase
import dev.juanrincon.domain.models.Area
import dev.juanrincon.plugins.dbQuery

class AreaRepository: AreaDatabase {
    override suspend fun getByUser(userId: Int) = dbQuery {
        AreaEntity.find { AreaTable.userId eq userId }.map { it.toModel() }
    }

    override suspend fun get(id: Int) = dbQuery {
        AreaEntity.findById(id)?.toModel()
    }

    override suspend fun delete(id: Int) = dbQuery {
        AreaEntity.findById(id)?.let {
            it.delete()
            true
        } ?: false
    }

    override suspend fun update(entry: Area) = dbQuery {
        val area = AreaEntity.findById(entry.id)

        area?.let {
            it.name = entry.name
            entry
        }
    }

    override suspend fun add(entry: Area) = dbQuery {
        val currentUser = UserEntity.findById(entry.id)
        val newArea = AreaEntity.new {
            name = entry.name
            user = currentUser!!
        }
        newArea.toModel()
    }
}