package dev.juanrincon.domain.interfaces.utilities

interface ByUserRepository<O> {

    suspend fun getByUser(userId: Int): List<O>
}