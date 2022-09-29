package dev.juanrincon.controllers

import dev.juanrincon.data.services.ProjectService
import dev.juanrincon.domain.USER_ID
import dev.juanrincon.domain.models.request.ProjectRequest
import dev.juanrincon.domain.models.routing.ProjectRoute
import dev.juanrincon.domain.models.utilities.ApiResponse
import dev.juanrincon.domain.models.utilities.ServiceResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.resources.delete
import io.ktor.server.resources.get
import io.ktor.server.response.*

fun Route.projectController(service: ProjectService) {
    authenticate {
        post<ProjectRoute> {
            val newProject = call.receive<ProjectRequest>()
            newProject.userId = call.principal<JWTPrincipal>()!!.payload.getClaim(USER_ID).asInt()

            when (val response = service.createProject(newProject)) {
                is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
                is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
            }
        }

        get<ProjectRoute> {
            val userId = call.principal<JWTPrincipal>()!!.payload.getClaim(USER_ID).asInt()
            val area = call.request.queryParameters["area"]

            if (area.isNullOrEmpty()) {
                when (val response = service.getUserProjects(userId)) {
                    is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
                    is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
                }
            } else {
                try {
                    val areaId = area.toInt()
                    when (val response = service.getAreaProjects(areaId, userId)) {
                        is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
                        is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
                    }
                } catch (e: NumberFormatException) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.fail("Area parameter must be a number"))
                }
            }
        }

        put<ProjectRoute.Id> { parameters ->
            val projectRequest = call.receive<ProjectRequest>()
            projectRequest.userId = call.principal<JWTPrincipal>()!!.payload.getClaim(USER_ID).asInt()
            when (val response = service.updateProject(parameters.id, projectRequest)) {
                is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
                is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
            }
        }

        delete<ProjectRoute.Id> { parameters ->
            when (val response = service.deleteProject(parameters.id)) {
                is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
                is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
            }
        }
    }
}