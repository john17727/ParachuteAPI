package dev.juanrincon.domain.interfaces

import dev.juanrincon.domain.interfaces.utilities.Repository
import dev.juanrincon.domain.models.Area

interface AreaDatabase: Repository<Area> {

    suspend fun getByUser(userId: Int): List<Area>
}