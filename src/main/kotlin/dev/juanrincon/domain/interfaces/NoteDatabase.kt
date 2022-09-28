package dev.juanrincon.domain.interfaces

import dev.juanrincon.domain.interfaces.utilities.MutableRepository
import dev.juanrincon.domain.models.Note
import dev.juanrincon.domain.models.request.NoteRequest

interface NoteDatabase: MutableRepository<NoteRequest, Note> {

    suspend fun archiveNote(id: Int): Int?

    suspend fun archiveNotes(ids: List<Int>): List<Int>
}