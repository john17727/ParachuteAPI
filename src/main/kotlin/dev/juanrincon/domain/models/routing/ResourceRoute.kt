package dev.juanrincon.domain.models.routing

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/resource")
class ResourceRoute {

    @Serializable
    @Resource("{id}")
    class Id(val parent: ResourceRoute = ResourceRoute(), val id: Int)
}