package dev.juanrincon.controllers

import dev.juanrincon.data.services.UtilityService
import dev.juanrincon.domain.models.routing.UtilityRoute
import dev.juanrincon.domain.models.utilities.ApiResponse
import dev.juanrincon.domain.models.utilities.ServiceResponse
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.resources.get
import io.ktor.server.response.*

fun Route.utilityController(service: UtilityService) {
    get<UtilityRoute.TaskStatuses> {
        when (val response = service.getTaskStatuses()) {
            is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
            is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    get<UtilityRoute.ProjectStatuses> {
        when (val response = service.getProjectStatuses()) {
            is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
            is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    get<UtilityRoute.NoteTypes> {
        when (val response = service.getNoteTypes()) {
            is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
            is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    get<UtilityRoute.PriorityLevels> {
        when (val response = service.getPriorityLevels()) {
            is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
            is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }
}