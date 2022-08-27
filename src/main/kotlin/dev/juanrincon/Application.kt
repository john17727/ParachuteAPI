package dev.juanrincon

import io.ktor.server.application.*
import dev.juanrincon.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureExposed()
    configureSecurity()
    configureSerialization()
    configureRouting()
}
