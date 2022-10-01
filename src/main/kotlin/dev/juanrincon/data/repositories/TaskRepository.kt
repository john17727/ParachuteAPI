package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.*
import dev.juanrincon.domain.interfaces.TaskDatabase
import dev.juanrincon.domain.models.request.TaskRequest
import dev.juanrincon.plugins.dbQuery
import java.time.LocalDateTime

class TaskRepository: TaskDatabase {
    override suspend fun getByProject(projectId: Int, userId: Int) = dbQuery {
        if (projectId < 1) {
            TaskEntity.find { TaskTable.userId eq userId }.filter { it.project == null }.map { it.toModel() }
        } else {
            TaskEntity.find { TaskTable.projectId eq projectId }.map { it.toModel() }
        }
    }

    override suspend fun delete(id: Int) = dbQuery {
        TaskEntity.findById(id)?.let {
            it.delete()
            true
        } ?: false
    }

    override suspend fun add(entry: TaskRequest) = dbQuery {
        TaskEntity.new {
            title = entry.title
            markdown = entry.markdown
            html = entry.html
            status = getStatus(entry.status)
            dueDate = LocalDateTime.parse(entry.dueDate)
            priority = getPriority(entry.priority)
            parentTask = getParentTask(entry.parentTask)
            project = getProject(entry.project)
            user = getUser(entry.userId)
        }.toModel()
    }

    override suspend fun update(id: Int, entry: TaskRequest) = dbQuery {
        TaskEntity.findById(id)?.let {
            it.title = entry.title
            it.markdown = entry.markdown
            it.html = entry.html
            it.status = getStatus(entry.status)
            it.dueDate = LocalDateTime.parse(entry.dueDate)
            it.priority = getPriority(entry.priority)
            it.parentTask = getParentTask(entry.parentTask)
            it.project = getProject(entry.project)
            it.toModel()
        }
    }

    override suspend fun getByUser(userId: Int) = dbQuery {
        TaskEntity.find { TaskTable.userId eq userId }.map { it.toModel() }
    }

    private fun getStatus(id: Int) = TaskStatusEntity.findById(id)!!

    private fun getPriority(id: Int) = PriorityLevelEntity.findById(id)!!

    private fun getParentTask(id: Int?) = id?.let {
        TaskEntity.findById(it)
    }

    private fun getProject(id: Int?) = id?.let {
        ProjectEntity.findById(it)
    }

    private fun getUser(id: Int) = UserEntity.findById(id)!!
}