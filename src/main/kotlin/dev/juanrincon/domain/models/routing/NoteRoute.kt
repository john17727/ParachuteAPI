package dev.juanrincon.domain.models.routing

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/notes")
class NoteRoute {

    @Serializable
    @Resource("{id}")
    class Id(val parent: NoteRoute = NoteRoute(), val id: Int) {

        @Serializable
        @Resource("archive")
        class Archive(val parent: Id)
    }
}