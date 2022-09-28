package dev.juanrincon.domain.models

data class Note(
    val id: Int,
    val title: String,
    val markdown: String,
    val html: String,
    val type: Int,
    val favorite: Boolean,
    val fleeting: Boolean,
    val projectId: Int?,
    val resourceId: Int?,
    val areaId: Int?,
)
