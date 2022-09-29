package dev.juanrincon.data.services

import dev.juanrincon.domain.interfaces.UserDatabase
import dev.juanrincon.domain.models.User
import dev.juanrincon.domain.models.request.HashedUser
import dev.juanrincon.domain.models.request.RegisterRequest
import dev.juanrincon.domain.models.utilities.ServiceResponse
import dev.juanrincon.security.hashing.HashingService
import io.ktor.http.*

class UserService(
    private val repository: UserDatabase,
    private val hashingService: HashingService
) {

    suspend fun registerUser(request: RegisterRequest) : ServiceResponse<User> {
        if (request.email.isEmpty() || request.password.isEmpty()) {
            return ServiceResponse.Failed("Missing Fields", HttpStatusCode.BadRequest)
        }

        val userExists = repository.checkUserExists(request.email)
        return if (userExists) {
            ServiceResponse.Failed("User with this email already exists", HttpStatusCode.Conflict)
        } else {
            val saltedHash = hashingService.generateSaltedHash(request.password)
            val hashedUser = HashedUser(
                request.firstName,
                request.lastName,
                request.imageUrl,
                request.email,
                saltedHash.hash,
                saltedHash.salt
            )
            ServiceResponse.Success(repository.add(hashedUser), HttpStatusCode.Created)
        }
    }

    suspend fun loginUser(email: String, password: String) : ServiceResponse<User> {
        if (email.isEmpty() || password.isEmpty()) {
            return ServiceResponse.Failed("Missing Fields", HttpStatusCode.BadRequest)
        }

        return repository.getSaltedHash(email)?.let { saltedHash ->
            if (hashingService.verify(password, saltedHash)) {
                ServiceResponse.Success(repository.get(email))
            } else {
                ServiceResponse.Failed(
                    "Your login credentials don't match an account in our system",
                    HttpStatusCode.BadRequest
                )
            }
        } ?: ServiceResponse.Failed(
            "Your login credentials don't match an account in our system",
            HttpStatusCode.BadRequest
        )
    }
}