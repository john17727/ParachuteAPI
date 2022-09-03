package dev.juanrincon.data.services

import dev.juanrincon.domain.interfaces.UserDatabase
import dev.juanrincon.domain.models.User
import dev.juanrincon.domain.models.request.RegisterRequest
import dev.juanrincon.domain.models.utilities.ServiceResponse
import io.ktor.http.*

class UserService(val repository: UserDatabase) {

    suspend fun registerUser(user: RegisterRequest) : ServiceResponse<User> {
        if (user.email.isEmpty() || user.password.isEmpty()) return ServiceResponse.Failed(
            HttpStatusCode.BadRequest, "Missing Fields"
        )

        val userExists = repository.checkIfUserExists(user.email)
        return if (userExists) {
            ServiceResponse.Failed(HttpStatusCode.Conflict, "User with this email already exists")
        } else {

            ServiceResponse.Success(repository.add(user.toModel()), HttpStatusCode.Created)
        }
    }
}