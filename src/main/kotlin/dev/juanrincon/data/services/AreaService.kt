package dev.juanrincon.data.services

import dev.juanrincon.domain.interfaces.AreaDatabase
import dev.juanrincon.domain.interfaces.utilities.MutableRepository
import dev.juanrincon.domain.models.Area
import dev.juanrincon.domain.models.request.AreaRequest
import dev.juanrincon.domain.models.utilities.ServiceResponse
import io.ktor.http.*

class AreaService(private val repository: AreaDatabase) {

    suspend fun createArea(areaRequest: AreaRequest): ServiceResponse<Area> {
        if (areaRequest.name.isEmpty()) {
            return ServiceResponse.Failed("Missing Fields", HttpStatusCode.BadRequest)
        }

        return ServiceResponse.Success(repository.add(areaRequest), HttpStatusCode.Created)
    }

    suspend fun getUserAreas(userId: Int): ServiceResponse<List<Area>> {
        return ServiceResponse.Success(repository.getByUser(userId))
    }

    suspend fun updateArea(id: Int, areaRequest: AreaRequest): ServiceResponse<Area> {
        if (areaRequest.name.isEmpty()) {
            return ServiceResponse.Failed("Missing Fields", HttpStatusCode.BadRequest)
        }

        return repository.update(id, areaRequest)?.let {
            ServiceResponse.Success(it)
        } ?: ServiceResponse.Failed("Area was not found", HttpStatusCode.NotFound)
    }

    suspend fun deleteArea(id: Int): ServiceResponse<Boolean> {
        return if (repository.delete(id)) {
            ServiceResponse.Success(true)
        } else {
            ServiceResponse.Failed("Failed to delete area", HttpStatusCode.NotFound)
        }
    }
}