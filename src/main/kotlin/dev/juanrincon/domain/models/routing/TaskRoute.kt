package dev.juanrincon.domain.models.routing

import io.ktor.resources.*
import kotlinx.serialization.Serializable


@Serializable
@Resource("/tasks")
class TaskRoute {

    @Serializable
    @Resource("{id}")
    class Id(val parent: TaskRoute = TaskRoute(), val id: Int)
}