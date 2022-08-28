package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object TaskTable: IntIdTable("tasks") {
    val title = varchar("title", 256)
    val markdown = varchar("markdown", Int.MAX_VALUE)
    val html = varchar("html", Int.MAX_VALUE)
    val statusId = reference("status_id", TaskStatusTable)
    val dueDate = datetime("due_date")
    val priorityId = reference("priority_id", PriorityLevelTable)
    val parentTaskId = reference("parent_task_id", TaskTable)
    val projectId = reference("projectId", ProjectTable)
    val userId = reference("user_id", UserTable)
}