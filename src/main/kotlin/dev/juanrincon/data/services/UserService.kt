package dev.juanrincon.data.services

import dev.juanrincon.domain.interfaces.UserDatabase
import dev.juanrincon.domain.models.User
import dev.juanrincon.domain.models.request.UserRequest
import dev.juanrincon.domain.models.utilities.ServiceResponse
import io.ktor.http.*

class UserService(private val repository: UserDatabase) {

    suspend fun registerUser(request: UserRequest) : ServiceResponse<User> {
        if (request.email.isEmpty() || request.password.isEmpty()) {
            return ServiceResponse.Failed(HttpStatusCode.BadRequest, "Missing Fields")
        }

        val userExists = repository.checkUserExists(request.email)
        return if (userExists) {
            ServiceResponse.Failed(HttpStatusCode.Conflict, "User with this email already exists")
        } else {
            ServiceResponse.Success(repository.add(request), HttpStatusCode.Created)
        }
    }

    suspend fun loginUser(email: String, password: String) : ServiceResponse<User> {
        if (email.isEmpty() || password.isEmpty()) {
            return ServiceResponse.Failed(HttpStatusCode.BadRequest, "Missing Fields")
        }

        val passwordsMatch = repository.checkPasswordMatches(email, password)
        return if (passwordsMatch) {
            ServiceResponse.Success(repository.get(email))
        } else {
            ServiceResponse.Failed(
                HttpStatusCode.BadRequest,
                "Your login credentials don't match an account in our system"
            )
        }
    }
}