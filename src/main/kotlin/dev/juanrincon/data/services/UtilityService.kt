package dev.juanrincon.data.services

import dev.juanrincon.domain.interfaces.UtilityDatabase
import dev.juanrincon.domain.models.GenericType
import dev.juanrincon.domain.models.utilities.ServiceResponse

class UtilityService(private val repository: UtilityDatabase) {
    suspend fun getTaskStatuses(): ServiceResponse<List<GenericType>> {
        return ServiceResponse.Success(repository.getTaskStatuses())
    }

    suspend fun getProjectStatuses(): ServiceResponse<List<GenericType>> {
        return ServiceResponse.Success(repository.getProjectStatuses())
    }

    suspend fun getNoteTypes(): ServiceResponse<List<GenericType>> {
        return ServiceResponse.Success(repository.getNoteTypes())
    }

    suspend fun getPriorityLevels(): ServiceResponse<List<GenericType>> {
        return ServiceResponse.Success(repository.getPriorityLevels())
    }
}