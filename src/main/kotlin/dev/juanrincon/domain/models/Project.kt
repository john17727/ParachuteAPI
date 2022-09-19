package dev.juanrincon.domain.models

data class Project(
    val id: Int,
    val name: String,
    val status: Int,
    val areaId: Int?
)
