package dev.juanrincon.domain.interfaces.utilities

interface MutableRepository<T> {

    suspend fun delete(id: Int): Boolean

    suspend fun add(entry: T): T

    suspend fun update(entry: T): T?
}