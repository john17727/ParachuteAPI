package dev.juanrincon.data.services

import dev.juanrincon.domain.interfaces.AreaDatabase
import dev.juanrincon.domain.models.Area
import dev.juanrincon.domain.models.utilities.ServiceResponse
import io.ktor.http.*

class AreaService(private val repository: AreaDatabase) {

    suspend fun createArea(area: Area): ServiceResponse<Area> {
        if (area.name.isEmpty()) {
            return ServiceResponse.Failed(HttpStatusCode.BadRequest, "Missing Fields")
        }

        return ServiceResponse.Success(repository.add(area), HttpStatusCode.Created)
    }

    suspend fun getUserAreas(userId: Int): ServiceResponse<List<Area>> {
        return ServiceResponse.Success(repository.getByUser(userId))
    }

    suspend fun updateArea(area: Area): ServiceResponse<Area> {
        if (area.name.isEmpty()) {
            return ServiceResponse.Failed(HttpStatusCode.BadRequest, "Missing Fields")
        }

        return repository.update(area)?.let {
            ServiceResponse.Success(it)
        } ?: ServiceResponse.Failed(HttpStatusCode.NotFound, "Area was not found")
    }

    suspend fun deleteArea(id: Int): ServiceResponse<Boolean> {
        return if (repository.delete(id)) {
            ServiceResponse.Success(true)
        } else {
            ServiceResponse.Failed(HttpStatusCode.NotFound,"Failed to delete area")
        }
    }
}