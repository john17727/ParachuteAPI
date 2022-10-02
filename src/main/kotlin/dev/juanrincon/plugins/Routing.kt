package dev.juanrincon.plugins

import dev.juanrincon.controllers.*
import dev.juanrincon.data.repositories.*
import dev.juanrincon.data.services.*
import dev.juanrincon.domain.models.JwtDetails
import dev.juanrincon.security.hashing.SHA256HashingService
import dev.juanrincon.security.token.JwtTokenService
import dev.juanrincon.security.token.TokenConfig
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.resources.*

fun Application.configureRouting() {
    install(Resources)
    routing {
        userController(
            UserService(UserRepository(), SHA256HashingService()),
            JwtTokenService(),
            TokenConfig(
                this@configureRouting.environment.config.property("jwt.domain").getString(),
                this@configureRouting.environment.config.property("jwt.audience").getString(),
                60000L * 60L * 24L * 7L,
                this@configureRouting.environment.config.property("jwt.secret").getString()
            )
        )
        areaController(AreaService(AreaRepository()))
        resourceController(ResourceService(ResourceRepository()))
        taskController(TaskService(TaskRepository()))
        noteController(NoteService(NoteRepository()))
        utilityController(UtilityService(UtilityRepository()))
    }
}
