package dev.juanrincon.domain.models.request

data class ProjectRequest(
    val name: String,
    val statusId: Int,
    val areaId: Int?,
    var userId: Int
)
