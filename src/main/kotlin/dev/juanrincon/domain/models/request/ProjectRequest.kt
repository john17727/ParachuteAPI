package dev.juanrincon.domain.models.request

data class ProjectRequest(
    val name: String,
    val status: Int,
    var areaId: Int?
)
