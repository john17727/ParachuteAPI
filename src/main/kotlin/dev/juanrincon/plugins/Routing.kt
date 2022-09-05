package dev.juanrincon.plugins

import dev.juanrincon.controllers.areaController
import dev.juanrincon.controllers.userController
import dev.juanrincon.data.repositories.AreaRepository
import dev.juanrincon.data.repositories.UserRepository
import dev.juanrincon.data.services.AreaService
import dev.juanrincon.data.services.UserService
import dev.juanrincon.domain.models.JwtDetails
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.resources.*

fun Application.configureRouting() {
    install(Resources)
    routing {
        userController(
            UserService(UserRepository()),
            JwtDetails(
                this@configureRouting.environment.config.property("jwt.domain").getString(),
                this@configureRouting.environment.config.property("jwt.audience").getString(),
                this@configureRouting.environment.config.property("jwt.secret").getString()
            )
        )
        areaController(AreaService(AreaRepository()))
    }
}
