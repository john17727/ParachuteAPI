package dev.juanrincon.controllers

import dev.juanrincon.data.services.AreaService
import dev.juanrincon.domain.models.request.AreaRequest
import dev.juanrincon.domain.models.routing.AreaRoute
import dev.juanrincon.domain.models.utilities.ApiResponse
import dev.juanrincon.domain.models.utilities.ServiceResponse.Failed
import dev.juanrincon.domain.models.utilities.ServiceResponse.Success
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.resources.delete

fun Route.areaController(service: AreaService) {
    post<AreaRoute> {
        val newAreaRequest = call.receive<AreaRequest>()
        when (val response = service.createArea(newAreaRequest)) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    put<AreaRoute.Id> { parameters ->
        val areaRequest = call.receive<AreaRequest>()
        when (val response = service.updateArea(parameters.id, areaRequest)) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    delete<AreaRoute.Id> { parameters ->
        when (val response = service.deleteArea(parameters.id)) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }
}