package dev.juanrincon.controllers

import dev.juanrincon.data.services.UserService
import dev.juanrincon.domain.generateToken
import dev.juanrincon.domain.models.JwtDetails
import dev.juanrincon.domain.models.request.LoginRequest
import dev.juanrincon.domain.models.request.RegisterRequest
import dev.juanrincon.domain.models.response.TokenResponse
import dev.juanrincon.domain.models.response.UserPartialResponse
import dev.juanrincon.domain.models.routing.UserRoute
import dev.juanrincon.domain.models.utilities.ApiResponse
import dev.juanrincon.domain.models.utilities.ServiceResponse.Failed
import dev.juanrincon.domain.models.utilities.ServiceResponse.Success
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userController(service: UserService, jwtDetails: JwtDetails) {
    post<UserRoute.Register> {
        val user = call.receive<RegisterRequest>()
        when (val response = service.registerUser(user)) {
            is Success -> {
                val tokenResponse = response.data.let {
                    TokenResponse(
                        generateToken(it.id, jwtDetails),
                        UserPartialResponse(it.email, it.firstName, it.lastName)
                    )
                }
                call.respond(response.status, ApiResponse.success(tokenResponse))
            }
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    post<UserRoute.Login> {
        val user = call.receive<LoginRequest>()
        when (val response = service.loginUser(user.email, user.password)) {
            is Success -> {
                val tokenResponse = response.data.let {
                    TokenResponse(
                        generateToken(it.id, jwtDetails),
                        UserPartialResponse(it.email, it.firstName, it.lastName)
                    )
                }
                call.respond(response.status, ApiResponse.success(tokenResponse))
            }
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }
}