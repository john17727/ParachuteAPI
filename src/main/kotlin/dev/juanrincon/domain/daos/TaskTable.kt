package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object TaskTable: IntIdTable("tasks") {
    val title = varchar("title", 256)
    val markdown = text("markdown")
    val html = text("html")
    val statusId = reference("status_id", TaskStatusTable)
    val dueDate = datetime("due_date").nullable()
    val priorityId = reference("priority_id", PriorityLevelTable)
    val parentTaskId = reference("parent_task_id", TaskTable)
    val projectId = reference("projectId", ProjectTable).nullable()
    val userId = reference("user_id", UserTable)
}