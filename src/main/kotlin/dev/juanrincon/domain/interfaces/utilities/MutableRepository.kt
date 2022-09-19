package dev.juanrincon.domain.interfaces.utilities

interface MutableRepository<I, O> {

    suspend fun delete(id: Int): Boolean

    suspend fun add(entry: I): O

    suspend fun  update(id: Int, entry: I): O?
}