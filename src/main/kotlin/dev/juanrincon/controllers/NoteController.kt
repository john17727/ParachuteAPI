package dev.juanrincon.controllers

import dev.juanrincon.data.services.NoteService
import dev.juanrincon.domain.USER_ID
import dev.juanrincon.domain.models.request.NoteRequest
import dev.juanrincon.domain.models.routing.NoteRoute
import dev.juanrincon.domain.models.utilities.ApiResponse
import dev.juanrincon.domain.models.utilities.ServiceResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Route.noteController(service: NoteService) {
    authenticate {
        post<NoteRoute> {
            val newNote = call.receive<NoteRequest>()
            newNote.userId = call.principal<JWTPrincipal>()!!.payload.getClaim(USER_ID).asInt()

            when (val response = service.createNote(newNote)) {
                is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
                is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
            }
        }
    }

    get<NoteRoute> {
        val userId = call.principal<JWTPrincipal>()!!.payload.getClaim(USER_ID).asInt()
        val area = call.request.queryParameters["area"]

        if (area.isNullOrEmpty()) {
            when (val response = service.getUserNotes(userId)) {
                is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
                is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
            }
        } else {
            try {
                val areaId = area.toInt()
                when (val response = service.getAreaNotes(areaId, userId)) {
                    is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
                    is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
                }
            } catch (e: NumberFormatException) {
                call.respond(HttpStatusCode.BadRequest, ApiResponse.fail("Area parameter must be a number"))
            }
        }
    }

    put<NoteRoute.Id> { parameters ->
        val noteRequest = call.receive<NoteRequest>()
        noteRequest.userId = call.principal<JWTPrincipal>()!!.payload.getClaim(USER_ID).asInt()
        when (val response = service.updateNote(parameters.id, noteRequest)) {
            is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
            is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    delete<NoteRoute.Id> { parameters ->
        when (val response = service.deleteNote(parameters.id)) {
            is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
            is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    post<NoteRoute.Id.Archive> { parameters ->
        when (val response = service.archiveNote(parameters.parent.id)) {
            is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
            is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }
}