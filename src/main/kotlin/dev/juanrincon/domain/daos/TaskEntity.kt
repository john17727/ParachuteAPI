package dev.juanrincon.domain.daos

import dev.juanrincon.domain.interfaces.utilities.DomainMapper
import dev.juanrincon.domain.models.Task
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TaskEntity(id: EntityID<Int>): IntEntity(id), DomainMapper<Task> {
    companion object : IntEntityClass<TaskEntity>(TaskTable)
    var title by TaskTable.title
    var markdown by TaskTable.markdown
    var html by TaskTable.html
    var status by TaskStatusEntity referencedOn TaskTable.statusId
    var dueDate by TaskTable.dueDate
    var priority by PriorityLevelEntity referencedOn TaskTable.priorityId
    var parentTask by TaskEntity optionalReferencedOn TaskTable.parentTaskId
    var project by ProjectEntity optionalReferencedOn  TaskTable.projectId
    var user by UserEntity referencedOn TaskTable.userId

    override fun toModel() = Task(
        id.value,
        title,
        markdown,
        html,
        status.id.value,
        dueDate.toString(),
        priority.id.value,
        parentTask?.id?.value,
        project?.id?.value
    )
}