package dev.juanrincon.domain.models.utilities

import io.ktor.http.*

sealed class ServiceResponse<out T : Any> {
    data class Success<out T : Any>(val data: T, val status: HttpStatusCode = HttpStatusCode.OK) : ServiceResponse<T>()
    data class Failed(val message: String, val status: HttpStatusCode) : ServiceResponse<Nothing>()
}
