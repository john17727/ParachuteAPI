package dev.juanrincon.domain.models.request

data class NoteRequest(
    val title: String,
    val markdown: String,
    val html: String,
    val type: Int,
    val favorite: Boolean,
    val fleeting: Boolean,
    val projectId: Int?,
    val resourceId: Int?,
    val areaId: Int?,
    val userId: Int
)
