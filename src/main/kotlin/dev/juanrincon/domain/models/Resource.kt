package dev.juanrincon.domain.models

data class Resource(
    val id: Int,
    val name: String,
    var areaId: Int?
)
