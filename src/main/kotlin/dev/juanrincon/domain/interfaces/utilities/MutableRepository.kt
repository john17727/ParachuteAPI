package dev.juanrincon.domain.interfaces.utilities

import dev.juanrincon.domain.models.Task

interface MutableRepository<I, O> {

    suspend fun getByUser(userId: Int): List<O>

    suspend fun delete(id: Int): Boolean

    suspend fun add(entry: I): O

    suspend fun  update(id: Int, entry: I): O?
}