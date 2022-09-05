package dev.juanrincon.domain.interfaces

import dev.juanrincon.domain.interfaces.utilities.MutableRepository
import dev.juanrincon.domain.models.Area
import dev.juanrincon.domain.models.request.AreaRequest

interface AreaDatabase: MutableRepository<AreaRequest, Area> {
    suspend fun getByUser(userId: Int): List<Area>
}