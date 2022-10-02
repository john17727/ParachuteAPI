package dev.juanrincon.controllers

import dev.juanrincon.data.services.ResourceService
import dev.juanrincon.domain.USER_ID
import dev.juanrincon.domain.models.request.ResourceRequest
import dev.juanrincon.domain.models.routing.ResourceRoute
import dev.juanrincon.domain.models.utilities.ApiResponse
import dev.juanrincon.domain.models.utilities.ServiceResponse.Failed
import dev.juanrincon.domain.models.utilities.ServiceResponse.Success
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.resources.delete
import io.ktor.server.resources.get

fun Route.resourceController(service: ResourceService) {
    authenticate {
        post<ResourceRoute> {
            val newResource = call.receive<ResourceRequest>()
            newResource.userId = call.principal<JWTPrincipal>()!!.payload.getClaim(USER_ID).asInt()

            when (val response = service.createResource(newResource)) {
                is Success -> call.respond(response.status, ApiResponse.success(response.data))
                is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
            }
        }

        get<ResourceRoute> {
            val userId = call.principal<JWTPrincipal>()!!.payload.getClaim(USER_ID).asInt()
            val area = call.request.queryParameters["area"]

            if (area.isNullOrEmpty()) {
                when (val response = service.getUserResources(userId)) {
                    is Success -> call.respond(response.status, ApiResponse.success(response.data))
                    is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
                }
            } else {
                try {
                    val areaId = area.toInt()
                    when (val response = service.getAreaResources(areaId, userId)) {
                        is Success -> call.respond(response.status, ApiResponse.success(response.data))
                        is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
                    }
                } catch (e: NumberFormatException) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.fail("Area parameter must be a number"))
                }
            }
        }

        put<ResourceRoute.Id> { parameters ->
            val resourceRequest = call.receive<ResourceRequest>()
            resourceRequest.userId = call.principal<JWTPrincipal>()!!.payload.getClaim(USER_ID).asInt()
            when (val response = service.updateResource(parameters.id, resourceRequest)) {
                is Success -> call.respond(response.status, ApiResponse.success(response.data))
                is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
            }
        }

        delete<ResourceRoute.Id> { parameters ->
            when (val response = service.deleteResource(parameters.id)) {
                is Success -> call.respond(response.status, ApiResponse.success(response.data))
                is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
            }
        }
    }
}