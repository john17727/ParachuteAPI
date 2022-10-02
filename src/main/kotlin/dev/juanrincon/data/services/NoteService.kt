package dev.juanrincon.data.services

import dev.juanrincon.domain.interfaces.NotesDatabase
import dev.juanrincon.domain.models.Note
import dev.juanrincon.domain.models.request.NoteRequest
import dev.juanrincon.domain.models.utilities.ServiceResponse
import io.ktor.http.*

class NoteService(private val repository: NotesDatabase) {

    suspend fun createNote(noteRequest: NoteRequest): ServiceResponse<Note> {
        if (noteRequest.title.isEmpty()) {
            return ServiceResponse.Failed("Missing Fields", HttpStatusCode.BadRequest)
        }

        return ServiceResponse.Success(repository.add(noteRequest), HttpStatusCode.Created)
    }

    suspend fun getUserNotes(userId: Int): ServiceResponse<List<Note>> {
        return ServiceResponse.Success(repository.getByUser(userId))
    }

    suspend fun getAreaNotes(areaId: Int, userId: Int): ServiceResponse<List<Note>> {
        return ServiceResponse.Success(repository.getByArea(areaId, userId))
    }

    suspend fun getProjectNotes(projectId: Int, userId: Int): ServiceResponse<List<Note>> {
        return ServiceResponse.Success(repository.getByProject(projectId, userId))
    }

    suspend fun getResourceNotes(resourceId: Int, userId: Int): ServiceResponse<List<Note>> {
        return ServiceResponse.Success(repository.getByResource(resourceId, userId))
    }

    suspend fun updateNote(id: Int, noteRequest: NoteRequest): ServiceResponse<Note> {
        if (noteRequest.title.isEmpty()) {
            return ServiceResponse.Failed("Missing Fields", HttpStatusCode.BadRequest)
        }
        
        return repository.update(id, noteRequest)?.let {
            ServiceResponse.Success(it)
        } ?: ServiceResponse.Failed("Note was not found", HttpStatusCode.NotFound)
    }

    suspend fun deleteNote(id: Int): ServiceResponse<Boolean> {
        return if (repository.delete(id)) {
            ServiceResponse.Success(true)
        } else {
            ServiceResponse.Failed("Failed to delete note", HttpStatusCode.NotFound)
        }
    }
}