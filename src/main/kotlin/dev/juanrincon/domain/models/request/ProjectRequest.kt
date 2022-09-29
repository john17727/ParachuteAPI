package dev.juanrincon.domain.models.request

data class ProjectRequest(
    val name: String,
    val statusId: Int,
    var areaId: Int?,
    val userId: Int
)
