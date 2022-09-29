package dev.juanrincon.data.services

import dev.juanrincon.domain.interfaces.ProjectDatabase
import dev.juanrincon.domain.models.Project
import dev.juanrincon.domain.models.request.ProjectRequest
import dev.juanrincon.domain.models.utilities.ServiceResponse
import io.ktor.http.*

class ProjectService(private val repository: ProjectDatabase) {

    suspend fun createProject(projectRequest: ProjectRequest): ServiceResponse<Project> {
        if (projectRequest.name.isEmpty()) {
            return ServiceResponse.Failed("Missing Fields", HttpStatusCode.BadRequest)
        }

        return ServiceResponse.Success(repository.add(projectRequest), HttpStatusCode.Created)
    }

    suspend fun getUserProjects(userId: Int): ServiceResponse<List<Project>> {
        return ServiceResponse.Success(repository.getByUser(userId))
    }

    suspend fun getAreaProjects(areaId: Int, userId: Int): ServiceResponse<List<Project>> {
        val projects = repository.getByArea(areaId, userId)
        if (projects.isEmpty()) {
            return ServiceResponse.Failed("Area has no projects", HttpStatusCode.NotFound)
        }
        return ServiceResponse.Success(projects)
    }

    suspend fun updateProject(id: Int, projectRequest: ProjectRequest): ServiceResponse<Project> {
        if (projectRequest.name.isEmpty()) {
            return ServiceResponse.Failed("Missing Fields", HttpStatusCode.BadRequest)
        }

        return repository.update(id, projectRequest)?.let {
            ServiceResponse.Success(it)
        } ?: ServiceResponse.Failed("Project was not found", HttpStatusCode.NotFound)
    }

    suspend fun deleteProject(id: Int): ServiceResponse<Boolean> {
        return if (repository.delete(id)) {
            ServiceResponse.Success(true)
        } else {
            ServiceResponse.Failed("Failed to delete project", HttpStatusCode.NotFound)
        }
    }
}