package dev.juanrincon.domain.interfaces

import dev.juanrincon.domain.interfaces.utilities.MutableRepository
import dev.juanrincon.domain.models.Task
import dev.juanrincon.domain.models.request.TaskRequest

interface TaskDatabase: MutableRepository<TaskRequest, Task> {

    suspend fun getByUser(userId: Int): List<Task>
}