package dev.juanrincon.domain.interfaces.utilities

interface MutableRepository<M> {

    suspend fun delete(id: Int): Boolean

    suspend fun add(entry: M): M

    suspend fun  update(entry: M): M?
}