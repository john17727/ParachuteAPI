package dev.juanrincon.domain.interfaces

import dev.juanrincon.domain.interfaces.utilities.ByUserRepository
import dev.juanrincon.domain.interfaces.utilities.MutableRepository
import dev.juanrincon.domain.models.Task
import dev.juanrincon.domain.models.request.TaskRequest

interface TaskDatabase: MutableRepository<TaskRequest, Task>, ByUserRepository<Task> {

    suspend fun getByProject(projectId: Int, userId: Int): List<Task>
}