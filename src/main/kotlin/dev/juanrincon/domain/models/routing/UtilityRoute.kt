package dev.juanrincon.domain.models.routing

import io.ktor.resources.*
import kotlinx.serialization.Serializable


@Serializable
@Resource("/utility")
class UtilityRoute {

    @Serializable
    @Resource("task_statuses")
    class TaskStatuses(val parent: UtilityRoute = UtilityRoute())

    @Serializable
    @Resource("project_statuses")
    class ProjectStatuses(val parent: UtilityRoute = UtilityRoute())

    @Serializable
    @Resource("note_types")
    class NoteTypes(val parent: UtilityRoute = UtilityRoute())

    @Serializable
    @Resource("priority_levels")
    class PriorityLevels(val parent: UtilityRoute = UtilityRoute())
}