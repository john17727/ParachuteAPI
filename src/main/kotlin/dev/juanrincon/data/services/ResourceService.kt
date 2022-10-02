package dev.juanrincon.data.services

import dev.juanrincon.domain.interfaces.ResourceDatabase
import dev.juanrincon.domain.models.Resource
import dev.juanrincon.domain.models.request.ResourceRequest
import dev.juanrincon.domain.models.utilities.ServiceResponse
import io.ktor.http.*

class ResourceService(private val repository: ResourceDatabase) {

    suspend fun createResource(resourceRequest: ResourceRequest): ServiceResponse<Resource> {
        if (resourceRequest.name.isEmpty()) {
            return ServiceResponse.Failed("Missing Fields", HttpStatusCode.BadRequest)
        }

        return ServiceResponse.Success(repository.add(resourceRequest), HttpStatusCode.Created)
    }

    suspend fun getUserResources(userId: Int): ServiceResponse<List<Resource>> {
        return ServiceResponse.Success(repository.getByUser(userId))
    }

    suspend fun getAreaResources(areaId: Int, userId: Int): ServiceResponse<List<Resource>> {
        return ServiceResponse.Success(repository.getByArea(areaId, userId))
    }

    suspend fun updateResource(id: Int, resourceRequest: ResourceRequest): ServiceResponse<Resource> {
        if (resourceRequest.name.isEmpty()) {
            return ServiceResponse.Failed("Missing Fields", HttpStatusCode.BadRequest)
        }

        return repository.update(id, resourceRequest)?.let {
            ServiceResponse.Success(it)
        } ?: ServiceResponse.Failed("Resource was not found", HttpStatusCode.NotFound)
    }

    suspend fun deleteResource(id: Int): ServiceResponse<Boolean> {
        return if (repository.delete(id)) {
            ServiceResponse.Success(true)
        } else {
            ServiceResponse.Failed("Failed to delete resource", HttpStatusCode.NotFound)
        }
    }
}