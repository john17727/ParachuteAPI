package dev.juanrincon.data.services

import dev.juanrincon.domain.interfaces.TaskDatabase
import dev.juanrincon.domain.models.Task
import dev.juanrincon.domain.models.request.TaskRequest
import dev.juanrincon.domain.models.utilities.ServiceResponse
import io.ktor.http.*

class TaskService(private val repository: TaskDatabase) {

    suspend fun createTask(taskRequest: TaskRequest): ServiceResponse<Task> {
        if (taskRequest.title.isEmpty()) {
            return ServiceResponse.Failed("Missing Fields", HttpStatusCode.BadRequest)
        }

        return ServiceResponse.Success(repository.add(taskRequest), HttpStatusCode.Created)
    }

    suspend fun getUserTasks(userId: Int): ServiceResponse<List<Task>> {
        return ServiceResponse.Success(repository.getByUser(userId))
    }

    suspend fun getProjectTasks(projectId: Int, userId: Int): ServiceResponse<List<Task>> {
        val tasks = repository.getByProject(projectId, userId)
        if (tasks.isEmpty()) {
            return ServiceResponse.Failed("Area has no resources", HttpStatusCode.NotFound)
        }
        return ServiceResponse.Success(tasks)
    }

    suspend fun updateTask(id: Int, taskRequest: TaskRequest): ServiceResponse<Task> {
        if (taskRequest.title.isEmpty()) {
            return ServiceResponse.Failed("Missing Fields", HttpStatusCode.BadRequest)
        }

        return repository.update(id, taskRequest)?.let {
            ServiceResponse.Success(it)
        } ?: ServiceResponse.Failed("Resource was not found", HttpStatusCode.NotFound)
    }

    suspend fun deleteTask(id: Int): ServiceResponse<Boolean> {
        return if (repository.delete(id)) {
            ServiceResponse.Success(true)
        } else {
            ServiceResponse.Failed("Failed to delete resource", HttpStatusCode.NotFound)
        }
    }
}