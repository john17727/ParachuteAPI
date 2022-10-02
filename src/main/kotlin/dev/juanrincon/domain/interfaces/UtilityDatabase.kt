package dev.juanrincon.domain.interfaces

import dev.juanrincon.domain.models.GenericType

interface UtilityDatabase {

    suspend fun getTaskStatuses(): List<GenericType>
    suspend fun getProjectStatuses(): List<GenericType>
    suspend fun getNoteTypes(): List<GenericType>
    suspend fun getPriorityLevels(): List<GenericType>
}