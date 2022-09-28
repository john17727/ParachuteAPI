package dev.juanrincon.domain.models.request

data class ResourceRequest(
    val name: String,
    var areaId: Int?,
    var userId: Int
)