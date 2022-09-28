package dev.juanrincon.domain.interfaces

import dev.juanrincon.domain.interfaces.utilities.MutableRepository
import dev.juanrincon.domain.models.Resource
import dev.juanrincon.domain.models.request.ResourceRequest

interface ResourceDatabase: MutableRepository<ResourceRequest, Resource> {

    suspend fun getByArea(areaId: Int): List<Resource>
}