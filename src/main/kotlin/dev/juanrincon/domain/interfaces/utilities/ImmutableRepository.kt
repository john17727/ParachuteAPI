package dev.juanrincon.domain.interfaces.utilities

interface ImmutableRepository<T> {
    suspend fun get(id: Int): T?
}