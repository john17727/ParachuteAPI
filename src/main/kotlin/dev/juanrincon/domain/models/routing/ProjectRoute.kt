package dev.juanrincon.domain.models.routing

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/projects")
class ProjectRoute {

    @Serializable
    @Resource("{id}")
    class Id(val parent: ProjectRoute = ProjectRoute(), val id: Int)
}