package dev.juanrincon.domain.interfaces.utilities

interface ImmutableRepository<E> {
    suspend fun get(id: Int): E?
}