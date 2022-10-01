package dev.juanrincon.domain.models.request

data class TaskRequest(
    val title: String,
    val markdown: String,
    val html: String,
    val status: Int,
    val dueDate: String?,
    val priority: Int,
    val parentTask: Int?,
    val project: Int?,
    var userId: Int
)
