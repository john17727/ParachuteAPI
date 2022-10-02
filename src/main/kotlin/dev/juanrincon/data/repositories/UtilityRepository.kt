package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.NoteTypeEntity
import dev.juanrincon.domain.daos.PriorityLevelEntity
import dev.juanrincon.domain.daos.ProjectStatusEntity
import dev.juanrincon.domain.daos.TaskStatusEntity
import dev.juanrincon.domain.interfaces.UtilityDatabase
import dev.juanrincon.plugins.dbQuery

class UtilityRepository: UtilityDatabase {
    override suspend fun getTaskStatuses() = dbQuery {
        TaskStatusEntity.all().map { it.toModel() }
    }

    override suspend fun getProjectStatuses() = dbQuery {
        ProjectStatusEntity.all().map { it.toModel() }
    }

    override suspend fun getNoteTypes() = dbQuery {
        NoteTypeEntity.all().map { it.toModel() }
    }

    override suspend fun getPriorityLevels() = dbQuery {
        PriorityLevelEntity.all().map { it.toModel() }
    }

}