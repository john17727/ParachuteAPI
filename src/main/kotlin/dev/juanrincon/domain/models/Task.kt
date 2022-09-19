package dev.juanrincon.domain.models

data class Task(
    val id: Int,
    val title: String,
    val markdown: String,
    val html: String,
    val status: Int,
    val dueDate: String?,
    val priority: Int,
    val parentTask: Int?,
    val projectId: Int?
)
