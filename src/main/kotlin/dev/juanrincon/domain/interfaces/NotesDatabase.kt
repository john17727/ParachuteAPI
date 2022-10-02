package dev.juanrincon.domain.interfaces

import dev.juanrincon.domain.interfaces.utilities.ByUserRepository
import dev.juanrincon.domain.interfaces.utilities.MutableRepository
import dev.juanrincon.domain.models.Note
import dev.juanrincon.domain.models.request.NoteRequest

interface NotesDatabase: MutableRepository<NoteRequest, Note>, ByUserRepository<Note> {

    suspend fun getByArea(areaId: Int, userId: Int): List<Note>

    suspend fun getByProject(projectId: Int, userId: Int): List<Note>

    suspend fun getByResource(resourceId: Int, userId: Int): List<Note>

    suspend fun archive(id: Int): Boolean
}