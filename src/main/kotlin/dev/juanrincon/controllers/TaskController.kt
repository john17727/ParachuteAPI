package dev.juanrincon.controllers

import dev.juanrincon.data.services.TaskService
import dev.juanrincon.domain.USER_ID
import dev.juanrincon.domain.models.request.TaskRequest
import dev.juanrincon.domain.models.routing.TaskRoute
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

fun Route.taskController(service: TaskService) {
    authenticate {
        post<TaskRoute> {
            val newTask = call.receive<TaskRequest>()
            newTask.userId = call.principal<JWTPrincipal>()!!.payload.getClaim(USER_ID).asInt()

            when (val response = service.createTask(newTask)) {
                is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
                is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
            }
        }

        get<TaskRoute> {
            val userId = call.principal<JWTPrincipal>()!!.payload.getClaim(USER_ID).asInt()
            val project = call.request.queryParameters["project"]

            if (project.isNullOrEmpty()) {
                when (val response = service.getUserTasks(userId)) {
                    is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
                    is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
                }
            } else {
                try {
                    val projectId = project.toInt()
                    when (val response = service.getProjectTasks(projectId, userId)) {
                        is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
                        is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
                    }
                } catch (e: NumberFormatException) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.fail("Area parameter must be a number"))
                }
            }
        }

        put<TaskRoute.Id> { parameters ->
            val taskRequest = call.receive<TaskRequest>()
            taskRequest.userId = call.principal<JWTPrincipal>()!!.payload.getClaim(USER_ID).asInt()
            when (val response = service.updateTask(parameters.id, taskRequest)) {
                is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
                is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
            }
        }

        delete<TaskRoute.Id> { parameters ->
            when (val response = service.deleteTask(parameters.id)) {
                is ServiceResponse.Success -> call.respond(response.status, ApiResponse.success(response.data))
                is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
            }
        }
    }
}