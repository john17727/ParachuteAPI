package dev.juanrincon.domain.interfaces.utilities

interface MutableRepository<M, E> {

    suspend fun delete(id: Int): Boolean

    suspend fun add(entry: M): E

    suspend fun  update(id: Int, entry: M): E?
}